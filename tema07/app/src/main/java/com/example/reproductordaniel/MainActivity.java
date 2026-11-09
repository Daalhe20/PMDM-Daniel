package com.example.reproductordaniel;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer player;

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

        //damos las funciones a los botones
        btnPlay.setOnClickListener(v -> player.play());
        btnPausa.setOnClickListener(v -> player.pause());

        //funcion para reiniciar cancion cuando finaliza
        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int estado) {
                if (estado == Player.STATE_ENDED) {
                    player.seekTo(0);
                    //pause para que no se reinicie reproduciendo
                    player.pause();
                }
            }
        });

    }
}