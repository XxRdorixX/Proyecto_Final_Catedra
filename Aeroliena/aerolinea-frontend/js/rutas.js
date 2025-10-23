async function listarRutas() {
    const rutas = await get("rutas");
    const tbody = document.getElementById("listaRutas");
    tbody.innerHTML = "";
    rutas.forEach(r => {
        const fila = `<tr>
      <td>${r.idruta}</td>
      <td>${r.origen}</td>
      <td>${r.destino}</td>
      <td>${r.duracion}</td>
      <td><button onclick="eliminarRuta(${r.idruta})" class="btn btn-danger btn-sm">Eliminar</button></td>
    </tr>`;
        tbody.innerHTML += fila;
    });
}

function mostrarFormularioRuta() {
    document.getElementById("formularioRuta").style.display = "block";
}

function cancelarRuta() {
    document.getElementById("formularioRuta").style.display = "none";
}

async function guardarRuta() {
    const origen = document.getElementById("origen").value;
    const destino = document.getElementById("destino").value;
    const duracion = document.getElementById("duracion").value;

    await post("rutas", { origen, destino, duracion });
    alert("Ruta agregada correctamente");
    listarRutas();
}

async function eliminarRuta(id) {
    await eliminar(`rutas/${id}`);
    alert("Ruta eliminada");
    listarRutas();
}

listarRutas();
