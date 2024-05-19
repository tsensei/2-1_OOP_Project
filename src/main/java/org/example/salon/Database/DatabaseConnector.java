package org.example.salon.Database;

import org.example.salon.Database.Model.Appointment;
import org.example.salon.Database.Model.Service;
import org.example.salon.Database.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.namespace.QName;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://viaduct.proxy.rlwy.net:12244/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "FQrpiREqkJrmYGtsvWCSYpcAjLXUpmLK";

    public static Connection getConnection() {
        try {
            // Registering the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establishing the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failure.");
            e.printStackTrace();
        }
        return null;
    }

    public static void createSchema() {
        String createTableSQL = "CREATE TABLE USERS (user_id SERIAL PRIMARY KEY, username VARCHAR(50) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, role VARCHAR(10) NOT NULL CHECK (role IN ('customer', 'employee')), full_name VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, phone_number VARCHAR(20), address TEXT, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'users' created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating schema.");
            e.printStackTrace();
        }
    }

    public static void insertUser(User u) {
        String insertUserSQL = "INSERT INTO USERS (username, password, role, full_name, email, phone_number) VALUES (?,?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            preparedStatement.setString(1, u.getUserName());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setString(3, u.getRole());
            preparedStatement.setString(4, u.getFullName());
            preparedStatement.setString(5, u.getEmail());
            preparedStatement.setString(6, u.getPhone());
            preparedStatement.executeUpdate();
            System.out.println("user inserted: " + u.getUserName());
        } catch (SQLException e) {
            System.err.println("Error inserting user.");
            e.printStackTrace();
        }
    }

    public static List<String> getAllUsers() {
        String selectAllUsersSQL = "SELECT * FROM users";
        List<String> users = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAllUsersSQL)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("password");
                users.add("ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Password: " + password);

            }

            System.out.println(users);
        } catch (SQLException e) {
            System.err.println("Error retrieving users.");
            e.printStackTrace();
        }

        return users;
    }

    public static boolean validateUser(String username) {
        String selectUserSQL = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error validating user.");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateUser(String username, String password) {
        String selectUserSQL = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.err.println("Error validating user.");
            e.printStackTrace();
            return false;
        }
    }

    
    public static void insertService(Service s) {
        String insertServiceSQL = "INSERT INTO SERVICES (service_name, price, category, duration) VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertServiceSQL)) {

            preparedStatement.setString(1, s.getName());
            preparedStatement.setDouble(2, s.getPrice());
            preparedStatement.setString(3, s.getCategory());
            preparedStatement.setInt(4, s.getDuration());
            preparedStatement.executeUpdate();
            System.out.println("Service inserted: " + s.getName());
        } catch (SQLException e) {
            System.err.println("Error inserting service.");
            e.printStackTrace();
        }

    }

    public static int getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM Users WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                } else {
                    System.out.println("User not found");
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public static User getUserObjectbyUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username ILIKE ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String fullName = rs.getString("full_name");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phone_number");
                    String role = rs.getString("role");
                    
                    return new User(userId, username, fullName, email, phoneNumber, role);
                } else {
                    System.out.println("User not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Service getServicebyName(String service_name) {
        String sql = "SELECT * FROM services WHERE service_name ILIKE ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, service_name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int service_id = rs.getInt("service_id");
                    String category = rs.getString("category");
                    Double price = rs.getDouble("price");
                    int duration = rs.getInt("duration");
                    return new Service(service_id, service_name, price, duration, category);
                } else {
                    System.out.println("User not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public static int getServiceIdByName(String serviceName) {
        String sql = "SELECT service_id FROM Services WHERE service_name ILIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, serviceName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("service_id");
                } else {
                    System.out.println("Service not found");
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static void addAppointment(Appointment a) {
        String insertAppointmentSQL = "INSERT INTO APPOINTMENTS (customer_id, stylist_id, service_id, appointment_date, appointment_time, status) VALUES (?,?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertAppointmentSQL)) {

            preparedStatement.setInt(1, a.getCustomer().getUserID());
            preparedStatement.setInt(2, a.getStylist().getUserID());
            preparedStatement.setInt(3, a.getService().getServiceID());
            preparedStatement.setDate(4, a.getDate());
            preparedStatement.setTime(5, a.getTime());
            preparedStatement.setString(6, a.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Appointment inserted.");
        } catch (SQLException e) {
            System.err.println("Error inserting appointment.");
            e.printStackTrace();
        }
    }


    public static ArrayList<Service> getServicesByCategory(String category) {
        ArrayList<Service> services = new ArrayList<>();
        String query = "SELECT * FROM Services WHERE category = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

             stmt.setString(1, category);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int serviceID = rs.getInt("service_id");
                    String service_name = rs.getString("service_name");
                    Double price = rs.getDouble("price");
                    Service s = new Service(serviceID, service_name, price);
                    services.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }


    public static ArrayList<User> getEmployees() {
        ArrayList<User> employees = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE role = 'employee'";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String role = rs.getString("role");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");

                User user = new User(userId, username, fullName, email, phoneNumber, role);
                employees.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static void clearDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // Retrieve all table names
            ResultSet resultSet = statement.executeQuery(
                    "SELECT tablename FROM pg_tables WHERE schemaname = 'public'");

            // Collect all table names
            List<String> tables = new ArrayList<>();
            while (resultSet.next()) {
                tables.add(resultSet.getString("tablename"));
            }

            // Drop each table
            for (String table : tables) {
                statement.execute("DROP TABLE IF EXISTS " + table + " CASCADE");
                System.out.println("Table '" + table + "' deleted if it existed.");
            }
        } catch (SQLException e) {
            System.err.println("Error clearing database.");
            e.printStackTrace();
        }
    }
}