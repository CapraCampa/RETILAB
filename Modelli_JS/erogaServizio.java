import java.io.*;
import java.net.*;

public class erogaServizio extends Thread{

	private Socket sock2Cl;

	public erogaServizio(final Socket socket){
		if(socket == null){
			throw new NullPointerException("Il parametro non può essere null");
		}
		this.sock2Cl = socket;
	}

	public void run(){
		int dim_buffer = 100;
		byte buffer[] = new byte[dim_buffer];

		while(true){
			try{
				InputStream fromCl = sock2Cl.getInputStream();
				int letti = fromCl.read(buffer);
				if(letti > 0){
					String stampa = new String(buffer,0,letti);
					System.out.println(stampa);
				}else{
					sock2Cl.close();
					return;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}