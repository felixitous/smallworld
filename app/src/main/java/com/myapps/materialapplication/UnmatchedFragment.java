package com.myapps.materialapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by briantruong626 on 12/6/15.
 */
public class UnmatchedFragment extends Fragment {
    public static final String TAG = "unmatched";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.unmatchedfragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Switch aSwitch = (Switch) getActivity().findViewById(R.id.switch1);
        if (aSwitch != null) {
            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // do something, the isChecked will be
                    // true if the switch is in the On position
                    if (isChecked) {
                        Fragment fragment;
                        fragment = (getActivity().getFragmentManager().findFragmentByTag(LoadingFragment.TAG));
                        if (fragment == null) {
                            fragment = new LoadingFragment();
                        }
                        MainActivity.isMatching = true;
                        getActivity().getFragmentManager().beginTransaction().replace(R.id.container, fragment, LoadingFragment.TAG).commit();
                    }
                }
            });
        }
    }
}