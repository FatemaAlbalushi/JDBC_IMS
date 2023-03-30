package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
       Connection connection = null;
       Statement statement = null;
       ResultSet rs = null;

       System.out.println(System.getenv("DB_USER_NAME"));
       System.out.println(System.getenv("DB_USER_PASS"));

      try {
           connection= DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/tradb",
                   System.getenv("DB_USER_NAME"),
                   System.getenv("DB_USER_PASS")
           );
           statement = connection.createStatement();


           rs = statement.executeQuery("select * from student ;");

          while (rs.next()){
              int id = rs.getInt("id");
              String name = rs.getString("name");
              String email = rs.getString("email");
              int age = rs.getInt("age");

              System.out.println("ID: "+ id + ", Name: "+ name + ", Email: " + email + ", age: " + age);

          }

          int idToUpdate = 3;
          String newName = "New Name";
          statement.executeUpdate("UPDATE student SET name = '" + newName + "' WHERE id = " + idToUpdate);
          System.out.println("Updated record with Id " + idToUpdate + " to have name "+ newName);


      }catch (SQLException e){
          throw new RuntimeException(e);
      }

    }
}