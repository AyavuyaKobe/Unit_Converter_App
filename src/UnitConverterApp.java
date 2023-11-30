import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitConverterApp extends JFrame {

    private JComboBox<String> fromUnitCombo, toUnitCombo;
    private JTextField quantityField;
    private JLabel resultLabel;

    public UnitConverterApp() {
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Create components
        String[] units = {"Feet", "Meters", "Pounds", "Kilograms", "Fahrenheit", "Celsius"};
        fromUnitCombo = new JComboBox<>(units);
        toUnitCombo = new JComboBox<>(units);
        quantityField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the frame
        addLabel("From Unit:", gbc, 0, 0);
        addComponent(fromUnitCombo, gbc, 1, 0);
        addLabel("To Unit:", gbc, 0, 1);
        addComponent(toUnitCombo, gbc, 1, 1);
        addLabel("Quantity:", gbc, 0, 2);
        addComponent(quantityField, gbc, 1, 2);
        addComponent(convertButton, gbc, 1, 3);
        addComponent(resultLabel, gbc, 1, 4);

        // Set up event handling for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });
    }

    private void addLabel(String text, GridBagConstraints gbc, int gridx, int gridy) {
        JLabel label = new JLabel(text);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        add(label, gbc);
    }

    private void addComponent(Component component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        add(component, gbc);
    }

    private void convert() {
        try {
            double quantity = Double.parseDouble(quantityField.getText());
            String fromUnit = fromUnitCombo.getSelectedItem().toString();
            String toUnit = toUnitCombo.getSelectedItem().toString();
            double result;

            switch (fromUnit) {
                case "Feet":
                    result = convertFeet(quantity, toUnit);
                    break;
                case "Meters":
                    result = convertMeters(quantity, toUnit);
                    break;
                case "Pounds":
                    result = convertPounds(quantity, toUnit);
                    break;
                case "Kilograms":
                    result = convertKgs(quantity, toUnit);
                    break;
                case "Fahrenheit":
                    result = convertFahrenheit(quantity, toUnit);
                    break;
                case "Celsius":
                    result = convertCelsius(quantity, toUnit);
                    break;
                default:
                    result = Double.NaN;
            }

            resultLabel.setText("Result: " + (Double.isNaN(result) ? "Invalid conversion" : String.format("%.2f %s", result, toUnit)));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private double convertFeet(double quantity, String toUnit) {
        if (toUnit.equals("Meters"))
            return quantity * 0.3048;
        else
            return Double.NaN;
    }

    private double convertMeters(double quantity, String toUnit) {
        if (toUnit.equals("Feet"))
            return quantity / 0.3048;
        else
            return Double.NaN;
    }

    private double convertPounds(double quantity, String toUnit) {
        if (toUnit.equals("Kilograms"))
            return quantity * 0.453592;
        else
            return Double.NaN;
    }

    private double convertKgs(double quantity, String toUnit) {
        if (toUnit.equals("Pounds"))
            return quantity / 0.453592;
        else
            return Double.NaN;
    }

    private double convertFahrenheit(double quantity, String toUnit) {
        if (toUnit.equals("Celsius"))
            return (quantity - 32) * 5 / 9;
        else
            return Double.NaN;
    }

    private double convertCelsius(double quantity, String toUnit) {
        if (toUnit.equals("Fahrenheit"))
            return (quantity * 9 / 5) + 32;
        else
            return Double.NaN;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UnitConverterApp().setVisible(true);
            }
        });
    }
}