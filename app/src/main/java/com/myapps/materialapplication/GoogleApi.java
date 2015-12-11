package com.myapps.materialapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by felixliu on 12/7/15.
 */
public class GoogleApi implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    public static final String START_ACTIVITY_PATH = "/start/MainActivity";
    public static final String START_SUB_ACTIVITY = "/start/SubActivity";
    public static final String FINISH_WATCH_FACE = "/close/MainActivity";

    public void startGoogleApi(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("GoogleApi Service", "onConnected: " + bundle);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("GoogleApi Service", "onConnectionSuspended: " + i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("GoogleApi Service", "onConnectionFailed: " + connectionResult);
    }

    public void fireOffMessage() {
        Log.d("Mobile", "sending start activity");
        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                for (Node node : getConnectedNodesResult.getNodes()) {
                    Log.d("going here", "omg");
                    sendMessage(node.getId(), START_ACTIVITY_PATH);
                }
            }
        });
    }

    public void fireOffMessage2() {
        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                for (Node node : getConnectedNodesResult.getNodes()) {
                    Log.d("going here", "tes");
                    sendMessage(node.getId(), START_SUB_ACTIVITY);
                }
            }
        });
    }

    public void closeLoadingWatch() {
        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
            @Override
            public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                for (Node node : getConnectedNodesResult.getNodes()) {
                    Log.d("going here", "closing watchface");
                    sendMessage(node.getId(), FINISH_WATCH_FACE);
                }
            }
        });
    }

    private void sendMessage(String node, String target) {
        Wearable.MessageApi.sendMessage(mGoogleApiClient, node, target, new byte[0]).setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
            @Override
            public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                if (!sendMessageResult.getStatus().isSuccess()) {
                    Log.e("GoogleApi", "Failed to send message with status code: "
                            + sendMessageResult.getStatus().getStatusCode());
                }
            }
        });
    }


}
