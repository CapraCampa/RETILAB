import java.net.*;
import java.io.*;

public class Client{

	public static void main(String[] args){
		DatagramSocket sClient = null;

		try{
			if(args.length != 1){
				throw new IllegalArgumentException("Non hai specificato il numero porta");
			}
			int porta = Integer.parseInt(args[0]);

			if(porta <= 0){
				throw new IllegalArgumentException("porta non valida");
			}
			sClient = new DatagramSocket();
			InetAddress ia = InetAddress.getLocalHost();
			InetSocketAddress isa = new InetSocketAddress(ia,porta);
			InputStreamReader tastiera = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(tastiera);
			String frase = br.readLine();
			byte[] buffer = frase.getBytes();
			DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
			dp.setSocketAddress(isa);
			sClient.send(dp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}