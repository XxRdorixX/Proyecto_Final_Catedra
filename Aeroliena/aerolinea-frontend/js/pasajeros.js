async function listarPasajeros() {
    const pasajeros = await get("pasajeros");
    const tbody = document.getElementById("listaPasajeros");
    tbody.innerHTML = "";
    pasajeros.forEach(p => {
        const fila = `<tr>
      <td>${p.idpasajero}</td>
      <td>${p.nombre}</td>
      <td>${p.documento}</td>
      <td>${p.correo}</td>
      <td><button onclick="eliminarPasajero(${p.idpasajero})" class="btn btn-danger btn-sm">Eliminar</button></td>
    </tr>`;
        tbody.innerHTML += fila;
    });
}

function mostrarFormularioPasajero() {
    document.getElementById("formularioPasajero").style.display = "block";
}

function cancelarPasajero() {
    document.getElementById("formularioPasajero").style.display = "none";
}

async function guardarPasajero() {
    const nombre = document.getElementById("nombrePasajero").value;
    const documento = document.getElementById("documentoPasajero").value;
    const correo = document.getElementById("correoPasajero").value;

    await post("pasajeros", { nombre, documento, correo });
    alert("Pasajero agregado correctamente");
    listarPasajeros();
}

async function eliminarPasajero(id) {
    await eliminar(`pasajeros/${id}`);
    alert("Pasajero eliminado");
    listarPasajeros();
}

listarPasajeros();
