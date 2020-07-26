package JavaFX.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Despre implements  ActionListener{

    private  static JPanel panel;
    private static TextArea text;
    private static JFrame frame;

    public static void  Text ()    {

        panel =new JPanel();
        frame = new JFrame();
        frame.setSize( 500, 300 );
        //frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        frame.add( panel );
        panel.setLayout(  null);
        panel.setLocation( 600,600 );
        frame.setTitle( "Despre" );

        text = new TextArea("    Program de evidenta a salariatilor  \n" +
                "  Contact:   \n " +
                "  e-mail: sorin.ginara@imobilizari.ro   \n " +
                "  site: http://programpersonal.imobilizari.ro/  \n " +
                "  Tel: 0771.55.22.84  \n " +
                "\n" +
                "  Pentru rularea acestui program aveti nevoie sa instalati MySQL \n" +
                "de la adresa https://dev.mysql.com/downloads/shell/  \n");
        text.setBounds( 50, 23,400,180 );
        panel.add( text );

        frame.setVisible( true );
        text.setVisible( true );
    }


    @Override
    public void actionPerformed ( ActionEvent e ) {

    }}
