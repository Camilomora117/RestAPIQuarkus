// Obteniendo la lista en JavaScript
const lista = ['Título 1', 'Título 2', 'Título 3', 'Título 4', 'Título 5'];

// Seleccionando el contenedor HTML para agregar los elementos h1
const contenedorH1 = document.getElementById('content');

// Creando los elementos h1 en base a la lista
lista.forEach((titulo) => {
  const elementoH1 = document.createElement('h1');
  const contenidoH1 = document.createTextNode(titulo);
  elementoH1.appendChild(contenidoH1);
  contenedorH1.appendChild(elementoH1);
});