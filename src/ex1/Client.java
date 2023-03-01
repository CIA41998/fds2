package ex1;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

        public static void main(String args[]) throws Exception {

                // Create client socket
                Socket s = new Socket("localhost", 123);

                // Send data to server
                DataOutputStream output = new DataOutputStream(s.getOutputStream());

                // Get data from server
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

                // Get data from keyboard
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

                String word, responseMessage;
                System.out.println("Please type in a word");

                // Until user types exit
                while (!(word = keyboard.readLine()).equals("exit")) {

                        // send to the server
                        output.writeBytes(word + "\n");

                        // receive from the server
                        responseMessage = input.readLine();

                        System.out.println(responseMessage);
                }

                // close connection.
                output.close();
                input.close();
                keyboard.close();
                s.close();
        }
}
