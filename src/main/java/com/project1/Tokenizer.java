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

        opcodeLookup.add(("Move A,C\t000"));
        opcodeLookup.add(("Move B,C\t001"));
        opcodeLookup.add(("ADD\t010"));
        opcodeLookup.add(("SUB\t011"));
        opcodeLookup.add(("AND\t100"));
        opcodeLookup.add(("OR\t101"));
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
                case "&" -> operationName.add(opcodeLookup.get(4));
                case "|" -> operationName.add(opcodeLookup.get(5));
                case "+" -> operationName.add(opcodeLookup.get(2));
                case "-" -> operationName.add(opcodeLookup.get(3));
            }
        }

        System.out.println("Numbers: " + numbers);
        System.out.println("Binary equivalents: " + binaryEquivalent);
        System.out.println("Operators: " + arithmeticAndLogicalOperators);
        System.out.println("Operators names: " + operationName);

        String formattedOutput = cleanFormattedOutput(numbers, binaryEquivalent, operationName, opcodeLookup);
        // TODO fix bug the text area not clearing upon pressing re enter key
        mainWindow.updateTextArea(formattedOutput);

    }

    private String cleanFormattedOutput(ArrayList<String> numbers, ArrayList<String> binaryEquivalent, ArrayList<String> operationName, ArrayList<String> opcodeLookup) {
        StringBuilder concatOutput = new StringBuilder();

        if (numbers.size() < 2) {
            return "Error: Expression must contain at least two numbers";
        }

        // Load the first number into Register A
        concatOutput.append(opcodeLookup.get(6)).append("\n");
        concatOutput.append("with ").append(numbers.get(0)).append("\t").append(binaryEquivalent.get(0)).append("\n");

        // Load the second number into Register B
        concatOutput.append(opcodeLookup.get(7)).append("\n");
        concatOutput.append("with ").append(numbers.get(1)).append("\t").append(binaryEquivalent.get(1)).append("\n");

        // Perform the first operation between Register A and Register B
        // TODO use the correct syntax according to the operation {+,&,| with, - from}
        concatOutput.append(operationName.getFirst()).append("\n");
        concatOutput.append("the contents of Register B from the\n");
        concatOutput.append("contents of Register A and\n");
        concatOutput.append("store the results in Register C").append("\n");

        // Move the result from Register C to Register A (only perform this move operation once)
        if(numbers.size() > 2) {
            concatOutput.append(opcodeLookup.getFirst()).append("\n");
        }

        // If there's a third number available, continue with additional operations
        for (int i = 2; i < numbers.size(); i++) {
            // Load the next number into Register B
            concatOutput.append(opcodeLookup.get(7)).append("\n");
            concatOutput.append("with ").append(numbers.get(i)).append("\t").append(binaryEquivalent.get(i)).append("\n");

            // Perform the next operation between Register A and Register B
            concatOutput.append(operationName.get(i - 1)).append("\n");
            concatOutput.append("the contents of Register B from the\n");
            concatOutput.append("contents of Register A and\n");
            concatOutput.append("store the results in Register C").append("\n");
        }
        return concatOutput.toString();
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

    public void clearArrayLists() {
        numbers.clear();
        arithmeticAndLogicalOperators.clear();
        binaryEquivalent.clear();
        operationName.clear();
    }
}
