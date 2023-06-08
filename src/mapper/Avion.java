package mapper;
import java.util.Random;

class Avion {
    private static final int MAX_ALTITUDE = 10000;
    private static final int MAX_SPEED = 500;
    private static final int MAX_ANGLE = 360;

    //azaedkjzkqsdks

    private String id;
    private Position position;
    private int altitude;
    private int speed;
    private int angle;

    public Avion(String id, Position position, int altitude, int speed, int angle) {
        this.id = id;
        this.position = position;
        this.altitude = altitude;
        this.speed = speed;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void updatePosition() {
        double distance = speed * 15.0 / 3600.0; // Convert speed from km/h to km/s
        double dx = distance * Math.sin(Math.toRadians(angle));
        double dy = distance * Math.cos(Math.toRadians(angle));
        position = new Position(position.getLatitude() + dx, position.getLongitude() + dy);
    }

    public static Avion generateRandomAvion(Position center) {
        Random random = new Random();
        String id = "Avion-" + random.nextInt(100);
        double latitude = center.getLatitude() + random.nextDouble() - 0.5;
        double longitude = center.getLongitude() + random.nextDouble() - 0.5;
        int altitude = random.nextInt(MAX_ALTITUDE);
        int speed = random.nextInt(MAX_SPEED);
        int angle = random.nextInt(MAX_ANGLE);
        return new Avion(id, new Position(latitude, longitude), altitude, speed, angle);
    }
}
