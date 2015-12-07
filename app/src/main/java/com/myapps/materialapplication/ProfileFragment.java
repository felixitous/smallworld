package com.myapps.materialapplication;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by briantruong626 on 12/6/15.
 */
public class ProfileFragment extends Fragment {
    public static final String TAG = "profile";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_display, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("resuming:", "profilefragment resuming");

        try {

            ImageView image = (ImageView) getActivity().findViewById(R.id.profileImage);
            BitmapDrawable ob = new BitmapDrawable(getResources(), MainActivity.userPicture);
            image.setBackground(ob);
            TextView text1 = (TextView) getActivity().findViewById(R.id.nameInfo);
            text1.setText(MainActivity.rdr.getString("name"));
            TextView text2 = (TextView) getActivity().findViewById(R.id.ageInfo);
            text2.setText(MainActivity.rdr.getString("age"));
            TextView text3 = (TextView) getActivity().findViewById(R.id.phoneNumberInfo);
            text3.setText(MainActivity.rdr.getString("contact"));

            JSONArray items = MainActivity.rdr.getJSONArray("interests");
            TextView text4 = (TextView) getActivity().findViewById(R.id.interest1Info);
            text4.setText(items.get(0).toString());
            TextView text5 = (TextView) getActivity().findViewById(R.id.interest2Info);
            text5.setText(items.get(1).toString());
            TextView text6 = (TextView) getActivity().findViewById(R.id.interest3Info);
            text6.setText(items.get(2).toString());


        } catch (Exception e) {
            Log.d("Error", "cannot get info from JSON");
        }
    }

}