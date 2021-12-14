package main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSideServerListener implements Runnable{

    private ClackClient client;
    private Scanner inFromStd;

    public void ClientSideServerListener(ClackClient client){
        this.client = client;
    }

    @Override
    public void run() {
        Socket skt = null;
        try {
            skt = new Socket(this.client.getHostName(), this.client.getPort());

            this.client.outToServer = new ObjectOutputStream( skt.getOutputStream() );
            this.client.inFromServer = new ObjectInputStream( skt.getInputStream() );
            this.inFromStd = new Scanner( new InputStreamReader( System.in ) );

            this.client.dataToSendToServer = new ClientClackData(this.client);
            this.client.sendData();

            this.client.closedConnection = false;

        } catch(UnknownHostException uhe) {
            System.err.println( uhe.getMessage() );
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
        while(!this.client.closedConnection){
            this.client.readClientData();
            this.client.sendData();
            this.client.receiveData();
            if(this.client.dataToReceiveFromServer != null)
                this.client.printData();
        }
        try {
            skt.close();
            inFromStd.close();
            this.client.outToServer.close();
            this.client.inFromServer.close();
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
    }
}
