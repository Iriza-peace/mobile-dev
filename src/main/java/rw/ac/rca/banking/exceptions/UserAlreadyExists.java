package rw.ac.rca.banking.exceptions;

public class UserAlreadyExists extends RuntimeException{
  public UserAlreadyExists(String errorMessage){
      super(errorMessage);
  }
}
