function validarLogin(event) {
    event.preventDefault();

    // Arrays de Usuarios y contraseñas
    let usuariosRegistrados = ["mrKristo67", "Vickyy04", "johannbachx25", "freeGirl"];
    let clavesRegistradas = ["elorrieta00", "estrellaluna", "piscobamba", "iamfree99"];
    let freePremium = ["Premium", "Free", "Premium", "Free"];

    // Coge los dtos del formlario mediante su ID
    let userIngresado = document.getElementById("usuario").value;
    let passIngresado = document.getElementById("password").value;

    let accesoConcedido = false;

    // Recorremos el array en busca del usuario
    for (let i = 0; i < usuariosRegistrados.length; i++) {
        if (userIngresado === usuariosRegistrados[i] && passIngresado === clavesRegistradas[i]) {
            accesoConcedido = true;

            localStorage.setItem("nombre", usuariosRegistrados[i]);
            localStorage.setItem("plan", freePremium[i]);
        }
    }

    // Respuesta de si encontro el usuario y su contraseña o no
    if (accesoConcedido) {
        alert("Acceso correcto! Redirigiendo...");
        window.location.href = "index.html";
    } else {
        alert("Usuario o contraseña incorrectos.");
        document.getElementById("password").value = "";
    }
}

window.onload = function() {
    const nombreGuardado = localStorage.getItem("nombre");
    const planGuardado = localStorage.getItem("plan");

    if (nombreGuardado && planGuardado) {
        const saludo = document.getElementById("saludo");
        const seccionLogin = document.getElementById("seccion-login");

        if (saludo) {
            saludo.textContent = `Bienvenido ${planGuardado}, ${nombreGuardado}`;
        }
    }
};

//FALTA Q ESTO FUNCIONE
function cerrarSesion() {
    localStorage.removeItem("saludo");
    window.location.href = "index.html";
}