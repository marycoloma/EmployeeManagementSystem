import java.time.LocalDateTime;
import java.util.List;

public class TestDB {
    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAOImpl();

        try {
            System.out.println("--- Testing Add Employee ---");

            Employee emp = new Employee();
            emp.setName("Alice Smith");
            emp.setAge(30);
            emp.setAccessLevel("Admin");
            emp.setSalary(75000.0);
            emp.setDepartmentId(1); 
            emp.setHireDate(LocalDateTime.now());
            emp.setPosition("Senior Developer");

            dao.addEmployee(emp);

            System.out.println("\n--- Testing List All Employees ---");

            List<Employee> list = dao.getAllEmployees();
            for (Employee e : list) {
                System.out.println("Name: " + e.getName() + ", Position: " + e.getPosition());
            }

        } catch (DatabaseException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
