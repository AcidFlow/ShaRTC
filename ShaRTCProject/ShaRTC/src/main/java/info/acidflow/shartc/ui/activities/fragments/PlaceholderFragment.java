package info.acidflow.shartc.ui.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.acidflow.shartc.R;
import info.acidflow.shartc.client.ClientToServerConnection;
import info.acidflow.shartc.services.SignalisationService;
import info.acidflow.shartc.utilities.NetworkUtils;

/**
 * Created by acidflow on 27/10/13.
 */
public class PlaceholderFragment extends Fragment implements View.OnClickListener{

    private View mView;
    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeClickListeners();
        ((TextView) mView.findViewById(R.id.ip_address)).setText(NetworkUtils.getLocalIpAddress());
    }

    private void initializeClickListeners(){
        mView.findViewById(R.id.btn_start_server).setOnClickListener(this);
        mView.findViewById(R.id.btn_stop_server).setOnClickListener(this);
        mView.findViewById(R.id.btn_connect_to_server).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_server:
                getActivity().startService(new Intent(getActivity(), SignalisationService.class));
                break;

            case R.id.btn_stop_server:
                getActivity().stopService(new Intent(getActivity(), SignalisationService.class));
                break;
            case R.id.btn_connect_to_server:
                ClientToServerConnection connection = new ClientToServerConnection("10.0.2.2", 40666);
                connection.connectToServer();
                break;
            default:
                break;
        }
    }
}
