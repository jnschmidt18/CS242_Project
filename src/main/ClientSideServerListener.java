package main;

import com.sun.security.ntlm.Client;
import data.*;

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

    public ClientSideServerListener(ClackClient client){
        this.client = client;
    }

    @Override
    public void run() {
        //Socket skt = null;
        try {
            Socket skt = new Socket(this.client.getHostName(), this.client.getPort());

            this.client.outToServer = new ObjectOutputStream( skt.getOutputStream() );
            this.client.inFromServer = new ObjectInputStream( skt.getInputStream() );
            this.inFromStd = new Scanner( new InputStreamReader( System.in ) );

            this.client.dataToSendToServer = new ClientClackData(this.client.getUserName(), this.client.toString());
            this.client.sendData();
            this.client.dataToSendToServer = new MessageClackData("Anon", " ", ClackData.CONSTANT_LISTUSERS);
            this.client.sendData();

            this.client.closedConnection = false;

        while(!this.client.closedConnection){
            this.client.readClientData();

            this.client.sendData();
            this.client.receiveData();

            if(this.client.dataToReceiveFromServer != null)
                this.client.printData();
        }
            skt.close();
            inFromStd.close();
            this.client.outToServer.close();
            this.client.inFromServer.close();
        } catch(UnknownHostException uhe) {
            System.err.println( uhe.getMessage() );
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
    }
}
