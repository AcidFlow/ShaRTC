package info.acidflow.shartc.server.handlers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import info.acidflow.shartc.config.Constants;
import info.acidflow.shartc.server.SignalisationServer;

/**
 * Created by acidflow on 27/10/13.
 * Runnable containing all the logic to execute when a client connects himself to the signalisation server
 */
public class SignalisationClientConnectionHandler implements Runnable {

    /**
     * The socket used by the client
     */
    private Socket mClientSocket;

    /**
     * Create a new connection handler for the client using clientSocket
     * @param clientSocket the socket used by the client (returned by accept() method)
     */
    public SignalisationClientConnectionHandler(Socket clientSocket){
        super();
        mClientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{
            Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Dealing connection with client");
            PrintWriter out = new PrintWriter(mClientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(mClientSocket.getInputStream()));
            out.write("Hello client !\n");
            out.write("You use : " + mClientSocket.getInetAddress() + " IP Address\n");
            out.flush();
            SignalisationServer.getInstance().addPeer(mClientSocket.getInetAddress().toString());
            mClientSocket.close();
        }catch(IOException e){
            Log.e(Constants.CONFIGURATION.LOG.LOG_TAG, "ERROR when Dealing connection with client");
        }
    }
}
