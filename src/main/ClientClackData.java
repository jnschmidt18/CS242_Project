package main;

import data.ClackData;

public class ClientClackData  extends ClackData {

    private ClackClient client;

    public ClientClackData(ClackClient client){
        this.client = client;
    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public ClackClient getClientData() {
        return this.client;
    }

    @Override
    public String getData(String key) {
        return null;
    }
}
