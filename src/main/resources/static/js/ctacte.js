/*----------------- FUNCION PARA MOSTRAR IMPORTES CON SEPARADORES DE MILES -------------*/
function separadorDeMiles(importe) {
  // Convertir importe a entero
  importe = parseInt(importe);

  return importe.toString().replace(/\B(?=(\d{3})+(?!\d))/g, "."); // Retornar importe con separadores de miles
}

/* ----------------- MOSTRAR IMPORTES CON SEPARADORES DE MILES -----------------*/

const precioUnitario = document.getElementsByClassName("preciounitario");
const precioTotal = document.getElementsByClassName("preciototal");
const montoACancelar = document.getElementsByClassName("montoacancelar");
const pt = document.getElementsByClassName("prectotal");

for (let i = 0; i < montoACancelar.length; i++) {
  // Precio Unitario
  var precUnit = precioUnitario[i].getAttribute("value");

  precUnit = separadorDeMiles(precUnit);

  precioUnitario[i].textContent = `$ ${precUnit}`;

  // Precio Total
  var precTotal = precioTotal[i].getAttribute("value");

  precTotal = separadorDeMiles(precTotal);

  precioTotal[i].textContent = `$ ${precTotal}`;

  // Monto a cancelar
  var montACanc = montoACancelar[i].getAttribute("value");

  montACanc = separadorDeMiles(montACanc);

  montoACancelar[i].textContent = `$ ${montACanc}`;

  // Precio total para cancelar
  pt[i].value = montACanc;
}

/*-- Mostrar campo de ingreso de monto a cancelar de acuerdo si es total o parcial --*/
const pago = document.getElementsByClassName("pago");
const opcionPago = document.getElementsByClassName("opcionpago");

for (let i = 0; i < opcionPago.length; i++) {
  opcionPago[i].addEventListener("change", () => {
    if (opcionPago[i].value == "parcial") {
      const monto = document.createElement("div");
      monto.classList.add("d-flex");
      monto.classList.add("mb-5");
      monto.classList.add("montoproducto");
      monto.innerHTML = `
            <label for="" class="form-label">Monto a cancelar: $</label>
            <input
              style="margin-left: 10px"
              type="number"
              class="form-control w-50"
              name="entregado"
            />`;
      pago[i].appendChild(monto);
    } else {
      const monto = document.getElementsByClassName("montoproducto");

      pago[i].removeChild(monto[i]);
    }
  });
}

/*-- IMPORTE TOTAL DE LA CUENTA CORRIENTE --*/

const importetotal = document.getElementById("importetotal");

let importetotalcta = 0;

if (montoACancelar != null){
  for (let i = 0; i < montoACancelar.length; i++) {
      importetotalcta += parseInt(montoACancelar[i].getAttribute("value"));
  }
}

importetotal.value = `$ ${separadorDeMiles(importetotalcta)}`;
