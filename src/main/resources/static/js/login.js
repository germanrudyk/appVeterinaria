const inputs = document.querySelectorAll('input');

inputs.forEach(input => {

    input.addEventListener('blur', function() {
        if (this.value != '') {
            this.parentNode.querySelector('label').classList.add("contenidoInput");
        } else {
            this.parentNode.querySelector('label').classList.remove("contenidoInput");
        }
    });
});