const input = document.getElementById("input");
const productos = document.getElementById("listaproductos");
const seleccionados = document.getElementById("seleccionados");
var productosSeleccionados = [];

// Tomar valor de la barra de busqueda
input.addEventListener("input", () => {
  const texto = input.value.toLowerCase();
  const productosFiltrados = todosProductos.filter((producto) =>
    producto.nombre.toLowerCase().includes(texto)
  );
  if (texto != "") {
    displaySearchResults(productosFiltrados);
  } else {
    productos.innerHTML = "";
    productos.setAttribute("style", "border: 0px transparent;");
  }
});

// Mostrar resultados de la barra de busqueda
function displaySearchResults(resultados) {
  productos.innerHTML = "";
  productos.setAttribute(
    "style",
    "border: 2px solid; margin-top: -125px; margin-left: -28px;"
  );
  resultados.forEach((producto) => {
    const resultado = document.createElement("div");
    resultado.classList.add("item");
    resultado.textContent = producto.nombre;
    resultado.addEventListener("click", () => addSelectedProduct(producto));
    productos.appendChild(resultado);
  });
}

// Agregar a la tabla de productos a vender
function addSelectedProduct(producto) {
  const nuevaFila = document.createElement("tr");
  nuevaFila.innerHTML = `
<input hidden name="idProducto" value=${producto.id}>
<td name="nombre">${producto.nombre}</td>
<td><input value="1" min="1" max="${producto.stock}"class="cantidad w-25 p-1" type=number name="cantidad"></td>
<td class="valorprecio" value=${producto.precio}>$ ${producto.precio}</td>
<td class="totalprecio" value="${producto.precio}">$ ${producto.precio}</td>
<td class="quitar"><img class='quitarproducto' src="https://c7.alamy.com/compes/w1jh6r/ilustracion-vectorial-diseno-plano-cancelar-eliminar-quitar-icono-w1jh6r.jpg"</td>
`;
  seleccionados.appendChild(nuevaFila);
  productos.innerHTML = "";
  productos.setAttribute("style", "border: 0px transparent;");

  displayTotalPrice();
}

/* -------- QUITAR PRODUCTO DE LA VENTA -------- */
seleccionados.addEventListener("click", () => {
  if (event.target && event.target.classList.contains("quitarproducto")) {
    event.target.closest("tr").remove();
  }

  displayTotalPrice();
});

/* ------ MOSTRAR PRECIO TOTAL DE CADA PRODUCTO----------*/
function displayTotalPrice() {
  const cantidad = document.getElementsByClassName("cantidad");
  const valorprecio = document.getElementsByClassName("valorprecio");
  const total = document.getElementsByClassName("totalprecio");

  for (let i = 0; i < cantidad.length; i++) {

    function mostrarPrecios(){

    let importePrecioUnitario = valorprecio[i].getAttribute("value");
    
    importePrecioUnitario = separadorDeMiles(importePrecioUnitario);
    
    valorprecio[i].textContent = `$ ${importePrecioUnitario}`;
    
    total[i].value = cantidad[i].value * valorprecio[i].getAttribute("value");
    
    let importePrecioTotal = separadorDeMiles(total[i].value);
    
    total[i].textContent = `$ ${importePrecioTotal}`;
    }

    mostrarPrecios();
    //Actualizar precio total al ingresar cantidad
    cantidad[i].addEventListener("input", () => {
      mostrarPrecios();
    });
  }
}

/*----------------- FUNCION PARA MOSTRAR IMPORTES CON SEPARADORES DE MILES -------------*/
function separadorDeMiles(importe) {
  // Convertir importe a entero
  importe = parseInt(importe);

  return importe.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."); // Retornar importe con separadores de miles
}

/*----------MOSTRAR PRECIO TOTAL DE LA VENTA ------------*/
const vender = document.getElementById("vender");

vender.addEventListener("click", () => {
  const totalVenta = document.getElementById("totalventa");

  const totalPrecio = document.getElementsByClassName("totalprecio");

  let precioAMostrar = 0;

  for (let i = 0; i < totalPrecio.length; i++) {
    precioAMostrar += totalPrecio[i].value;
  }

  totalVenta.value = separadorDeMiles(precioAMostrar);
});