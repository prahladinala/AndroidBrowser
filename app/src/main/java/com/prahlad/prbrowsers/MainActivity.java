package com.prahlad.prbrowsers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    Button go,forward,back,clear,reload,share,love,update,home;
    ProgressBar progressBar;

    public class Splash extends Activity {

        /** Duration of wait **/
        private final int SPLASH_DISPLAY_LENGTH = 3000;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.activity_splash);

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this,Menu.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(!isConnected())
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Hey! Boss No network Connection")
                    .setMessage("I hope you should check your internet connection.")
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Welcome Boss", Toast.LENGTH_LONG).show();

        }


        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        brow=(WebView)findViewById(R.id.wv_brow);
        urledit = (EditText)findViewById(R.id.et_url);
        go = (Button)findViewById(R.id.btn_go);
        forward = (Button)findViewById(R.id.btn_fwd);
        back = (Button)findViewById(R.id.btn_bck);
        clear = (Button)findViewById(R.id.btn_clear);
        reload = (Button)findViewById(R.id.btn_reload);
        share = (Button)findViewById(R.id.btn_share);
        love = (Button)findViewById(R.id.btn_love);
        update = (Button)findViewById(R.id.btn_update);
        home = (Button)findViewById(R.id.btn_home);





        brow.setWebViewClient(new ourViewClient());
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);

                if(newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        WebSettings websettings = brow.getSettings();
        websettings.setJavaScriptEnabled(true);


        brow.loadUrl("http://weblover.000webhostapp.com/browser");

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edittextvalue = urledit.getText().toString();

                if (!edittextvalue.startsWith("http://"))
                    edittextvalue = "http://" + edittextvalue;

                String url = edittextvalue;
                brow.loadUrl(url);

                //Hiding the keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(urledit.getWindowToken(),0);
            }
        });



        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brow.canGoForward())
                    brow.goForward();

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(brow.canGoBack())
                    brow.goBack();
            }
        });


        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.reload();

            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brow.clearHistory();

            }
        });

        love.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        contact.class);
                startActivity(myIntent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                brow.loadUrl("http://weblover.000webhostapp.com/browser");
                brow.clearHistory();

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://weblover.000webhostapp.com/browser/pr_browser/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            }
        });




        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                myIntent.putExtra(Intent.EXTRA_SUBJECT,"Hii There, i am using PR Browser");
                myIntent.putExtra(Intent.EXTRA_TEXT,"Hii There, i am using PR Browser" +
                        "   I am having a wonderful experience with this browser, " +
                        "1.Noo History is stored. " +
                        "2.No Ads." +
                        "3.Light weight browser." +
                        "And many more features are waiting for you." +
                        "Try that out http://weblover.000webhostapp.com/browser/pr_browser " +
                        "I hope you will also have a great fun.");
                startActivity(Intent.createChooser(myIntent, "Share your love using"));
            }
        });


    }
    @Override
    public void onBackPressed() {
        if(brow.canGoBack()) {
            brow.goBack();
        } else {
            Toast.makeText(this, "Bye!!! Boss", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if (brow != null) {
            brow.destroy();
        }
    }
    int backButtonCount = 0;
    /**
     * Back button listener.
     * Will close the application if the back button pressed twice.
     */
/** @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 4)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Finally say bye!!! To me.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }*/

    private boolean isConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
