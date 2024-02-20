package Iterativo;

import java.net.*;
import java.io.*;

public class Server{

    public static void main(String[] args){
        int dim_buffer = 100;
        byte buffer[] = new byte[dim_buffer];

        ServerSocket sSrv = null;
        Socket client = null;

        try{
            sSrv = new ServerSocket(0,1);
            System.out.println(sSrv.getLocalPort());
            while(true){
                client = sSrv.accept();
                System.out.println("Indirizzo cliente " + client.getInetAddress()
                                    + ", su porta " + client.getLocalPort());
                InputStream fromCl = client.getInputStream();
                OutputStream toCl = client.getOutputStream();


                toCl.write("SYNACK".getBytes(),0,"SYNACK".length());

                int letti = fromCl.read(buffer);
                String messaggio = new String(buffer,0,letti);
                System.out.println(messaggio);

                if(messaggio.equals("FIN")){
                    fromCl.close();
                    toCl.close();
                    client.close();
                    break;
                }
            }
        }catch(IOException e){
            System.err.println("Non sono riuscito a connettermi!");
        }

        finally{
            try{
                sSrv.close();
                client.close();
            }catch(Exception e){
                System.err.println("Errore in chiusura socket");
                e.printStackTrace();
            }
        }
    }

}