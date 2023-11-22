package com.example.lb2.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            if(validate(req.getParameter("x"),
                    req.getParameter("y"),
                    req.getParameter("r"))){
                log("КОнтроллер");
                resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                resp.setHeader("Location", "./areaCheck");
            }
        } catch (Exception e) {
            sendError(resp, e.toString());
        }
    }

    private void sendError(HttpServletResponse response, String message) {
        try {
            response.setStatus(422);
            response.getWriter().write(message);
        } catch (IOException e) {
            response.setStatus(500);
        }
    }

    protected boolean validate(String x, String y, String r)  {

        return (validateX(x) && validateY(y) && validateR(r));
    }

    protected boolean validateX(String x){
        if (x == null || x.isEmpty()){
            return false;
        }
        try {
            double xValue = Double.parseDouble(x);
            if((xValue >= -5) && ( xValue <= 5)){
                return true;
            }
        }catch (NumberFormatException e){

        }
        return false;
    }

    protected boolean validateY(String y){
        if (y == null || y.isEmpty()){
            return false;
        }
        try {
            double xValue = Double.parseDouble(y);
            if((xValue >= -5) && ( xValue <= 5)){
                return true;
            }
        }catch (NumberFormatException e){

        }
        return false;
    }

    protected boolean validateR(String r){
        if (r == null || r.isEmpty()){
            return false;
        }
        try {
            int rValue = Integer.parseInt(r);
            List<Integer> values = Arrays.asList(1,2,3,4,5);
            if (values.contains(rValue)) {
                return true;
            }
        }catch (NumberFormatException e){

        }
        return false;
    }

}