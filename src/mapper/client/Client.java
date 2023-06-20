package mapper.client;
import mapper.server.Server;

import java.io.IOException;
import java.net.*;

class Client{
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];
    public static void main(String[] argv) throws Exception{

        DatagramSocket socket = new DatagramSocket(Server.portClient);

        int nb = 0;

        while(true)
        {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);
            System.out.println(new String(data.getData()));

            if (nb%10 == 0){
                ChangeVitesse(10);
            }
            nb++;
            Thread.sleep(1000);

        }
    }


    private static void ChangeVitesse(int vitesse) throws IOException {
        InetAddress serveur = InetAddress.getByName("127.0.0.1");

        String buf = String.valueOf(vitesse);
        int length = buf.length();
        byte[] buffer = buf.getBytes();

        DatagramPacket dataSent = new DatagramPacket(buffer,length,serveur, Server.portServer);
        DatagramSocket socket = new DatagramSocket();
        socket.send(dataSent);
    }

}
