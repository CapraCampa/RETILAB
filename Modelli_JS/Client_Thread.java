import java.net.*;
import java.io.*;

public class Client{
	public static void main(String[] args){
		Socket sClient;
		InetAddress ia; // IP address SERVER
		InetSocketAddress isa; // socket address SERVER

		sClient = new Socket();
		int porta;
		if(args.length != 0){
		 porta = Integer.parseInt(args[0]);
		}else{
			throw new IllegalArgumentException("Non è stato passato il numero di porta");
		}
		try{
			ia = InetAddress.getLocalHost();
			isa = new InetSocketAddress(ia,porta);
			sClient.connect(isa);
			System.out.println("Connessione effettuata!");
			System.out.println("Porta locale: " + sClient.getLocalPort()); // la mia porta
			System.out.println("Indirizzo server : " + sClient.getInetAddress() + "; porta: " + sClient.getPort());
		}catch(Exception e){
			e.printStackTrace();
		}

		try{
		 InputStreamReader tastiera = new InputStreamReader(System.in);
		 OutputStream toSrv = sClient.getOutputStream();
		 BufferedReader br = new BufferedReader(tastiera);
		 while(true){
			 String frase = br.readLine();
	         toSrv.write(frase.getBytes(),0,frase.length());
	         if(frase.equals(".")){
	     	 	break;
	     	 }
	    }
	   }catch(IOException e){
	   	 e.printStackTrace();
	   }

	   finally{
	   	try{
	   		sClient.close();
	   	}catch(Exception e){
	   		e.printStackTrace();
	   	}
	   }
	}
}