package main;

import data.ClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSideClientIO implements Runnable{

    ClackServer server;
    Socket clientSocket;
    boolean closedConnection;

    ClackData dataToReceiveFromClient;
    ClackData dataToSendToClient;
    ObjectInputStream inFromClient;
    ObjectOutputStream outToClient;

    public ServerSideClientIO(ClackServer server, Socket socket){
        this.server = server;
        this.clientSocket = socket;
        closedConnection = false;

        ClackData dataToReceiveFromClient = null;
        ClackData dataToSendToClient = null;
        ObjectInputStream inFromClient = null;
        ObjectOutputStream outToClient = null;
    }

    @Override
    public void run(){
        try {
            outToClient = new ObjectOutputStream( clientSocket.getOutputStream() );
            inFromClient = new ObjectInputStream( clientSocket.getInputStream() );
        } catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
        while (!closedConnection){
            receiveData();
            server.broadcast(dataToSendToClient);
        }
        try {
            inFromClient.close();
            outToClient.close();
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
    }

    public synchronized void receiveData(){
        try {
            if(!closedConnection)
                dataToReceiveFromClient = (ClackData)inFromClient.readObject();
            if(dataToReceiveFromClient.getType() == 1){
                closedConnection = true;
                server.remove(this);
            }
            System.out.println(dataToReceiveFromClient);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
        }
    }

    public synchronized void sendData(){
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public synchronized void setDataToSendToClient(ClackData dataToSendToClient){
        this.dataToSendToClient = dataToSendToClient;
    }

}
