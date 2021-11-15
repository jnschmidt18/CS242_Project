package data;

import java.io.Serializable;
import java.util.Date;

public abstract class ClackData implements Serializable {

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

    public static String encrypt(String toEncrypt, String key) {
        String encrypted = "";
        int temp = 0;
        int emptySpace = 0;
        boolean isCapital = false;
        for(int i = 0; i < toEncrypt.length(); i++) {
            if((toEncrypt.charAt(i) >= 65 && toEncrypt.charAt(i) <= 90) ||
                    (toEncrypt.charAt(i) >= 97 && toEncrypt.charAt(i) <= 122)) {
                if(toEncrypt.charAt(i) >= 65 && toEncrypt.charAt(i) <= 90)
                    isCapital = true;
                temp = toEncrypt.toLowerCase().charAt(i) - 97;
                temp = ((temp + (key.toLowerCase().charAt((i-emptySpace) % key.length())) - 97) % 26);
                temp += 97;
                if(isCapital)
                    temp -= 32;
            }
            else {
                temp = toEncrypt.charAt(i);
                emptySpace++;
            }
            encrypted += (char)temp;
            temp = 0;
            isCapital = false;
        }
        return encrypted;
    }

    public static String decrypt(String toDecrypt, String key) {
        String decrypted = "";
        int temp = 0;
        int emptySpace = 0;
        boolean isCapital = false;
        for(int i = 0; i < toDecrypt.length(); i++) {
            if((toDecrypt.charAt(i) >= 65 && toDecrypt.charAt(i) <= 90) ||
                    (toDecrypt.charAt(i) >= 97 && toDecrypt.charAt(i) <= 122)) {
                if(toDecrypt.charAt(i) >= 65 && toDecrypt.charAt(i) <= 90)
                    isCapital = true;
                temp = toDecrypt.toLowerCase().charAt(i) - 97;
                temp = ((temp - (key.toLowerCase().charAt((i-emptySpace) % key.length())) + 97 + 26) % 26);
                temp += 97;
                if(isCapital)
                    temp -= 32;
            }
            else {
                temp = toDecrypt.charAt(i);
                emptySpace++;
            }
            decrypted += (char)temp;
            temp = 0;
            isCapital = false;
        }
        return decrypted;
    }

}