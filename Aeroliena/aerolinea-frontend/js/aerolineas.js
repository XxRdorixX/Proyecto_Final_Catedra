async function listarAerolineas() {
    const aerolineas = await get("aerolineas");
    const tbody = document.querySelector("#tabla tbody");
    tbody.innerHTML = "";
    aerolineas.forEach(a => {
        const fila = `<tr><td>${a.idaerolinea}</td><td>${a.nombre}</td><td>${a.pais}</td></tr>`;
        tbody.innerHTML += fila;
    });
}

function mostrarFormulario() {
    document.getElementById("formulario").style.display = "block";
}

async function guardarAerolinea() {
    const nombre = document.getElementById("nombre").value;
    const pais = document.getElementById("pais").value;
    await post("aerolineas", { nombre, pais });
    alert("Aerolínea guardada correctamente");
    listarAerolineas();
}

listarAerolineas();
