<?php
session_start();

// get paramers from POST
$radius = isset($_POST['radius']) ? floatval($_POST['radius']) : 0;
$xCoordinate = isset($_POST['xCoordinate']) ? floatval($_POST['xCoordinate']) : 0;
$yCoordinate = isset($_POST['yCoordinate']) ? floatval($_POST['yCoordinate']) : 0;

if (!validate_values($xCoordinate, $yCoordinate, $radius)) {
  http_response_code(412);
  echo("x={$xCoordinate}, y={$yCoordinate}, r={$radius}");
  return;
}

function validate_values($xCoordinate, $yCoordinate, $radius)
{
    return in_array($xCoordinate, [-3, -2, -1, 0, 1, 2, 3, 4, 5])
        and (is_numeric($yCoordinate) and $yCoordinate >= -5 and $yCoordinate <= 3)
        and in_array($radius, [1, 1.5, 2, 2.5, 3]);
}

// validation
if (!is_numeric($radius) || !is_numeric($yCoordinate) || !is_numeric($xCoordinate)) {
    echo '<tr><td colspan="6">Ошибка: Некорректные данные</td></tr>';
} else {
    // check hit
    $inArea = checkPointInArea($radius, $xCoordinate, $yCoordinate);

    // script duration time
    $executionTime = microtime(true) - $_SERVER['REQUEST_TIME_FLOAT'];
    date_default_timezone_set('Europe/Moscow');
    $current_time = date("H:i:s");

    // generate result
    $resultHtml = '<tr>';
    $resultHtml .= "<td>{$radius}</td>";
    $resultHtml .= "<td>{$xCoordinate}</td>";
    $resultHtml .= "<td>{$yCoordinate}</td>";
    $resultHtml .= "<td>{$inArea}</td>";
    $resultHtml .= "<td>{$current_time}</td>";
    $resultHtml .= "<td>" . number_format($executionTime * 1000, 2) . " мс</td>";
    $resultHtml .= '</tr>';

    // add to session
    $_SESSION['results'][] = $resultHtml;

    // return answer
    echo $resultHtml;
}

function checkPointInArea($r, $x, $y) {
    // check second zone
    $area1 = (((-($r/2)) <= ($x)) && (($x) <= (0))) && (((0) <= ($y)) && (($y) <= ($r)));

    // check fourth zone
    $area2 = ($x >= 0) && ($y <= 0) && ((($x ** 2) + ($y ** 2)) <= ((($r) / 2)**2));

    // check first zone
    $area3 = (((-($r / 2)) >= ($x))  &&  (($x) >= (0))) && (((-($r)) >= ($y))  &&  (($y) >= (0))) && ($y >= (-($x + ($r / 2))));

    if ($area1 || $area2 || $area3){
        return 'hit';}
    else{
        return 'miss';
    } // Пример, замените на вашу логику
}
?>

