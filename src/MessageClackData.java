public class MessageClackData extends ClackData{

    String Message;

    public MessageClackData(String userName, String Message, int type) {
        super(userName, type);
        this.Message = Message;
    }



    @Override
    public String getData() {
        return this.Message;
    }
}
