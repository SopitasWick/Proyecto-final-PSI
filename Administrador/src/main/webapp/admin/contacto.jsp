<%-- 
    Document   : contacto
    Created on : 16 nov 2024, 01:19:10
    Author     : José Iván Vázquez Brambila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contacto - MG Rentas</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Contacto</h1>
    </header>

    <section class="contacto">
        <p>
            Si tienes preguntas, comentarios o necesitas más información, por favor completa el formulario a continuación o 
            contáctanos directamente a través de nuestros canales oficiales.
        </p>

        <form id="formulario-contacto">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>

            <label for="correo">Correo Electrónico:</label>
            <input type="email" id="correo" name="correo" required>

            <label for="mensaje">Mensaje:</label>
            <textarea id="mensaje" name="mensaje" rows="5" required></textarea>

            <button type="submit">Enviar</button>
        </form>
    </section>

    <footer>
        <p>&copy; 2024 MG Rentas. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
