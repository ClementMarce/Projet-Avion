package mapper.client;
import mapper.Avion;
import mapper.Position;
import mapper.server.Server;
import java.io.IOException;
import java.net.*;

class Client{
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];
    public static void main(String[] argv) throws Exception{
        MyFrame frame = new MyFrame(); //Creates a new frame
        for (int i = 0; i < 5; i++) {
            frame.CreateNewPlane("plane"+String.valueOf(i));
        }
        DatagramSocket socket = new DatagramSocket(Server.portClient);
        int nb = 0;
        while(true) {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);
            System.out.println(new String(data.getData()));
            var dataReceived = new String(data.getData()).split(";");
            int x = TransformCoordonnates(Double.parseDouble(dataReceived[1]));
            int y = TransformCoordonnates(Double.parseDouble(dataReceived[2]));
            frame.updatePlane(dataReceived[0],x,y, dataReceived[0]+" "+dataReceived[1]+" "+dataReceived[2]);
            if (nb%10 == 0){
                ChangeVitesse(20);
            }
            nb++;
            Thread.sleep(10);
        }
    }

    private static int TransformCoordonnates(double coordonnees){
        return (int) (coordonnees * 10);
    }

    private static void ChangeVitesse(int vitesse) throws IOException {
        InetAddress serveur = InetAddress.getByName("127.0.0.1");
        String buf = vitesse + ";";
        int length = buf.length();
        byte[] buffer = buf.getBytes();
        DatagramPacket dataSent = new DatagramPacket(buffer,length,serveur, Server.portServer);
        DatagramSocket socket = new DatagramSocket();
        socket.send(dataSent);
    }
}
