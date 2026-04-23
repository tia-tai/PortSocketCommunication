import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket hernandeztopia = new Socket("10.69.62.35", 1234);
            OutputStream outPut = hernandeztopia.getOutputStream();
            PrintWriter printOut = new PrintWriter(outPut, true);
            printOut.println("Who let the dogs out?");
        } catch (IOException e) {
            System.out.println("Messenger Failed: " + e);
        }
    }
}