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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static javaPac.DatabaseConstants.CREATE_TABLE;
import static javaPac.DatabaseConstants.USE_TABLE;

public class Stage1Controller<personData, personDataApp> implements Initializable {
    @FXML
    private MenuItem createTable;
    @FXML
    private MenuItem openTable;
    //definire tabel view cu date din baza de date
    @FXML
    private TableView<DatabaseConstants> tableViewBazaDate;
    @FXML
    private TableColumn<DatabaseConstants, String> marcaTableView;
    @FXML
    private TableColumn<DatabaseConstants, String> cnpTableView;
    @FXML
    private TableColumn<DatabaseConstants, String> npTableView;
    @FXML
    private TableColumn<DatabaseConstants, LocalDate> dataNasterii;
    @FXML
    private TableColumn<DatabaseConstants, LocalDate> dataAngajarii;
    @FXML
    private TableColumn<DatabaseConstants, String> vechimeaTableView;
    @FXML
    private TableColumn<DatabaseConstants, String> salBrutTableView;
    @FXML
    private TableColumn<DatabaseConstants, String> cassTableView;
    @FXML
    private TableColumn<DatabaseConstants, String> casTableView;
    @FXML
    private TableColumn<DatabaseConstants, String> impozitTableView;
    @FXML
    private TableColumn<DatabaseConstants, String> salNetTableView;
    @FXML
    private Label plecatLabel;
    @FXML
    private Label existNr;
    @FXML
    private Label totalSBLabel;
    @FXML
    private Label totalCASSLabel;
    @FXML
    private Label totalCASLabel;
    @FXML
    private Label totalImpLabel;
    @FXML
    private Label totalSNLabel;

    private URL location;
    private ResourceBundle resources;


    public ObservableList<DatabaseConstants> personalData;//declarare pt listview
    //DatabaseConstants db =DatabaseConstants.getInstantce();
    Connection connection = DriverManager.getConnection( DatabaseConstants.URL, AccesSQL.USER, AccesSQL.PASSWORD );
    Statement stm = connection.createStatement();
    ResultSet rs1 = stm.executeQuery( "SELECT * FROM personal WHERE stare = 'angajat' " );

    //create Table+
    public Connection getConectionNew () throws SQLException {
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, AccesSQL.USER, AccesSQL.PASSWORD );
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
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, AccesSQL.USER, AccesSQL.PASSWORD );
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
        Parent tableView2 = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage2Intro.fxml" ) );
        Scene tabeleViewScene2 = new Scene( tableView2 );
        //This line gets the stage inforation
        Stage window2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window2.setScene( tabeleViewScene2 );
        window2.show();
    }

    public void goToStage3Deleted ( ActionEvent actionEvent ) throws IOException {
        Parent tableView3 = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage3Deleted.fxml" ) );
        Scene tableViewScene3 = new Scene( tableView3 );

        Stage window3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window3.setScene( tableViewScene3 );
        window3.show();
    }

    public void quitButton ( ActionEvent actionEvent ) {
        System.exit( 0 );
    }

    //deschidere   Folder
    public void openFolder ( ActionEvent actionEvent ) {
        Desktop desktop = null;
        try {
            desktop.getDesktop().open( new File( "c:\\GynTaSoft\\Salarii" ) );
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void despre () {
        Despre.Text();
    }


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

// popularea tableView si a Labeluri-lor
        personalData = FXCollections.observableArrayList();
        try {
            int count = 0;
            while (rs1.next()) {
                personalData.addAll( new DatabaseConstants( rs1.getInt( "marca" ),
                        rs1.getString( "cnp" ),
                        rs1.getString( "nume" ),
                        rs1.getInt( "vechimea" ),
                        rs1.getString( "salariuBrut" ),
                        rs1.getInt( "cass" ),
                        rs1.getInt( "cas" ),
                        rs1.getInt( "impozit" ),
                        rs1.getInt( "salNet" ) ) );
                count++;
                existNr.setText( "  Avem  " + String.valueOf( count ) + " angajati" );
            }
            tableViewBazaDate.setItems( personalData );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet total = stm.executeQuery( "SELECT SUM(salariuBrut) AS salariuBrut FROM personal WHERE stare = 'angajat' " );
            while (total.next()) {
                int salBrut = total.getInt( "salariuBrut" );
                int salB = ++salBrut - 1;
                System.out.println( "Total  SB=    " + salB );
                totalSBLabel.setText( String.valueOf( salB ) );
            }
        } catch (SQLException throwables) {
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet totalcas = stm.executeQuery( "SELECT SUM(cas) AS cas FROM personal WHERE stare = 'angajat' " );//WHERE stare = 'angajat'
            while (totalcas.next()) {
                int casT = totalcas.getInt( "cas" );
                int casTotal = ++casT - 1;
                System.out.println( "Total cas=    " + casTotal );
                totalCASLabel.setText( String.valueOf( casTotal ) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet totalcass = stm.executeQuery( "SELECT SUM(cass) AS cass FROM personal WHERE stare = 'angajat' " );//WHERE stare = 'angajat'
            while (totalcass.next()) {
                int cassT = totalcass.getInt( "cass" );
                int cassTotal = ++cassT - 1;
                System.out.println( "Total cass=    " + cassTotal );
                totalCASSLabel.setText( String.valueOf( cassTotal ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet totalimpozit = stm.executeQuery( "SELECT SUM(impozit) AS impozit FROM personal WHERE stare = 'angajat' " );//WHERE stare = 'angajat'
            while (totalimpozit.next()) {
                int ipozit = totalimpozit.getInt( "impozit" );
                int ipozitTotal = ++ipozit - 1;
                System.out.println( "Total impozit= " + ipozitTotal );
                totalImpLabel.setText( String.valueOf( ipozitTotal ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet totalSalNet = stm.executeQuery( "SELECT SUM(salNet) AS salNet FROM personal WHERE stare = 'angajat' " );//WHERE stare = 'angajat'
            while (totalSalNet.next()) {
                int salNet = totalSalNet.getInt( "salNet" );
                int salNetTotal = ++salNet - 1;
                System.out.println( "Total SN=     " + salNetTotal );
                totalSNLabel.setText( String.valueOf( salNetTotal ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void refreshButton ( ActionEvent actionEvent ) throws IOException, SQLException {
        new Stage1Controller();
    }

    public Stage1Controller () throws SQLException, IOException {
        try {
            Connection connection = DriverManager.getConnection( DatabaseConstants.URL, AccesSQL.USER, AccesSQL.PASSWORD );
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery( "SELECT * FROM personal WHERE stare = 'angajat'" );

//creaza folderul in care pun fisierele
            File file = new File( "c:\\GynTaSoft" );
            File file1 = new File( "c:\\GynTaSoft\\Salarii" );
            boolean fileExists = file.mkdir();
            boolean fileExists1 = file1.mkdir();
            //boolean dirCreated = file.mkdir();

//Print - Crearea si prima linie a fisierilui de raport

            LocalDateTime date = LocalDateTime.now();
            String nume = date.toString();
            String replaceNume = nume.replace( ":", "-" );
            String replaceNume2 = replaceNume.replace( "T", " ora " );
            System.out.println( replaceNume );


            BufferedWriter writer0 = new BufferedWriter( new FileWriter( "c:\\GynTaSoft\\Salarii\\TabelPersonalActiv-" + replaceNume2 + ".csv", false ) );
            writer0.append( "marca, cnp ,nume si prenume, Salariu brut, Vechimea, CASS, CAS, Impozit, Salariu net, data nasterii, data angajarii" );
            writer0.close();
//Parcurgerea BD si extragerea datelor iterate through the java resultset
            while (rs.next()) {
                Integer marcaTableViewPrint = rs.getInt( "marca" );
                String cnpTableView = rs.getString( "cnp" );
                String npTableView = rs.getString( "nume" );
                String salBrutTableView = rs.getString( "salariuBrut" );
                String dataAngajarii = rs.getString( "dataAngajarii" );
                String dataNasterii = rs.getString( "dataNasterii" );
                Integer vechimeaTableView = rs.getInt( "vechimea" );
                Integer cassTableView = rs.getInt( "cass" );
                Integer casTableView = rs.getInt( "cas" );
                Integer impozitTableView = rs.getInt( "impozit" );
                Integer salNetTableView = rs.getInt( "salNet" );

                //  System.out.format( "%s, %s, %s, %s, %s, %s, %s, %s,%s\n", marcaTableView, cnpTableView, npTableView, vechimeaTableView, salBrutTableView, cassTableView, casTableView, impozitTableView, salNetTableView );


//print - adaugarea datelor in fisier
                String datele = (Integer) marcaTableViewPrint + "," + (String) cnpTableView + "," + (String) npTableView + "," + (Integer) vechimeaTableView + "," + (String) salBrutTableView + "," + (Integer) cassTableView + "," + (Integer) casTableView + "," + (Integer) impozitTableView + "," + (Integer) salNetTableView + "," + dataNasterii + "," + dataAngajarii;
                BufferedWriter writer = new BufferedWriter( new FileWriter( "c:\\GynTaSoft\\Salarii\\TabelPersonalActiv-" + replaceNume2 + ".csv", true ) );
                writer.append( " \n" );
                writer.append( datele );
                writer.close();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void despre1 () {
        Despre.Text();
    }

    public void deletePersonTableAndSQL ( ActionEvent actionEvent ) {
        deletePersFromTable();
        return;
    }

    public void deletePersFromTable () {
        //delete person
        try (Statement statement = connection.createStatement()) {

            ObservableList<DatabaseConstants> selectedRows, allPeople;
            allPeople = tableViewBazaDate.getItems();
            selectedRows = tableViewBazaDate.getSelectionModel().getSelectedItems();
            for (DatabaseConstants person : selectedRows) {
                String marcaSelectata = String.format( person.getMarca().toString() ); // Asa se extrage o data dintr-un Observable list
                String numeSelectat = String.format( person.getNume().toString() );
                allPeople.remove( person );
                System.out.println( marcaSelectata );
                int changeStare = statement.executeUpdate( "UPDATE personal SET stare = 'plecat' WHERE marca='" + marcaSelectata + "'" );
                plecatLabel.setText( "Marca " + marcaSelectata + " cu numele:  " + numeSelectat + "  a plecat" );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
