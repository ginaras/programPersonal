package javaPac;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.Period;

public class DatabaseConstants {

//    public static String USER; //= "root";
//    public static  String PASSWORD;// = "poiulkjh123";
    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS cursuri";
    //public static final String DATABASE = "cursuri";
    public static String URL = String.format("jdbc:mysql://localhost:3306/%s?useLegacyDateTimeCode=false&serverTimezone=GMT", "cursuri");

    public static final String UserWeb= "imobiliz_sorin";
    public static final String PASSWORDWEB = "PoiuLkjh123";
    public static final String URLWEB = String.format("jdbc:mysql://programpersonal.imobilizari.ro:3306/%s?useLegacyDateTimeCode=false&serverTimezone=America/New_York", "imobiliz_cursuri");
    //public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS personal ( cnp VARCHAR(13), nume VARCHAR(30), dataNasterii DATE,salariuBrut VARCHAR(6))";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS personal (marca INT(7) AUTO_INCREMENT PRIMARY KEY, cnp VARCHAR(14),nume VARCHAR(300), dataNasterii DATE, dataAngajarii DATE, vechimea INT(2),salariuBrut VARCHAR(10),salNet INTEGER(6),cas INTEGER(6), cass INTEGER(6), impozit INTEGER(5), stare VARCHAR(10)";
    public static final String USE_TABLE = "USE personal";

    private  Integer marca;
    private SimpleStringProperty  cnp;
    private SimpleStringProperty nume;
    private LocalDate dataNasterii;
    private LocalDate dataAngajarii;
    private Integer vechimea;
    private SimpleStringProperty salariuBrut;
    private Integer cas;
    private Integer cass;
    private Integer impozit;
    private Integer salNet;
    private SimpleStringProperty stare;


    // constructor pentru adaugarea in tabela de la Stage1
    public DatabaseConstants ( int marca, String cnp, String nume, int vechimea, String salariuBrut, int cass, int cas, int impozit, int salNet ) {
        this.marca= marca;
        this.cnp =new SimpleStringProperty(cnp);
        this.nume=new SimpleStringProperty(nume);
        this.salariuBrut= new SimpleStringProperty( salariuBrut );
        this.cass = cass;
        this.vechimea = vechimea;
        this.cas = cas;
        this.impozit = impozit;
        this.salNet = salNet;
   }
//constr pe Stage3demo
    public DatabaseConstants (int marca, String cnp, String nume, int vechimea){

    }

    public Integer getMarca () {       return marca;    }
    public String getCnp () { return cnp.get(); }
    public String getNume () { return nume.get(); }
    public LocalDate getDataNasterii () { return dataNasterii;   }
    public LocalDate getDataAngajarii () { return dataAngajarii;   }
    public Integer getVechimea () {  return vechimea;  }
    public String getSalariuBrut () { return salariuBrut.get();  }
    public Integer getCas () {  return cas;  }
    public Integer getCass () {    return cass;  }
    public Integer getImpozit () {    return impozit;   }
    public Integer getSalNet () {       return salNet;    }
    public String getStare () { return stare.get();  }

    public SimpleStringProperty numeProperty () {  return nume;  }
    public SimpleStringProperty cnpProperty () { return cnp;  }
    public SimpleStringProperty salariuBrutProperty () {  return salariuBrut;  }
    public SimpleStringProperty stareProperty () { return stare;  }

    public void setMarca ( Integer marca ) {        this.marca = marca;    }
    public void setCnp ( String cnp ) { this.cnp.set( cnp ); }
    public void setNume ( String nume ) {  this.nume.set( nume );  }
    public void setDataNasterii ( LocalDate dataNasterii) { this.dataNasterii = dataNasterii; } //Schimbat LocalDate cu String
    public void setDataAngajarii ( LocalDate dataAngajarii ) { this.dataAngajarii = dataAngajarii; } //Schimbat LocalDate cu String
    public void setVechimea ( Integer vechimea ) {   this.vechimea = vechimea;    }
    public void setSalariuBrut ( String salariuBrut ) {this.salariuBrut.set( salariuBrut );  }
    public void setCas ( Integer cas ) {   this.cas = cas;    }
    public void setCass ( Integer cass ) {       this.cass = cass;    }
    public void setImpozit ( Integer impozit ) {        this.impozit = impozit;    }
    public void setSalNet ( Integer salNet ) {        this.salNet = salNet;    }
    public void setStare ( String stare ) {  this.stare.set( stare );  }


    public int getAge(){return Period.between( dataAngajarii, LocalDate.now() ).getYears();    }

 //constructor pentru adaugarea in tabela de la Stage2
    public DatabaseConstants ( String cnp, String nume, LocalDate dataNasterii, LocalDate dataAngajarii, String salariuBrut ) {
            this.cnp = new SimpleStringProperty(cnp);
            this.nume = new SimpleStringProperty( nume );
            this.dataNasterii = dataNasterii;
            this.dataAngajarii = dataAngajarii;
            this.salariuBrut= new SimpleStringProperty(salariuBrut);
            }

//    public   DatabaseConstants ( Integer marca, String cnp, String nume, Integer vechimea, String salariuBrut, Integer cass, Integer cas, Integer impozit, Integer salNet ){
//         this.marca = new Integer(marca);
//        this.cnp = new SimpleStringProperty(cnp);
//        this.nume = new SimpleStringProperty( nume );
//        this.vechimea= new Integer( vechimea );
//        this.salariuBrut= new SimpleStringProperty(salariuBrut);
//        this.cass=new Integer( cass );
//        this.cas=new Integer( cas );
//        this.impozit=new Integer( impozit );
//        this.salNet=new Integer( salNet );
//
//    }


}

    //    private Persoana() { throw new IllegalAccessError("utility class, do not instantiate");    }

