// script.js

// Obtener elementos
const modal = document.getElementById("modal-formulario-carrito");
const btnCrear = document.getElementById("btn-crear");
const btnCancelar = document.getElementById("btn-cancelar");
const spanCerrar = document.getElementsByClassName("cerrar")[0];
const formularioCarrito = document.getElementById("formulario-carrito");
const tablaCarritos = document.querySelector(".tabla-carritos tbody");

// Variables para manejar el modo de edición
let modoEdicion = false;
let filaActual; // Guardará la fila que se está editando

// Función para abrir el modal en modo creación
btnCrear.onclick = function() {
    modoEdicion = false; // Configurar para modo creación
    formularioCarrito.reset(); // Limpiar el formulario
    document.getElementById("modal-titulo").textContent = "Crear Nuevo Carrito";
    modal.style.display = "block";
}

// Función para cerrar el modal
btnCancelar.onclick = function() {
    modal.style.display = "none";
}

spanCerrar.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Función para agregar o actualizar un carrito en la tabla
function agregarOActualizarCarrito(tipo, estado) {
    if (modoEdicion) {
        // Modo edición: actualizar la fila actual
        filaActual.cells[1].textContent = tipo;
        filaActual.cells[2].textContent = estado;
        modoEdicion = false; // Volver al modo creación
    } else {
        // Modo creación: agregar una nueva fila
        const fila = document.createElement("tr");

        // Crear celdas para el ID, Tipo, Estado y Acciones
        const celdaID = document.createElement("td");
        celdaID.textContent = tablaCarritos.rows.length + 1;

        const celdaTipo = document.createElement("td");
        celdaTipo.textContent = tipo;

        const celdaEstado = document.createElement("td");
        celdaEstado.textContent = estado;

        const celdaAcciones = document.createElement("td");

        // Botón de actualizar
        const btnActualizar = document.createElement("button");
        btnActualizar.textContent = "Actualizar";
        btnActualizar.className = "btn-actualizar";
        btnActualizar.onclick = function() {
            // Configurar el formulario para editar el carrito
            modoEdicion = true;
            filaActual = fila;
            document.getElementById("carrito-tipo").value = tipo;
            document.getElementById("carrito-estado").value = estado;
            document.getElementById("modal-titulo").textContent = "Editar Carrito";
            modal.style.display = "block";
        };

        // Botón de eliminar
        const btnEliminar = document.createElement("button");
        btnEliminar.textContent = "Eliminar";
        btnEliminar.className = "btn-eliminar";
        btnEliminar.onclick = function() {
            fila.remove();
        };

        // Añadir los botones a la celda de acciones
        celdaAcciones.appendChild(btnActualizar);
        celdaAcciones.appendChild(btnEliminar);

        // Añadir las celdas a la fila
        fila.appendChild(celdaID);
        fila.appendChild(celdaTipo);
        fila.appendChild(celdaEstado);
        fila.appendChild(celdaAcciones);

        // Añadir la fila a la tabla
        tablaCarritos.appendChild(fila);
    }
}

// Manejar el evento de envío del formulario
formularioCarrito.onsubmit = function(event) {
    event.preventDefault();

    // Obtener los valores del formulario
    const tipo = document.getElementById("carrito-tipo").value;
    const estado = document.getElementById("carrito-estado").value;

    // Llamar a la función para agregar o actualizar el carrito
    agregarOActualizarCarrito(tipo, estado);

    // Limpiar el formulario y cerrar el modal
    formularioCarrito.reset();
    modal.style.display = "none";
}
