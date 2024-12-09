import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

abstract class Transport {
    String mode;

    public Transport(String mode) {
        this.mode = mode;
    }

    abstract double calculateEmissions(double distance);
    abstract double calculateTravelTime(double distance);
    abstract int calculateEcoScore(double distance);
}

class Bike extends Transport {
    public Bike() {
        super("Bike");
    }

    @Override
    double calculateEmissions(double distance) {
        return 0;
    }

    @Override
    double calculateTravelTime(double distance) {
        return (distance / 15) * 60;
    }

    @Override
    int calculateEcoScore(double distance) {
        return 100;
    }
}

class Jeepney extends Transport {
    public Jeepney() {
        super("Jeepney");
    }

    @Override
    double calculateEmissions(double distance) {
        return 17 * distance / 1000;
    }

    @Override
    double calculateTravelTime(double distance) {
        return (distance / 16) * 60;
    }

    @Override
    int calculateEcoScore(double distance) {
        return (int) (100 - calculateEmissions(distance) * 200);
    }
}

class Car extends Transport {
    public Car() {
        super("Car");
    }

    @Override
    double calculateEmissions(double distance) {
        return distance * 0.15;
    }

    @Override
    double calculateTravelTime(double distance) {
        return (distance / 60) * 60;
    }

    @Override
    int calculateEcoScore(double distance) {
        return (int) (100 - calculateEmissions(distance) * 50);
    }
}

class Route {
    private String start;
    private String destination;
    private double distance;

    public Route(String start, String destination, double distance) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public String getDetails() {
        return "Route from " + start + " to " + destination + ", Distance: " + distance + " km";
    }
}

public class JavaGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Transport Emissions and Eco-Score Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(new Color(0, 0, 139));

        JLabel startLabel = new JLabel("Starting Location: ");
        startLabel.setForeground(Color.WHITE);
        JTextField startField = new JTextField();
        startField.setBackground(Color.BLACK); 
        startField.setForeground(Color.WHITE); 

        JLabel destinationLabel = new JLabel("Destination: ");
        destinationLabel.setForeground(Color.WHITE); 
        JTextField destinationField = new JTextField();
        destinationField.setBackground(Color.BLACK); 
        destinationField.setForeground(Color.WHITE); 

        JLabel distanceLabel = new JLabel("Distance (km): ");
        distanceLabel.setForeground(Color.WHITE); 
        JTextField distanceField = new JTextField();
        distanceField.setBackground(Color.BLACK); 
        distanceField.setForeground(Color.WHITE); 

        inputPanel.add(startLabel);
        inputPanel.add(startField);
        inputPanel.add(destinationLabel);
        inputPanel.add(destinationField);
        inputPanel.add(distanceLabel);
        inputPanel.add(distanceField);

        frame.add(inputPanel, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultPanel.add(scrollPane);
        frame.add(resultPanel, BorderLayout.CENTER);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(new Color(102, 204, 255));
        frame.add(calculateButton, BorderLayout.SOUTH);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String start = startField.getText();
                    String destination = destinationField.getText();
                    double distance = Double.parseDouble(distanceField.getText());

                    Route route = new Route(start, destination, distance);

                    Transport bike = new Bike();
                    Transport jeepney = new Jeepney();
                    Transport car = new Car();

                    StringBuilder result = new StringBuilder();
                    result.append(route.getDetails()).append("\n\n");

                    Transport[] transports = {bike, jeepney, car};
                    for (Transport transport : transports) {
                        result.append(transport.mode).append("\n");
                        result.append("Emissions: ").append(transport.calculateEmissions(route.getDistance())).append(" kg CO2\n");
                        result.append("Travel Time: ").append(transport.calculateTravelTime(route.getDistance())).append(" minutes\n");
                        result.append("Eco-Score: ").append(transport.calculateEcoScore(route.getDistance())).append("\n\n");
                    }

                    resultArea.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid distance.");
                }
            }
        });

        frame.setVisible(true);
    }
}



