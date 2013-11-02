package info.acidflow.shartc.client;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import info.acidflow.shartc.config.Constants;
import info.acidflow.shartc.protocol.SignalisationProtocol;

/**
 * Created by acidflow on 27/10/13.
 */
public class ClientToServerConnection {
    /**
     * The IP address of the server
     */
    private String mServerIPAddress;

    /**
     * The server port
     */
    private int mServerPort;

    /**
     * The created socket from IP Address and port
     */
    private Socket mSocket;

    public ClientToServerConnection(String host, int port){
        super();
        mServerIPAddress = host;
        mServerPort = port;
    }

    public void connectToServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    mSocket = new Socket(mServerIPAddress, mServerPort);
                    PrintWriter out = new PrintWriter(mSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                    String response = SignalisationProtocol.getMessage(in);
                    JSONObject json = new JSONObject(response);
                    if(json.optString("status", "notConnected").equals("connected")){
                        JSONObject a = new JSONObject();
                        a.put("acknolegded", true);
                        SignalisationProtocol.sendMessage(out, a);
                    }
                    Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Server says : " + response);
                    mSocket.close();
                }catch(IOException e){
                    Log.e(Constants.CONFIGURATION.LOG.LOG_TAG, "Error when connecting to server " + mServerIPAddress + ":" + mServerPort);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
