import java.util.Scanner;

//Encapsulation
//The Transport class bundles the mode of transport then calculates the emissions, travel time, and eco-score.
abstract class Transport {
    String mode;

    public Transport(String mode) {
        this.mode = mode; //Mode of transport is encapsulated in this class
    }

    //Abstraction.
    abstract double calculateEmissions(double distance); 
    abstract double calculateTravelTime(double distance); 
    abstract int calculateEcoScore(double distance);
}

//Inheritance
//Bike class inherits from Transport class
class Bike extends Transport {
    public Bike() {
        super("Bike"); 
    }

    @Override
    double calculateEmissions(double distance) {
        return 0; //Emissions for bike are always 0
    }

    @Override
    double calculateTravelTime(double distance) {
        return (distance / 15) * 60; 
    }

    @Override
    int calculateEcoScore(double distance) {
        return 100; //Bike has the highest eco-score because it produces no emmisions
    }
}

//Inheritance 
//Jeepney class inherits from Transport class
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

//Inheritance
//Car class inherits from Transport class
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

//Encapsulation
//encapsulates the start, destination, and distance of the route
class Route {
    private String start; //private attribute, it's not directly accessible outside this class
    private String destination;
    private double distance;

    //Constructor to initialize the route
    public Route(String start, String destination, double distance) {
        this.start = start;
        this.destination = destination;
        this.distance = distance;
    }

    //Getter for distance 
    public double getDistance() {
        return distance;
    }

    //Getter for route details
    public String getDetails() {
        return "Route from " + start + " to " + destination + ", Distance: " + distance + " km";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the starting location: ");
        String start = scanner.nextLine();

        System.out.print("Enter the destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter the distance in kilometers: ");
        double distance = scanner.nextDouble();

        Route route = new Route(start, destination, distance); 

        Transport bike = new Bike(); 
        Transport jeepney = new Jeepney(); 
        Transport car = new Car(); 

        System.out.println(route.getDetails());

        //Polymorphism 
        //Looping through different transport modes and calling methods on the Transport objects
        Transport[] transports = {bike, jeepney, car}; 
        for (Transport transport : transports) {
            System.out.println(transport.mode + " emissions: " + transport.calculateEmissions(route.getDistance()) + " kg CO2");
            System.out.println(transport.mode + " travel time: " + transport.calculateTravelTime(route.getDistance()) + " minutes");
            System.out.println(transport.mode + " eco-score: " + transport.calculateEcoScore(route.getDistance()));
        }

        scanner.close();
    }
}


