<?php
// GET ver.php?id=3 → una juego de pokemon en JSON
require_once __DIR__ . '/datos/ConexionBD.php';

header('Content-Type: application/json; charset=utf-8');

$id = filter_var($_GET['id'] ?? '', FILTER_VALIDATE_INT);
if ($id === false) {
http_response_code(400);
echo json_encode(['error' => ERROR_FALTA_ID]);
exit;
}

try {
    $bd = new ConexionBD();

    $consulta = $bd->getConexion()
        ->prepare('SELECT id, titulo, anio, imagen FROM pokemons WHERE id = ?');
    $consulta->execute([$id]);
    $fila = $consulta->fetch();

    if ($fila === false) {
        http_response_code(404);
        echo json_encode(['error' => ERROR_NO_EXISTE]);
    } else {
        echo json_encode($fila);
    }

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => ERROR_CONEXION], JSON_UNESCAPED_UNICODE);
}
