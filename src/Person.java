import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {

    private String name;
    private String nid;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;

    public Person() {}

    public Person(String name, String nid, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.nid = nid;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    public Person(String name, String nid, String email, String phoneNumber, String dateOfBirth) {
        this.name = name;
        this.nid = nid;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.setDateOfBirth(dateOfBirth);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int getAge(){
        LocalDate today = LocalDate.now();
        Period period = Period.between(this.dateOfBirth, today);
        return period.getYears();
    }
}
