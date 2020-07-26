package JavaFX.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AccesSQL implements ActionListener {

    public static String USER;
    public static String PASSWORD;

    private static JPanel panel;
    private static JLabel text;
    private static JLabel userLabel;
    private static TextField userText;
    private static JLabel passLabel;
    //private  static JTextField passText;
    private static TextField passText;
    private static Button sqlLogButton;
    public static JButton exitButton;
    private static Label clik;
    private static Checkbox checkbox;
    private static Label checkExplain;
    //private  static JFrame frame;


    public static void sqlLogin () {

        panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize( 400, 300 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        frame.add( panel );
        panel.setLayout( null );
        panel.setLocation( 600, 600 );
        frame.setTitle( "Date logare MySQL" );

        text = new JLabel( "Nu inchideti aceasta fereastra" );
        text.setBounds( 60, 10, 300, 50 );
        panel.add( text );

        userLabel = new JLabel( "MySQL user:" );
        userLabel.setBounds( 30, 60, 120, 25 );
        panel.add( userLabel );

        userText = new TextField();
        userText.setBounds( 170, 60, 165, 25 );
        panel.add( userText );

        passLabel = new JLabel( "MySql Pasword:" );
        passLabel.setBounds( 30, 100, 120, 25 );
        panel.add( passLabel );

        passText = new TextField();
        passText.setBounds( 170, 100, 165, 25 );
        panel.add( passText );

        sqlLogButton = new Button( "logare" );
        sqlLogButton.setBounds( 140, 170, 105, 25 );
        sqlLogButton.addActionListener( new AccesSQL() );
        //sqlLogButton.addActionListener( new SampleConectionController().clik() );
        panel.add( sqlLogButton );

        clik = new Label( "Daca ai pus datele corecte poti apasa START" );
        clik.setBounds( 40, 205, 340, 30 );

        checkbox = new Checkbox();
        checkbox.setBounds( 125, 140, 20, 20 );
        panel.add( checkbox );

        checkExplain = new Label( "Retine datele" );
        checkExplain.setBounds( 145, 137, 105, 25 );
        panel.add( checkExplain );

        frame.setVisible( true );
    }




    // public void clearFields (){userText.remove();}

    @Override
    public void actionPerformed ( ActionEvent e ) {
        File mkDir= new File( "c:\\GynTaSoft\\Tmp\\" );
        if (USER != null && PASSWORD != null) {
            try {
                BufferedReader reader=new BufferedReader( new FileReader( "c:\\GynTaSoft\\Tmp\\tmp.txt" ));
                USER = reader.readLine();
                PASSWORD= reader.readLine();
                System.out.println(USER + PASSWORD);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
//            if else{
//        USER = userText.getText();
//        PASSWORD = passText.getText();
//        panel.add( clik );


            if (!mkDir.exists()) {
                System.out.println("creating directory: " + mkDir.getName());
                boolean result = false;

                try{
                    mkDir.mkdir();
                    result = true;
                }
                catch(SecurityException se){
                    //handle it
                }
                if(result) {
                    System.out.println("DIR created");
                }
            }
        }
//        if (USER != null && PASSWORD != null) {
//          //  if (checkbox.getState()){
//
//            try {
//                BufferedWriter writer = new BufferedWriter( new FileWriter( "c:\\GynTaSoft\\Tmp\\tmp.txt",false ) );
//                writer.append( USER +"\n");
//                writer.append( PASSWORD );
//                writer.close();
//               // writer.write( USER, PASSWORD );
//
//            } catch (IOException ioException) {                ioException.printStackTrace();            }
//        }
      }
    }

