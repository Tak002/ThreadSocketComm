package org.example.blockSync;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerMain {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket socket = serverSocket.accept();

            String inputMessage;
            String outputMessage;

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));


            while((inputMessage = reader.readLine()) != null && !inputMessage.isEmpty()){
                System.out.println("Input message: "+inputMessage);
                System.out.print("Output message: ");
                outputMessage = bufferedReader.readLine();
                out.write((outputMessage+"\n").getBytes(StandardCharsets.UTF_8));
            }

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}