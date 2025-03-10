package org.example.blockAsync;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class RunnableImpl implements Runnable {
    Socket socket;
    RunnableImpl(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        String inputMessage;
        String threadName= Thread.currentThread().getName();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            String outputMessage;

            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            while((inputMessage = reader.readLine()) != null && !inputMessage.isEmpty()) {
                System.out.println(threadName+ " - Input message: "+inputMessage);
                System.out.print(threadName+ " - Output message: ");

                outputMessage = bufferedReader.readLine();
                out.write((outputMessage+"\n").getBytes(StandardCharsets.UTF_8));
            }

            } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(threadName+ " finished");
    }
}

public class ServerMain {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){
            while(true){
                Socket socket  = serverSocket.accept();
                (new Thread(new RunnableImpl(socket))).start();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}