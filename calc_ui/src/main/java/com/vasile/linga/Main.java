package com.vasile.linga;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {

       JFrame frame  = new JFrame("Expression Calculator");
       frame.setSize(400,200);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel panel1 = new JPanel();
       panel1.setBackground(Color.black);
       panel1.setLayout(new GridBagLayout());

       GridBagConstraints constraints = new GridBagConstraints();
       constraints.fill = GridBagConstraints.HORIZONTAL;
       constraints.gridx = 0;
       constraints.gridy = 0;
       constraints.gridwidth = 2;
       constraints.ipadx = 70;
       constraints.ipady = 5;


        JLabel promptLabel = new JLabel("Introdu expresia");
        promptLabel.setFont(new Font("Arial",Font.BOLD,11));
        promptLabel.setForeground(Color.WHITE);
        panel1.add(promptLabel,constraints);

        JTextField expressionField = new JTextField();
        expressionField.setFont(new Font("Arial",Font.PLAIN,16));
        constraints.gridy = 1;

        panel1.add(expressionField,constraints);

        JLabel resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial",Font.BOLD,13));
        resultLabel.setForeground(Color.WHITE);
        constraints.gridy = 3;
        panel1.add(resultLabel,constraints);

        JButton calculateButton = getButton(expressionField, resultLabel);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel1.add(calculateButton,constraints);
        frame.add(panel1);
        frame.setVisible(true);


    }


    private static JButton getButton(JTextField expressionField, JLabel resultLabel) {
        JButton calculateButton = new JButton("Calculate");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   int result = Algorithm.DoCalculation(expressionField.getText());
                   resultLabel.setText("Result:"+result);
                } catch (Exception ex) {
                    resultLabel.setText("Error:"+ ex.getMessage());
                    Utilites.ClearStacks(Algorithm.operatori,Algorithm.operanzi);
                }
            }
        });
        return calculateButton;
    }
}