package com.project1;

import java.util.ArrayList;
import java.util.Arrays;

public class Tokenizer {
    Window mainWindow;
    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<String> arithmeticAndLogicalOperators = new ArrayList<>();
    ArrayList<String> binaryEquivalent = new ArrayList<>();
    ArrayList<String> operationName = new ArrayList<>();
    ArrayList<String> opcodeLookup = new ArrayList<>();
    public Tokenizer(Window mainWindow) {
        this.mainWindow = mainWindow;
    }
    // TODO
    //   learn how to use String builder and know how to put the correct output for any expression
    //  support for multi value expressions
    //  add whitespace support to the application
    //  fix bugs
    public void startEngine(ArrayList<String> expression) {
        //System.out.println("Evaluation Started");
        //System.out.println(expression);

        opcodeLookup.add(("Move A,C\t000"));
        opcodeLookup.add(("Move B,C\t001"));
        opcodeLookup.add(("ADD\t\t010"));
        opcodeLookup.add(("SUB\t\t\t011"));
        opcodeLookup.add(("AND\t\t\t100"));
        opcodeLookup.add(("OR\t\t\t101"));
        opcodeLookup.add(("Load register A\t110"));
        opcodeLookup.add(("Load register B\t111"));

        for (String currentElement : expression) {
            String pattern = "[0-9]";
            if (currentElement.matches(pattern)) {
                numbers.add(currentElement);
                binaryEquivalent.add(convertToBinary(Integer.parseInt(currentElement)));
            } else {
                arithmeticAndLogicalOperators.add(currentElement);
            }
        }
        for (String operand : arithmeticAndLogicalOperators) {
            switch (operand) {
                case "&" -> operationName.add("AND");
                case "|" -> operationName.add("OR");
                case "+" -> operationName.add("ADD");
                case "-" -> operationName.add("SUBTRACT");
            }

        }

        System.out.println("Numbers: " + numbers);
        System.out.println("Binary equivalents: " + binaryEquivalent);
        System.out.println("Operators: " + arithmeticAndLogicalOperators);
        System.out.println("Operators names: " + operationName);

        mainWindow.updateTextArea(cleanFormattedOutput(opcodeLookup.get(6), opcodeLookup.get(7), numbers.get(0), binaryEquivalent.getFirst(), binaryEquivalent.get(1), numbers.get(1), operationName.getFirst()));
    }

    private String cleanFormattedOutput(String reg1, String reg2, String num1, String bin1, String bin2, String num2, String operationName) {
        return reg1 + "\n" +
                "with "+ num1 + "\t" + bin1 + "\n" +
                reg2 + "\n" +
                "with " + num2 + "\t" + bin2 + "\n" +
                operationName + "\n" +
                "contents of Register B from the\n" +
                "contents of Register C and\n" +
                "store the results in Register C";
    }

    private String convertToBinary(int num) {
        int[] binary = new int[3];
        int index = 0;
        while (num > 0) {
            binary[index++] = num % 2;
            num = num / 2;
        }
        return Arrays.toString(binary);
    }
}
