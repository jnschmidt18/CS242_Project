package test;

import data.*;

public class testClackData{
    public static void main(String[] args){
        ClackData dataMessage = new MessageClackData("testUser", "test text", 1);
        ClackData dataFile = new FileClackData("testUser", "example.txt", 1);
    }
}

