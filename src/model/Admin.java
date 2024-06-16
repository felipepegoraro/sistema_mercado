// maybe unused

package model;

public class Admin extends Person {
    public Admin(int id, String name, String email, String passwd){
        super(id, name, email, passwd, true);
    }
    
    public Admin(String name, String email, String passwd){
        super(name, email, passwd);
        super.setAdmin(true);
    }
}
