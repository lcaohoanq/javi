package com.lcaohoanq.sparkjava;

import java.util.Collection;

public interface IUserService {

    void addUser(User user);

    Collection<User> getUsers();

    User getUser(String id);

    User editUser(User user) throws UserException;

    void deleteUser(String id);

    boolean userExist(String id);
}
