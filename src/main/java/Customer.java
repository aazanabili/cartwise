/**
 * Abbildung der Kundengruppen als Klasse zur Analyse
 */
public class Customer {

    private String gender;  // Geschlecht
    private String age;  // Alter
    private String children;  // Kinder (Ja/Nein)
    private String maritalStatus;  // Familienstand
    private String employed;  // Berufstaetig
    private String day;  // Einkaufstag
    private String time;  // Einkaufsuhrzeit
    private String residence;  // Wohnort
    private String income;  // Haushaltsnettoeinkommen
    private String regular;  // Stammkunde


    public Customer(String[] params) {
        this.gender = params[0];
        this.age = params[1];
        this.children = params[2];
        this.maritalStatus = params[3];
        this.employed = params[4];
        this.day = params[5];
        this.time = params[6];
        this.residence = params[7];
        this.income = params[8];
        this.regular = params[9];
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) { this.residence = residence; }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmployed() {
        return employed;
    }

    public void setEmployed(String employed) {
        this.employed = employed;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
