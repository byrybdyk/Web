package com.example.model;


import com.example.database.DatabaseConnector;
import com.example.database.DatabaseScripts;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import java.util.Objects;

@Named
@SessionScoped
public class ResultsControllerBean implements Serializable {
    @Inject
    private PointBean pointBean;


    @Inject
    private DatabaseConnector databaseConnector;


    private LinkedList<PointBean> results;


    public ResultsControllerBean() {
        super();
        results = new LinkedList<>();
    }

    public LinkedList<PointBean> getResults(){
        return results;
    }

    @PostConstruct
    public void init(){
        try (Connection connection = databaseConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DatabaseScripts.GET_POINTS_DATA);

            while (resultSet.next()){
                PointBean result = new PointBean();
                result.setX(resultSet.getDouble("xvalue"));
                result.setY(resultSet.getDouble("yvalue"));
                result.setR(resultSet.getDouble("rvalue"));
                result.setResult(resultSet.getBoolean("result"));
                results.add(result);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDatabaseConnector(DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
    }

    public void setResults(LinkedList<PointBean> results){
        this.results = results;
    }


    public void addResult(final double x, final double y, final double r, final String status){
        final PointBean currentRes = new PointBean();
        final boolean result = PointBean.isInArea(x, y, r);
        currentRes.setX(x);
        currentRes.setY(y);
        currentRes.setR(r);
        currentRes.setResult(result);


        try (Connection connection = databaseConnector.connect();
             PreparedStatement statement = connection.prepareStatement(DatabaseScripts.ADD_POINT_DATA)){

            statement.setDouble(1, x);
            statement.setDouble(2, y);
            statement.setDouble(3, r);
            statement.setBoolean(4, result);
            statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (!Objects.equals(status, "dbTest")){
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("drawPoint(" + x + ", " + y + ", " + r + ", " + result + ");");
        }
        results.addFirst(currentRes);

        System.out.println(results.toString());
    }

    public void clearResults(double rVal){
        results.clear();
        FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("drawCoordsPlane(" + rVal + ");");

        try (Connection connection = databaseConnector.connect();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DatabaseScripts.CLEAR_RESULTS);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateRValue(double newRValue){
        for (PointBean point : results){
            point.setR(newRValue);
        }
    }

    public void generateRedrawScript(){
        for (PointBean point : results){
            double x = point.getX();
            double y = point.getY();
            double r = point.getR();
            boolean result = PointBean.isInArea(x, y, r);
            FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("drawPoint(" + x + ", " + y + ", " + r + ", " + result + ");");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultsControllerBean that = (ResultsControllerBean) o;
        return Objects.equals(pointBean.getX(), that.pointBean.getX()) && Objects.equals(pointBean.getY(), that.pointBean.getY()) && Objects.equals(pointBean.getR(), that.pointBean.getR()) && Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointBean, results);
    }

    @Override
    public String toString() {
        return "ResultsControllerBean{" +
                "xBean=" + pointBean.getX() +
                ", yBean=" + pointBean.getY() +
                ", rBean=" + pointBean.getR() +
                ", results=" + results +
                '}';
    }
}