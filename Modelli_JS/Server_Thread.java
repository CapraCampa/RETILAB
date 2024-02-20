import java.net.*;
import java.io.*;

public class Server{
	public static void main(String[] args){
		ServerSocket sSrv;
		Socket toClient = new Socket();

		try{
			sSrv = new ServerSocket(0);
			System.out.println(sSrv.getLocalPort());
			
			while(true){
				toClient = sSrv.accept();
				System.out.println(toClient.getInetAddress() + " " + toClient.getPort());
				Thread t = new erogaServizio(toClient);
				t.start();
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}