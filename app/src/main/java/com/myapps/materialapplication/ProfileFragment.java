package com.myapps.materialapplication;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by briantruong626 on 12/6/15.
 */
public class ProfileFragment extends Fragment {
    public static final String TAG = "profile";
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;

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
        Log.d("response", MainActivity.rdr.toString());

        try {

            ImageView image = (ImageView) getActivity().findViewById(R.id.profileImage);
            BitmapDrawable ob = new BitmapDrawable(getResources(), MainActivity.userPicture);
            image.setBackground(ob);
            text1 = (TextView) getActivity().findViewById(R.id.nameInfo);
            text1.setText(MainActivity.rdr.getString("name"));
            text2 = (TextView) getActivity().findViewById(R.id.ageInfo);
            text2.setText(MainActivity.rdr.getString("age"));
            text3 = (TextView) getActivity().findViewById(R.id.phoneNumberInfo);
            text3.setText(MainActivity.rdr.getString("contact"));

            JSONArray items = MainActivity.rdr.getJSONArray("interests");
            text4 = (TextView) getActivity().findViewById(R.id.interest1Info);
            text4.setText(items.get(0).toString());
            text5 = (TextView) getActivity().findViewById(R.id.interest2Info);
            text5.setText(items.get(1).toString());
            text6 = (TextView) getActivity().findViewById(R.id.interest3Info);
            text6.setText(items.get(2).toString());


        } catch (Exception e) {
            Log.d("Error", "cannot get info from JSON");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        List<String> interests = new ArrayList<String>();
        interests.add(text4.getText().toString());
        interests.add(text5.getText().toString());
        interests.add(text6.getText().toString());
        JSONArray jsArray = new JSONArray(interests);
        try {
            MainActivity.rdr.put("name", text1.getText());
            MainActivity.rdr.put("age", text2.getText());
            MainActivity.rdr.put("contact", text3.getText());
            MainActivity.rdr.put("interests", jsArray);
        } catch (JSONException e) {
            Log.d("JSON", "Something went wrong with putting");
        }
    }

}