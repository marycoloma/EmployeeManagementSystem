import java.util.List;

public interface EmployeeDAO {

    /**
     * Adds a new employee to the database.
     */
    void addEmployee(Employee employee) throws DatabaseException;

    /**
     * Retrieves an employee by their ID.
     */
    Employee getEmployeeById(int id) throws DatabaseException;

    /**
     * Retrieves all employees from the database.
     */
    List<Employee> getAllEmployees() throws DatabaseException;

    /**
     * Updates an existing employee's information.
     */
    void updateEmployee(Employee employee) throws DatabaseException;

    /**
     * Deletes an employee from the database by ID.
     */
    void deleteEmployee(int id) throws DatabaseException;
}
