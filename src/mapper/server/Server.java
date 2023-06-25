package mapper.server;
import mapper.Avion;
import mapper.Position;
import mapper.Data;
import java.io.IOException;
import java.net.*;

public class Server {
    private static Data data;
    private static final double AIRPORT_LATITUDE = 500;
    private static final double AIRPORT_LONGITUDE = 500;
    private static final int MAX_AVIONS = 5;
    private static final int UPDATE_INTERVAL = 1000; // 15 seconds
    public final static int portClient = 8532;
    public final static int portServer = 8533;
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];
    public static void main(String[] argv) throws Exception{
        data = new Data();
        generateAvions();
        startAutoUpdateThread();
        DatagramSocket socket = new DatagramSocket(portServer);
        String[] data_flights = new String[3];
        for (int i=0; i < 3 ; i++){
            data_flights[i] = String.valueOf(i+10);
        }
        while(true){
            DatagramPacket theOtherData = new DatagramPacket(buffer,buffer.length);
            socket.receive(theOtherData);
            String vitesseUpdateString = new String(theOtherData.getData());
            String[] receivedData = vitesseUpdateString.split(";");
            int vitesseUpdate = Integer.parseInt(receivedData[0]);
            System.out.println("increase the speed by : " + vitesseUpdate);
            int vitesse = data.getAvions().get(0).getSpeed();
            vitesse += vitesseUpdate;
            data.getAvions().get(0).setSpeed(vitesse);
        }
    }

    private static void generateAvions() {
        for (int i = 1; i < MAX_AVIONS + 1; i++) {
            Avion avion = Avion.generateAvion(new Position(AIRPORT_LATITUDE, AIRPORT_LONGITUDE), "plane-"+ i);
            data.addAvion(avion);
        }
    }

    private static void startAutoUpdateThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(UPDATE_INTERVAL);
                    data.updateAvionsPosition();
                    sendAvionsPosition();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private static void sendAvionsPosition() throws IOException {
        InetAddress serveur = InetAddress.getByName("127.0.0.1");
        for (Avion avion : data.getAvions()) {
            String buf = avion.getId() + ";" + avion.getPosition().getLatitude() + ";" +
                    avion.getPosition().getLongitude() + ";" + avion.getAltitude() + ";" +
                    avion.getAngle() + ";" + avion.getSpeed() + ";END";
            int length = buf.length();
            byte[] buffer = buf.getBytes();
            DatagramPacket dataSent = new DatagramPacket(buffer,length,serveur, portClient);
            DatagramSocket socket = new DatagramSocket();
            socket.send(dataSent);
        }
    }
}
