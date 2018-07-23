package com.jcourse.vlsnk;

import java.sql.Timestamp;

public class Record {
    private int recordId;
    private Timestamp postDate;
    private String postText;

    public Record(int recordId, Timestamp postDate, String postText) {
        this.recordId = recordId;
        this.postDate = postDate;
        this.postText = postText;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", postDate=" + postDate +
                ", postText='" + postText + '\'' +
                '}';
    }
}
