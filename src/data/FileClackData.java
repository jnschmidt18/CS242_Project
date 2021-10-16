package data;

import data.ClackData;

import java.io.*;

public class FileClackData extends ClackData {

    int CONSTANT_SENDFILE = 3;

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

    public String getData(String key){
        return decrypt(this.fileContents, key);
    }

    public void readFileContents() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            if(reader.readLine()==null)
                throw new IOException("File not Found");
            while (reader.readLine() != null) {
                this.fileContents += reader.readLine();
            }
            reader.close();
        }
        catch(Exception misc){
            System.out.println("Error Reading");
        }
    }

    public void readFileContents(String key) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            if(reader.readLine()==null)
                throw new IOException("File not Found");
            while (reader.readLine() != null) {
                this.fileContents += encrypt(reader.readLine(), key);
            }
            reader.close();
        }
        catch(Exception misc){
            System.out.println("Error Reading");
        }
    }

    public void writeFileContents(){
        String output;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
            writer.write(this.fileContents);
            writer.close();
        }
        catch(Exception misc){
            System.out.println("Error Writing");
        }
    }

    public void writeFileContents(String key){
        String output;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
            writer.write(encrypt(this.fileContents, key));
            writer.close();
        }
        catch(Exception misc){
            System.out.println("Error Writing");
        }
    }
}