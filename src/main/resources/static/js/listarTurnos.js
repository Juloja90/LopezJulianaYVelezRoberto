const apiURL = "http://localhost:8080";

// Obtener la referencia a la tabla y al modal
const tableBody = document.querySelector("#turnoTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
let currentTurnoId;

// Función para obtener y mostrar los turnos
function fetchTurnos() {
    // listar los turnos
    fetch(`${apiURL}/turno/buscartodos`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            // Limpiar el contenido actual de la tabla
            tableBody.innerHTML = "";

            // Insertar los datos en la tabla
            data.forEach((turno, index) => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                    <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                    <td>${turno.fecha}</td>
                    <td>${turno.hora}</td>
                    <td>
                        <button class="btn btn-primary btn-sm" onclick="editTurno(${turno.id}, '${turno.paciente.id}', '${turno.odontologo.id}', '${turno.fecha}', '${turno.hora}')">Modificar</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteTurno(${turno.id})">Eliminar</button>
                    </td>
            `;

                tableBody.appendChild(row);
            });
        })
        .catch((error) => {
            console.error("Error fetching data:", error);
        });
}

// Función para abrir el modal y cargar los datos del turno
editTurno = function (id, pacienteId, odontologoId, fecha, hora) {
    currentTurnoId = id;
    document.getElementById("editPaciente").value = pacienteId;
    document.getElementById("editOdontologo").value = odontologoId;
    document.getElementById("editFecha").value = fecha;
    document.getElementById("editHora").value = hora;
    editModal.show();
};

// Función para editar un turno
editForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const pacienteId = document.getElementById("editPaciente").value;
    const odontologoId = document.getElementById("editOdontologo").value;
    const fecha = document.getElementById("editFecha").value;
    const hora = document.getElementById("editHora").value;

    // modificar un turno
    fetch(`${apiURL}/turno/modificar`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            id: currentTurnoId,
            paciente: { id: pacienteId },
            odontologo: { id: odontologoId },
            fecha,
            hora,
        }),
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            alert("Turno modificado con éxito");
            fetchTurnos();
            editModal.hide();
        })
        .catch((error) => {
            console.error("Error editando turno:", error);
        });
});

// Función para eliminar un turno
deleteTurno = function (id) {
    if (confirm("¿Está seguro de que desea eliminar este turno?")) {
        // eliminar el turno
        fetch(`${apiURL}/turno/eliminar/${id}`, {
            method: "DELETE",
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                alert("Turno eliminado con éxito");
                fetchTurnos();
            })
            .catch((error) => {
                console.error("Error borrando turno:", error);
            });
    }
};

// Llamar a la función para obtener y mostrar los turnos
fetchTurnos();
