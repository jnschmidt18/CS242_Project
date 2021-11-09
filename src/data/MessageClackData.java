package data;

public class MessageClackData extends ClackData {

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

    public MessageClackData( String userName, String message, String key, int type ){
        super(userName, type);
        this.Message = encrypt(message, key);
    }

    @Override
    public String getData() {
        return this.Message;
    }

    @Override
    public String getData(String key){
        return decrypt(this.Message, key);
    }

    @Override
    public int hashCode(){
        return 0x40;
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof MessageClackData))
            return false;

        MessageClackData temp = (MessageClackData)obj;

        return this.getUserName().equals(temp.getUserName()) &&
                this.getType() == temp.getType() &&
                this.getData().equals(temp.getData());
    }

    @Override
    public String toString() {
        return "The userName for this ClackData is:      " + this.getUserName() + '\n' +
                "The Data Type for this ClackData is:     " + this.getType() + '\n' +
                "This ClackData was created at:           " + this.getDate() + '\n' +
                "The data for this ClackData is:          " + this.getData() + '\n';
    }
}
