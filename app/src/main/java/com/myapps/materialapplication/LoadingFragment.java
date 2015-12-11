package com.myapps.materialapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felipecsl.gifimageview.library.GifImageView;

/**
 * Created by briantruong626 on 12/6/15.
 */
public class LoadingFragment extends Fragment {
    public static final String TAG = "loading";
    private GifImageView gifView;
    public GoogleApi googleapi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loadingfragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        googleapi = new GoogleApi();
        googleapi.startGoogleApi(getActivity());
        // 9 second delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment fragment;
                // set matched state!
                MainActivity.isMatching = false;
                MainActivity.isMatched = true;
                fragment = getActivity().getFragmentManager().findFragmentByTag(MatchFragment.TAG);
                googleapi.closeLoadingWatch();
                MainActivity.notification("Kelly Shen", MainActivity.matchedPicture);
                if (fragment == null) {

                    fragment = new MatchFragment();
                }
                getActivity().getFragmentManager().beginTransaction().replace(R.id.container, fragment, MatchFragment.TAG).commit();
            }
        }, 5000);
    }

}
