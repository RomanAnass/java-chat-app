
package rmi;

import java.io.Serializable;


public class Message implements Serializable{
    
    private int idEmit;
    private int idRece;
    private String Message;
    String file = null;
    private String nameFile;

    public Message(int idEmit, int idRece, String Message) {
        this.idEmit = idEmit;
        this.idRece = idRece;
        this.Message = Message;
    }

    public Message(int idEmit, int idRece, String nameFile, String file) {
        this.idEmit = idEmit;
        this.idRece = idRece;
        this.file = file;
        this.nameFile = nameFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public int getIdEmit() {
        return idEmit;
    }
    
    public void setIdEmit(int idEmit) {
        this.idEmit = idEmit;
    }

    public int getIdRece() {
        return idRece;
    }

    public void setIdRece(int idReci) {
        this.idRece = idReci;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Message{" + "idEmit=" + idEmit + ", idRece=" + idRece + ", Message=" + Message + ", file=" + file + ", nameFile=" + nameFile + '}';
    }
       
}
