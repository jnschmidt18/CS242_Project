package main;

import data.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class ClackServer {

    private int port;
    private boolean closedConnection;
    private ArrayList<ServerSideClientIO> serverSideClientIOList;
    private ArrayList<ClackClient> listUsers;

    public ClackServer(int port) {
        try {
            if(port < 1024) {
                throw new IllegalArgumentException("Not a valid port");
            }
            this.port = port;
            this.closedConnection = true;
            this.serverSideClientIOList = new ArrayList<ServerSideClientIO>();
            this.listUsers = new ArrayList<ClackClient>();
        } catch (IllegalArgumentException iae) {
            System.err.println( iae.getMessage() );
        }
    }

    public ClackServer() {
        this(7000);
    }

    public void start() {
        try {
            ServerSocket sskt = new ServerSocket(this.getPort());
            this.closedConnection = false;
            while (!this.closedConnection) {
                Socket skt = sskt.accept();
                ServerSideClientIO temp = new ServerSideClientIO(this, skt);
                serverSideClientIOList.add(temp);

                Thread tr = new Thread(temp);
                tr.start();
            }
            sskt.close();
        } catch (UnknownHostException unh) {
            System.err.println(unh.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());

        }
    }


    public synchronized void broadcast(ClackData dataToBroadcastToClients){
        for(ServerSideClientIO ssc : serverSideClientIOList){
            ssc.setDataToSendToClient(dataToBroadcastToClients);
            ssc.sendData();
        }
    }

    public synchronized void remove( ServerSideClientIO serverSideClientToRemove ){
        serverSideClientIOList.remove(serverSideClientToRemove);
    }

    public int getPort(){
        return this.port;
    }

    public ArrayList<ClackClient> getListUsers() {
        return listUsers;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashcode = 7;
        hashcode = prime*hashcode + this.getPort();
        if(this.closedConnection)
            hashcode = prime*hashcode*-1;
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClackServer))
            return false;

        ClackServer temp = (ClackServer)obj;

        return this.getPort() == temp.getPort() &&
                this.closedConnection == temp.closedConnection;

    }

    @Override
    public String toString() {
        return "The port for this ClackClient is: " + this.getPort() + '\n' +
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