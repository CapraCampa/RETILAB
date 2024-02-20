package Iterativo;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Non hai inserito un numero di porta");
            return;
        }

        int porta;
        try{
            porta = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            System.out.println("Porta inserita non è valida!");
            return;
        }

        Socket sclient = new Socket();

        int dim_buffer = 100;
        byte[] buffer = new byte[dim_buffer];

        try{
            InetAddress ia = InetAddress.getLocalHost();
            InetSocketAddress isa = new InetSocketAddress(ia,porta);
            sclient.connect(isa);
        }catch(UnknownHostException e){
            System.err.println("Non riesco a trovare ip server");
        }catch(IllegalArgumentException e){
            System.err.println("La porta che hai inserito è fuori dal range dei valori ammissibili!");
        } catch (IOException e) {
            System.out.println("Non riesco a connettermi al server");
        }

        InputStreamReader tastiera = new InputStreamReader(System.in);
        BufferedReader tastieraBufferizzata = new BufferedReader(tastiera);

        try{
            InputStream fromSrv = sclient.getInputStream();
            OutputStream toSrv = sclient.getOutputStream();

            toSrv.write("SYN".getBytes(),0,"SYN".length());

            int letti = fromSrv.read(buffer);

            String mx = new String(buffer,0,letti);

            if(!mx.equals("SYNACK")){
                fromSrv.close();
                toSrv.close();
                throw new IllegalStateException("Non riesco a connettermi al server");
            }

            System.out.println("Inserisci nome");

            mx = tastieraBufferizzata.readLine();
            toSrv.write(mx.getBytes(),0,mx.length());

        }catch(IOException e){
            System.err.println("Errore in apertura stream");
        }

        try {
            tastieraBufferizzata.close();
            tastiera.close();
        } catch (IOException e) {
            System.err.println("Errore in chiusura reader");
        }

    }
}
