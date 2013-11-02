package info.acidflow.shartc.ui.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.acidflow.shartc.R;
import info.acidflow.shartc.client.ClientToServerConnection;
import info.acidflow.shartc.utilities.NetworkUtils;

/**
 * Created by acidflow on 27/10/13.
 */
public class ConnectToServerFragment extends Fragment implements View.OnClickListener{

    private View mView;
    private EditText mServerIpAddressEditText;
    private EditText mServerPortEditText;
    public ConnectToServerFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.shartc_connect_to_server_fragment, container, false);
            mServerPortEditText = (EditText) mView.findViewById(R.id.edittext_server_port);
            mServerIpAddressEditText = (EditText) mView.findViewById(R.id.edittext_server_address);
        }else{
            ViewGroup parent = (ViewGroup) mView.getParent();
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeClickListeners();
        ((TextView) mView.findViewById(R.id.ip_address)).setText(NetworkUtils.getLocalIpAddress());
    }

    private void initializeClickListeners(){
        mView.findViewById(R.id.btn_connect_to_server).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_connect_to_server:
                if(!TextUtils.isEmpty(mServerIpAddressEditText.getText()) && !TextUtils.isEmpty(mServerPortEditText.getText())){
                    ClientToServerConnection connection = new ClientToServerConnection(mServerIpAddressEditText.getText().toString(), Integer.parseInt(mServerPortEditText.getText().toString()));
                    connection.connectToServer();
                }else{
                    Toast.makeText(getActivity(), "please specify port and address", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
