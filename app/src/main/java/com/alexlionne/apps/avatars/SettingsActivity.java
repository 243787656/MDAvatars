package com.alexlionne.apps.avatars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alexlionne.apps.avatars.Utils.Gitty;
import com.alexlionne.apps.avatars.Utils.Utils;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.thefinestartist.finestwebview.FinestWebView;


/**
 * Created by Alex on 08/03/2016.
 */

public class SettingsActivity extends PreferenceActivity {
private Utils utils;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_settings);

        preferences = getSharedPreferences("com.alexlionne.apps.avatars", MODE_PRIVATE);
        editor = preferences.edit();
        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        root.addView(bar, 0); // insert at top
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        utils = new Utils(this);
        Preference changelog = (Preference) findPreference("changelog");
        Preference github = (Preference) findPreference("github");
        Preference issue = (Preference) findPreference("issue");
        Preference libs = (Preference) findPreference("libs");
        Preference cache = (Preference) findPreference("cache");

        cache.setSummary("delete "+Utils.getCache()+"of cache");
        github.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                showGithub();
                return true;}
        });


        cache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                clearCache();
                return true;}
        });
        changelog.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                showChangeLog();
            return true;}
        });
        issue.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                showIssue();
                return true;}
        });
        libs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                showLibs();
                return true;}
        });
    }
    private void showLibs(){
        new LibsBuilder()
                .withActivityStyle(Libs.ActivityStyle.DARK)
                .start(this);
    }
    private void showIssue(){
        Intent i = new Intent(SettingsActivity.this, Gitty.class);
        startActivity(i);
    }
    private void showChangeLog() {


    }
    private void showGithub(){
        new FinestWebView.Builder(SettingsActivity.this).show(getResources().getString(R.string.github_link));
    }
    private void clearCache(){
        Utils.trimCache(this);
    }

}