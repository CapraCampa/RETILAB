import java.net.*;
import java.io.*;

public class Server{
	public static void main(String[] args){
		DatagramSocket sSrv = null;

		try{	
			sSrv = new DatagramSocket();
			System.out.println(sSrv.getLocalPort());

			int dim_buffer = 100;
			byte[] buffer = new byte[dim_buffer];

			DatagramPacket dpin = new DatagramPacket(buffer,dim_buffer);
			sSrv.receive(dpin);

			String stringa = new String(buffer,0,dpin.getLength());
			System.out.println("ricevuto: " + stringa);
			InetAddress ia = dpin.getAddress();
			int porta = dpin.getPort();
			System.out.println("Indirizzo: " + ia.getHostAddress() + " porta " + porta);
			sSrv.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}