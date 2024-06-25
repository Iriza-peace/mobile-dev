package rw.ac.rca.banking.mailHandling;

public interface EmailService {
    public void sendMessage(String to,String subject,String text);
}
