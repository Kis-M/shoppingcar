package com.mhy.shoppingcar.adapter;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

//import static com.mhy.shoppingcar.adapter.XuanzuoActivity.ip;//记得导包

public class SendAsyncTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        String str = params[0];
        try {
            Socket client = new Socket("172.20.10.2", 8080);
            client.setSoTimeout(5000);
            //获取Socket的输出流，用来发送数据给服务端
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

