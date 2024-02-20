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
		 BufferedReader br = new BufferedReader(tastiera);
		 int dim_buffer = 100;
         byte buffer[] = new byte[dim_buffer];

		 while(true){
			 String frase = br.readLine();
			 if(frase == null){
			 	break;
			 }
	     	 OutputStream toSrv = sClient.getOutputStream();
	         toSrv.write(frase.getBytes(),0,frase.length());
	         if(frase.equals("0")){
	     	 	break;
	     	 }
	    }
	   }catch(IOException e){
	   	 e.printStackTrace();
	   }

	   finally{
	   	try{
	   		System.out.println("La connessione con il server all'indirizzo " + sClient.getInetAddress() + " su porta " + sClient.getPort() + " è stata chiusa!");
	   		sClient.close();
	   	}catch(Exception e){
	   		e.printStackTrace();
	   	}
	   }
	}
}
