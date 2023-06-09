package mapper;
import java.util.Random;

public class Avion {
    private static final int MAX_ANGLE = 360;
    private final String id;
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
        double dAltitude = (altitude * 15.0) / 60000.0; // Convert altitude from ft to km
        position = new Position(position.getLatitude() + dx, position.getLongitude() + dy);
        position = new Position(position.getLatitude(), position.getLongitude() + dAltitude);
    }

    public static Avion generateAvion(Position center, String name) {
        Random random = new Random();
        double latitude = center.getLatitude() + (random.nextDouble()* 500 - 250);
        double longitude = center.getLongitude() + (random.nextDouble()* 500 - 250);
        int altitude = 1000 + random.nextInt(9)*1000;
        int speed = 1000;
        int angle = random.nextInt(MAX_ANGLE);
        return new Avion(name, new Position(latitude, longitude), altitude, speed, angle);
    }
}
