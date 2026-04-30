import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            final int currentVersion = 1;

            Socket hernandeztopia = new Socket("10.69.62.35", 2000);
            OutputStream outPut = hernandeztopia.getOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(outPut);
            ObjectInputStream myObjInput = new ObjectInputStream(hernandeztopia.getInputStream());

            Message newMessage = new Message(currentVersion, 1, "Hello, this is Tai", "Tai", "SERVER");
            objOut.writeObject(newMessage);
            objOut.flush();

            Message replyMessage = (Message)myObjInput.readObject();
            System.out.println("Server Reply: " + replyMessage);

            CommunicationOut comOut = new CommunicationOut(objOut, currentVersion);
            CommunicationIn comIn = new CommunicationIn(myObjInput);

            Thread comOutThread = new Thread(comOut);
            Thread comInThread = new Thread(comIn);

            comInThread.start();
            comOutThread.start();

        } catch (IOException e) {
            System.out.println("Messenger Failed: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}