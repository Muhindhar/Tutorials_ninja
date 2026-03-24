package DemoJdbc;

import java.util.Scanner;

public class EmployeeCrud {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Insert Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Update Employee Department");
            System.out.println("4. Delete Employee");
            System.out.println("5. Get Employee by ID");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\n-- Insert Employee --");

                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    dao.addEmployee(new Employee(id, name, dept));
                    break;

                case 2:
                    dao.getAllEmployees();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new Department: ");
                    String newDept = sc.nextLine();

                    dao.updateEmployee(uid, newDept);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();

                    dao.deleteEmployee(did);
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    int gid = sc.nextInt();

                    dao.getEmployeeById(gid);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}