package com.transport_kh.transport_kh.activities;

/**
 * Created by Dmytro on 20.11.2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.transport_kh.transport_kh.R;

public class LoginActivity extends AppCompatActivity {

    TextView txtViewError;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtEmail = (EditText) findViewById(R.id.edt_email);
        final EditText edtPassword = (EditText) findViewById(R.id.edt_password);
        txtViewError  = (TextView) findViewById(R.id.txtView_login_error);

        pd = new ProgressDialog(this);
        pd.setTitle(null);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage(getString(R.string.loading));

        findViewById(R.id.btn_log_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validationComplete = true;
                txtViewError.setVisibility(View.GONE);

                if( edtEmail.getText().toString().equalsIgnoreCase("") ){
                    validationComplete = false;
                    txtViewError.setVisibility(View.VISIBLE);
                    txtViewError.setText(getString(R.string.you_must_fill_email));
                }
                if( edtPassword.getText().toString().equalsIgnoreCase("")){
                    validationComplete = false;
                    if(txtViewError.getVisibility()==View.VISIBLE)
                        txtViewError.setText(getString(R.string.login_password_error));
                    else {
                        txtViewError.setVisibility(View.VISIBLE);
                        txtViewError.setText(getString(R.string.you_must_fill_password));
                    }
                }
                if(validationComplete){
                    pd.show();
                    Backendless.UserService.login(edtEmail.getText().toString(), edtPassword.getText().toString(), new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            pd.dismiss();
                            //TODO
                            startActivity(new Intent(LoginActivity.this, BaseActivity.class));
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            pd.dismiss();
                            Log.e("test", "fault:  " + fault.toString());
                            if(fault.getCode().equals("3040")){
                                txtViewError.setVisibility(View.VISIBLE);
                                txtViewError.setText(getString(R.string.wrong_email_format));
                            }
                            else if(fault.getCode().equals("3003")){
                                txtViewError.setVisibility(View.VISIBLE);
                                txtViewError.setText(getString(R.string.wrong_email_or_password));
                            }
                            else{
                                Toast.makeText(LoginActivity.this, getString(R.string.something_goes_wrong), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        findViewById(R.id.btn_registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

    }
}
