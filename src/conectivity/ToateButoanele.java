package conectivity;

import javaPac.DatabaseConstants;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javaPac.MainJDBC.*;
import static jdk.nashorn.internal.objects.Global.print;
import static javaPac.DatabaseConstants.*;
import static jdk.nashorn.internal.objects.Global.setDate;

public class ToateButoanele implements Initializable {

    @FXML
    public Button ConnectButton;
    @FXML
    public Menu menuBar;


    public Connection getConection () {


        try (Connection connection = DriverManager.getConnection( DatabaseConstants.URL, DatabaseConstants.USER, DatabaseConstants.PASSWORD )) {
            // vreau sa inregistrez Driverul

            createTable( connection );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

//       insertBaseData( connection );
//        print( connection );
//        updateData( connection );
//        print( connection );
//        DeleteData( connection );
//        print( connection );
//        addDeletedData( connection );
        return null;
    }

    public static void addDeletedData ( Connection connection ) {
        String sql = "INSERT INTO products (id, code, name, quantity, price) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement( sql )) {
            //setam autocomit ca false ca sa putem gestiona noi tranzactia
            connection.setAutoCommit( false );

            statement.setInt( 1, 101 );
            statement.setString( 2, "PEC" );
            statement.setString( 3, "pencil HB" );
            statement.setInt( 4, 2 );
            statement.setBigDecimal( 5, new BigDecimal( 3.333 ) );

            //connection.commit();
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void DeleteData ( Connection connection ) {
        String sqlInitial = "INSERT INTO products (id, code, name, quantity, price) VALUES (\"1001\",'PEC', 'Pencil HB, 2, 3.333)";

        try (Statement statement = connection.createStatement()) {
            System.out.println( "Executing Delete" );
            int delete = statement.executeUpdate( "DELETE FROM products WHERE id = '1001'" );

            System.out.println( delete );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateData ( Connection connection ) {
        try (Statement statement = connection.createStatement()) {
            System.out.println( "Executing Update" );
            int update = statement.executeUpdate( "UPDATE products SET name='Sera was here' WHERE id = '1001'" );
            System.out.println( update );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable ( Connection connection ) {
        try (Statement statement = connection.createStatement()) {
            int created = statement.executeUpdate( DatabaseConstants.CREATE_TABLE );
            System.out.println( String.format( "Altered %s rows", created ) );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBaseData ( Connection connection ) {
        try (Statement statement = connection.createStatement()) {
            int insert = statement.executeUpdate( "INSERT INTO products (code, name, quantity, price) VALUES ('PEC', 'Pencil HB', '2', '3.333'), ('PE1, 'Pencil HB-2', '3', '4.222')" );
            System.out.println( insert );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void print ( Connection connection ) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery( "SELECT * FROM products" )) {
            System.out.println( "id  |  code  |  name  |  quantity  |  price" );
            while (resultSet.next()) {
                int id = resultSet.getInt( "id" );
                String code = resultSet.getString( "code" );
                String name = resultSet.getString( "name" );
                int quantity = resultSet.getInt( "quantity" );
                BigDecimal price = resultSet.getBigDecimal( "price" );
                System.out.println( String.format( " %s  | %s  | %s  | %s |  %.2f", id, code, name, quantity, price ) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize ( java.net.URL location, ResourceBundle resources ) {

    }
}