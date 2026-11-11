package com.example.reproductordaniel;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;
    private SeekBar barraDuracion;
    private TextView minutoActual;
    private TextView duracionTotal;
    private boolean arrastrando = false;
    private SoundPool soundPool;



    //creamos el handler para que la barra avance sola
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable actualizar = new Runnable() {
        @Override
        public void run() {
            if (!arrastrando) {
                long posicion = player.getCurrentPosition();
                barraDuracion.setProgress((int) posicion);
                minutoActual.setText(formatear(posicion));
            }
            handler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //creamos el ExoPlayer
        player = new ExoPlayer.Builder(this).build();

        //agregamos la cancion con una URI a res/raw
        Uri uri = Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.cancion);
        player.setMediaItem(MediaItem.fromUri(uri));
        player.prepare();

        //creamos el objeto Button y lo linkamos/asignamos
        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPausa = findViewById(R.id.btnPause);

        //linkamos/asignamos las variables de textview y seekbar
        barraDuracion = findViewById(R.id.duracionCancion);
        minutoActual = findViewById(R.id.tiempoActual);
        duracionTotal = findViewById(R.id.duracionTotal);

        //creamos/asignamos los estados del reproductor
        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int estado) {
                if (estado == Player.STATE_READY) {
                    long duracion = player.getDuration();
                    barraDuracion.setMax((int) duracion);
                    duracionTotal.setText(formatear(duracion));
                } else if (estado == Player.STATE_ENDED) {
                    //mueve la barra al principio
                    player.seekTo(0);
                    //pause para que no se reinicie reproduciendo
                    player.pause();
                }
            }
        });

        //damos las funciones a los botones
        btnPlay.setOnClickListener(v -> player.play());
        btnPausa.setOnClickListener(v -> player.pause());

        //creamos la funcion para que el usuario mueva la barra
        barraDuracion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar barra) {
                arrastrando = true;
            }

            @Override
            public void onProgressChanged(SeekBar barra, int progreso, boolean delUsuario) {
                if (delUsuario) {
                    minutoActual.setText(formatear(progreso));
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar barra) {
                player.seekTo(barra.getProgress());
                arrastrando = false;
            }
        });

        //arrancamos el handler
        //handler.post(actualizar);

        AudioAttributes atributos = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

        soundPool = new SoundPool.Builder().setMaxStreams(4).setAudioAttributes(atributos).build();

        int idLaser = soundPool.load(this, R.raw.laser, 1);
        int idPitido = soundPool.load(this, R.raw.pitido, 1);
        int idAplausos = soundPool.load(this, R.raw.aplausos, 1);
        int idRedoble = soundPool.load(this, R.raw.drumroll, 1);

        //linkamos/asignamos las variables de los botones de sonido
        Button btnLaser = findViewById(R.id.btnLaser);
        Button btnPitido = findViewById(R.id.btnPitido);
        Button btnAplausos = findViewById(R.id.btnAplausos);
        Button btnRedoble = findViewById(R.id.btnDrum);

        //creamos el listener para los botones
        soundPool.setOnLoadCompleteListener((pool, idSonido, estado) -> {
            if (estado == 0) {
                if (idSonido == idLaser) {
                    btnLaser.setEnabled(true);
                } else if (idSonido == idPitido) {
                    btnPitido.setEnabled(true);
                } else if (idSonido == idAplausos) {
                    btnAplausos.setEnabled(true);
                } else if (idSonido == idRedoble) {
                    btnRedoble.setEnabled(true);
                }
            }
        });

        //asignamos el sonido a cada boton con play()
        btnLaser.setOnClickListener(v -> soundPool.play(idLaser, 1f, 1f, 1, 0, 1f));
        btnPitido.setOnClickListener(v -> soundPool.play(idPitido, 1f, 1f, 1, 0, 1f));
        btnAplausos.setOnClickListener(v -> soundPool.play(idAplausos, 1f, 1f, 1, 0, 1f));
        btnRedoble.setOnClickListener(v -> soundPool.play(idRedoble, 1f, 1f, 1, 0, 1f));
    }

    //en onStop, la música se pausa y la barra deja de actualizarse
    @Override
    protected void onStop() {
        super.onStop();
        player.pause();
        handler.removeCallbacks(actualizar);
    }

    //en onStart, la barra vuelve a actualizarse
    @Override
    protected void onStart() {
        super.onStart();
        handler.post(actualizar);
    }

    //en onDestroy, el reproductor se libera con release()
    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
        soundPool.release();
    }

    //creamos el metodo formatear
    private String formatear(long ms) {
        long segundos = ms / 1000;
        return String.format(Locale.getDefault(), "%02d:%02d", segundos / 60, segundos % 60);
    }
}