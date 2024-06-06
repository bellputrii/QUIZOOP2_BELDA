import java.util.Random;
public abstract class Employee {
    private String name;
    private String dateOfBirth;
    private String kodeDepartemen;
    private int gaji;
    private String id;
    public Employee(String name, String dateOfBirth, String kodeDepartemen, int gaji) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.kodeDepartemen = kodeDepartemen;
        this.gaji = gaji;
        this.id = generateID();
    }
    private String generateID() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return String.format("000%s%s%03d", kodeDepartemen, dateOfBirth.replaceAll("/", ""), randomNumber);
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getKodeDepartemen() {
        return kodeDepartemen;
    }

    public int getGaji() {
        return gaji;
    }

    public String getId() {
        return id;
    }
}
