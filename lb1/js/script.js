document.addEventListener('DOMContentLoaded', function () {
    const pointForm = document.getElementById('form');
    const resultsTable = document.getElementById('resultsTable');
    const clearButton = document.getElementById('clearButton');
    const rSelected = document.getElementById('rSelect');
    const xRadios = document.querySelectorAll('input[name="xRadio"]');
    const yCoordinateInput = document.getElementById('yCoordinate');
    
  
  
    pointForm.addEventListener('submit', function (event) {
        event.preventDefault();
  
        const selectedX = document.querySelector('input[name="xRadio"]:checked');
        
        
        if (!selectedX) {
            alert('Пожалуйста, выберите радиус.');
            return;
        }
  
  
        const yCoordinate = parseFloat(yCoordinateInput.value);

        

       

        if(isNaN(yCoordinate)){
            alert('не число');
            return
        }

        
  
        if (   yCoordinate > 5 || yCoordinate < -3 ) {
            alert('Пожалуйста, введите корректное числовое значение Y-координаты в диапазоне от -3 до 5.');
            return;
        }
  
        const xCoordinate = selectedX.value;
        const radius = rSelected.value;
  
        printDotOnGraph(xCoordinate, yCoordinate);
  
        // send parameters for checking
        $.ajax({
            url: 'php/submit.php',
            method: 'POST',
            data: { radius, xCoordinate, yCoordinate },
            success: function (response) {
                // add result to table
                resultsTable.querySelector('tbody').innerHTML += response;
            },
            error: function () {
                alert('Произошла ошибка при отправке данных.');
            }
        });
    });
  
     // clear table
    clearButton.addEventListener('click', function () {
        resultsTable.querySelector('tbody').innerHTML = '';
    });
  
    
  
  
      // Redraw graph for new R
      rSelected.addEventListener('change', function () {
          const selectedR = this.value;
          const validRadius = [ '1', '1.5', '2', '2.5', '3'];
  
          if (!validRadius.includes(selectedR)) {
              alert('Выбран недопустимый R.');
              this.checked = false;
          }
          redrawGraph(selectedR);
      });

      
    
   
  });
  