package com.example.fastandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    //Class Variables
    AlertDialog alert;
    private Button activate;
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button)findViewById(R.id.scan_button);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        activate = (Button)findViewById(R.id.activate);

        alert.setMessage("Oops! :(/nPlease Enter a Valid SRJC Asset Tag");
        scanBtn.setOnClickListener(this);
        activate.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.getId() == R.id.scan_button){

            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        if(v.getId() == R.id.activate){
            if(contentTxt != null){
                int length = contentTxt.getText().length();
                if(length != 5 && length != 7){
                    alert.getListView();
                }
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {

            String scanContent = scanningResult.getContents();
            //Keeping in case the Format is needed
            String scanFormat = scanningResult.getFormatName();

            contentTxt.setText(scanContent);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
