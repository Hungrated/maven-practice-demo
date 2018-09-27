package edu.zju.cst.myproject.dao;

import edu.zju.cst.myproject.model.User;

public interface IUserDao {
    public void add(User user);
    public User getUserByName(String userName);
    public void delete(User user);
}
