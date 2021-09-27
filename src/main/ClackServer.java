package main;

import data.ClackData;

public class ClackServer {
    int port;
    boolean closeConnection;
    ClackData dataToReceiveFromClient;
    ClackData DataToSendToClient;

    public ClackServer(int port) {
        this.port = port;
    }

    public ClackServer() {
        this.port = 7000;
    }

    public void start(){}

    public void receiveData(){}

    public void sendData(){}

    public int getPort(){
        return this.port;
    }

    @Override
    public int hashCode(){
        return 0x41;
    }

    @Override
    public boolean equals(Object obj){
        ClackServer other = (ClackServer) obj;
        if(other.port==this.port){
            if(other.closeConnection==this.closeConnection){
                if(other.dataToReceiveFromClient==this.dataToReceiveFromClient){
                    if(other.DataToSendToClient==this.DataToSendToClient){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "main.ClackServer object with port " + this.port + " and activity " + this.closeConnection + " and data " + this.dataToReceiveFromClient + this.DataToSendToClient;
    }

}