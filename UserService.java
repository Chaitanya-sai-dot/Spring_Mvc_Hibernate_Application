package crudHibernate.mvcHibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crudHibernate.mvcHibernate.dao.UserDao;
import crudHibernate.mvcHibernate.model.User;


@Service
@Transactional
public class UserService {
 @Autowired
    UserDao userDao;
 
    public List getAllUsers() {
        return userDao.getAllUsers();
    }
 
  
    public User getUser(int id) {
        return userDao.getUser(id);
    }
 
   
    public void addUser(User user) {
        userDao.addUser(user);
    }
 
   
    public void updateUser(User user) {
        userDao.updateUser(user);
 
    }
 
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

}
