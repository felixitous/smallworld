package com.myapps.materialapplication;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by briantruong626 on 12/4/15.
 */
public class MatchedFragment extends Fragment  {
    public static final String TAG = "matched";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_complicated_profile, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button button = (Button) getActivity().findViewById(R.id.locationbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "hello", Toast.LENGTH_LONG);
                mapsWindow();
            }
        });
    }

    public void mapsWindow() {
        Intent intent = new Intent(this.getActivity(), Destination.class);
        startActivity(intent);
    }

}