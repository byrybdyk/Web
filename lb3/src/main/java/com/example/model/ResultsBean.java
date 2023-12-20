package com.example.model;


//import com.wift.lab3.db.Point.PointDAO;
import jakarta.inject.Named;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.rmi.server.LogStream.log;
@Named
public class ResultsBean implements Serializable {
    private List<PointBean> results;


//    public PointDAO getDao() {
//        return dao;
//    }

//    public void setDao(PointDAO dao) {
//        this.dao = dao;
//    }

    public ResultsBean() {
        results = new ArrayList<>();
    }

    public List<PointBean> getResults() {

//        results = dao.getAllPoints(getSessionId());
        return results;
    }

    private String getSessionId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Get the ExternalContext
        ExternalContext externalContext = facesContext.getExternalContext();

        // Get the HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        // Get the HttpSession
        HttpSession session = request.getSession();

        return session.getId();
    }

    public void addPoint(PointBean point) {
        log("Added point");
        results.add(point);
    }

    public void setResults(List<PointBean> results) {
        this.results = results;
    }
}
