package com.myapps.materialapplication;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

/**
 * Created by briantruong626 on 12/4/15.
 */
public class MatchedFragment extends Fragment  {
    public static final String TAG = "matched";
    public GoogleApi googleapi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_complicated_profile, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        googleapi = new GoogleApi();
        googleapi.startGoogleApi(getActivity());
        final Button button = (Button) getActivity().findViewById(R.id.locationbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "hello", Toast.LENGTH_LONG);
                mapsWindow();
            }
        });

        final Button prompt = (Button) getActivity().findViewById(R.id.promptbutton);
        prompt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                googleapi.fireOffMessage2();
            }
        });

        final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        final Button endMeeting = (Button) getActivity().findViewById(R.id.endmeetingbutton);
        endMeeting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment;
                fragment = (getActivity().getFragmentManager().findFragmentByTag(UnmatchedFragment.TAG));
                if (fragment == null) {
                    fragment = new UnmatchedFragment();
                }
                getActivity().getFragmentManager().beginTransaction().replace(R.id.container, fragment, UnmatchedFragment.TAG).commit();
                String name = "";
                try {
                    name = MainActivity.personalInfo.getJSONObject().getString("name");
                } catch (JSONException | NullPointerException e) {
                    Log.d("bad response", "oh man");
                }
                getActivity().setTitle("Hello, " + name);
                notificationManager.cancelAll();
            }
        });
    }

    public void mapsWindow() {
        Intent intent = new Intent(this.getActivity(), Destination.class);
        startActivity(intent);
    }

    public void promptClicked() {
        Log.d("prompt", "clicked");
    }

}