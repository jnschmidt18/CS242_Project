package main;

import data.ClackData;

public class ClackClient extends ClackData {

    String userName;
    String hostName;
    int port;
    boolean closeConnection;
    ClackData dataToSendToServer;
    ClackData dataToReceiveFromServer;

    public ClackClient(String userName, String hostName, int port){
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        // no clue about closeConnection maybe true???
        dataToSendToServer();
        dataToReceiveFromServer();


    }

    public ClackClient(String userName, String hostName){
        this.port = 7000;
        this.userName = userName;
        this.hostName = hostName;

    }

    public ClackClient(){
        //huh?

    }

    public void start(){

    }

    public void readClientData(){

    }

    public void sendData(){

    }

    public void receiveData(){

    }

    public void printData(){

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








}
