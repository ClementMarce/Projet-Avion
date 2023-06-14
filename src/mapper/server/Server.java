package mapper.server;
import java.net.*;

public class Server {
    public final static int port = 8532;
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];
    public static void main(String[] argv) throws Exception
    {
        DatagramSocket socket = new DatagramSocket(port);
        String[] data_flights = new String[3];
        for (int i=0; i < 3 ; i++){
            data_flights[i] = String.valueOf(i+10);
        }
        while(true)
        {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);
            System.out.println(data.getAddress());
            System.out.println(new String(data.getData()));
            byte[] buf;
            String data_ac = "";
            for (int i=0; i < 3 ; i++){
                data_ac += data_flights[i]  + ";";
            }
            data_ac   += "END";
            System.out.println("Data to  send: " + data_ac);
            buf = data_ac.getBytes();
            DatagramPacket data_to_send = new DatagramPacket(buf,buf.length, data.getAddress(), data.getPort());
            socket.send(data_to_send);
        }
    }
}
