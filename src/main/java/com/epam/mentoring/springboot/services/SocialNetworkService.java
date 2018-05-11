package com.epam.mentoring.springboot.services;

import com.epam.mentoring.springboot.beans.FriendShips;
import com.epam.mentoring.springboot.beans.User;
import java.util.List;

public interface SocialNetworkService {

  List getUsers();

  User getUser(final int id);

  void editUser(final User user);

  List<FriendShips> getUserFriendShips(final int userId);

  List<FriendShips> getAllFriendShips();

  void removeUser(final int userId);

  void addUser(final User user);

  void generateUsers(int count);
}
