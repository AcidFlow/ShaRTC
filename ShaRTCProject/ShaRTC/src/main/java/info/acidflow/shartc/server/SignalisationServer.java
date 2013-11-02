package info.acidflow.shartc.server;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import info.acidflow.shartc.config.Constants;
import info.acidflow.shartc.server.handlers.SignalisationClientConnectionHandler;

/**
 * Created by acidflow on 27/10/13.
 * This Singleton class represent the Signalisation server.
 */
public class SignalisationServer{

    /**
     * The instance for singleton pattern
     */
    private static SignalisationServer mInstance;

    /**
     * The thread in which the server will run
     */
    private Thread mServerThread;

    /**
     * The listening socket
     */
    private ServerSocket mServerSocket;

    /**
     * Boolean to know if the server is running or not
     */
    private boolean mServerOrderRun = false;


    private SignalisationServer(){
        super();
    }

    /**
     * Create a new Signalisation server or use the existing one
     * @return a new Signalisation server or use the existing one
     */
    public static SignalisationServer getInstance(){
        if(mInstance == null){
            mInstance = new SignalisationServer();
        }
        return mInstance;
    }

    /**
     * Return the running state of the signalisation server
     * @return true if the server is running, false otherwise
     */
    public boolean isServerRunning(){
        return mServerOrderRun;
    }

    /**
     * Stop the signalisation server if it has been started.
     */
    public void stopServer(){
        if(isServerRunning()){
            Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Stopping server");
            mServerOrderRun = false;
            if(mServerSocket != null && !mServerSocket.isClosed()){
                try{
                    mServerSocket.close();
                }catch(IOException e){
                    Log.e(Constants.CONFIGURATION.LOG.LOG_TAG, "Error when closing server socket : " + e.getMessage());
                }
            }
            if(mServerThread != null && mServerThread.isAlive()){
                try{
                    mServerThread.join();
                } catch (InterruptedException e) {
                    Log.e(Constants.CONFIGURATION.LOG.LOG_TAG, "Error when joining server thread: " + e.getMessage());
                }
            }
        }else{
            Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Server is already stopped");
        }
    }

    /**
     * Start the signalisation server if it has not already been started
     */
    public void startServer(){
        if(!isServerRunning()){
            Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Starting server");
            mServerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    mServerOrderRun = true;
                    try{
                        mServerSocket = new ServerSocket(Constants.CONFIGURATION.SERVER.SIGNALISATION_SERVER_LISTENING_PORT);
                        while(mServerOrderRun){
                            Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Waiting for connection");
                            Socket clientSocket = mServerSocket.accept();
                            Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Connection accepted");
                            handleClientConnection(clientSocket);
                        }
                    }catch (IOException e){
                        mServerOrderRun = false;
                        Log.e(Constants.CONFIGURATION.LOG.LOG_TAG, "Error when starting server : " + e.getMessage());
                    }
                }
            });
            mServerThread.start();
        }else{
            Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Server is already running");
        }
    }

    private void handleClientConnection(Socket clientSocket){
        new Thread(new SignalisationClientConnectionHandler(clientSocket)).start();
    }

    public void addPeer(String ipAddress){
        Log.i(Constants.CONFIGURATION.LOG.LOG_TAG, "Adding peer with address : " + ipAddress);
    }
}
