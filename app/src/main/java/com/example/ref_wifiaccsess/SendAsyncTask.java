package com.example.ref_wifiaccsess;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

//This is to guide the package according to your own file, you need to change it here
import static com.example.ref_wifiaccsess.MainActivity.ip;
import static com.example.ref_wifiaccsess.MainActivity.port;

public class SendAsyncTask extends AsyncTask<String, Void, Void> {

    //Here is the IP and port connected to ESP8266, you can see the IP address when you connect to the mobile phone
    private static final String IP = ip;//You can directly call global variables
    private static final int PORT = port;

    @Override
    protected Void doInBackground(String... params) {
        String str = params[0];
        try {
            Socket client = new Socket(IP, PORT);
            client.setSoTimeout(5000);
            //Get the output stream of Socket, used to send data to the server
            PrintStream out = new PrintStream(client.getOutputStream());
            out.print(str);
            out.flush();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}