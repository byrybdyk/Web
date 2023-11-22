document.addEventListener('DOMContentLoaded', function () {
    const pointForm = document.getElementById('form');
    const yCoordinateInput = document.getElementById('yCoordinate');


    const submit = document.getElementById("submit");
    if(submit){
        pointForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const selectedX = document.querySelector('input[name="xRadio"]:checked');


            if (!selectedX) {
                alert('Пожалуйста, выберите X.');
                return;
            }

            const selectedR = document.querySelector('input[name="rRadio"]:checked');


            if (!selectedR) {
                alert('Пожалуйста, выберите радиус.');
                return;
            }


            const yCoordinate = parseFloat(yCoordinateInput.value);





            if(isNaN(yCoordinate)){
                alert('не число');
                return
            }



            if (   yCoordinate > 3 || yCoordinate < -5 ) {
                alert('Пожалуйста, введите корректное числовое значение Y-координаты в диапазоне от -5 до 3.');
                return;
            }

            const xCoordinate = selectedX.value;
            const radius = selectedR.value;

            const form = $('<form>', {
                action: "controller",
                method: "post"
            });

            const args = { action: "submit", x: xCoordinate, y: yCoordinate, r: radius };
            Object.entries(args).forEach(entry => {
                const [key, value] = entry;
                $('<input>').attr({
                    type: "hidden",
                    name: key,
                    value: value
                }).appendTo(form);
            });

            form.appendTo('body').submit();

        });
    }




    function addToTable(x, y, r, result) {
        const table = document.getElementById("resultsTable");


        const row = table.insertRow();
        row.insertCell().innerText = r;
        row.insertCell().innerText = x;
        row.insertCell().innerText = y;
        row.insertCell().innerHTML = result;
    }





    $(document).ready(function() {
        $('input[type=radio][name="rRadio"]').change(function() {
            const selectedR = parseInt(this.value);
            redrawGraph(selectedR);
        });
    });

    document.addEventListener('click', function(evt) {
        if(evt.clientX >= 300 && evt.clientY >= 200 && evt.clientX <= 586 && evt.clientY <= 476 ){
            const selectedR = document.querySelector('input[name="rRadio"]:checked');
            if(selectedR){
                const radius = selectedR.value;
                const xMouse =   +(((evt.clientX - 445)*radius)/110).toFixed(3);
                const yMouse =  -(((evt.clientY - 335)*radius)/110).toFixed(3);
                // printDotOnGraph(xMouse, yMouse);
                const form = $('<form>', {
                    action: "controller",
                    method: "post"
                });

                const args = { action: "submit", x: xMouse, y: yMouse, r: radius };
                Object.entries(args).forEach(entry => {
                    const [key, value] = entry;
                    $('<input>').attr({
                        type: "hidden",
                        name: key,
                        value: value
                    }).appendTo(form);
                });

                form.appendTo('body').submit();
            }
            else{
                alert("Выбирите R");
            }


        }


    });

});
