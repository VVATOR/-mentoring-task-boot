package com.epam.mentoring.springboot.beans;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friendships")
public class Friendships {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "userid1")
  private Long userId1;
  @Column(name = "userid2")
  private Long userId2;
  @Column(name = "timestamp")
  private Timestamp timestamp;

  public Friendships(Long userId1, Long userId2, Timestamp timestamp) {
    this.userId1 = userId1;
    this.userId2 = userId2;
    this.timestamp = timestamp;
  }

  public Friendships(Long userId2, Timestamp timestamp) {
    this.userId2 = userId2;
    this.timestamp = timestamp;
  }

  public Long getUserId1() {
    return userId1;
  }

  public void setUserId1(Long userId1) {
    this.userId1 = userId1;
  }

  public Long getUserId2() {
    return userId2;
  }

  public void setUserId2(Long userId2) {
    this.userId2 = userId2;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Friendships{" +
        "userId1=" + userId1 +
        ", userId2=" + userId2 +
        ", timestamp=" + timestamp +
        '}';
  }
}
