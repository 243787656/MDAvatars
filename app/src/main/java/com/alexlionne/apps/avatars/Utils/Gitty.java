package com.alexlionne.apps.avatars.Utils;

import android.os.Bundle;

import com.alexlionne.apps.avatars.R;
import com.github.paolorotolo.gitty_reporter.GittyReporter;

public class Gitty extends GittyReporter {

    // Please DO NOT override onCreate. Use init instead.
    @Override
    public void init(Bundle savedInstanceState) {

        // Set where Gitty will send issues.
        // (username, repository name);
        setTargetRepository("alexlionne", "MDAvatars");

        // Set Auth token to open issues if user doesn't have a GitHub account
        // For example, you can register a bot account on GitHub that will open bugs for you.
        setGuestOAuth2Token("28f479f73db97d912611b27579aad7a76ad2baf5");


        // OPTIONAL METHODS

        // Set if User can send bugs with his own GitHub account (default: true)
        // If false, Gitty will always use your Auth token
        //enableUserGitHubLogin(true);

        // Set if Gitty can use your Auth token for users without a GitHub account (default: true)
        // If false, Gitty will redirect non registred users to github.com/join
        //enableGuestGitHubLogin(true);

        // Include other relevant info in your bug report (like custom variables)
       // setExtraInfo("Example string");

        // Allow users to edit debug info (default: false)
        canEditDebugInfo(false);

        // Customize Gitty appearance
        setFabColor1(getResources().getColor(R.color.accent), getResources().getColor(R.color.colorAccentDark), getResources().getColor(R.color.accent));
        setFabColor2(getResources().getColor(R.color.primary), getResources().getColor(R.color.primary_dark), getResources().getColor(R.color.primary));
        setBackgroundColor1(getResources().getColor(R.color.primary));
        setBackgroundColor2(getResources().getColor(R.color.accent));
        setRippleColor(getResources().getColor(R.color.accent));
    }
}
