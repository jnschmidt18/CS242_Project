package main;

import data.*;
import java.util.Scanner;
import java.net.*;
import java.io.*;

public class ClackClient{

    public final static int DEFAULT_PORT = 7000;
    public final static int CONSTANT_LISTUSERS = 0;
    public final static int CONSTANT_LOGOUT = 1;
    public final static int CONSTANT_SENDMESSAGE = 2;
    public final static int CONSTANT_SENDFILE = 3;

    public final static String CONSTANT_KEY = "TIMELORD";

    String userName;
    String hostName;

    int port;
    boolean closedConnection;

    ClackData dataToSendToServer;
    ClackData dataToReceiveFromServer;

    ObjectInputStream inFromServer;
    ObjectOutputStream outToServer;

    private Scanner inFromStd;

    public ClackClient(String userName, String hostName, int port) throws IllegalArgumentException{
        try {
            if(userName == null) {
                throw new IllegalArgumentException("Not a valid username");
            }
            if(hostName == null) {
                throw new IllegalArgumentException("Not a valid hostname");
            }
            if(port < 1024) {
                throw new IllegalArgumentException("Not a valid port");
            }
            this.userName = userName;
            this.hostName = hostName;
            this.closedConnection = true;
            this.inFromServer = null;
            this.outToServer = null;
            this.port = port;
        } catch (IllegalArgumentException iae) {
            System.err.println( iae.getMessage() );
        }
    }

    public ClackClient(String userName, String hostName){
        this(userName, hostName, DEFAULT_PORT);
    }

    public ClackClient(String userName){
        this(userName, "localhost", DEFAULT_PORT);
    }

    public ClackClient(){
        this("Anon", "localhost", DEFAULT_PORT);
    }

    public void start(){
        Socket skt = null;
        try {
            skt = new Socket(this.getHostName(), this.getPort());

            outToServer = new ObjectOutputStream( skt.getOutputStream() );
            inFromServer = new ObjectInputStream( skt.getInputStream() );
            inFromStd = new Scanner( new InputStreamReader( System.in ) );

            this.closedConnection = false;

        } catch(UnknownHostException uhe) {
            System.err.println( uhe.getMessage() );
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
        while(!this.closedConnection) {
            readClientData();
            sendData();
            receiveData();
            if(dataToReceiveFromServer != null)
                printData();
        }
        try {
            skt.close();
            inFromStd.close();
            outToServer.close();
            inFromServer.close();
        } catch (IOException ioe) {
            System.err.println( ioe.getMessage() );
        }
    }

    public void readClientData(){
        String cmd = inFromStd.next();
        switch (cmd) {
            case "DONE":
                this.closedConnection = true;
                break;
            case "SEND_FILE":
                String Fname = inFromStd.next();
                dataToSendToServer = new FileClackData(this.getUserName(), Fname, CONSTANT_SENDFILE);
                try {
                    Scanner testScanner = new Scanner( new File (Fname) );
                    testScanner.close();
                }catch(FileNotFoundException fnfe) {
                    this.dataToSendToServer = null;
                    System.err.println(fnfe.getMessage());
                }
                break;
            case "LISTUSERS":
                break;
            default:
                cmd += inFromStd.nextLine();
                this.dataToSendToServer = new MessageClackData(this.getUserName(), cmd, CONSTANT_SENDMESSAGE);
                break;
        }
    }

    public void sendData(){
        try {
            outToServer.writeObject(dataToSendToServer);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public void receiveData(){
        try {
            dataToReceiveFromServer = (ClackData)inFromServer.readObject();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
        }
    }

    public void printData(){
        System.out.println(dataToReceiveFromServer.getData());
    }

    public String getUserName(){
        return this.userName;
    }

    public String getHostName(){
        return this.hostName;
    }

    public int getPort(){
        return this.port;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashcode = 7;
        hashcode = prime*hashcode + this.getPort();
        hashcode = prime*hashcode + this.getHostName().length();
        hashcode = prime*hashcode + this.getUserName().length();
        if(this.closedConnection)
            hashcode = prime*hashcode*-1;
        if(dataToSendToServer != null)
            hashcode = prime*hashcode + dataToSendToServer.hashCode();
        if(dataToReceiveFromServer != null)
            hashcode = prime*hashcode + dataToReceiveFromServer.hashCode();
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClackClient))
            return false;

        ClackClient temp = (ClackClient)obj;

        return this.getUserName().equals(temp.getUserName()) &&
                this.getHostName().equals(temp.getHostName()) &&
                this.getPort() == temp.getPort() ||
                this.closedConnection != temp.closedConnection ||
                ((this.dataToSendToServer != null || temp.dataToSendToServer != null) &&
                        (!this.dataToSendToServer.equals(temp.dataToSendToServer))) ||
                ((this.dataToReceiveFromServer != null || temp.dataToReceiveFromServer != null) &&
                        (!this.dataToReceiveFromServer.equals(temp.dataToReceiveFromServer)));
    }

    @Override
    public String toString() {
        return "The userName for this ClackClient is:  " + this.getUserName() + '\n' +
                "The hostName for this ClackClient is:  " + this.getHostName() + '\n' +
                "The port for this ClackClient is:      " + this.getPort() + '\n' + '\n' +
                "The data sent out is:\n\n" + dataToSendToServer + "\n\n" +
                "The data received is:\n\n" + dataToReceiveFromServer + "\n\n" +
                "The connection is closed: " + closedConnection + '\n';
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            ClackClient c = new ClackClient();
            c.start();
        }
        //else if (args[0].contains("@") && args[0].contains(":")) {
        else {
            String[] strs = args[0].split("[@:]");
            if (strs.length == 3) {
                String userName = strs[0];
                String hostName = strs[1];
                int port = Integer.parseInt(strs[2]);
                ClackClient c = new ClackClient(userName, hostName, port);
                c.start();
            }
            if (strs.length == 2) {
                String userName = strs[0];
                String hostName = strs[1];
                //int port = Integer.parseInt(strs[2]);
                ClackClient c = new ClackClient(userName, hostName);
                c.start();
            }
            if (strs.length == 1) {
                String userName = strs[0];
                //String hostName = strs[1];
                //int port = Integer.parseInt(strs[2]);
                ClackClient c = new ClackClient(userName);
                c.start();
            }
        }
    }






}
