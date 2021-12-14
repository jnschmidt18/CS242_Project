package data;

import main.ClackClient;

public class MessageClackData extends ClackData {

    private String Message;

    public MessageClackData(String userName, String Message, int type) {
        super(userName, type);
        this.Message = Message;
    }

    public MessageClackData( String userName, String message, String key, int type ){
        super(userName, type);
        this.Message = encrypt(message, key);
    }

    public MessageClackData(){
        super("Anon", CONSTANT_SENDMESSAGE);
        this.Message = null;
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
        final int prime = 31;
        int hashcode = 7;
        hashcode = prime*hashcode + this.getType();
        hashcode = prime*hashcode + this.getUserName().length();
        if(this.getData() != null)
            hashcode = prime*hashcode + this.getData().length();
        return hashcode;
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
