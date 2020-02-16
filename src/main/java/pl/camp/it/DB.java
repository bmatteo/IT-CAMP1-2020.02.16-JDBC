package pl.camp.it;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    public static Connection connection;

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DB.connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/personsDb?user=root&password=");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("NIe udało się !!");
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            DB.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void persistPerson(Person person) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO tperson (name, surname, age) VALUES ('")
                    .append(person.getName())
                    .append("', '")
                    .append(person.getSurname())
                    .append("', ")
                    .append(person.getAge())
                    .append(");");

            String sql = sb.toString();

            Statement statement = DB.connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Coś się zepsuło !!");
            e.printStackTrace();
        }
    }

    public static void persistPerson2(Person person) {
        try {
            String sql = "INSERT INTO tperson (name, surname, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = DB.connection.prepareStatement(sql);

            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Coś się zepsuło !!");
            e.printStackTrace();
        }
    }

    public static Person getPersonById(int id) {
        try {
            String sql = "SELECT * FROM tperson WHERE id = ?";
            PreparedStatement preparedStatement = DB.connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Person person = new Person();


                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));

                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Person> getAllPersons() {
        ArrayList<Person> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tperson";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Person person = new Person();

                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setAge(rs.getInt("age"));

                result.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
