package data;

import main.ClackClient;

import java.io.*;
import java.util.Scanner;

public class FileClackData extends ClackData {

    String fileName;
    String fileContents;

    public FileClackData(String userName, String fileName, int type) {
        super(userName, type);
        this.fileName = fileName;
        this.fileContents = "NULL";
    }
    public FileClackData() {
        super(CONSTANT_SENDFILE);
        this.fileName = "";
        this.fileContents = null;
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
            Scanner sc = new Scanner( new File(fileName) );
            fileContents = "";
            while (sc.hasNext()) {
                fileContents += sc.next() + " ";
            }
            sc.close();
        }
        catch(FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }

    public void readFileContents(String key) throws IOException {
        try {
            readFileContents();
            fileContents =  encrypt(fileContents, key);
        }
        catch(FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }

    public void writeFileContents(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
            writer.write(this.fileContents);
            writer.close();
        }
        catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    //this function might need to decrypt instead.
    public void writeFileContents(String key){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName));
            writer.write(encrypt(this.fileContents, key));
            writer.close();
        }
        catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashcode = 7;
        hashcode = prime*hashcode + this.getUserName().length();
        hashcode = prime*hashcode + this.getType();
        hashcode = prime*hashcode + this.getFileName().length();
        if(this.getData() != null)
            hashcode = prime*hashcode + this.getData().length();
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FileClackData))
            return false;

        FileClackData temp = (FileClackData)obj;

        if(this.getData() == null ^ temp.getData() == null)
            return false;

        return this.getUserName().equals(temp.getUserName()) &&
                this.getFileName().equals(temp.getFileName()) &&
                this.getType() == temp.getType() &&
                ((this.getData() == null && temp.getData() == null) || this.getData().equals(temp.getData()));
    }

    @Override
    public String toString() {
        return "The userName for this ClackData is:      " + this.getUserName() + '\n' +
                "The Data Type for this ClackData is:     " + this.getType() + '\n' +
                "This ClackData was created at:           " + this.getDate() + '\n' +
                "The file name for this ClackData is:     " + this.getFileName() + '\n' +
                "The data for this ClackData is:          " + this.getData() + '\n';
    }
}