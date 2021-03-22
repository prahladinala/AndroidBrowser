package com.prahlad.prbrowsers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.ImageButton;

public class social extends Activity {

    ImageButton btn_facebook,btn_twitter,instagram,telegram,btn_youtube,btn_github,btn_mail,btn_house;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.social_media);
    }

    public void addListenerOnButton() {

        final Context context = this;

        btn_facebook = (ImageButton) findViewById(R.id.btn_facebook);
        btn_twitter = (ImageButton) findViewById(R.id.btn_twitter);
        instagram = (ImageButton) findViewById(R.id.instagram);
        telegram = (ImageButton) findViewById(R.id.telegram);
        btn_youtube = (ImageButton) findViewById(R.id.btn_youtube);
        btn_github = (ImageButton) findViewById(R.id.btn_github);
        btn_house = (ImageButton) findViewById(R.id.btn_home);


        btn_facebook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String url = "https://www.facebook.com/funtobeanonymous/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });

        btn_twitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String url = "https://twitter.com/pr_theking";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });

        instagram.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String url = "https://www.instagram.com/pr_theking/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });
        telegram.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String url = "http://t.me/prtheking";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });

        btn_youtube.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String url = "https://www.youtube.com/channel/UCcAGuK1S6LWte5Qe_neQ7kA";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });

        btn_github.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String url = "https://github.com/prprojects";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });

        btn_house.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);

            }

        });



    }



}