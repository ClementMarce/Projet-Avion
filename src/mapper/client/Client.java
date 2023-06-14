package mapper.client;
import mapper.server.Server;
import java.net.*;

class Client{
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];
    public static void main(String[] argv) throws Exception
    {
        InetAddress serveur = InetAddress.getByName(argv[0]);

        int length = argv[1].length();
        byte[] buffer = argv[1].getBytes();
        DatagramPacket dataSent = new DatagramPacket(buffer,length,serveur, Server.port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(dataSent);
        DatagramPacket data_Received = new DatagramPacket(new byte[1024],1024);
        socket.receive(data_Received);
        System.out.println("Data received : " + new String(data_Received.getData()));
        System.out.println("From : " + data_Received.getAddress() + ":" + data_Received.getPort());
    }
}

