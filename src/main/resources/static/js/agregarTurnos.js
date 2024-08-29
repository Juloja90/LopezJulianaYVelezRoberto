const form = document.getElementById("agregarTurnoForm");
const apiURL = "http://localhost:8080";

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const paciente = document.getElementById("paciente").value;
    const odontologo = document.getElementById("odontologo").value;
    const fechaTurno = document.getElementById("fechaTurno").value;

    // Llamando al endpoint de agregar turno
    const datosFormulario = {
        paciente,
        odontologo,
        fechaTurno,
    };

    fetch(`${apiURL}/turno/guardar`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(datosFormulario),
    })
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            alert("Turno agregado con éxito");
            form.reset(); // Resetear el formulario
        })
        .catch((error) => {
            console.error("Error agregando turno:", error);
        });
});
