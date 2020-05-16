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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static javaPac.DatabaseConstants.CREATE_DATABASE;

public class SampleConectionController implements Initializable {

    @FXML    public Button ConnectButton;
    @FXML public Button goToStage1;

    public Connection getConection () throws SQLException {
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, DatabaseConstants.USER, DatabaseConstants.PASSWORD);
            createDtabase( connection );
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
    public void goToStage1 ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage1.fxml" ));
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window =  (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }
        public void clik(){
        this.goToStage1.setDisable( false );
        this.ConnectButton.setDisable( true );  }

    public void quitButton( ActionEvent actionEvent ){
        System.exit( 0 );
    }

    @Override
    public void initialize ( java.net.URL location, ResourceBundle resources ) {
    this.goToStage1.setDisable( true );
    }


}

