package mapper;
import java.util.Scanner;

class AirportSimulator {
    private static final double AIRPORT_LATITUDE = 48.8333;
    private static final double AIRPORT_LONGITUDE = 2.6167;
    private static final int MAX_AVIONS = 5;

    private Data data;

    public AirportSimulator() {
        this.data = new Data();
    }
    public void start() {
        generateRandomAvions();
        printAvions();

        // Simulation loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Air Traffic Control ===");
            System.out.println("1. Afficher les avions");
            System.out.println("2. Envoyer un ordre à un avion");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    printAvions();
                    break;
                case 2:
                    sendOrder(scanner);
                    break;
                case 0:
                    System.out.println("Programme terminé.");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void generateRandomAvions() {
        for (int i = 0; i < MAX_AVIONS; i++) {
            Avion avion = Avion.generateRandomAvion(new Position(AIRPORT_LATITUDE, AIRPORT_LONGITUDE));
            data.addAvion(avion);
        }
    }

    private void printAvions() {
        System.out.println("\n=== Avions ===");
        for (Avion avion : data.getAvions()) {
            System.out.println(avion.getId() + ": " + avion.getPosition().getLatitude() + ", " +
                    avion.getPosition().getLongitude() + " (Altitude: " + avion.getAltitude() +
                    ", Cap: " + avion.getAngle() + ", Vitesse: " + avion.getSpeed() + ")");
        }
    }

    private void sendOrder(Scanner scanner) {
        System.out.print("Entrez l'ID de l'avion : ");
        String avionId = scanner.nextLine();
        Avion avion = findAvionById(avionId);
        if (avion == null) {
            System.out.println("Avion introuvable.");
            return;
        }

        System.out.println("Choisissez un ordre :");
        System.out.println("1. Changer le cap");
        System.out.println("2. Changer l'altitude");
        System.out.println("3. Changer la vitesse");
        System.out.println("0. Annuler");
        System.out.print("Choix : ");
        int order = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (order) {
            case 1:
                System.out.print("Nouveau cap : ");
                int newAngle = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                avion.setAngle(newAngle);
                System.out.println("Ordre envoyé : l'avion " + avionId + " a changé de cap.");
                break;
            case 2:
                System.out.print("Nouvelle altitude : ");
                int newAltitude = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                avion.setAltitude(newAltitude);
                System.out.println("Ordre envoyé : l'avion " + avionId + " a changé d'altitude.");
                break;
            case 3:
                System.out.print("Nouvelle vitesse : ");
                int newSpeed = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                avion.setSpeed(newSpeed);
                System.out.println("Ordre envoyé : l'avion " + avionId + " a changé de vitesse.");
                break;
            case 0:
                System.out.println("Ordre annulé.");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }
    }
    private Avion findAvionById(String avionId) {
        for (Avion avion : data.getAvions()) {
            if (avion.getId().equals(avionId)) {
                return avion;
            }
        }
        return null;
    }
}
