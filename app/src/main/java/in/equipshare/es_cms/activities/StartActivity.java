package in.equipshare.es_cms.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import in.equipshare.es_cms.R;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity_E";

    private TextInputLayout emailInput;
    private TextInputLayout passInput;

    private TextView forgotpass;

    private Button loginBT;
    private Button signupBT;

    private ProgressDialog progressDialog;


    private String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passInput);

        loginBT = findViewById(R.id.loginBT);
        signupBT = findViewById(R.id.signBT);

        forgotpass = findViewById(R.id.forgot_button);



        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getEditText().getText().toString();
                password = passInput.getEditText().getText().toString();

                if(checkInput(email,password)){

                    startSignin();

                }


            }
        });

        signupBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(StartActivity.this,RegisterActivity.class));

            }
        });


    }

    private boolean checkInput(String email, String pass) {


        boolean cancel = true;

        emailInput.setError(null);
        passInput.setError(null);

        if(!email.contains("@")){

            emailInput.setError(getString(R.string.error_invalid_email));
            cancel = false;

        }

        if(pass.length() < 6){

            passInput.setError(getString(R.string.error_invalid_password));
            cancel = false;
        }


        return cancel;


    }

    private void startSignin() {

        progressDialog=new ProgressDialog(StartActivity.this);
        progressDialog.setMessage("Signing In");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                progressDialog.dismiss();
                if(authResult.getUser().isEmailVerified()){
                    startActivity(new Intent(StartActivity.this,HomeActivity.class));
                    finish();
                }else {

                    new AlertDialog.Builder(StartActivity.this)
                            .setTitle("Email not verified")
                            .setMessage("Please verify your email to login")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    auth.signOut();

                                    Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                }
                            })
                            .show();

                }




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();
                Toast.makeText(StartActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG,e.getLocalizedMessage());

            }
        });


    }

}
