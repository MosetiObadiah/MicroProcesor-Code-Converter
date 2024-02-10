package com.project1;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (NullPointerException | SecurityException | UnsupportedLookAndFeelException e) {
            System.out.println(e.getMessage());
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Window().setVisible(true));
    }
}
