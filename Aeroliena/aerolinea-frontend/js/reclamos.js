async function listarReclamos() {
    const reclamos = await get("reclamos");
    const tbody = document.getElementById("listaReclamos");
    tbody.innerHTML = "";
    reclamos.forEach(r => {
        const fila = `<tr>
      <td>${r.idreclamo}</td>
      <td>${r.pasajero}</td>
      <td>${r.descripcion}</td>
      <td>${r.estado}</td>
      <td><button onclick="eliminarReclamo(${r.idreclamo})" class="btn btn-danger btn-sm">Eliminar</button></td>
    </tr>`;
        tbody.innerHTML += fila;
    });
}

function mostrarFormularioReclamo() {
    document.getElementById("formularioReclamo").style.display = "block";
}

function cancelarReclamo() {
    document.getElementById("formularioReclamo").style.display = "none";
}

async function guardarReclamo() {
    const pasajero = document.getElementById("pasajeroReclamo").value;
    const descripcion = document.getElementById("descripcionReclamo").value;
    const estado = document.getElementById("estadoReclamo").value;

    await post("reclamos", { pasajero, descripcion, estado });
    alert("Reclamo guardado correctamente");
    listarReclamos();
}

async function eliminarReclamo(id) {
    await eliminar(`reclamos/${id}`);
    alert("Reclamo eliminado");
    listarReclamos();
}

listarReclamos();
