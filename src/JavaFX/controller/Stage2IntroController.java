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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class Stage2IntroController<salBrut> implements Initializable {

    @FXML private TableView<DatabaseConstants> tableView;
    @FXML private TableColumn<DatabaseConstants, String> marca;
    @FXML private TableColumn<DatabaseConstants, String> cnp;
    @FXML private TableColumn<DatabaseConstants, String> nume;
    @FXML private TableColumn<DatabaseConstants, LocalDate> dataNasterii;
    @FXML private TableColumn<DatabaseConstants, LocalDate> dataAngajarii;
        @FXML private TableColumn<DatabaseConstants,String>salariuBrut;

    @ FXML private Label pAdaugata;
    @ FXML private Label labelNP;
    @FXML private  Label labelVechimea;
    @ FXML private Label labelSB;
    @ FXML private Label labelCASS;
    @ FXML private Label labelCAS;
    @ FXML private Label labelImpozit;
    @ FXML private Label labelSN;


    @FXML private TextField cnpField;
    @FXML private TextField numeField;
    @FXML private DatePicker dataNasteriiField;
    @FXML private DatePicker dataAngajariiField;
    @FXML private TextField salariuBrutField;

    @FXML private Button addPerson;
    @FXML private Button delPerson;
    @FXML private Button detailedButton;


    public void userClickOnTable(){
//        this.detailedButton.setDisable( false );
        this.delPerson.setDisable( false );
    }
    public void newUserClickOnTable () {
        String control1 =  cnpField.getText() ;
        String control2 =  numeField.getText() ;
        if (control1 != null && control2 != null) {
            this.addPerson.setDisable( false );
        }
    }

//Conectare si adaugare in BD
    public Connection getConectionPersAdd () throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, AccesSQL.USER, AccesSQL.PASSWORD);
        Class.forName("com.mysql.cj.jdbc.Driver");
        addToSQL( connection );
        labelShow ();
        textFieldCleard();
        new LimitedTextFields();/////////////////////////////////////////////////////////////////
        return null;
    }
//adaugare date in DB sql
      public void addToSQL ( Connection connection ){
            String sql = "INSERT INTO personal (cnp, nume, dataNasterii,    dataAngajarii,vechimea, salariuBrut,salNet,   cas,cass,impozit,stare) VALUES (?,?,?,  ?,?,?,?,  ?,?,?,angajat)";
            try (PreparedStatement statement = connection.prepareStatement(sql) ){
        DatabaseConstants newPerson = new DatabaseConstants (
                cnpField.getText(),
                numeField.getText(),
                dataNasteriiField.getValue(),
                dataAngajariiField.getValue(),
                salariuBrutField.getText());

                int vechimeaInt = Period.between( dataAngajariiField.getValue(),LocalDate.now() ).getYears();

                int salBrut = parseInt( salariuBrutField.getText() );

                int cassInt = (int) Math.round( parseInt( String.valueOf( salariuBrutField.getText() ) )*0.10 );
                String CASS = Integer.toString( cassInt);
                if (salariuBrutField != null); this.labelCASS.setText( CASS);

                int casInt = (int) Math.round( parseInt( String.valueOf( salariuBrutField.getText() ) )*0.25 );
                String CAS =   Integer.toString(casInt );
                if (salariuBrutField != null); this.labelCAS.setText( CAS);

                int impozitInt = (int) Math.round( (salBrut -casInt-cassInt)*0.1);
                String IMPOZIT = Integer.toString( impozitInt );
                if (salariuBrutField != null); this.labelImpozit.setText( IMPOZIT);

                int salNetInt = salBrut-casInt-cassInt-impozitInt;
                String SALARIUNET = Integer.toString( salNetInt );
                if (salariuBrutField != null); this.labelSN.setText(SALARIUNET);

                String angajat = "angajat";

         tableView.getItems().addAll( newPerson );//pune pers in tabela..............................................................
                //pune pers in BD
         statement.executeUpdate("INSERT INTO PERSONAL(cnp,nume,dataNasterii,dataAngajarii, vechimea, salariuBrut,salNet, cas, cass, impozit,stare ) VALUES(' "+cnpField.getText()+"'," +
                 "'"+numeField.getText()+"','"+dataNasteriiField.getValue()+"','"+dataAngajariiField.getValue()+"','"+vechimeaInt+"','"+salariuBrutField.getText()+"','"+salNetInt+"','"+casInt+"','"+cassInt+"','"+impozitInt+"','"+angajat+"')");
                  } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
      }
//preluarea datelor noi si punerea  in label-urile de sub tabel
      public void labelShow (){
        if (getPeople() != null) ;               this.pAdaugata.setText( "Persoana adaguta cu succes!" );
        if (numeField.getText() != null);        this.labelNP.setText( numeField.getText());

        int salBrut = parseInt( salariuBrutField.getText());
        String salB =Integer.toString( salBrut );

        if (salariuBrutField.getText() != null);  this.labelSB.setText(salariuBrutField.getText());

        int cassInt = (int) Math.round( parseInt( String.valueOf( salariuBrutField.getText() ) )*0.10 );
        String CASS = Integer.toString( cassInt);
        if (salariuBrutField != null); this.labelCASS.setText( CASS);

          int casInt = (int) Math.round( parseInt( String.valueOf( salariuBrutField.getText() ) )*0.25 );
          String CAS =   Integer.toString(casInt );
          if (salariuBrutField != null); this.labelCAS.setText( CAS);

        int impozitInt = (int) Math.round( (salBrut -casInt-cassInt)*0.1);
        String IMPOZIT = Integer.toString( impozitInt );
        if (salariuBrutField != null); this.labelImpozit.setText( IMPOZIT);

          int salNet = salBrut-casInt-cassInt-impozitInt;
          String SALARIUNET = Integer.toString( salNet );
          if (salariuBrutField != null); this.labelSN.setText(SALARIUNET);

       int vechimeaInt = Period.between( dataAngajariiField.getValue(),LocalDate.now()).getYears() ;
       String vechimea = Integer.toString( vechimeaInt );
       if ((salariuBrutField !=null)); this.labelVechimea.setText( vechimea );
         // labelVechimea.setText( Integer.toString( Period.between(LocalDate.now(),dataAgajariiField.getValue() ).getYears() ));

    }
//CLEAR TEXTfIELD:
    public void textFieldCleard()  {  cnpField.clear(); numeField.clear();  dataNasteriiField.getEditor().clear(); dataAngajariiField.getEditor().clear(); salariuBrutField.clear();   }// cnpField=STRING, dataNasteriiField= DataPicker

        public void deleteButton(){
        ObservableList<DatabaseConstants> selectedRows, allPeople;
        allPeople=tableView.getItems();
        selectedRows =tableView.getSelectionModel().getSelectedItems();
        for (DatabaseConstants person : selectedRows){
            allPeople.remove( person );
            }
        }
//load data in table view
        public ObservableList<DatabaseConstants> getPeople (){       ObservableList <DatabaseConstants> people = FXCollections.observableArrayList() ;
        return people;
        }
    public void goToStage1 ( ActionEvent actionEvent ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage1.fxml" ));
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window =  (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }
    public void goToStage3Deleted (ActionEvent actionEvent) throws IOException {
        Parent tableView3 =FXMLLoader.load( getClass().getClassLoader().getResource( "JavaFX/Stage3Deleted.fxml" ) );
        Scene tableViewScene3 = new Scene( tableView3 );

        Stage window3 = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window3.setScene( tableViewScene3 );
        window3.show();
    }

        //QUIT
        public void quitButton( ActionEvent actionEvent ){  System.exit( 0 );   }
        public void despre(){Despre.Text();}



        @Override
        public void initialize ( URL location, ResourceBundle resources ) {
        //set up the column & populate the list
        //marca.setCellValueFactory( new PropertyValueFactory<>( "marca" ) );
        cnp.setCellValueFactory( new PropertyValueFactory<>( "cnp" ) );
        nume.setCellValueFactory( new PropertyValueFactory<>( "nume" ) );
        dataNasterii.setCellValueFactory( new PropertyValueFactory<>( "dataNasterii" ) );
        dataAngajarii.setCellValueFactory( new PropertyValueFactory<>( "dataAngajarii" ) );
        salariuBrut.setCellValueFactory( new PropertyValueFactory<>( "salariuBrut" ) );
        tableView.setItems( getPeople() );

        //desable a button
//        this.detailedButton.setDisable( true );
        this.addPerson.setDisable( true );
        this.delPerson.setDisable( true );


        }

    }
