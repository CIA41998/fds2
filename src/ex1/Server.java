package ex1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    public static void main(String args[])
            throws Exception {

        // Create server Socket
        ServerSocket ss = new ServerSocket(123);

        // connect it to client socket
        Socket s = ss.accept();
        System.out.println("Connection established");

        // to send data to the client
        PrintStream ps = new PrintStream(s.getOutputStream());

        // to read data coming from the client
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        s.getInputStream()));

        // to read data from the keyboard
        BufferedReader kb = new BufferedReader(
                new InputStreamReader(System.in));
        String str;

        // server executes until exit is typed
        while (!(str = kb.readLine()).equals("exit")) {

            // read from client
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                int number = printAndCountLetters(str);
                System.out.println("The word " + str + " has " + number + " letters.");

                // send to client
                ps.println(number);
            }

            // close connection
            ps.close();
            br.close();
            kb.close();
            ss.close();
            s.close();

            // terminate application
            System.exit(0);

        } // end of while
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
