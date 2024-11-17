<%-- 
    Document   : admin-catalogo
    Created on : 16 nov 2024, 01:16:58
    Author     : José Iván Vázquez Brambila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo de Carritos - MG Rentas</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <h1>Catálogo de Carritos</h1>
    </header>

    <!-- Botones de acción -->
    <section class="acciones">
        <button id="btn-crear">Crear Nuevo Carrito</button>
        <button id="btn-consultar-disponibilidad">Consultar Disponibilidad</button>
    </section>

    <!-- Tabla de carritos -->
    <section class="tabla-carritos">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tipo</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Hot Dog Clásico</td>
                    <td>Disponible</td>
                    <td>
                        <button class="btn-actualizar">Actualizar</button>
                        <button class="btn-eliminar">Eliminar</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </section>

    <!-- Formulario modal para crear o editar un carrito -->
    <div id="modal-formulario-carrito" class="modal">
        <div class="modal-contenido">
            <span class="cerrar">&times;</span>
            <h2 id="modal-titulo">Crear Nuevo Carrito</h2>
            <form id="formulario-carrito">
                <label for="carrito-tipo">Tipo de Carrito:</label>
                <input type="text" id="carrito-tipo" name="carrito-tipo" required>
                <label for="carrito-estado">Estado:</label>
                <select id="carrito-estado" name="carrito-estado" required>
                    <option value="disponible">Disponible</option>
                    <option value="rentado">Rentado</option>
                    <option value="en reparación">En Reparación</option>
                </select>
                <button type="submit" id="btn-guardar">Guardar</button>
                <button type="button" id="btn-cancelar">Cancelar</button>
            </form>
        </div>
    </div>

    <script src="script.js"></script>
</body>
</html>
