package DemoJdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

public class EmployeeDAO {
    public void addEmployee(Employee emp) {
        try (Connection conn = Dbconnection.getConnection();
             CallableStatement cs = conn.prepareCall("{call insert_employee(?, ?, ?)}")) {

            cs.setInt(1, emp.getId());
            cs.setString(2, emp.getName());
            cs.setString(3, emp.getDepartment());

            cs.execute();
            System.out.println("Inserted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllEmployees() {
        try (Connection conn = Dbconnection.getConnection();
             CallableStatement cs = conn.prepareCall("{call get_all_employees()}");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("empid"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Department: " + rs.getString("dept"));
                System.out.println("-----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(int id, String department) {
        try (Connection conn = Dbconnection.getConnection();
             CallableStatement cs = conn.prepareCall("{call update_employee(?, ?)}")) {

            cs.setInt(1, id);
            cs.setString(2, department);

            cs.execute();
            System.out.println("Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteEmployee(int id) {
        try (Connection conn = Dbconnection.getConnection();
             CallableStatement cs = conn.prepareCall("{call delete_employee(?)}")) {

            cs.setInt(1, id);

            cs.execute();
            System.out.println("Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEmployeeById(int id) {
        try (Connection conn = Dbconnection.getConnection();
             CallableStatement cs = conn.prepareCall("{call get_employee_by_id(?, ?, ?)}")) {

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);

            cs.execute();

            System.out.println("Name: " + cs.getString(2));
            System.out.println("Department: " + cs.getString(3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}