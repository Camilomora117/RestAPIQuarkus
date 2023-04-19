const form = document.querySelector('form');

form.addEventListener('submit', (event) => {
  event.preventDefault();
  validarUsuario();
});

function validarUsuario() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username === User.email && password === User.password) {
      window.location = "pages/Home.html";
    } else {
      alert("Usuario o contraseña incorrectos.");
    }
}