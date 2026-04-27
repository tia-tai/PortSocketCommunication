import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            final int currentVersion = 1;

            Socket hernandeztopia = new Socket("10.69.62.35", 2000);
            OutputStream outPut = hernandeztopia.getOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(outPut);

            Message newMessage = new Message(currentVersion, 1, "Hello, this is Tai", "SERVER", "Tai");
            objOut.writeObject(newMessage);
            objOut.flush();

            ObjectInputStream myObjInput = new ObjectInputStream(hernandeztopia.getInputStream());
            Message replyMessage = (Message)myObjInput.readObject();
            System.out.println("Server Reply: " + replyMessage);
            // PrintWriter printOut = new PrintWriter(outPut);

            boolean completion = false;
            Scanner scanner = new Scanner(System.in);
            while (!completion) {
                System.out.println("Send Message (use . to finish): ");
                String message = scanner.nextLine();

                if (message.equals(".")) {
                    newMessage = new Message(currentVersion, 3, message, "SERVER", "Tai");
                    objOut.writeObject(newMessage);
                    objOut.flush();
                    completion = true;
                } else {
                    System.out.println("To: ");
                    String to = scanner.nextLine();
                    newMessage = new Message(currentVersion, 2, message, to, "Tai");
                    objOut.writeObject(newMessage);
                    objOut.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Messenger Failed: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}