import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MainCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("==========================================");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        while (running) {

            displayMenu();

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {

            case 1:
                addEmployee();
                break;

            case 2:
                viewEmployees();
                break;

            case 3:
                updateEmployee();
                break;

            case 4:
                deleteEmployee();
                break;

            case 5:
                System.out.println("\nExiting Employee Management System...");
                running = false;
                break;

            default:
                System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n==========================================");
        System.out.println("                MAIN MENU");
        System.out.println("==========================================");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employees");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
        System.out.println("==========================================");
    }

    private static void addEmployee() {

        System.out.println("\n==========================================");
        System.out.println("              ADD EMPLOYEE");
        System.out.println("==========================================");

        try {

            Employee employee = new Employee();

            String name = getStringInput("Enter employee name: ");
            if (name.isEmpty()) throw new InvalidInputException("Name cannot be empty.");
            employee.setName(name);

            int age = getIntInput("Enter employee age: ");
            if (age < 18 || age > 100) throw new InvalidInputException("Age must be between 18 and 100.");
            employee.setAge(age);

            String accessLevel = getStringInput("Enter access level (Admin/User): ");
            if (!accessLevel.equalsIgnoreCase("Admin") && !accessLevel.equalsIgnoreCase("User")) {
                throw new InvalidInputException("Access level must be Admin or User.");
            }
            employee.setAccessLevel(accessLevel);

            double salary = getDoubleInput("Enter employee salary: ");
            if (salary < 0) throw new InvalidInputException("Salary cannot be negative.");
            employee.setSalary(salary);

            int departmentId = getIntInput("Enter department ID (1 = HR, 2 = IT, 3 = Finance): ");
            if (departmentId < 1 || departmentId > 3) throw new InvalidInputException("Department ID must be 1, 2, or 3.");
            employee.setDepartmentId(departmentId);

            String position = getStringInput("Enter employee position: ");
            employee.setPosition(position);

            System.out.println("\nEnter hire date and time.");
            System.out.println("Format: yyyy-MM-dd HH:mm");
            LocalDateTime hireDate = getDateTimeInput("Hire date: ");
            employee.setHireDate(hireDate);

            System.out.print("Does the employee have a termination date? (Y/N): ");
            String terminated = scanner.nextLine().trim();

            if (terminated.equalsIgnoreCase("Y")) {
                System.out.println("Format: yyyy-MM-dd HH:mm");
                LocalDateTime terminationDate = getDateTimeInput("Termination date: ");
                employee.setTerminationDate(terminationDate);
            } else {
                employee.setTerminationDate(null);
            }

            employeeDAO.addEmployee(employee);
            System.out.println("\nEmployee has been added.");

        } catch (InvalidInputException e) {
            System.out.println("\nInvalid input: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("\nDatabase error: " + e.getMessage());
        }
    }

    private static void viewEmployees() {

        System.out.println("\n==========================================");
        System.out.println("             VIEW EMPLOYEES");
        System.out.println("==========================================");

        try {

            List<Employee> employees = employeeDAO.getAllEmployees();

            if (employees == null || employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }

            System.out.println("\nTotal employees: " + employees.size());

            for (Employee employee : employees) {
                System.out.println("------------------------------------------");
                System.out.println("Employee ID: " + employee.getId());
                System.out.println("Name: " + employee.getName());
                System.out.println("Age: " + employee.getAge());
                System.out.println("Access Level: " + employee.getAccessLevel());
                System.out.println("Salary: $" + String.format("%.2f", employee.getSalary()));
                System.out.println("Department ID: " + employee.getDepartmentId());
                System.out.println("Position: " + employee.getPosition());
                System.out.println("Hire Date: " + employee.getHireDate());
                System.out.println("Termination Date: " + employee.getTerminationDate());
            }

            System.out.println("------------------------------------------");

        } catch (DatabaseException e) {
            System.out.println("\nDatabase error: " + e.getMessage());
        }
    }

    private static void updateEmployee() {

        System.out.println("\n==========================================");
        System.out.println("            UPDATE EMPLOYEE");
        System.out.println("==========================================");

        try {

            int id = getIntInput("Enter the employee ID to update: ");
            Employee employee = employeeDAO.getEmployeeById(id);

            if (employee == null) {
                System.out.println("\nNo employee was found with ID " + id);
                return;
            }

            System.out.println("\nEmployee found:");
            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Position: " + employee.getPosition());

            String name = getStringInput("Enter new name: ");
            if (name.isEmpty()) throw new InvalidInputException("Name cannot be empty.");
            employee.setName(name);

            int age = getIntInput("Enter new age: ");
            if (age < 18 || age > 100) throw new InvalidInputException("Age must be between 18 and 100.");
            employee.setAge(age);

            String accessLevel = getStringInput("Enter new access level: ");
            if (!accessLevel.equalsIgnoreCase("Admin") && !accessLevel.equalsIgnoreCase("User")) {
                throw new InvalidInputException("Access level must be Admin or User.");
            }
            employee.setAccessLevel(accessLevel);

            double salary = getDoubleInput("Enter new salary: ");
            if (salary < 0) throw new InvalidInputException("Salary cannot be negative.");
            employee.setSalary(salary);

            int departmentId = getIntInput("Enter new department ID (1-3): ");
            if (departmentId < 1 || departmentId > 3) throw new InvalidInputException("Department ID must be 1, 2, or 3.");
            employee.setDepartmentId(departmentId);

            String position = getStringInput("Enter new position: ");
            employee.setPosition(position);

            System.out.println("\nEnter new hire date and time.");
            System.out.println("Format: yyyy-MM-dd HH:mm");
            LocalDateTime hireDate = getDateTimeInput("Hire date: ");
            employee.setHireDate(hireDate);

            System.out.print("Does the employee have a termination date? (Y/N): ");
            String terminated = scanner.nextLine().trim();

            if (terminated.equalsIgnoreCase("Y")) {
                System.out.println("Format: yyyy-MM-dd HH:mm");
                LocalDateTime terminationDate = getDateTimeInput("Termination date: ");
                employee.setTerminationDate(terminationDate);
            } else {
                employee.setTerminationDate(null);
            }

            employeeDAO.updateEmployee(employee);
            System.out.println("\nEmployee update completed.");

        } catch (InvalidInputException e) {
            System.out.println("\nInvalid input: " + e.getMessage());
        } catch (DatabaseException e) {
            System.out.println("\nDatabase error: " + e.getMessage());
        }
    }

    private static void deleteEmployee() {

        System.out.println("\n==========================================");
        System.out.println("            DELETE EMPLOYEE");
        System.out.println("==========================================");

        try {

            int id = getIntInput("Enter the employee ID to delete: ");
            Employee employee = employeeDAO.getEmployeeById(id);

            if (employee == null) {
                System.out.println("\nNo employee was found with ID " + id);
                return;
            }

            System.out.println("\nEmployee selected:");
            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Position: " + employee.getPosition());

            System.out.print("\nAre you sure you want to delete this employee? (Y/N): ");
            String confirmation = scanner.nextLine().trim();

            if (confirmation.equalsIgnoreCase("Y")) {
                employeeDAO.deleteEmployee(id);
                System.out.println("\nEmployee deleted successfully.");
            } else {
                System.out.println("\nDelete operation cancelled.");
            }

        } catch (DatabaseException e) {
            System.out.println("\nDatabase error: " + e.getMessage());
        }
    }

    private static int getIntInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static double getDoubleInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static String getStringInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }

    private static LocalDateTime getDateTimeInput(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return LocalDateTime.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
                System.out.println("Please use: yyyy-MM-dd HH:mm");
            }
        }
    }
}
