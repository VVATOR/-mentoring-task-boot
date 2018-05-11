package com.epam.mentoring.springboot.beans;

import java.sql.Timestamp;

public class FriendShips {

    private int userId1;
    private int userId2;
    private Timestamp timestamp;

    public FriendShips(int userId1, int userId2, Timestamp timestamp) {
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.timestamp = timestamp;
    }

    public FriendShips(int userId2, Timestamp timestamp) {
        this.userId2 = userId2;
        this.timestamp = timestamp;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FriendShips{" +
                "userId1=" + userId1 +
                ", userId2=" + userId2 +
                ", timestamp=" + timestamp +
                '}';
    }
}
