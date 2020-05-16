package JavaFX.controller;

import javaPac.DatabaseConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class Stage2ShowController<salBrut> implements Initializable {

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
        Connection connection = DriverManager.getConnection( DatabaseConstants.URL, DatabaseConstants.USER, DatabaseConstants.PASSWORD);
        Class.forName("com.mysql.cj.jdbc.Driver");
        addToSQL( connection );
        labelShow ();
        textFieldCleard();
        return null;
    }
//adaugare date in DB sql
      public void addToSQL ( Connection connection ){
            String sql = "INSERT INTO personal (cnp, nume, dataNasterii,    dataAngajarii,vechimea, salariuBrut,salNet,   cas,cass,impozit) VALUES (?,?,?,  ?,?,?,?,  ?,?,?)";
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

         tableView.getItems().addAll( newPerson );//pune pers in tabela..............................................................
                //pune pers in BD
         statement.executeUpdate("INSERT INTO PERSONAL(cnp,nume,dataNasterii,dataAngajarii, vechimea, salariuBrut,salNet, cas, cass, impozit) VALUES(' "+cnpField.getText()+"'," +
                 "'"+numeField.getText()+"','"+dataNasteriiField.getValue()+"','"+dataAngajariiField.getValue()+"','"+vechimeaInt+"','"+salariuBrutField.getText()+"','"+salNetInt+"','"+casInt+"','"+cassInt+"','"+impozitInt+"')");
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

       int vechimeaInt = Period.between( LocalDate.now(),dataAngajariiField.getValue()).getYears() ;
       String vechimea = Integer.toString( vechimeaInt );
       if ((salariuBrutField !=null)); this.labelVechimea.setText( vechimea );
         // labelVechimea.setText( Integer.toString( Period.between(LocalDate.now(),dataAgajariiField.getValue() ).getYears() ));

    }
//CLEAR TEXTfIELD:
    public void textFieldCleard()  {  cnpField.clear(); numeField.clear();  dataNasteriiField.getEditor().clear(); dataAngajariiField.getEditor().clear(); salariuBrutField.clear();   }// cnpField=STRING, dataNasteriiField= DataPicker
//DatabaseConstants
//    delete person
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
//         people.add( new DatabaseConstants( "  ","  ", LocalDate.of(   1, 0,0 ),  LocalDate.of( 0,0,0 ),"  " ));
         //people.add( new DatabaseConstants( "123565","Tit", LocalDate.of( 1990, MAY,31 ),  "2500" ));
        //people.add( new DatabaseConstants( "0","Oz",LocalDate.of( 20, 12, 1995 ),"2500"));
        return people;
        }

        //QUIT
        public void quitButton( ActionEvent actionEvent ){  System.exit( 0 );   }



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
