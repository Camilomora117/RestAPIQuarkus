const loginForm = document.getElementById('login-form');

loginForm.addEventListener('submit', function(event) {
  event.preventDefault();
  validarUsuario();
});

function prueba() {
  alert("hOlaaa");
  window.location = "http://127.0.0.1:5500/pages/Home.html";
}

function validarUsuario() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username === "user" && password === "123") {
      alert("Login exitoso!");
      window.location = "pages/Home.html";
    } else {
      alert("Usuario o contraseña incorrectos.");
    }
}