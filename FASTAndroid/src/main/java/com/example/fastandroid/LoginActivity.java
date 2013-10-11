package com.example.fastandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.net.ssl.SSLSocketFactory;


public class LoginActivity extends Activity {

    //UI Elements
    EditText mUsername;
    EditText mPassword;
    Button mLogin_Button;

    SSLUtil sslUtil = new SSLUtil(new TrustAllTrustManager());
    SSLSocketFactory socketFactory = sslUtil.createSSLSocketFactory();
    SSLContext sslContext = sslUtil.createSSLContext();
    ExtendedResult extendedResult = connection.processExtendedOperation(
            new StartTLSExtendedRequest(sslContext));

    if (extendedResult.getResultCode() == ResultCode.SUCCESS)
    {
        // The connection is now secure.
    }
    else
    {
        // The StartTLS negotiation failed for some reason.  The connection can no
        // longer be used.
        connection.close();
    }
    LDAPConnection connection = new LDAPConnection(socketFactory, "ad.santarosa.edu", 636);


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }


    public void onClick(View v){
        if(v.getId() == R.id.sign_in_button){
            connection.connect("ad.santarosa.edu", 389);
            connection.bind("uid=" + mUsername.getText() + ",dc=ad.santarosa,dc=edu",
                    mPassword.getText());

        }

    }
}
