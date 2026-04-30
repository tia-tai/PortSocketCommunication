import java.io.IOException;
import java.io.ObjectInputStream;

public class CommunicationIn implements Runnable {
    ObjectInputStream objIn;

    public CommunicationIn(ObjectInputStream objIn) {
        this.objIn = objIn;
    }

    @Override
    public void run() {
        while (true) {
            Message replyMessage = null;
            try {
                replyMessage = (Message) objIn.readObject();
                System.out.println("Server Reply: " + replyMessage);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }
    }
}
