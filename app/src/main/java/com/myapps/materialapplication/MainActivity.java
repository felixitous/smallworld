package com.myapps.materialapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    private GoogleApiClient mGoogleApiClient;
    public static final String START_ACTIVITY_PATH = "/start/MainActivity";
    public static final String START_SUB_ACTIVITY = "/start/SubActivity";
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    public static boolean isMatched = false;
    public static boolean isMatching = false;
    public String denied = "";
    public String accepted = "";
    public static JSONObject rdr;
    public static Activity mainAct;
    public static GraphResponse personalInfo;
    private URL url;
    public static Bitmap userPicture;
    public static Bitmap matchedPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        mainAct = this;
        matchedPicture = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.kelly_complicated);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        LoginManager.getInstance().logOut();
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        if (LoginActivity.loggedIn == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
//            this.finish();
        }

        denied = getIntent().getStringExtra("unmatched");
        accepted = getIntent().getStringExtra("matched");


        if (denied != null) {
            Log.d("denied", denied);
        }

        if (accepted != null) {
            Log.d("accepted", accepted);
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        if (denied != null) {
            Log.d("going into denied", "something else");
            Fragment fragment;
            fragment = getFragmentManager().findFragmentByTag(UnmatchedFragment.TAG);
            if (fragment == null) {
                fragment = new UnmatchedFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.container, fragment, UnmatchedFragment.TAG).commit();
            mNavigationDrawerFragment.closeDrawer();
            notificationManager.cancelAll();
        }

        if (accepted != null) {
            Log.d("going into accepted", "something else");
            Fragment fragment;
            fragment = getFragmentManager().findFragmentByTag(MatchedFragment.TAG);
            if (fragment == null) {
                fragment = new MatchedFragment();
            }
            getFragmentManager().beginTransaction().replace(R.id.container, fragment, MatchedFragment.TAG).commit();
            mNavigationDrawerFragment.closeDrawer();
            notificationManager.cancelAll();
            matchedNotification("Kelly Shen", matchedPicture);
        }




//        mNavigationDrawerFragment = (NavigationDrawerFragment)
//                getFragmentManager().findFragmentById(R.id.fragment_drawer);
//
//        // Set up the drawer.
//        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
//        mNavigationDrawerFragment.setUserData("John Doe", "johndoe@doe.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment;
        switch (position) {
            case 0: //search//todo
                if (isMatched) {
                    fragment = getFragmentManager().findFragmentByTag(MatchedFragment.TAG);
                    if (fragment == null) {
                        fragment = new MatchedFragment();
                    }
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, MatchedFragment.TAG).commit();
                } else {
                    if (isMatching) {
                        fragment = getFragmentManager().findFragmentByTag(LoadingFragment.TAG);
                        if (fragment == null) {
                            fragment = new LoadingFragment();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.container, fragment, LoadingFragment.TAG).commit();
                    } else {
                        fragment = getFragmentManager().findFragmentByTag(UnmatchedFragment.TAG);
                        if (fragment == null) {
                            fragment = new UnmatchedFragment();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.container, fragment, UnmatchedFragment.TAG).commit();
                    }
                }

                break;
            case 1: //stats
                fragment = getFragmentManager().findFragmentByTag(RecentsFragment.TAG);
                if (fragment == null) {
                    fragment = new RecentsFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, RecentsFragment.TAG).commit();
                break;
            case 2: //my account //todo
                fragment = getFragmentManager().findFragmentByTag(ProfileFragment.TAG);
                if (fragment == null) {
                    fragment = new ProfileFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, ProfileFragment.TAG).commit();
                break;
            case 3: //settings //todo
                fragment = getFragmentManager().findFragmentByTag(SettingsFragment.TAG);
                if (fragment == null) {
                    fragment = new SettingsFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, SettingsFragment.TAG).commit();
                break;
        }
    }


    public boolean isMatching() {
        return this.isMatching;
    }

    public void setMatching(boolean bool) {
        this.isMatching = bool;
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginActivity.loggedIn != null) {
            retrieveQuery();
            setPersonalInformation();
            if (accepted != null) {
                setTitle("Matched!!!");
            }
        }
    }


    public void retrieveQuery() {
        try {
            Log.d("URL Query", urlBuilder());
            url = new URL(urlBuilder());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder total = new StringBuilder();
            while ((line = r.readLine()) != null) {
                total.append(line);
            }

            rdr = new JSONObject(total.toString());
            Log.d("JSON Reponse:", total.toString());
            String target = rdr.getString("selfie");
            userPicture = DownloadImage(target);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setPersonalInformation() {
        mNavigationDrawerFragment.personalInfo(userPicture);
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }

    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    private String urlBuilder() {
        String email = "";
        String name = "";
        try {
            email = personalInfo.getJSONObject().getString("email");
            name = personalInfo.getJSONObject().getString("name");
        } catch (JSONException | NullPointerException e) {
            Log.d("bad response", "oh man");
        }
        String retrieval = "http://smallworld-db.herokuapp.com/users/search?format=json&email=";
        retrieval = retrieval + email;
        String new_title = "Hello, ";
        new_title = new_title + name;
        setTitle(new_title);
        return retrieval;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    public void matchedNotification(String name, Bitmap photo) {
        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setBackground(photo)
                        .setHintHideIcon(true);

        Notification notification = new NotificationCompat.Builder(MainActivity.mainAct)
                .setSmallIcon(R.drawable.earth)
                .setContentTitle("Matched")
                .setContentText("Kelly Shen")
                .setColor(Color.parseColor("#00BCD4"))
                .extend(wearableExtender)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.mainAct);
        notificationManager.cancelAll();
        notificationManager.notify(0, notification);

    }

    public static void notification(String name, Bitmap photo) {
        Intent viewIntent = new Intent(MainActivity.mainAct, MainActivity.class);
        Intent yesIntent = new Intent(MainActivity.mainAct, MainActivity.class);
        viewIntent.putExtra("unmatched", "true");
        yesIntent.putExtra("matched", "true");

        Log.d("name", name);
//        yesIntent.putExtra("name1", name);
        PendingIntent noPendingIntent = PendingIntent.getActivity(MainActivity.mainAct, 0, viewIntent, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent yesPendingIntent = PendingIntent.getActivity(MainActivity.mainAct, 0, yesIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Action denyAction =
                new NotificationCompat.Action.Builder(R.drawable.deny,
                        "Deny", noPendingIntent)
                        .build();

        NotificationCompat.Action acceptAction =
                new NotificationCompat.Action.Builder(R.drawable.yes,
                        "Accept", yesPendingIntent)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setBackground(photo)
                        .addAction(denyAction)
                        .addAction(acceptAction)
                        .setHintHideIcon(true);

        Notification notification = new NotificationCompat.Builder(MainActivity.mainAct)
                .setSmallIcon(R.drawable.earth)
                .setContentTitle(name)
                .setContentText("21")
                .setColor(Color.parseColor("#00BCD4"))
                .extend(wearableExtender)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.mainAct);
        notificationManager.cancelAll();
        notificationManager.notify(0, notification);

    }
}
