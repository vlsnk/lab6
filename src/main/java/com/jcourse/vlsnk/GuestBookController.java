package com.jcourse.vlsnk;

import java.util.List;

public interface GuestBookController{
    void addRecord(String message);
    List<Record> getRecords(); //Record {id, postDate, message}
}