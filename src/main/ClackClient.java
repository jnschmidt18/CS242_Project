package main;

import data.ClackData;
import data.FileClackData;

import java.util.Scanner;

public class ClackClient{

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
    }

    public ClackClient(String userName, String hostName){
        this.port = 7000;
        this.userName = userName;
        this.hostName = hostName;

    }

    public ClackClient(){
    }

    public void start(){

    }

    public void readClientData(){
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        if(input=="DONE")
                this.closeConnection=true;
        if(input.substring(0,8)=="SENDFILE"){
            FileClackData dataToSendToServer  = new FileClackData(this.userName, input.substring(8,input.length()), 3);
        }
        if(input == "LISTUSERS"){}
        else{
            FileClackData dataToSendToServer  = new FileClackData();
        }
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
