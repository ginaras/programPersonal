package javaPac;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.Period;

public class DatabaseConstants {

    public static final String USER = "root";
    public static final String PASSWORD = "poiulkjh123";
    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS cursuri";
    //public static final String DATABASE = "cursuri";
    public static final String URL = String.format("jdbc:mysql://localhost:3306/%s?useLegacyDateTimeCode=false&serverTimezone=America/New_York", "cursuri");
    //public static final String URL2 = String.format("jdbc:mysql://localhost:3306/%s?useLegacyDateTimeCode=false&serverTimezone=America/New_York", "cursuri");
    //public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS personal ( cnp VARCHAR(13), nume VARCHAR(30), dataNasterii DATE,salariuBrut VARCHAR(6))";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS personal (marca INT(7) AUTO_INCREMENT PRIMARY KEY, cnp VARCHAR(13),nume VARCHAR(300), dataNasterii DATE, dataAngajarii DATE, vechimea INT(2),salariuBrut VARCHAR(10),salNet INTEGER(6),cas INTEGER(6), cass INTEGER(6), impozit INTEGER(5))";
    public static final String USE_TABLE = "USE personal";

    private  Integer marca;
    private SimpleStringProperty  cnp;
    private SimpleStringProperty nume;
    private LocalDate dataNasterii;
    private LocalDate dataAgajarii;
    private Integer vechimea;
    private SimpleStringProperty salariuBrut;
    private Integer cas;
    private Integer cass;
    private Integer impozit;
    private Integer salNet;

    public Integer getMarca () {       return marca;    }
    public String getCnp () { return cnp.get(); }
    public String getNume () { return nume.get(); }
    public LocalDate getDataNasterii () { return dataNasterii;   }
    public LocalDate dataAgajarii () { return dataAgajarii;   }
    public Integer getVechimea () {  return vechimea;  }
    public String getSalariuBrut () { return salariuBrut.get();  }
    public Integer getCas () {  return cas;  }
    public Integer getCass () {    return cass;  }
    public Integer getImpozit () {    return impozit;   }
    public Integer getSalNet () {       return salNet;    }

    public SimpleStringProperty numeProperty () {  return nume;  }
    public SimpleStringProperty cnpProperty () { return cnp;  }
    public SimpleStringProperty salariuBrutProperty () {  return salariuBrut;  }

    public void setMarca ( Integer marca ) {        this.marca = marca;    }
    public void setCnp ( String cnp ) { this.cnp.set( cnp ); }
    public void setNume ( String nume ) {  this.nume.set( nume );  }
    public void setDataNasterii ( LocalDate dataNasterii) { this.dataNasterii = dataNasterii; } //Schimbat LocalDate cu String
    public void setDataAgajarii ( LocalDate dataAgajarii ) { this.dataAgajarii = dataAgajarii; } //Schimbat LocalDate cu String
    public void setVechimea ( Integer vechimea ) {   this.vechimea = vechimea;    }
    public void setSalariuBrut ( String salariuBrut ) {this.salariuBrut.set( salariuBrut );  }
    public void setCas ( Integer cas ) {   this.cas = cas;    }
    public void setCass ( Integer cass ) {       this.cass = cass;    }
    public void setImpozit ( Integer impozit ) {        this.impozit = impozit;    }
    public void setSalNet ( Integer salNet ) {        this.salNet = salNet;    }

    public int getAge(){return Period.between( dataAgajarii, LocalDate.now() ).getYears();    }

    public DatabaseConstants ( String cnp, String nume, LocalDate dataNasterii, LocalDate dataAgajarii, String salariuBrut) {
           // this.marca = new Integer(marca);
            this.cnp = new SimpleStringProperty(cnp);
            this.nume = new SimpleStringProperty( nume );
            this.dataNasterii = dataNasterii;
            this.dataAgajarii = dataAgajarii;
            this.salariuBrut= new SimpleStringProperty(salariuBrut);

        }



}

    //    private Persoana() { throw new IllegalAccessError("utility class, do not instantiate");    }

