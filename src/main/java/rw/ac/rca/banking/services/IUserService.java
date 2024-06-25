package rw.ac.rca.banking.services;

import rw.ac.rca.banking.models.User;

import java.util.Optional;

public interface IUserService {
  public User createUser(User user);
  public User getUserById(int id);
  public Optional<User> getUserByEmail(String email);
}
