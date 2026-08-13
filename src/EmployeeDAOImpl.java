import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void addEmployee(Employee employee) throws DatabaseException {
        String sql = "INSERT INTO employees (name, age, access_level, salary, department_id, hire_date, termination_date, position) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getAccessLevel());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setInt(5, employee.getDepartmentId());
            pstmt.setTimestamp(6, Timestamp.valueOf(employee.getHireDate()));
            pstmt.setTimestamp(7, employee.getTerminationDate() != null ? Timestamp.valueOf(employee.getTerminationDate()) : null);
            pstmt.setString(8, employee.getPosition());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error adding employee", e);
        }
    }

    @Override
    public Employee getEmployeeById(int id) throws DatabaseException {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        Employee employee = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                employee = mapResultSetToEmployee(rs);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching employee by ID", e);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() throws DatabaseException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching all employees", e);
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) throws DatabaseException {
        String sql = "UPDATE employees SET name = ?, age = ?, access_level = ?, salary = ?, department_id = ?, hire_date = ?, termination_date = ?, position = ? WHERE employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getAccessLevel());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.setInt(5, employee.getDepartmentId());
            pstmt.setTimestamp(6, Timestamp.valueOf(employee.getHireDate()));
            pstmt.setTimestamp(7, employee.getTerminationDate() != null ? Timestamp.valueOf(employee.getTerminationDate()) : null);
            pstmt.setString(8, employee.getPosition());
            pstmt.setInt(9, employee.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error updating employee", e);
        }
    }

    @Override
    public void deleteEmployee(int id) throws DatabaseException {
        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting employee", e);
        }
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();

        emp.setId(rs.getInt("employee_id"));
        emp.setName(rs.getString("name"));
        emp.setAge(rs.getInt("age"));
        emp.setAccessLevel(rs.getString("access_level"));
        emp.setSalary(rs.getDouble("salary"));
        emp.setDepartmentId(rs.getInt("department_id"));
        emp.setHireDate(rs.getTimestamp("hire_date").toLocalDateTime());

        Timestamp term = rs.getTimestamp("termination_date");
        if (term != null) {
            emp.setTerminationDate(term.toLocalDateTime());
        }

        emp.setPosition(rs.getString("position"));

        return emp;
    }
}
