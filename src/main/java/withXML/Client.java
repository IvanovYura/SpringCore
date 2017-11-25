package withXML;

public class Client {

    private int id;
    private String fullName;
    private String grreting;

    public Client(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setGrreting(String grreting) {
        this.grreting = grreting;
    }

}
