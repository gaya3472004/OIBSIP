import java.util.*;
public class ReservationSystem {
    private static final Map<String, String> userAccounts = new HashMap<>();
    private static final Map<Integer, String> trainDetails = new HashMap<>();
    private static final List<Ticket> tickets = new ArrayList<>();
    private static int nextPnr = 1; // PNR counter

    public static void main(String[] args) {
        // Predefined users and trains
        userAccounts.put("user1", "pass123");
        userAccounts.put("admin", "admin123");
        trainDetails.put(101, "City Express");
        trainDetails.put(102, "Mountain Express");
        trainDetails.put(103, "River Express");

        Scanner scanner = new Scanner(System.in);

        // User Login
        System.out.println("Welcome to Online Reservation System");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!authenticateUser(username, password)) {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }

        // Main Menu
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Tickets");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> bookTicket(scanner);
                case 2 -> cancelTicket(scanner);
                case 3 -> displayTickets();
                case 4 -> {
                    System.out.println("Thank you for using the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean authenticateUser(String username, String password) {
        return userAccounts.containsKey(username) && userAccounts.get(username).equals(password);
    }

    private static void bookTicket(Scanner scanner) {
        System.out.print("Enter your name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        if (!trainDetails.containsKey(trainNumber)) {
            System.out.println("Invalid train number. Booking failed.");
            return;
        }
        String trainName = trainDetails.get(trainNumber);

        System.out.print("Enter travel class (Sleeper/AC): ");
        scanner.nextLine(); // Consume newline
        String travelClass = scanner.nextLine();

        System.out.print("Enter source station: ");
        String source = scanner.nextLine();

        System.out.print("Enter destination station: ");
        String destination = scanner.nextLine();

        System.out.print("Enter journey date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        int pnr = nextPnr++;
        Ticket ticket = new Ticket(pnr, name, trainNumber, trainName, travelClass, source, destination, date);
        tickets.add(ticket);

        System.out.println("Booking successful! Your PNR is: " + pnr);
    }

    private static void cancelTicket(Scanner scanner) {
        System.out.print("Enter PNR to cancel: ");
        int pnr = scanner.nextInt();

        for (Iterator<Ticket> iterator = tickets.iterator(); iterator.hasNext(); ) {
            Ticket ticket = iterator.next();
            if (ticket.pnr == pnr) {
                System.out.println("Ticket found: " + ticket);
                System.out.print("Confirm cancellation? (yes/no): ");
                scanner.nextLine(); // Consume newline
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("yes")) {
                    iterator.remove();
                    System.out.println("Ticket canceled successfully.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                return;
            }
        }

        System.out.println("PNR not found.");
    }

    private static void displayTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            System.out.println("Booked Tickets:");
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }
    }
}

