package data;

import java.util.Date;

public abstract class ClackData {

    public final static int CONSTANT_LISTUSERS = 0;
    public final static int CONSTANT_LOGOUT = 1;
    public final static int CONSTANT_SENDMESSAGE = 2;
    public final static int CONSTANT_SENDFILE = 3;

    private String userName;
    private int type;
    private Date date;

    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        date = new Date();
    }

    public ClackData(int type) {
        this("Anon", type);
    }

    public ClackData() {
        this("Anon", CONSTANT_LISTUSERS);
    }

    public int getType() {
        return this.type;
    }

    public String getUserName() {
        return this.userName;
    }

    public Date getDate() {
        return this.date;
    }

    public abstract String getData();

    public abstract String getData( String key );

    protected String encrypt(String text, String key) {
        int counter = 0;
        while (key.length() != text.length()) {
            key += key.charAt(counter);
            counter++;
        }
        String encrypted = "";
        for (int i = 0; i < text.length(); i++) {
            int charVal = ((text.charAt(i) + key.charAt(i)) % 26) + 65;
            encrypted += (char) (charVal);
        }
        return encrypted;
    }

    protected String decrypt(String text, String key) {
        int counter = 0;
        while (key.length() != text.length()) {
            key += key.charAt(counter);
            counter++;
        }
        String decrypted = "";
        for (int i = 0; i < text.length(); i++) {
            int charVal = ((text.charAt(i) - key.charAt(i) + 26) % 26) + 65;
            decrypted += (char) (charVal);
        }
        return decrypted;
    }

}