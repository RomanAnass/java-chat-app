
package rmi;

import java.io.Serializable;


public class Dfile implements Serializable{
 
    int idEmit;
    int idRece;
    String nameFile;
    int len;
    byte[] b;

    public Dfile(int idEmit, int idRece, String nameFile, int len, byte[] b) {
        this.idEmit = idEmit;
        this.idRece = idRece;
        this.nameFile = nameFile;
        this.len = len;
        this.b = b;
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

    public void setIdRece(int idRece) {
        this.idRece = idRece;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getB() {
        return b;
    }

    public void setB(byte[] b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Dfile{" + "idEmit=" + idEmit + ", idRece=" + idRece + ", nameFile=" + nameFile + ", len=" + len + ", b=" + b + '}';
    }
    
       
}
