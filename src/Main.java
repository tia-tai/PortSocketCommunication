import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Socket hernandeztopia = new Socket("10.69.62.35", 2000);
            OutputStream outPut = hernandeztopia.getOutputStream();
            PrintWriter printOut = new PrintWriter(outPut);
            boolean completion = false;
            Scanner scanner = new Scanner(System.in);
            while (!completion) {
                System.out.println("Send Message (use . to finish):");
                String message = scanner.nextLine();
                if (message.equals(".")) {
                    completion = true;
                } else {
                    printOut.println(message);
                    printOut.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Messenger Failed: " + e);
        }
    }
}