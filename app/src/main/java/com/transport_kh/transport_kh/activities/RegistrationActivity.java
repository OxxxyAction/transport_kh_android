package com.transport_kh.transport_kh.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.transport_kh.transport_kh.R;
import com.transport_kh.transport_kh.dialogFragments.DatePickerBirth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dmytro on 22.10.2016.
 */
public class RegistrationActivity extends Activity implements DatePickerDialog.OnDateSetListener{
    EditText edt_reg_date_of_birth, edt_reg_email, edt_reg_password, edt_reg_password_re;
    RadioGroup radioGroup;
    TextView txtViewEmailError, txtViewPasswordError, txtViewPasswordErrorRe, txtViewSexError, txtViewDateBirthError;
    String dateToBackEndLess;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edt_reg_date_of_birth = (EditText) findViewById(R.id.edt_reg_date_of_birth);
        edt_reg_email = (EditText) findViewById(R.id.edt_reg_email);
        edt_reg_password = (EditText) findViewById(R.id.edt_reg_password);
        edt_reg_password_re = (EditText) findViewById(R.id.edt_reg_password_re);
        radioGroup = (RadioGroup) findViewById(R.id.radio_sex);

        txtViewEmailError = (TextView) findViewById(R.id.txtView_reg_email_error);
        txtViewPasswordError = (TextView) findViewById(R.id.txtView_reg_password_err);
        txtViewPasswordErrorRe = (TextView) findViewById(R.id.txtView_reg_password_re_err);
        txtViewSexError = (TextView) findViewById(R.id.txtView_reg_sex_err);
        txtViewDateBirthError = (TextView) findViewById(R.id.txtView_reg_date_err);

        pd = new ProgressDialog(this);
        pd.setTitle(null);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.setMessage(getString(R.string.loading));

        findViewById(R.id.btn_back_registration_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.edt_reg_date_of_birth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerBirth newDatePicker = new DatePickerBirth();
                newDatePicker.setOnDateSetListener(RegistrationActivity.this);
                newDatePicker.show(getFragmentManager(), "datePicker");
            }
        });

        findViewById(R.id.btn_reg_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validationComplete = true;
                BackendlessUser backendlessUser = new BackendlessUser();
                if( getSex()!=null ) {
                    backendlessUser.setProperty("gender", getSex());
                }
                else{
                    Log.e("test", "sex flag");
                    validationComplete=false;
                }

                if( !edt_reg_email.getText().toString().equalsIgnoreCase("") ){
                    if(txtViewEmailError.getVisibility()==View.VISIBLE)
                        txtViewEmailError.setVisibility(View.GONE);
                    backendlessUser.setEmail( edt_reg_email.getText().toString() );
                }
                else{
                    txtViewEmailError.setVisibility(View.VISIBLE);
                    txtViewEmailError.setText(getString(R.string.you_must_fill_email));
                    validationComplete=false;
                    Log.e("test", "email flag");
                }
                if(validatePasswords()){
                    backendlessUser.setPassword(edt_reg_password.getText().toString());
                }
                else{
                    Log.e("test", "password flag");
                    validationComplete = false;
                }


                if( !edt_reg_date_of_birth.getText().toString().equalsIgnoreCase("") ){
                    if(txtViewDateBirthError.getVisibility()==View.VISIBLE)
                        txtViewDateBirthError.setVisibility(View.GONE);
                    backendlessUser.setProperty("birth", dateToBackEndLess);
                }
                else{
                    txtViewDateBirthError.setVisibility(View.VISIBLE);
                    validationComplete=false;
                    Log.e("test", "date flag");
                }

                if(validationComplete){
                    pd.show();
                    Log.e("test", "backendless user:");
                    Log.e("test", backendlessUser.toString());
                    Backendless.UserService.register(backendlessUser, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            pd.dismiss();
                            Toast.makeText(RegistrationActivity.this, getString(R.string.reg_complete), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            pd.dismiss();
                            Log.e("test", "fault:  " + fault.toString());
                            if(fault.getCode().equals("3040")){
                                txtViewEmailError.setVisibility(View.VISIBLE);
                                txtViewEmailError.setText(getString(R.string.wrong_email_format));
                            }else{
                                Toast.makeText(RegistrationActivity.this, getString(R.string.something_goes_wrong), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Log.e("test", "chto za bred");
                }



            }
        });
    }

    public boolean validatePasswords(){
        boolean validationComplete = true;
        if( !edt_reg_password.getText().toString().equalsIgnoreCase("") ){
            if(txtViewPasswordError.getVisibility()==View.VISIBLE)
                txtViewPasswordError.setVisibility(View.GONE);
        }
        else{
            txtViewPasswordError.setVisibility(View.VISIBLE);
            txtViewPasswordError.setText(getString(R.string.you_must_fill_password));
            validationComplete=false;
        }
        if( !edt_reg_password_re.getText().toString().equalsIgnoreCase("") ){
            if(txtViewPasswordErrorRe.getVisibility()==View.VISIBLE)
                txtViewPasswordErrorRe.setVisibility(View.GONE);
        }
        else{
            txtViewPasswordErrorRe.setVisibility(View.VISIBLE);
            txtViewPasswordErrorRe.setText(getString(R.string.you_must_fill_password_re));
            validationComplete=false;
        }
        if( validationComplete && !edt_reg_password.getText().toString().equalsIgnoreCase(edt_reg_password_re.getText().toString())){
            if(txtViewPasswordError.getVisibility()==View.VISIBLE)
                txtViewPasswordError.setVisibility(View.GONE);
            txtViewPasswordErrorRe.setVisibility(View.VISIBLE);
            txtViewPasswordErrorRe.setText(getString(R.string.password_must_be_equals));
            validationComplete=false;
        }
        return validationComplete;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        Calendar myCalendar = Calendar.getInstance();
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date date = new Date(myCalendar.getTimeInMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        edt_reg_date_of_birth.setText( dateFormat.format(date) );
        SimpleDateFormat dateFormatBackEndLess = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss Z", Locale.getDefault());
        dateToBackEndLess = dateFormatBackEndLess.format(date);
    }

    public String getSex(){
        if(txtViewSexError.getVisibility()==View.VISIBLE)
            txtViewSexError.setVisibility(View.GONE);
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radioSexMale:
                return "male";
            case R.id.radioSexFemale:
                return "female";
            default:
                txtViewSexError.setVisibility(View.VISIBLE);
                return null;
        }
    }
}

