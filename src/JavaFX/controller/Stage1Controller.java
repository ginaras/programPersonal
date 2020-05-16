package JavaFX.controller;

import javaPac.DatabaseConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javaPac.DatabaseConstants.CREATE_TABLE;
import static javaPac.DatabaseConstants.USE_TABLE;

public class Stage1Controller implements Initializable {
    @FXML    private MenuItem createTable;
    @FXML    private MenuItem openTable;
    //definire tabel view cu date din baza de date
    @FXML    private TableView<DatabaseConstants> tableViewBazaDate;
    @FXML    private TableColumn<DatabaseConstants, String> marcaTableView;
    @FXML    private TableColumn<DatabaseConstants, String> cnpTableView;
    @FXML    private TableColumn<DatabaseConstants, String> npTableView;
    @FXML    private TableColumn<DatabaseConstants, LocalDate> dataNasterii;
    @FXML    private TableColumn<DatabaseConstants, LocalDate> dataAngajarii;
    @FXML    private TableColumn<DatabaseConstants, String> vechimeaTableView;
    @FXML    private TableColumn<DatabaseConstants, String> salBrutTableView;
    @FXML    private TableColumn<DatabaseConstants, String> cassTableView;
    @FXML    private TableColumn<DatabaseConstants, String> casTableView;
    @FXML    private TableColumn<DatabaseConstants, String> impozitTableView;
    @FXML    private TableColumn<DatabaseConstants, String> salNetTableView;

    private URL location;
    private ResourceBundle resources;


    //create Table+
    public Connection getConectionNew () throws SQLException {
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, DatabaseConstants.USER, DatabaseConstants.PASSWORD );
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery( "SELECT * FROM personal" );

        createTable( connection );
        return null;
    }

    //+create Table
    public void createTable ( Connection connection ) throws SQLException {
        Statement stm = connection.createStatement();
        int update = stm.executeUpdate( CREATE_TABLE );
        System.out.println( "Baza creata/existenta" + update );

    }


    public Connection getConectionOpen () throws SQLException {
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, DatabaseConstants.USER, DatabaseConstants.PASSWORD );
        openTable1( connection );
        return null;
    }

    public void openTable1 ( Connection connection ) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate( USE_TABLE );
            System.out.println( String.format( "Baza deschisa/existenta" ) );

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println( "nu seschide tabela personal........................" );
        }
    }

    public void goToStage2 ( ActionEvent actionEvent ) throws IOException {
        Parent tableView2 = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage2Show.fxml" ) );
        Scene tabeleViewScene2 = new Scene( tableView2 );
        //This line gets the stage inforation
        Stage window2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window2.setScene( tabeleViewScene2 );
        window2.show();
    }

    public void quitButton ( ActionEvent actionEvent ) {        System.exit( 0 );    }


//    public void openTable ( MenuItem openTable ) {
//        try (Connection connection = DriverManager.getConnection( DatabaseConstants.URL, DatabaseConstants.USER, DatabaseConstants.PASSWORD )) {
//            openTable( (MenuItem) connection );
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            Class.forName( "com.mysql.cj.jdbc.Driver" );
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//    }



    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        this.location = location;
        this.resources = resources;
        marcaTableView.setCellValueFactory( new PropertyValueFactory<>( "marca" ) );
        cnpTableView.setCellValueFactory( new PropertyValueFactory<>( "cnp" ) );
        npTableView.setCellValueFactory( new PropertyValueFactory<>( "nume" ) );
        //dataNasterii.setCellValueFactory( new PropertyValueFactory<>( "dataNasterii" ) );
        //dataAngajarii.setCellValueFactory( new PropertyValueFactory<>( "dataAngajarii" ) );
        vechimeaTableView.setCellValueFactory( new PropertyValueFactory<>( "vechimea" ) );
        salBrutTableView.setCellValueFactory( new PropertyValueFactory<>( "salariuBrut" ) );
        casTableView.setCellValueFactory( new PropertyValueFactory<>( "cas" ) );
        cassTableView.setCellValueFactory( new PropertyValueFactory<>( "cass" ) );
        impozitTableView.setCellValueFactory( new PropertyValueFactory<>( "impozit" ) );
        salNetTableView.setCellValueFactory( new PropertyValueFactory<>( "salNet" ) );
        // cassTableView.setCellValueFactory( new PropertyValueFactory<>( "cass" ) );
        /// tableView.setItems( getPeople() );
    }


    public Stage1Controller () throws SQLException, IOException {
        List data = new ArrayList();
        try {
            Connection connection = DriverManager.getConnection( DatabaseConstants.URL, DatabaseConstants.USER, DatabaseConstants.PASSWORD );
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery( "SELECT * FROM personal" );
//Print - Crearea si prima linie a fisierilui de raport
            BufferedWriter writer0 = new BufferedWriter( new FileWriter( "S:\\TabelPersoane(curs)-"+ LocalDate.now()+".csv",false ));
            writer0.append( "mrca;cnp;nume si prenume;Salariu brut; Vechimea; CASS; CAS, Impozit; Salariu net" );
            writer0.close();
//Parcurgerea BD si extragerea datelor iterate through the java resultset
            while (rs.next()) {
                String marcaTableView = rs.getString( "marca" );
                String cnpTableView = rs.getString( "cnp" );
                String npTableView = rs.getString( "nume" );
                String salBrutTableView = rs.getString( "salariuBrut" );
                //String lastName = rs.getString("npTableView");
                //String lastName = rs.getString("salBrutTableView");
                String vechimeaTableView = rs.getString( "vechimea" );
                String cassTableView = rs.getString( "cass" );
                String casTableView = rs.getString( "cas" );
                String impozitTableView = rs.getString( "impozit" );
                String salNetTableView = rs.getString( "salNet" );
                //data.add( marcaTableView + "" + cnpTableView + "" + npTableView + "" + salBrutTableView + "" + vechimeaTableView + "" + cassTableView + "" + casTableView + "" + impozitTableView + "" + salBrutTableView + "" );
                System.out.format( "%s, %s, %s, %s, %s, %s, %s, %s,%s\n", marcaTableView, cnpTableView, npTableView, vechimeaTableView, salBrutTableView, cassTableView, casTableView, impozitTableView, salNetTableView );


                // astea doua blocheaza next-ul
                //tableViewBazaDate.getItems().addAll(  );
               // tableViewBazaDate.setItems( (ObservableList<DatabaseConstants>) data );
               // writer0.write( capTabel );
//print - adaugarea datelor in fisier
                String datele  = (String)marcaTableView +";"+ (String) cnpTableView +";"+ (String)npTableView +";"+ (String) vechimeaTableView +";"+ (String) salBrutTableView +";"+ (String) cassTableView +";"+ (String) casTableView +";"+ (String) impozitTableView +";"+ (String) salNetTableView;
                BufferedWriter writer = new BufferedWriter(new FileWriter("S:\\TabelPersoane(curs)-"+ LocalDate.now()+".csv", true));
                writer.append( " \n" );
                writer.append(datele);
                writer.close();
            }

        } catch (IOException ioException) {            ioException.printStackTrace();        }
    }
    public ObservableList<DatabaseConstants> getLista () {
        ObservableList<DatabaseConstants> people = FXCollections.observableArrayList();
        return people;
    }
    }