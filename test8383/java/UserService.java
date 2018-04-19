package jbr.springmvc.service;

import jbr.springmvc.model.Login;
import jbr.springmvc.model.User;

public interface UserService {

  User validateUser(Login login);
}