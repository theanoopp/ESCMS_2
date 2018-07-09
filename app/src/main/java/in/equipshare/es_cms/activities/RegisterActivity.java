package in.equipshare.es_cms.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.SignupUser;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout companyNameInput;
    private TextInputLayout emailInput;
    private TextInputLayout passInput;
    private TextInputLayout cpassInput;
    private TextInputLayout contactPersonInput;
    private TextInputLayout ownerNameInput;
    private TextInputLayout ownerEmailInput;
    private TextInputLayout addressInput;
    private TextInputLayout pinInput;

    private Spinner stateSpinner;
    private Spinner citySpinner;



    private RadioGroup compnayTypeRadio;

    private Button sbtButton;

    private boolean getOwner = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        getSupportActionBar().setTitle("Profile");

        companyNameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);
        cpassInput = findViewById(R.id.cpassInput);
        contactPersonInput = findViewById(R.id.firmInput);
        ownerNameInput = findViewById(R.id.directorInput);
        ownerEmailInput = findViewById(R.id.ownerEmailInput);
        addressInput = findViewById(R.id.addressInput);
        pinInput = findViewById(R.id.pinInput);


        compnayTypeRadio = findViewById(R.id.compnayRadio);
        stateSpinner = findViewById(R.id.stateSpinner);
        citySpinner = findViewById(R.id.citySpinner);

        sbtButton = findViewById(R.id.submitBT);

        compnayTypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.proprietorRadio){

                    ownerNameInput.setVisibility(View.GONE);
                    ownerEmailInput.setVisibility(View.GONE);

                    getOwner = false;

                }else {

                    ownerNameInput.setVisibility(View.VISIBLE);
                    ownerEmailInput.setVisibility(View.VISIBLE);

                }


            }
        });



        sbtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkInput();

            }
        });

    }


    private void checkInput() {

        // Reset errors.
        companyNameInput.setError(null);
        emailInput.setError(null);
        passInput.setError(null);
        cpassInput.setError(null);

        contactPersonInput.setError(null);
        ownerNameInput.setError(null);
        ownerEmailInput.setError(null);
        addressInput.setError(null);
        pinInput.setError(null);

        // Store values at the time of the login attempt.

        String companyName = companyNameInput.getEditText().getText().toString();
        String email = emailInput.getEditText().getText().toString();
        String password = passInput.getEditText().getText().toString();
        String cpass = cpassInput.getEditText().getText().toString();

        String contactPerson = contactPersonInput.getEditText().getText().toString();

        String ownerName = ownerNameInput.getEditText().getText().toString();
        String ownerEmail = ownerEmailInput.getEditText().getText().toString();

        String state = stateSpinner.getSelectedItem().toString() ;
        String city = citySpinner.getSelectedItem().toString() ;

        int radioButtonID = compnayTypeRadio.getCheckedRadioButtonId();
        RadioButton radioButton = compnayTypeRadio.findViewById(radioButtonID);
        int idx = compnayTypeRadio.indexOfChild(radioButton);

        RadioButton r = (RadioButton)  compnayTypeRadio.getChildAt(idx);
        String companyType = r.getText().toString();


        String address = addressInput.getEditText().getText().toString();
        String pin = pinInput.getEditText().getText().toString();


        boolean cancel = false;
        View focusView = null;


        if(companyName.length() < 3){

            companyNameInput.setError("Enter valid Name");
            focusView = companyNameInput;
            cancel = true;

        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailInput.setError(getString(R.string.error_field_required));
            focusView = emailInput;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailInput.setError(getString(R.string.error_invalid_email));
            focusView = emailInput;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (password.length() < 6) {
            passInput.setError(getString(R.string.error_invalid_password));
            focusView = passInput;
            cancel = true;
        }

        if(!password.equals(cpass)){
            cpassInput.setError("Password must be same");
            focusView = cpassInput;
            cancel = true;
        }

        if (contactPerson.length() < 3) {
            contactPersonInput.setError("Enter valid name");
            focusView = contactPersonInput;
            cancel = true;
        }

        if (ownerName.length() < 3) {
            ownerNameInput.setError("Enter valid name");
            focusView = ownerNameInput;
            cancel = true;
        }


        if (TextUtils.isEmpty(ownerEmail)) {
            ownerEmailInput.setError(getString(R.string.error_field_required));
            focusView = ownerEmailInput;
            cancel = true;
        } else if (!isEmailValid(ownerEmail)) {
            ownerEmailInput.setError(getString(R.string.error_invalid_email));
            focusView = ownerEmailInput;
            cancel = true;
        }



        if (address.length() < 3) {
            addressInput.setError("Address too short");
            focusView = addressInput;
            cancel = true;
        }

        if (pin.length() != 6) {
            pinInput.setError("Invalid Pin code");
            focusView = pinInput;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to

            // TODO: 7/4/2018 showProgress(true);

            //startSignup(companyName,email,password,contactPerson,companyType,ownerName,ownerEmail,state,city,address,pin,contactNumber);


            Intent intent = new Intent(RegisterActivity.this,MobileAuthActivity.class);

            SignupUser user1 = new SignupUser(companyName,companyType,email,"default",contactPerson,password,state,city,pin,address,ownerName,ownerEmail);

            intent.putExtra("signup_model",user1);

            startActivity(intent);


        }


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
}
