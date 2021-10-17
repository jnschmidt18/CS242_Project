package test;

import main.ClackClient;

public class testClackClient{
    public static void main(String[] args){
        ClackClient client1 = new ClackClient("TestUserName", "TestHostName", 2476);
        ClackClient client2 = new ClackClient("TestUserName", "TestHostName");
        ClackClient client3 = new ClackClient("TestUserName");
    }

}
