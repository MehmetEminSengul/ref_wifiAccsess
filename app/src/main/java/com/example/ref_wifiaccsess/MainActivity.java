package com.example.ref_wifiaccsess;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String ip, pport, sendData;//Call global data variables, easy to call
    public static int port;
    private EditText et_ip, et_port, et_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//Initialize the control
    }

    //Initialize the control
    private void initView() {
        Button btn_send = findViewById(R.id.btn_send);
        Button btn_link = findViewById(R.id.btn_link);
        et_ip = findViewById(R.id.et_ip);
        et_port = findViewById(R.id.et_port);
        et_data = findViewById(R.id.et_data);

        btn_send.setOnClickListener(this);
        btn_link.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //connection
            case R.id.btn_link:
                if (et_port.length() != 0 && et_ip.length() != 0) {
                    ip = et_ip.getText().toString().trim();//Get the input ip address
                    pport = et_port.getText().toString().trim();//Because the input type of EditText is String type, first get the input content, and then use the following code to convert the Int type, if you have a better method, you can also modify it yourself
                    port = Integer.parseInt(pport);//Convert the port's String type data to int type
                    String str = "o\n";//This is the test data sent when the 8266 is connected. It will only respond if the corresponding judgment is made on the 8266. If it is not connected to the 8266, you can comment out the following two lines of code
                    new SendAsyncTask().execute(str);
                    Toast.makeText(getApplicationContext(), "If you see the ESP8266 light flashing twice, it means the connection is successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the correct ip and port number!", Toast.LENGTH_SHORT).show();
                }
                break;
            //send data
            case R.id.btn_send:
                sendData = et_data.getText().toString().trim();//Input data
                if (!sendData.isEmpty()) {
                    new SendAsyncTask().execute(sendData);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the data!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}