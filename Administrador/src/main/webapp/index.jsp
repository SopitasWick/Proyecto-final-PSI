<%-- 
    Document   : index
    Created on : 16 nov 2024, 01:11:23
    Author     : José Iván Vázquez Brambila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión - MG Rentas</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Inicio de Sesión</h1>
    </header>

    <section class="login">
        <form id="formulario-login">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required placeholder="Ingresa tu usuario">

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required placeholder="Ingresa tu contraseña">

            <button id="enviar" class="btn-menu" type="submit">Iniciar Sesión</button>
        </form>
    </section>

    <footer>
        <p>&copy; 2024 MG Rentas. Todos los derechos reservados.</p>
    </footer>

    <script src="login.js"></script>
</body>
</html>

