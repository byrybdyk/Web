<%@ page import="com.example.lb2.Models.Point" %>
<%@ page import="com.example.lb2.Models.Points" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>LB 2</title>
  <link rel="stylesheet" href="css/styles.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect-" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>
<div class="background">

  <header class="header">
    <div class="header-container">
      <a class="header-item" href="https://se.ifmo.ru/~s353349/lb1/index.htm" target="_blank"> <h2>Lab Work 1</h2></a>
      <a class="header-item" href="https://isu.ifmo.ru/pls/apex/f?p=2437:7:109555636440371:::::" target="_blank"> <h2>Zarubov E.N</h2></a>
      <a class="header-item" href="https://isu.ifmo.ru/pls/apex/f?p=2143:GR:109555636440371::NO:RP:GR_GR,GR_DATE:P3218," target="_blank"> <h2>Group P3218</h2></a>
      <a class="header-item" href="https://se.ifmo.ru/courses/web" target="_blank"> <h2>Variant 2802</h2></a>
    </div>
    <div class="git-logo">
      <a href="https://github.com/byrybdyk/Web" target="_blank">
        <img class="git-img" src="source/GitHublogo.png"  alt="git-logo">
      </a>
    </div>
  </header>

  <main class="main">
    <div class="graph-container">
      <form id="form">
        <div class="graph">
          <canvas id="graph" height="277" width="280"></canvas>
        </div>

        <div class="input">


          <div class="main-input-x">
            X
            <div class="input-form-X">

              <p>
                <label for="xRadio-5">-5</label>
                <input type="radio" id="xRadio-5" name="xRadio" value="-5" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio-4">-4</label>
                <input type="radio" id="xRadio-4" name="xRadio" value="-4" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio-3">-3</label>
                <input type="radio" id="xRadio-3" name="xRadio" value="-3" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio-2">-2</label>
                <input type="radio" id="xRadio-2" name="xRadio" value="-2" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio-1">-1</label>
                <input type="radio" id="xRadio-1" name="xRadio" value="-1" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio0">0</label>
                <input type="radio" id="xRadio0" name="xRadio" value="0" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio1">1</label>
                <input type="radio" id="xRadio1" name="xRadio" value="1" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio2">2</label>
                <input type="radio" id="xRadio2" name="xRadio" value="2" required title="x radio">
              </p>

            </div>
            <div class="input-form-X">

              <p>
                <label for="xRadio3">3</label>
                <input type="radio" id="xRadio3" name="xRadio" value="3" required title="x radio">
              </p>

            </div>



          </div>

          <div class="input-y">
            Y
            <div class="y-form">
              <input id="yCoordinate" required name="yCoordinate" placeholder="Enter a number"
                     type="number" step="any" min="-3" max="5" value="">
            </div>

          </div>


          <div class="main-input-r">
            R
            <div class="input-form-r">

              <p>
                <label for="rRadio1">1</label>
                <input type="radio" id="rRadio1" name="rRadio" value="1" required title="r radio">
              </p>

            </div>
            <div class="input-form-r">

              <p>
                <label for="rRadio2">2</label>
                <input type="radio" id="rRadio2" name="rRadio" value="2" required title="r radio">
              </p>

            </div>
            <div class="input-form-r">

              <p>
                <label for="rRadio3">3</label>
                <input type="radio" id="rRadio3" name="rRadio" value="3" required title="r radio">
              </p>

            </div>

            <div class="input-form-r">

              <p>
                <label for="rRadio4">4</label>
                <input type="radio" id="rRadio4" name="rRadio" value="4" required title="r radio">
              </p>

            </div>
            <div class="input-form-r">

              <p>
                <label for="rRadio5">5</label>
                <input type="radio" id="rRadio5" name="rRadio" value="5" required title="r radio">
              </p>

            </div>
          </div>

          <div class="input-submit">
            <button class="submit-form-button" type="submit" id="submit">Submit</button>
          </div>


        </div>



      </form>



    </div>

    <div class="main-table-block">
      <%
        Points points = (Points) request.getSession().getAttribute("points");
        if (points == null) {
      %>
      <table id="resultsTable">
        <thead>
        <tr>
          <th>R Параметр</th>
          <th>X координата</th>
          <th>Y координата</th>
          <th>Попадание в область</th>
        </tr>
        </thead>
        <tbody id="results-values2">
        </tbody>
      </table>
      <%} else {%>
      <table id="resultsTable">
        <thead>
        <tr>
          <th>R Параметр</th>
          <th>X координата</th>
          <th>Y координата</th>
          <th>Попадание в область</th>
        </tr>
        </thead>
        <tbody id="results-values">
        <%for(Point point : points.getPoints()) {%>
        <tr>
          <td><%=point.getR()%></td>
          <td><%=point.getX()%></td>
          <td><%=point.getY()%></td>
          <td><%=point.isInArea()%></td>
        </tr>
        <%}%>
        </tbody>
      </table>
      <%}%>
    </div>

  </main>


  <script type='text/javascript' src="js/graph.js"></script>
  <script src="js/script.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</div>

</body>
</html>
