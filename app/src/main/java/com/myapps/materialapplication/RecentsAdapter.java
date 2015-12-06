package com.myapps.materialapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by felixliu on 12/3/15.
 */
public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.UserViewHolder> {

    List<User> users;
    Context parent;

    RecentsAdapter(Context parent, List<User> users){
        this.users = users;
        this.parent = parent;

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_card, viewGroup, false);
        UserViewHolder pvh = new UserViewHolder(parent, v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(UserViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(users.get(i).name);
        personViewHolder.personPhoto.setImageResource(users.get(i).photoId);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView personName;
        ImageView personPhoto;
        Context parent;

        UserViewHolder(Context parent2, View itemView) {
            super(itemView);
            parent = parent2;
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            Typeface tf = Typeface.createFromAsset(itemView.getContext().getAssets(),
                    "fonts/muli.ttf");
            personName.setTypeface(tf);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            Fragment fragment;
            fragment = ((Activity) this.parent).getFragmentManager().findFragmentByTag(MatchFragment.TAG);
            if (fragment == null) {
                fragment = new MatchFragment();
            }
            ((Activity) this.parent).getFragmentManager().beginTransaction().replace(R.id.container, fragment, MatchFragment.TAG).commit();

        }

    }
}
