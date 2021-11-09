package main;

import data.*;
import java.io.*;
import java.net.*;

public class ClackServer {

    int port;
    boolean closedConnection;

    ClackData dataToReceiveFromClient;
    ClackData dataToSendToClient;

    ObjectInputStream inFromClient;
    ObjectOutputStream outToClient;

    public ClackServer(int port) {
        try {
            if(port < 1024) {
                throw new IllegalArgumentException("Not a valid port");
            }
            this.port = port;
            this.outToClient = null;
            this.inFromClient = null;
            this.closedConnection = true;
        } catch (IllegalArgumentException iae) {
            System.err.println( iae.getMessage() );
        }
    }

    public ClackServer() {
        this.port = 7000;
    }

    public void start(){
        ServerSocket sskt = null;
        try {
            sskt = new ServerSocket(this.getPort());
            Socket client = sskt.accept();
            outToClient = new ObjectOutputStream( client.getOutputStream() );
            inFromClient = new ObjectInputStream( client.getInputStream() );

            this.closedConnection = false;

        } catch(UnknownHostException uhe) {
            System.err.println( uhe.getMessage() );
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
        while(!this.closedConnection) {
            this.sendData();
            this.receiveData();

        }



        try {
            sskt.close();
            inFromClient.close();
            outToClient.close();
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
    }

    public void receiveData(){
        try {
            dataToReceiveFromClient = (ClackData)inFromClient.readObject();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
        }
    }

    public void sendData(){
        try {
            outToClient.writeObject(dataToSendToClient);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public int getPort(){
        return this.port;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashcode = 7;
        hashcode = prime*hashcode + this.getPort();
        if(this.closedConnection)
            hashcode = prime*hashcode*-1;
        if(dataToSendToClient != null)
            hashcode = prime*hashcode + dataToSendToClient.hashCode();
        if(dataToReceiveFromClient != null)
            hashcode = prime*hashcode + dataToReceiveFromClient.hashCode();
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClackServer))
            return false;

        ClackServer temp = (ClackServer)obj;

        return this.getPort() == temp.getPort() &&
                this.closedConnection == temp.closedConnection ||
                ((this.dataToSendToClient != null || temp.dataToSendToClient != null) &&
                        (!this.dataToSendToClient.equals(temp.dataToSendToClient))) ||
                ((this.dataToReceiveFromClient != null || temp.dataToReceiveFromClient != null) &&
                        (!this.dataToReceiveFromClient.equals(temp.dataToReceiveFromClient)));
    }

    @Override
    public String toString() {
        return "The port for this ClackClient is: " + this.getPort() + '\n' +
                "The data sent out is:\n\n" + dataToSendToClient + "\n\n" +
                "The data received is:\n\n" + dataToReceiveFromClient + "\n\n" +
                "The connection is closed: " + closedConnection + '\n';
    }

    public static void main(String[] args) {
        ClackServer s;
        if (args.length == 0) {
            s = new ClackServer();
        } else {
            s = new ClackServer(Integer.parseInt(args[0]));

        }
        s.start();
    }

}