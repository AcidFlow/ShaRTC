package info.acidflow.shartc.ui.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.acidflow.shartc.R;

/**
 * Created by acidflow on 27/10/13.
 */
public class WelcomeFragment extends Fragment{

    private View mView;
    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.shartc_welcome_fragment, container, false);
        return mView;
    }
}
