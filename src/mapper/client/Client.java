package mapper.client;
import mapper.server.Server;
import java.io.IOException;
import java.net.*;

class Client{
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];
    public static void main(String[] argv) throws Exception{



        MyFrame frame = new MyFrame();
        for (int i = 1; i < 6; i++) {
            frame.CreateNewPlane("plane-"+ i);
        }
        DatagramSocket socket = new DatagramSocket(Server.portClient);
        while(true) {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);
            System.out.println(new String(data.getData()));
            var dataReceived = new String(data.getData()).split(";");
            int x = TransformCoordonnates(Double.parseDouble(dataReceived[1]));
            int y = TransformCoordonnates(Double.parseDouble(dataReceived[2]));
            frame.updatePlane(dataReceived[0],x,y, dataReceived[0]+" "+dataReceived[3]);
            Thread.sleep(10);
            frame.drawRadar();
        }
    }

    public static void ChangeValues(String avionID, String command, int value) throws IOException {
        InetAddress serveur = InetAddress.getByName("127.0.0.1");
        String buf = command + ";" + avionID + ";" + value + ";";
        int length = buf.length();
        byte[] buffer = buf.getBytes();
        DatagramPacket dataSent = new DatagramPacket(buffer, length, serveur, Server.portServer);
        DatagramSocket socket = new DatagramSocket();
        socket.send(dataSent);
    }

    private static int TransformCoordonnates(double coordonnees){
        return (int) (coordonnees);
    }
}
