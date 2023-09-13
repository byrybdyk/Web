const canvas = document.getElementById("graph"),
    ctx = canvas.getContext('2d');

canvas.height = canvas.width;
let w = canvas.width, h = canvas.height;

const hatchWidth = 20 / 2;
const hatchGap = 56;

var center_x = w/2;
var center_y = h/2;
var Rad = hatchGap;

let rValue = 'R';
function redrawGraph(r) {
    ctx.clearRect(0, 0, w, h);

    ctx.lineWidth = 2;
    ctx.strokeStyle = 'black';

    // y axis
    ctx.beginPath();
    ctx.moveTo(center_x, 0);
    ctx.lineTo(center_x - 10, 15);
    ctx.moveTo(center_x, 0);
    ctx.lineTo(center_x + 10, 15);
    ctx.moveTo(center_x, 0);
    ctx.lineTo(center_x, h);
    ctx.stroke();
    ctx.closePath();

    // x axis
    ctx.beginPath();
    ctx.moveTo(w, center_y);
    ctx.lineTo(w - 15, center_y - 10);
    ctx.moveTo(w, center_y);
    ctx.lineTo(w - 15, center_y + 10);
    ctx.moveTo(w, center_y);
    ctx.lineTo(0, center_y);
    ctx.stroke();
    ctx.closePath();

    


    ctx.lineWidth = 1;
    ctx.strokeStyle = 'black';

    //circle
    ctx.fillStyle = '#E98074';
    ctx.beginPath();
    ctx.moveTo(w / 2, h / 2);  
    ctx.lineTo(w / 2 + hatchGap * 0, h / 2);
    ctx.arc(w / 2, h / 2, hatchGap, 0, 1 / 2 * Math.PI, false); 
    ctx.lineTo(w/2,h/2 -hatchGap*2); 

    //rectangle
    ctx.lineTo(w/2-hatchGap,h/2 -hatchGap*2 );
    ctx.lineTo(w/2-hatchGap,h/2);
    ctx.lineTo(w/2+hatchGap,h/2);

    //triangle
    ctx.lineTo(w/2,h/2-hatchGap*2);
    ctx.fill();
    ctx.strokeStyle = '#000'
    ctx.stroke();
    ctx.closePath();

    //notches
    ctx.beginPath();
    ctx.moveTo(center_x - hatchWidth, center_y - hatchGap);
    ctx.lineTo(center_x + hatchWidth, center_y - hatchGap);
    ctx.moveTo(center_x - hatchWidth, center_y - hatchGap * 2);
    ctx.lineTo(center_x + hatchWidth, center_y - hatchGap * 2);
    ctx.moveTo(center_x - hatchWidth, center_y + hatchGap);
    ctx.lineTo(center_x + hatchWidth, center_y + hatchGap);
    ctx.moveTo(center_x - hatchWidth, center_y + hatchGap * 2);
    ctx.lineTo(center_x + hatchWidth, center_y + hatchGap * 2);
    ctx.moveTo(center_x - hatchGap, center_y - hatchWidth);
    ctx.lineTo(center_x - hatchGap, center_y + hatchWidth);
    ctx.moveTo(center_x - hatchGap * 2, center_y - hatchWidth);
    ctx.lineTo(center_x - hatchGap * 2, center_y + hatchWidth);
    ctx.moveTo(center_x + hatchGap, center_y - hatchWidth);
    ctx.lineTo(center_x + hatchGap, center_y + hatchWidth);
    ctx.moveTo(center_x + hatchGap * 2, center_y - hatchWidth);
    ctx.lineTo(center_x + hatchGap * 2, center_y + hatchWidth);
    ctx.stroke();
    ctx.closePath();

    

    const fontSize = hatchGap / 3.5
    ctx.fillStyle = 'black'

    ctx.font = `500 ${fontSize * 1.4}px OpenSans`;
    ctx.fillText('y', center_x - hatchWidth * 2.8, 15)
    ctx.fillText('x', w - 20, center_y - hatchWidth * 2.4)

    let label1, label2;
    if (isNaN(r)) {
        label1 = r + '/2'
        label2 = r
    } else {
        label1 = r / 2
        label2 = r
    }
    rValue = label2

    ctx.font = `800 ${fontSize}px Open Sans`;
    ctx.fillText(label1, center_x + hatchGap - 3, center_y + hatchWidth * 2.8);
    ctx.fillText(label2, center_x + hatchGap * 2 - 3, center_y + hatchWidth * 2.8);
    ctx.fillText('-' + label1, center_x - hatchGap - 7, center_y + hatchWidth * 2.8);
    ctx.fillText('-' + label2, center_x - hatchGap * 2 - 7, center_y + hatchWidth * 2.8);

    ctx.fillText(label1, center_x + hatchWidth * 2, center_y - hatchGap + 3);
    ctx.fillText(label2, center_x + hatchWidth * 2, center_y - hatchGap * 2 + 3);
    ctx.fillText('-' + label1, center_x + hatchWidth * 2, center_y + hatchGap + 3);
    ctx.fillText('-' + label2, center_x + hatchWidth * 2, center_y + hatchGap * 2 + 3);
}



// draw graph with standard label
redrawGraph(rValue);

function printDotOnGraph(xCenter, yCenter) {
    redrawGraph(rValue);
    ctx.fillStyle = 'blue'
    let x = center_x + xCenter * hatchGap * (2 / rValue) - 3, y = center_y - yCenter * hatchGap * (2 / rValue) - 3;
    ctx.fillRect(x, y, 6, 6);
}
