package com.jcourse.vlsnk;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestBookControllerImpl implements GuestBookController {

    private Connection connection;
    private int i = 0;
    private String url = "jdbc:h2:mem:post;DB_CLOSE_DELAY=-1";
    private String user = "user";
    private String password = "password";
    private String createTable = "CREATE TABLE PUBLIC.record(\n" +
            "  recordId INT AUTO_INCREMENT,\n" +
            "  postDate TIMESTAMP,\n" +
            "  postText VARCHAR(512)\n" +
            ");";

    public GuestBookControllerImpl()  {
        try {
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            try (PreparedStatement statement = connection.prepareStatement(createTable)) {
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRecord(String message) {

        try (PreparedStatement statement = connection.prepareStatement("INSERT  INTO PUBLIC.RECORD (RECORDID, POSTDATE, POSTTEXT) VALUES (?, ?, ?)")){
            statement.setInt(1, i++);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setString(3, message);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getRecords() {
        List<Record> recordList = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            statement.executeQuery("select * from PUBLIC.record");
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
