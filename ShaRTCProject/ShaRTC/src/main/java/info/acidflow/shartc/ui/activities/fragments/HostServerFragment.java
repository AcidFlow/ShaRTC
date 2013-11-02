package info.acidflow.shartc.ui.activities.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.acidflow.shartc.R;
import info.acidflow.shartc.services.SignalisationService;

/**
 * Created by acidflow on 27/10/13.
 */
public class HostServerFragment extends Fragment implements View.OnClickListener{

    private View mView;
    public HostServerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.shartc_create_server_fragment, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeClickListeners();
    }

    private void initializeClickListeners(){
        mView.findViewById(R.id.btn_start_server).setOnClickListener(this);
        mView.findViewById(R.id.btn_stop_server).setOnClickListener(this);
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
            default:
                break;
        }
    }
}
