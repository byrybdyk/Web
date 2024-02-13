package com.example.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.Objects;


@Named
@RequestScoped
public class PointBean implements Serializable {
    private double x = 0.0;
    private double y = 0.0;
    private double r = 0.0;
    private boolean result;
//    private List<Integer> selectedR;
    private String sessionId;

    public PointBean() {
        sessionId = getSessionId();
    }

//    public static boolean isInArea() {
//        return (((x >= 0 )&&(x <= r)&&(0 >= y)&& (y>= -r/2))
//                || ((x >= 0)&& (y >= 0)&& (x*x+y*y <= r*r))
//                || ((r/2 >= -x)&&(x<=0)&&(y >= 0)&&(y <=(r/2 +x)*2 )));
//    }

    public static boolean isInArea(double x, double y, double r) {
        return (((x >= 0 )&&(x <= r)&&(0 >= y)&& (y>= -r/2))
                || ((x >= 0)&& (y >= 0)&& (x*x+y*y <= r*r))
                || ((r/2 >= -x)&&(x<=0)&&(y >= 0)&&(y <=(r/2 +x)*2 )));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }




    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult(){
        return this.result;
    }

    public String getSessionId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Get the ExternalContext
        ExternalContext externalContext = facesContext.getExternalContext();

        // Get the HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        // Get the HttpSession
        HttpSession session = request.getSession();

        return session.getId();
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointBean pointBean = (PointBean) o;
        return Double.compare(pointBean.x, x) == 0 && Double.compare(pointBean.y, y) == 0 && r == pointBean.r && result == pointBean.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, result);
    }

    @Override
    public String toString() {
        return "PointBean{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isInArea=" + result +
                '}';
    }
}
