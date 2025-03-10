package org.example.blockAsync;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ClientMain {
    public static void main(String[] args) {
        String inputMessage;

        try(Socket socket = new Socket("localhost",5000)){
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String outputMessage;

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            outputMessage="서버에 소켓연결됨";
            out.write((outputMessage+"\n").getBytes(StandardCharsets.UTF_8));
            while((inputMessage = reader.readLine()) != null && !inputMessage.isEmpty()){
                System.out.println("Input message: "+inputMessage);
                System.out.print("Output message: ");

                outputMessage = bufferedReader.readLine();
                out.write((outputMessage+"\n").getBytes(StandardCharsets.UTF_8));
            }
             // 한 줄 읽기 (개행 문자 기준)

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}