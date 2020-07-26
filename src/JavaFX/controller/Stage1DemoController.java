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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Stage1DemoController<personData, personDataApp> implements Initializable {
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
    @FXML    private TableColumn<DatabaseConstants, String> salBrutTableView ;
    @FXML    private TableColumn<DatabaseConstants, String> cassTableView;
    @FXML    private TableColumn<DatabaseConstants, String> casTableView;
    @FXML    private TableColumn<DatabaseConstants, String> impozitTableView;
    @FXML    private TableColumn<DatabaseConstants, String> salNetTableView;
    @FXML    private Label plecatLabel;
    @FXML    private Label existNr;
    @FXML    private Label totalSBLabel;
    @FXML    private Label totalCASSLabel;
    @FXML    private Label totalCASLabel;
    @FXML    private Label totalImpLabel;
    @FXML    private Label totalSNLabel;

    private URL location;
    private ResourceBundle resources;



    public void goToStage2 ( ActionEvent actionEvent ) throws IOException {
        Parent tableView2 = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage2IntroDemo.fxml" ) );
        Scene tabeleViewScene2 = new Scene( tableView2 );
        //This line gets the stage inforation
        Stage window2 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window2.setScene( tabeleViewScene2 );
        window2.show();
    }
    public void goToStage3Deleted (ActionEvent actionEvent) throws IOException {
        Parent tableView3 =FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage3DeletedDemo.fxml" ) );
        Scene tableViewScene3 = new Scene( tableView3 );

        Stage window3 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window3.setScene( tableViewScene3 );
        window3.show();
    }

    public void quitButton ( ActionEvent actionEvent ) {
        System.exit( 0 );
    }
    public void despre(){Despre.Text();}


    //deschidere   Folder
    public void openFolder (ActionEvent actionEvent){
        Desktop desktop= null;
        try {
            desktop.getDesktop().open( new File( "c:\\GynTaSoft\\Salarii" ) ); ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ObservableList<DatabaseConstants> getPeople () {
        ObservableList<DatabaseConstants> people = FXCollections.observableArrayList();
        people.add( new DatabaseConstants( 4, "2591201415265", "Cristi Petreanu", 6,"10000",2500,1000,650,5850 ) );
        people.add( new DatabaseConstants( 1, "1589201415265", "Sorin Gurita", 4,"10000",2500,1000,650,5850 ) );
        return people;}

//    public int sumSB(){
//        int totalSBLabel = 0;
//       // Callback<TableView<DatabaseConstants>, TableRow<DatabaseConstants>> rowsCount = tableViewBazaDate.getRowFactory();
//        ObservableList<DatabaseConstants> rows = tableViewBazaDate.getItems();
//        int rowsCount = Integer.parseInt( String.valueOf(rows) );
//        for(int i = 0; i<rowsCount ; i++){
//            totalSBLabel = totalSBLabel+Integer.parseInt( JTable.getDefaultLocale().toString() );
//        }
//     return totalSBLabel;
//        //TextField.setText(Integer.toString(totalSBLabel));
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
        tableViewBazaDate.setItems( getPeople() );


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
        System.out.println( replaceNume );

        String datele = "marca,cnp ,nume si prenume,vechime,salBrut,CAS, CASS,impozit,salNet";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new FileWriter( "c:\\GynTaSoft\\Salarii\\DemoTabelPersonalActiv-" + replaceNume + ".csv", true ) );
             writer.append( datele );
            writer.append( " \n" );
            writer.write( "4, 2591201415265, Cristi Petreanu, 6,10000,2500,1000,650,5850" );
            writer.append( " \n" );
            writer.write( "1, 1589201415265, Sorin Gurita, 4,10000,2500,1000,650,5850" );
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//        public void sum (){
//            TabableView <> tableViewBazadate
//        //int totatSBLabel = 0 ;
//        for (DatabaseConstants value : tableViewBazaDate.getItems()) {
//        totalSBLabel = totalSBLabel +  value;}}

    public void deletePersonTableAndSQL (ActionEvent actionEvent){
        deletePersFromTable();
        return;
    }
    public void deletePersFromTable(){
        //delete person
        }
        }



