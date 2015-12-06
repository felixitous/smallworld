package com.myapps.materialapplication;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by briantruong626 on 12/4/15.
 */
public class SettingsFragment extends PreferenceFragment {
    public static final String TAG = "settings";

    private ListPreference mListPreference;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        for(int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            initializeSummary(getPreferenceScreen().getPreference(i));
        }
    }


    private void initializeSummary(Preference p)
    {
        if(p instanceof ListPreference) {
            ListPreference listPref = (ListPreference)p;
            p.setSummary(listPref.getEntry());
        }
        if(p instanceof EditTextPreference) {
            EditTextPreference editTextPref = (EditTextPreference)p;
            p.setSummary(editTextPref.getText());
        }
    }
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////        return inflater.inflate(R.layout.settingsfragment, container, false);
//        return nu
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

}