package test;

import main.ClackServer;

public class testClackServer {

    public static void main(String[] args) {
        ClackServer server1 = new ClackServer(1111);
        ClackServer server2 = new ClackServer();

        System.out.println(server1);
        System.out.println(server2);

        System.out.println();

        System.out.println(server1.hashCode());
        System.out.println(server2.hashCode());

        System.out.println();

        System.out.println(!(server1.equals(server2)));
        System.out.println(server2.equals(server2));

    }
}
