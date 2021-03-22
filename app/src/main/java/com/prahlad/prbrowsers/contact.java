package com.prahlad.prbrowsers;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class contact extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    ImageButton more,phone_call,txtSms,sendwts;
    EditText your_message;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_main);
        addListenerOnButton();
    }

    public void addListenerOnButton() {

        final Context context = this;

        more = (ImageButton) findViewById(R.id.more);

        more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, social.class);
                startActivity(intent);

            }

        });



        phone_call = (ImageButton)findViewById(R.id.btn_call);



        phone_call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+917396170742"));
                startActivity(callIntent);
            }

        });

        ImageView b = (ImageView) findViewById(R.id.prahlad_inala);
        b.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
                return false;
            }
        });

        txtSms = (ImageButton)findViewById(R.id.txtSms);
        //Performing action on button click
        txtSms.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try{
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage("+917396170742",null,your_message.getText().toString(),null,null);
                    Toast.makeText(contact.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(contact.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


        sendwts = (ImageButton)findViewById(R.id.sendwts);
        sendwts.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String smsNumber = "917396170742"; // E164 format without '+' sign
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                //  Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, your_message.getText().toString());
                sendIntent.setPackage("com.whatsapp");

                startActivity(sendIntent);
            }

        });


    }




}
