async function listarAviones() {
    const aviones = await get("aviones");
    const tbody = document.getElementById("listaAviones");
    tbody.innerHTML = "";
    aviones.forEach(a => {
        const fila = `<tr>
      <td>${a.idavion}</td>
      <td>${a.modelo}</td>
      <td>${a.capacidad}</td>
      <td>${a.aerolinea}</td>
      <td><button onclick="eliminarAvion(${a.idavion})" class="btn btn-danger btn-sm">Eliminar</button></td>
    </tr>`;
        tbody.innerHTML += fila;
    });
}

function mostrarFormularioAvion() {
    document.getElementById("formularioAvion").style.display = "block";
}

function cancelarAvion() {
    document.getElementById("formularioAvion").style.display = "none";
}

async function guardarAvion() {
    const modelo = document.getElementById("modelo").value;
    const capacidad = document.getElementById("capacidad").value;
    const aerolinea = document.getElementById("aerolinea").value;

    await post("aviones", { modelo, capacidad, aerolinea });
    alert("Avión agregado correctamente");
    listarAviones();
}

async function eliminarAvion(id) {
    await eliminar(`aviones/${id}`);
    alert("Avión eliminado");
    listarAviones();
}

listarAviones();
