import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorApp {
    private JFrame frame;
    private JTextField display;
    private double result;
    private String operator;
    private boolean startNewNumber;

    public CalculatorApp() {
        frame = new JFrame("Calculator");
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(200, 50));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));  // Changed grid to 5 rows to fit the Clear button

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"  
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        result = 0;
        operator = "=";
        startNewNumber = true;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789.".contains(command)) {
                if (startNewNumber) {
                    display.setText("");
                    startNewNumber = false;
                }
                display.setText(display.getText() + command);
            } else if (command.equals("C")) { 
                display.setText("");
                result = 0;
                operator = "=";
                startNewNumber = true;
            } else {
                if (startNewNumber) {
                    if (command.equals("-")) {
                        display.setText(command);
                        startNewNumber = false;
                    } else {
                        operator = command;
                    }
                } else {
                    double x = Double.parseDouble(display.getText());
                    calculate(x);
                    operator = command;
                    startNewNumber = true;
                }
            }
        }

        private void calculate(double n) {
            switch (operator) {
                case "+":
                    result += n;
                    break;
                case "-":
                    result -= n;
                    break;
                case "*":
                    result *= n;
                    break;
                case "/":
                    result /= n;
                    break;
                case "=":
                    result = n;
                    break;
            }
            display.setText("" + result);
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
