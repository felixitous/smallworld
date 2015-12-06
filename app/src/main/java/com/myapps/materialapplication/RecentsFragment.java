package com.myapps.materialapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by briantruong626 on 12/6/15.
 */
public class RecentsFragment extends Fragment {
    public static final String TAG = "recents";
    private List<User> users;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recentsfragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView rv = (RecyclerView) getActivity().findViewById(R.id.recentsList);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        initializeData();
        RecentsAdapter adapter = new RecentsAdapter(this.getActivity(), users);
        rv.setAdapter(adapter);
    }

    private void initializeData(){
        users = new ArrayList<>();
        users.add(new User("Brian Truong", R.drawable.brian));
        users.add(new User("Lucky Ding", R.drawable.lucky));
        users.add(new User("Angela Tang", R.drawable.angela));
        users.add(new User("Kyle Chang", R.drawable.kyle));
        users.add(new User("Kelly Shen", R.drawable.kelly));
        users.add(new User("Alice Cai", R.drawable.alice));
    }
}