
public class MessageClackData extends ClackData{

    int CONSTANT_SENDMESSAGE = 2;
    String Message;

    public MessageClackData(String userName, String Message, int type) {
        super(userName, type);
        this.Message = Message;
    }

    public MessageClackData(){
        super();
        new MessageClackData("Anon", "NULL", CONSTANT_SENDMESSAGE);
    }

    @Override
    public String getData() {
        return this.Message;
    }
}
