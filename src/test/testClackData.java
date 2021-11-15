package test;

import data.*;

import java.io.IOException;

public class testClackData{
    public static void main(String[] args) throws IOException {
        ClackData dm1 = new MessageClackData("testUser", "test message", 2);
        ClackData dmEQ = new MessageClackData("testUser", "test message", 2);
        ClackData dm2 = new MessageClackData("testUser", "test message", "key", 2);
        ClackData dm3 = new MessageClackData();

        FileClackData df1 = new FileClackData("testUser", "example.txt", 3);
        FileClackData dfEQ = new FileClackData("testUser", "example.txt", 3);
        FileClackData df2 = new FileClackData();
/*
        System.out.println(dm1.equals(dmEQ));
        System.out.println(dm1.equals(dm2));
        System.out.println(dm1);
        System.out.println(dm2);
        System.out.println(dm2.getData("key"));
        System.out.println(dm3);

        System.out.println(df1.equals(dfEQ));
        System.out.println(df1.equals(df2));
        System.out.println(df1);
        System.out.println(df2);
*/
        df1.setFileName("test.txt");
        df1.readFileContents();
        System.out.println(df1.getData());
        df1.readFileContents("key");
        System.out.println(df1.getData());
        System.out.println(df1.getData("key"));
        df1.setFileName("testOut.txt");
        df1.writeFileContents();

    }
}

