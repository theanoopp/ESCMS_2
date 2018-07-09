package in.equipshare.es_cms.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.SignupUser;

public class MobileAuthActivity extends AppCompatActivity {

    private static final String TAG = "MobileAuthActivity";

    private SignupUser signupUser;

    private ConstraintLayout phoneLayout,codeLayout;
    private TextInputLayout phoneET,codeET;
    private ProgressBar phoneProgress,codeProgress;
    private Button sendCodeBT;
    private TextView errorText;


    private FirebaseAuth mAuth;

    private int btnType = 0;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_auth);


        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        signupUser = (SignupUser)bundle.getSerializable("signup_model");


        phoneLayout = findViewById(R.id.phoneLayout);
        codeLayout  = findViewById(R.id.codeLayout);

        phoneET = findViewById(R.id.phoneET);
        codeET = findViewById(R.id.codeET);

        phoneProgress = findViewById(R.id.phoneProgress);
        codeProgress = findViewById(R.id.codeProgress);

        sendCodeBT = findViewById(R.id.sendCodeBT);

        errorText = findViewById(R.id.errorText);


        mAuth = FirebaseAuth.getInstance();


        sendCodeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendCodeBT.setError(null);

                if (btnType==0) {

                    String phoneNumber = phoneET.getEditText().getText().toString();

                    if(phoneNumber.length()==10){

                        phoneProgress.setVisibility(View.VISIBLE);
                        phoneET.setEnabled(false);
                        //sendCodeBT.setVisibility(View.GONE);


                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91"+phoneNumber,
                                60,
                                TimeUnit.SECONDS,
                                MobileAuthActivity.this,
                                mCallbacks);

                    }else {
                        phoneET.setError("Please enter a valid number...");
                    }



                }else {

                    String code = codeET.getEditText().getText().toString();

                    if(code.length()!=6){

                        codeET.setError("Please enter a valid code");

                    }else {

                        sendCodeBT.setEnabled(false);
                        codeProgress.setVisibility(View.VISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId,code);

                        signInWithPhoneAuthCredential(phoneAuthCredential);

                    }



                }
            }
        });



        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                signInWithPhoneAuthCredential(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {


                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    errorText.setText(e.getMessage());
                    Log.d("error",e.getLocalizedMessage());
                } else if (e instanceof FirebaseAuthInvalidUserException) {
                    errorText.setText("Invalid mobile number");
                    Log.d("error",e.getLocalizedMessage());
                } else {
                    errorText.setText(e.getMessage());
                    Log.d("error",e.getLocalizedMessage());
                }

                errorText.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                mVerificationId = verificationId;
                mResendToken = forceResendingToken;

                phoneProgress.setVisibility(View.INVISIBLE);

                codeLayout.setVisibility(View.VISIBLE);

                btnType = 1;

                sendCodeBT.setEnabled(true);
                sendCodeBT.setText("Verify Code");

                Toast.makeText(MobileAuthActivity.this,"Code sent", Toast.LENGTH_SHORT).show();



            }
        };


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = task.getResult().getUser();
                            String contactNo = user.getPhoneNumber();

                            signupUser.setContactNumber(contactNo);

                            startSignup(signupUser);



                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                                codeProgress.setVisibility(View.INVISIBLE);

                                errorText.setText("The sms verification code is invalid. Please check the code");
                                Log.d(TAG,task.getException().getLocalizedMessage());

                                errorText.setVisibility(View.VISIBLE);
                                sendCodeBT.setEnabled(true);


                            }
                        }
                    }
                });
    }


    private void startSignup(final SignupUser signupUser) {

        progressDialog=new ProgressDialog(MobileAuthActivity.this);
        progressDialog.setMessage("Please wait while we create your account.");
        progressDialog.setCancelable(false);
        progressDialog.show();

        AuthCredential credential = EmailAuthProvider.getCredential(signupUser.getEmail(),signupUser.getPassword());

        mAuth.getCurrentUser().linkWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                mAuth.getCurrentUser().sendEmailVerification();

                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                firebaseFirestore.collection("users").document(mAuth.getCurrentUser().getUid()).set(signupUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        new AlertDialog.Builder(MobileAuthActivity.this)
                                .setTitle("New Account created")
                                .setMessage("Please verify your email to login")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        mAuth.signOut();

                                        Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);


                                    }
                                })
                                .show();
                        progressDialog.cancel();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MobileAuthActivity.this,"Server error : "+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.cancel();

                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(MobileAuthActivity.this,"Server error : "+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.cancel();

            }
        });

    }
}
