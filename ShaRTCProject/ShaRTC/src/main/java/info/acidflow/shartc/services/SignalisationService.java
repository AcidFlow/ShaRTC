package info.acidflow.shartc.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import info.acidflow.shartc.server.SignalisationServer;

/**
 * Created by acidflow on 27/10/13.
 * Service used to keep connections alive even if the application is killed by the system
 * This service goal is only handling the signalisation communication between the host and its peers
 */
public class SignalisationService extends Service {

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SignalisationServer server = SignalisationServer.getInstance();
        server.startServer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        SignalisationServer.getInstance().stopServer();
        super.onDestroy();
    }
}
