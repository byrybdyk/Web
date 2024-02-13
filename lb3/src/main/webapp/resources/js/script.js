let RadiusInput = document.getElementById('form:radius');
let submitButton = document.getElementById("form:submit");
let xRadio =document.getElementsByName("form:xRadio");
let xHidden =document.getElementById("form:X-Input-hidden");
window.onload = function () {
    let pointForm = document.getElementById('form');
    let yCoordinateInput = document.getElementById('form:yCoordinate');
    let xCoordinateInput = document.getElementById('form:xRadio');

    }








    function validateR(selectedR){
        return(selectedR && !isNaN(selectedR)&& selectedR >=1 && selectedR <=4);
    }

    document.addEventListener('click', function(evt) {
        if(evt.clientX >= 300 && evt.clientY >= 200 && evt.clientX <= 586 && evt.clientY <= 476 ){
            let selectedR =  document.getElementById('form:radius').value;
            if(validateR(selectedR)){
                const radius = selectedR;
                const xMouse =   +(((evt.clientX - 445)*radius)/110).toFixed(3);
                const yMouse =  -(((evt.clientY - 335)*radius)/110).toFixed(3);
                // alert(evt.clientX +" " + evt.clientY + " " + radius);
                let xClick = document.getElementById("X-Input-hidden");
                let yClick = document.getElementById("");
                document.getElementById("form:X-Input-hidden").value = xMouse;
                document.getElementById("form:yCoordinate").value = yMouse;
                submitButton.click();
                // update_hidden_x();
            }
            else{
                alert("Неверное значение R");
            }


        }
    });


    $(document).ready(function() {
        $('input[type=radio][name="form:xRadio"]').change(function() {
            xHidden.value = document.querySelector('input[name="form:xRadio"]:checked').value;
        });
    });


    RadiusInput.oninput = function (){
        let selectedR = parseFloat(this.value);
        if ( validateR(selectedR)){
            redrawGraph(selectedR);
            return;
        }
        alert('Неверный R')
    };

function updateGraph() {
    let r = RadiusInput.value;
    redrawGraph(r);
    // Дополнительный код, который вы хотите выполнить после ajax-запроса
}
function update_hidden_x(){
    xHidden.value = document.querySelector('input[name="form:xRadio"]:checked').value;
}
