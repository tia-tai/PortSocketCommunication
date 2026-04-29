import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CommunicationOut implements Runnable{
    ObjectOutputStream objOut;
    int currentVersion;

    public CommunicationOut(ObjectOutputStream objOut, int currentVersion) {
        this.objOut = objOut;
        this.currentVersion = currentVersion;
    }

    @Override
    public void run() {
        try {
            boolean completion = false;
            Scanner scanner = new Scanner(System.in);
            while (!completion) {
                System.out.println("Send Message (use . to finish): ");
                String message = scanner.nextLine();

                if (message.equals(".")) {
                    Message newMessage = new Message(currentVersion, 3, message, "SERVER", "Tai");
                    objOut.writeObject(newMessage);
                    objOut.flush();
                    completion = true;
                } else {
                    System.out.println("To: ");
                    String to = scanner.nextLine();
                    Message newMessage = new Message(currentVersion, 2, message, to, "Tai");
                    objOut.writeObject(newMessage);
                    objOut.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
