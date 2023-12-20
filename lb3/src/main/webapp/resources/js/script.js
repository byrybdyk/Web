document.addEventListener('DOMContentLoaded', function () {
    let pointForm = document.getElementById('form');
    let yCoordinateInput = document.getElementById('form:yCoordinate');
    let RadiusInput = document.getElementById('form:radius');
    alert('start');
    let submit = document.getElementById("form:submit");
    if(submit){
        pointForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const selectedX = document.querySelector('input[name="xRadio"]:checked');


            if (!selectedX) {
                alert('Пожалуйста, выберите X.');
                return;
            }

            let selectedR = parseFloat(radius);


            if (!selectedR) {
                alert('Пожалуйста, выберите радиус.');
                return;
            }
            if (isNaN(selectedR)){
                alert('Неверный Радиус')
                return;
            }


            let selectedY = parseFloat(yCoordinateInput.value);

            if(isNaN(selectedY)){
                alert('Y не число');
                return
            }



            if (   selectedY > 3 || selectedY < -5 ) {
                alert('Пожалуйста, введите корректное числовое значение Y-координаты в диапазоне от -5 до 3.');
                return;
            }

            let xCoordinate = selectedX.value;
            let radius = selectedR.value;
            let yCoordinate = selectedY.value;

            let form = $('<form>', {
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

    function processTable() {
        clearPoints();
        let table = document.getElementById("resultTable");
        for (let item of table.rows) {
            let x = parseFloat(item.children[0].innerText.trim());
            let y = parseFloat(item.children[1].innerText.trim());
            let r = parseFloat(item.children[2].innerText.trim());
            if (isNaN(x) || isNaN(y) || isNaN(r)) continue;

            let result = item.children[3].innerText.trim() === "Есть пробите!";
            drawPoint(x, y, result ? "true" : "false");
        }
    }




    function addToTable(x, y, r, result) {
        let table = document.getElementById("resultsTable");


        let row = table.insertRow();
        row.insertCell().innerText = r;
        row.insertCell().innerText = x;
        row.insertCell().innerText = y;
        row.insertCell().innerHTML = result;
    }




    RadiusInput.addEventListener("change", function (){
        let selectedR = parseFloat(this.value);
        if (!selectedR) {
            alert('Пожалуйста, выберите радиус.');
            return;
        }
        if (isNaN(selectedR)){
            alert('Неверный Радиус');
            return;
        }
        if ( selectedR >= 1 || selectedR <= 4){
            redrawGraph(selectedR);
            return;
        }
        alert('Неверный R')
    })

    // $(document).ready(function() {
    //     $('input[type=text][name="radius"]').change(function() {
    //         let selectedR = parseFloat(this.value);
    //         redrawGraph(selectedR);
    //     });
    // });

    document.addEventListener('click', function(evt) {
        if(evt.clientX >= 300 && evt.clientY >= 200 && evt.clientX <= 586 && evt.clientY <= 476 ){
            let selectedR = RadiusInput.value;
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
