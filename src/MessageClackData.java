
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

    @Override
    public int hashCode(){
        return 0x40;
    }

    @Override
    public boolean equals(Object obj){
        MessageClackData other = (MessageClackData) obj;
        if(this.Message == other.Message){
            if(this.userName == other.userName){
                if(this.type == other.type){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return ("Message with user " + super.userName + " and contents " + this.Message + " and type " + super.type);
    }
}
