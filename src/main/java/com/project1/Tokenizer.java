package com.project1;

import java.util.ArrayList;

public class Tokenizer {
    Window mainWindow;
    public Tokenizer(Window mainWindow) {
        this.mainWindow = mainWindow;
    }
    public void startEngine(ArrayList<String> expression) {
        //System.out.println("Evaluation Started");
        //System.out.println(expression);

        for (String operand: expression) {
            if(operand.equals("0")) {
                //System.out.println("000 sent");
                mainWindow.updateTextArea("000");

            } else if (operand.equals("1")) {
                mainWindow.updateTextArea("001");

            } else if (operand.equals("2")) {
                mainWindow.updateTextArea("010");

            } else if (operand.equals("3")) {
                mainWindow.updateTextArea("011");

            } else if (operand.equals("4")) {
                mainWindow.updateTextArea("100");

            } else if (operand.equals("5")) {
                mainWindow.updateTextArea("101");

            } else if (operand.equals("6")) {
                mainWindow.updateTextArea("110");

            } else if (operand.equals("7")) {
                mainWindow.updateTextArea("111");

            } else if (operand.equals("&")) {

            } else if (operand.equals("|")) {

            } else if (operand.equals("+")) {

            } else if (operand.equals("-")) {

            }
        }

    }
}
