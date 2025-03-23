package Model;


@SuppressWarnings("unused")
public class Role {
    private int id;
    private String role_name;
    private String role_position;
    private String role_office;
    private int role_age;
    private String role_start_date;
    private int role_salary;

    public Role(){

    }

    public Role(String role_name, String role_position, String role_office, int role_age, String role_start_date, int role_salary){
        this.role_name = role_name;
        this.role_position = role_position;
        this.role_office = role_office;
        this.role_age = role_age;
        this.role_start_date = role_start_date;
        this.role_salary = role_salary;
    }

    public int getRole_id() {
        return id;
    }

    public void setRole_id(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_position() {
        return role_position;
    }

    public void setRole_position(String role_position) {
        this.role_position = role_position;
    }

    public String getRole_office() {
        return role_office;
    }

    public void setRole_office(String role_office) {
        this.role_office = role_office;
    }

    public int getRole_age() {
        return role_age;
    }

    public void setRole_age(int role_age) {
        this.role_age = role_age;
    }

    public String getRole_start_date() {
        return role_start_date;
    }

    public void setRole_start_date(String role_start_date) {
        this.role_start_date = role_start_date;
    }

    public int getRole_salary() {
        return role_salary;
    }

    public void setRole_salary(int role_salary) {
        this.role_salary = role_salary;
    }
}
