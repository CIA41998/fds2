package ex1;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

        public static void main(String args[])
                        throws Exception {

                // Create client socket
                Socket s = new Socket("localhost", 123);

                // to send data to the server
                DataOutputStream dos = new DataOutputStream(
                                s.getOutputStream());

                // to read data coming from the server
                BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                                s.getInputStream()));


                Scanner keyboard = new Scanner(System.in);
                System.out.println("Please input a string to count the letters");
                String str = keyboard.nextLine();
                dos.writeBytes(str + "\n");

                // receive from the server
                String responseMessage = br.readLine();
                System.out.println(responseMessage);

                // close connection.
                dos.close();
                br.close();
                s.close();
        }
}
