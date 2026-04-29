import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            final int currentVersion = 1;

            Socket hernandeztopia = new Socket("127.0.0.1", 2000);
            OutputStream outPut = hernandeztopia.getOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(outPut);

            Message newMessage = new Message(currentVersion, 1, "Hello, this is Tai", "Tai", "SERVER");
            objOut.writeObject(newMessage);
            objOut.flush();

            ObjectInputStream myObjInput = new ObjectInputStream(hernandeztopia.getInputStream());
            Message replyMessage = (Message)myObjInput.readObject();
            System.out.println("Server Reply: " + replyMessage);

            CommunicationIn comIn = new CommunicationIn(myObjInput);
            CommunicationOut comOut = new CommunicationOut(objOut, currentVersion);

            Thread comInThread = new Thread(comIn);
            Thread comOutThread = new Thread(comOut);

            comInThread.start();
            comOutThread.start();

        } catch (IOException e) {
            System.out.println("Messenger Failed: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}