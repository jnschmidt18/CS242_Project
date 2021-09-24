public class FileClackData extends ClackData {

    String fileName;
    String fileContents;

    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = "NULL";
    }
    public FileClackData() {


    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getData() {
        return this.fileContents;
    }

    //public readFileContents{
    //
    //}

    //public writeFileContents(){
     //
    //}

}