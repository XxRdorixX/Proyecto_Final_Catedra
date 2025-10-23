const API_URL = "http://localhost:8080/api/v1";

async function get(endpoint) {
    const res = await fetch(`${API_URL}/${endpoint}`, {
        headers: { "Authorization": "Bearer " + localStorage.getItem("token") }
    });
    return await res.json();
}

async function post(endpoint, data) {
    const res = await fetch(`${API_URL}/${endpoint}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify(data)
    });
    return await res.json();
}
