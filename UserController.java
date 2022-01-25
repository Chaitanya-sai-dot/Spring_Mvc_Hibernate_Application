package crudHibernate.mvcHibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import crudHibernate.mvcHibernate.model.User;
import crudHibernate.mvcHibernate.service.UserService;


@RestController
@RequestMapping("/SpringRest")
public class UserController {
	
	@Autowired
    UserService userService;
 
    @RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
    public List getUsers() {
 
        List listOfUsers = userService.getAllUsers();
        return listOfUsers;
    }
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUserById(@PathVariable int id) {
        return userService.getUser(id);
    }
 
    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Accept=application/json")
    public void addUser(@RequestBody User user) { 
       userService.addUser(user);
 
    }
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);  
    } 
}
