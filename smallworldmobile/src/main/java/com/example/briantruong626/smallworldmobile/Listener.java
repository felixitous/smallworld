package smallworld.smallworldwear;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.GridView;

import com.example.briantruong626.smallworldmobile.MainActivity;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class Listener extends WearableListenerService {
    public static final String START_ACTIVITY_PATH = "/start/MainActivity";
    public static final String START_SUB_ACTIVITY = "/start/SubActivity";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("going here", "something");
        super.onMessageReceived(messageEvent);
        if(messageEvent.getPath().equals(START_ACTIVITY_PATH)){
            Log.d("going here", "yes");
            Intent intent = new Intent(this , MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (messageEvent.getPath().equals(START_SUB_ACTIVITY)) {
            Intent intent = new Intent(this, GridView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
