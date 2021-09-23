import java.util.Date;

public class ClackData {
    String userName;
    int type;
    Date date;

    public ClackData(String userName, int type){
        this.userName = userName;
        this.type = type;
    }

    public ClackData(int type){
        this.userName = "Anon";
        this.type = type;
    }


}

