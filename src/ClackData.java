import java.util.Date;

public abstract class ClackData {

    int CONSTANT_SENDMESSAGE = 2;

    String userName;
    int type;
    Date date;

    public ClackData(String userName, int type){
        this.userName = userName;
        this.type = type;
    }

    public ClackData(int type) {
        this.userName = "Anon";
        this.type = type;
    }

    public ClackData() {
        
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
}

