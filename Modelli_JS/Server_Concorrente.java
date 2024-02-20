package Concorrente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) {
        ServerSocket sSrv = null;
        List<Socket> clients = new ArrayList<>();

        int dim_buffer = 100;
        byte[] buffer = new byte[dim_buffer];

        int n_max_client = 5;
        int current = 0;


        try{
            sSrv = new ServerSocket(0,n_max_client);
        }catch(IOException e){
            System.err.println("Non sono riuscito a costruire server socket");
            return;
        }

        System.out.println("Porta del server: " + sSrv.getLocalPort());

        while(true){
            try{
                sSrv.setSoTimeout(3);
                if(current < n_max_client){
                    Socket temp = sSrv.accept();
                    clients.add(temp);
                    current++;
                }
            }catch(SocketException e){} catch (IOException e) {
                System.err.println("ERRORE IN CONNESSIONE CON IL CLIENT");
                return;
            }

            int i = 0;
            int index = current;
            while(index > 0){
                Socket currentSocket = clients.get(i);

                try {
                    currentSocket.setSoTimeout(3);
                    while (true) {
                        InputStream fromClient = currentSocket.getInputStream();
                        OutputStream toClient = currentSocket.getOutputStream();

                        int letti = fromClient.read(buffer);
                        if(letti == -1) throw new IOException("chiusa connessione");

                        String mx = new String(buffer,0,letti);
                        System.out.println(mx);

                        String mxToClient = "ricevuto!";
                        toClient.write(mxToClient.getBytes(),0,mxToClient.length());

                    }
                }catch (SocketException e) {}
                catch (IOException e) {
                    clients.remove(i);
                    current--;
                    try {
                        currentSocket.close();
                    } catch (IOException ex) {
                        System.err.println("Non sono riuscito a chiudere la connessione");
                    }
                    System.out.println("chiusa connessione");
                }
                i = index != 0 ? (i+1)%index : 0;
            }
        }

    }
}
