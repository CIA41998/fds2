package ex1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    public static void main(String args[]) throws Exception {

        // Create server Socket
        ServerSocket ss = new ServerSocket(123);

        // Accept incoming connections from clients
        Socket s = ss.accept();
        System.out.println("Connection established");
        System.out.println("For the first incoming input, press Enter to see the result on the screen and send a response to the client.");

        // Send data to client
        PrintStream output = new PrintStream(s.getOutputStream());

        // Get data from client
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // to read data from the keyboard
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String word;

        // server executes until exit is typed
        while (!(word = keyboard.readLine()).equals("exit")) {

            // read from client
            while ((word = input.readLine()) != null) {
                // Print the word coming from client
                System.out.println(word);
                // Count letters of word
                int number = printAndCountLetters(word);

                // Send response to client
                output.println(number);
            }

            // Close connection
            output.close();
            input.close();
            keyboard.close();
            ss.close();
            s.close();

            // Close application
            System.exit(0);
        }
    }

    public static int printAndCountLetters(String word) {
        int numberLetters = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i)))
                numberLetters++;
        }
        return numberLetters;
    }
}
