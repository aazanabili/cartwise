public class Marketing {
    private final int id;
    private String name;

    public Marketing(int i, String n) {
        id = i;
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
