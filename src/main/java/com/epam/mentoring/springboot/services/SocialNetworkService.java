package com.epam.mentoring.springboot.services;

import com.epam.mentoring.springboot.beans.Friendships;
import com.epam.mentoring.springboot.beans.User;
import java.util.List;

public interface SocialNetworkService {

  List getUsers();

  User getUser(final int id);

  void editUser(final User user);

  List<Friendships> getUserFriendShips(final int userId);

  List<Friendships> getAllFriendShips();

  void removeUser(final int userId);

  void addUser(final User user);

  void generateUsers(int count);
}
