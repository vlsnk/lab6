package com.jcourse.vlsnk;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GuestBookControllerImpl implements GuestBookController {

    private String url = "jdbc:h2:mem:post;DB_CLOSE_DELAY=-1";
    private String user = "user";
    private String password = "password";
    private static final String CREATE_TABLE = "CREATE TABLE record(\n" +
            "  recordId IDENTITY ,\n" +
            "  postDate TIMESTAMP,\n" +
            "  postText VARCHAR\n" +
            ");";
    private final static String INSERT_INTO_TABLE = "INSERT  INTO record (POSTDATE, POSTTEXT) VALUES (?, ?)";
    private final static String SELECT_FROM_TABLE = "SELECT * FROM record";

    public GuestBookControllerImpl()  {
        try {
            Class.forName("org.h2.Driver");
            try(Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addRecord(String message) {

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TABLE)){
            statement.setTimestamp(1, Timestamp.from(Instant.now()));
            statement.setString(2, message);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getRecords() {
        List<Record> recordList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement()){
            statement.executeQuery(SELECT_FROM_TABLE);
            ResultSet msgSet = statement.getResultSet();
            while (msgSet.next()) {
                Record r = new Record(msgSet.getInt(1),
                                      msgSet.getTimestamp(2),
                                      msgSet.getString(3));
                recordList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordList;
    }
}
