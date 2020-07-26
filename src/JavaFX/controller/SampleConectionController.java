package JavaFX.controller;


import javaPac.DatabaseConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static javaPac.DatabaseConstants.CREATE_DATABASE;

public class SampleConectionController implements Initializable {

    @FXML public Button ConnectButton;
    @FXML public Button goToStage1;
    @FXML public Button getConnectionWeb;
    @FXML public Button StartWebServer;
    @FXML public MenuItem despre;

    public Connection getConection () throws SQLException, ClassNotFoundException, InterruptedException {

        AccesSQL.sqlLogin();
        click();
         Class.forName("com.mysql.cj.jdbc.Driver"); //adaugata sa nu mai dea eroare la linia 14 din databaseConstants
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, AccesSQL.USER, AccesSQL.PASSWORD);
        createDtabase( connection );
       // click();


        return null;
    }
    public void despre(){Despre.Text();}

    public Connection getConnectionWeb () throws SQLException, ClassNotFoundException {
        Class.forName( "com.mysql.cj.jdbc.Driver" );
        Connection connectionWeb = DriverManager.getConnection( DatabaseConstants.URLWEB,DatabaseConstants.UserWeb,DatabaseConstants.PASSWORDWEB );

        createDatabaseWeb(  connectionWeb );
        return null;
    }
  //create DB
    private static void createDtabase ( Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate( CREATE_DATABASE );
            System.out.println("baza exista/este deja creata");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("BD necreata--------------------------------------------------");
        }
    }
    private static void createDatabaseWeb (Connection connectionWeb){
        try (Statement statement= connectionWeb.createStatement()){
            statement.executeUpdate( CREATE_DATABASE );
            System.out.println("BD Exista");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("BD Web necreata--------------------");
        }
    }


    public  void goToStage1 ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage1.fxml" ));
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window =  (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();

    }

    public void goToStage1Demo ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage2IntroDemo.fxml" ));
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window =  (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();

    }
        public void click (  ){
        this.goToStage1.setDisable( false );
        this.ConnectButton.setDisable( true );  }

    public void quitButton( ActionEvent actionEvent ){
        System.exit( 0 );
    }


    @Override
    public void initialize ( java.net.URL location, ResourceBundle resources ) {

        this.goToStage1.setDisable( true );
        this.getConnectionWeb.setDisable( true );
        this.StartWebServer.setDisable( true );
    }



}

