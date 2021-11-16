package test;

import main.ClackClient;

public class testClackClient{
    public static void main(String[] args){
        ClackClient client1 = new ClackClient("user", "host", 1234);
        ClackClient client2 = new ClackClient("user", "host", 1234);
        ClackClient client3 = new ClackClient();

        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);

        System.out.println(client1.hashCode());
        System.out.println(client2.hashCode());
        System.out.println(client3.hashCode());

        System.out.println();

        System.out.println(client1.equals(client2));
        System.out.println(!(client2.equals(client3)));
        System.out.println(client3.equals(client3));

    }

}
