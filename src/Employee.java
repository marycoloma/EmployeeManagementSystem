public class Employee extends Person {
    private String accessLevel;
    private double salary;
    private int departmentId;
    private java.time.LocalDateTime hireDate;
    private java.time.LocalDateTime terminationDate;
    private String position;

    public String getAccessLevel() {
        return accessLevel;
    }

    public double getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public java.time.LocalDateTime getHireDate() {
        return hireDate;
    }

    public java.time.LocalDateTime getTerminationDate() {
        return terminationDate;
    }

    public String getPosition() {
        return position;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setHireDate(java.time.LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public void setTerminationDate(java.time.LocalDateTime terminationDate) {
        this.terminationDate = terminationDate;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void displayInfo() {
        // Implementation will be completed in Part B
    }
}
