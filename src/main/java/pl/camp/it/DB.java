package pl.camp.it;

import java.sql.*;

public class DB {
    public static Connection connection;

    public static void connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DB.connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/personsDb?user=root&password=");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("NIe udało się !!");
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
}
