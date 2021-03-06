package com.bridgelabz.addressbook;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDatabase {
    private static AddressBookDatabase addressBookDatabase;

    private AddressBookDatabase() {

    }

    public static AddressBookDatabase getInstance() {
        if (addressBookDatabase == null)
            addressBookDatabase = new AddressBookDatabase();
        return addressBookDatabase;
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_db?useSSL=false";
        String userName = "root";
        String password = "Kaju9596#";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!!!" + connection);
        return connection;
    }


    public List<Person> readData() {
        String query = "SELECT * from person;";
        return this.getPersonDetailsFromDatabase(query);
    }

    private List<Person> getPersonDetailsFromDatabase(String query) {
        List<Person> personList = new ArrayList<Person>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            personList = this.getPersonData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    private List<Person> getPersonData(ResultSet resultSet) {

        List<Person> personList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String phoneNumber = resultSet.getString("phoneNumber");
                String email = resultSet.getString("email");
                LocalDate entryDate = resultSet.getDate("entryDate").toLocalDate();
                personList.add(new Person(id, firstName, lastName, address, city, state, zip, phoneNumber, email, entryDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;

    }
}
