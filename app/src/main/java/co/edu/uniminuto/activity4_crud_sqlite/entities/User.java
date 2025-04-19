package co.edu.uniminuto.activity4_crud_sqlite.entities;

public class User {
    private int document;
    private String name;
    private String lastName;
    private String user;
    private String pass;
    private String status;



    public User() {

    }

    public User(int document, String name, String lastName, String user, String pass) {
            this.document = document;
            this.name = name;
            this.lastName = lastName;
            this.user = user;
            this.pass = pass;

    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("document=").append(document);
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", pass='").append(pass).append('\'');
        sb.append(", Status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
