/* --- RELLENAR COLOR DE FONDO DE FILAS DE PRODUCTOS SIN STOCK ---*/

// Capturo todos las filas del contenido de la tabla
const elementosTR = document.querySelectorAll("tbody tr");

// Recorro cada fila con ciclo for
for (let i = 0; i < elementosTR.length; i++) {
  // Capturo la celda correspondiente a la cantidad
  const stock = elementosTR[i].getElementsByTagName("td")[1];

  // Comparo si la cantidad es igual a 0
  if (stock.textContent === "0") {
    // Si es 0, cambio el color de relleno a rojo indicando el faltante
    elementosTR[i].classList.add("faltante");
  } else if (i % 2 == 0) {
    // Los productos identificados en orden par se rellenan
    console.log("par");
    elementosTR[i].classList.add("filapar");
  }
}

/* --------------FILTRO DE BUSQUEDA -----------------*/

let buscar = document.getElementById("input");

buscar.addEventListener("input", function () {
  var filtro = this.value.toLowerCase(); // Obtener el valor del input en minúsculas
  var filas = document.getElementsByClassName("fila");

  // Recorrer cada fila de la tabla
  for (var i = 0; i < filas.length; i++) {
    var contenidoFila = filas[i].textContent.toLowerCase(); // Obtener el contenido de la fila en minúsculas

    // Comparar el contenido de la fila con el término de búsqueda
    if (contenidoFila.indexOf(filtro) === -1) {
      filas[i].style.display = "none"; // Ocultar la fila si no coincide
    } else {
      filas[i].style.display = ""; // Mostrar la fila si coincide
    }
  }
});

/*----------------- FUNCION PARA MOSTRAR IMPORTES CON SEPARADORES DE MILES -------------*/
function separadorDeMiles(importe) {
  // Convertir importe a entero
  importe = parseInt(importe);

  return importe.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."); // Retornar importe con separadores de miles
}

/*--------------- MOSTRAR PRECIO CON PUNTOS DE MILES --------------*/

const precio = document.getElementsByClassName("mostrarprecio");

for (let i = 0; i < precio.length; i++) {
  var importe = precio[i].getAttribute("value");

  importe = separadorDeMiles(importe);

  precio[i].textContent = "$ " + importe;
}

/*--------------- MOSTRAR IMPORTE FINAL EN TIEMPO REAL ------------*/

// Capturar todos los elementos para luego identificar al que se quiere modificar
const importeFinal = document.getElementsByClassName("importefinalproducto");
const porcentaje = document.getElementsByClassName("%");
const precioActual = document.getElementsByClassName("precioactual");

// Mostrar importes finales con separadores de miles
for (let i = 0; i < importeFinal.length; i++) {
  importeFinal[i].value = separadorDeMiles(
    importeFinal[i].getAttribute("value")
  );
}

// Recorrer todos los productos para identificar al que se pretende modificar
for (let i = 0; i < importeFinal.length; i++) {
  // Identificar producto donde se ingresa el porcentaje
  porcentaje[i].addEventListener("input", () => {
    // Si no se ingresa porcentaje, no cambiar el precio final
    if (porcentaje[i].value == "0" || porcentaje[i].value == "") {
      importeFinal[i].value = separadorDeMiles(precioActual[i].value);
    } else {
      // Si se ingresa porcentaje, aplicarlo al precio
      function aplicarPorcentaje() {
        // Calcular porcentaje
        const porcentajeAAplicar = parseInt(porcentaje[i].value) / 100 + 1;

        // Aplicar porcentaje
        const aplicarPorcentaje =
          parseInt(precioActual[i].getAttribute("value")) * porcentajeAAplicar;

        // Redondear precio a centenas
        var precioFinal = Math.ceil(aplicarPorcentaje / 100) * 100;

        precioFinal = separadorDeMiles(precioFinal);

        return precioFinal;
      }

      importeFinal[i].value = aplicarPorcentaje();
    }
  });
}

/*----------- MOSTRAR IMPORTE TOTAL DE LA VENTA -------------*/

const cantidad = document.getElementsByClassName("cantidad");

const precioUnitario = document.getElementsByClassName("preciounitario");

const importeTotal = document.getElementsByClassName("importetotal");

for (let i = 0; i < importeTotal.length; i++) {
  
  // Mostrar importe total inicial
  importeTotal[i].value = precioUnitario[i].value;

  // Mostrar importe total al ingresar la cantidad
  cantidad[i].addEventListener("input", () => {

    importeTotal[i].value = cantidad[i].value * precioUnitario[i].value;

  })
  
}


/* ---------- MOSTRAR ERROR DE CANTIDAD AL VENDER -----------*/
/*
const stock = document.getElementsByClassName("stock");

const stockComponent = document.getElementsByClassName("stockenter");

const cantidad = document.getElementsByClassName("cantidad");

for (let i = 0; i < stock.length; i++) {
  // Crear un elemento div que muestre el error
  const error = document.createElement("div");

  error.classList.add("error");

  error.textContent = "Stock insuficiente";

  // Al ingresar cantidad determinar si es necesario mostrar el error
  cantidad[i].addEventListener("input", () => {
    stock[i].value = parseInt(stock[i].textContent);

    if (cantidad[i].value > stock[i].value) {
      if (!stockComponent[i].querySelector(".error")) {
        stockComponent[i].appendChild(error);
      }
    } else {
      if (stockComponent[i].querySelector(".error")) {

        const mensajeError = stockComponent[i].querySelector(".error");

        stockComponent[i].removeChild(mensajeError);
      }
    }
  });
} */
