
package rmi;

import java.io.Serializable;


public class User implements Serializable{
    private int id;
    private String firstname;
    private String lastname;
    private String password;
    private int isLogin;
    private int isAdmin;

    public User() {
    }

    public User(String firstname, String lastname, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public User(int id, String firstname, String lastname, String password, int isLogin, int isAdmin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.isLogin = isLogin;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", isLogin=" + isLogin + ", isAdmin=" + isAdmin + '}';
    }
    
    
    
    
    
    

    
    
    
}
