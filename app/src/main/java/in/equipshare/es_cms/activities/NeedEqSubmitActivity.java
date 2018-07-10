package in.equipshare.es_cms.activities;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.EquipmentSelect;
import in.equipshare.es_cms.model.RFQ;
import in.equipshare.es_cms.model.Result;
import in.equipshare.es_cms.rest.APIService;
import in.equipshare.es_cms.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeedEqSubmitActivity extends AppCompatActivity {

    private ArrayList<EquipmentSelect> equipList;

    private Button button;

    private PlaceAutocompleteFragment autocompleteFragment;

    //ui views
    private Spinner projectTypeSpinner;
    private Spinner monthDuration;
    private Spinner yearDuration;
    private TextView dateView;
    private TextInputLayout devInput;
    private TextInputLayout descriptionInput;

    private ProgressDialog progressDialog;


    private String location = "default";

    private String projectType;
    private String durationMonth,duratonYear;
    private String startDate;
    private String devAuthority;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_eq_submit);

        projectTypeSpinner = findViewById(R.id.projectTypeSpinner);
        monthDuration = findViewById(R.id.monthSpinner);
        yearDuration = findViewById(R.id.yearSpinner);
        dateView = findViewById(R.id.selectDate);
        devInput = findViewById(R.id.devAuthInput);
        descriptionInput = findViewById(R.id.projectDescriptionInput);

        equipList = (ArrayList<EquipmentSelect>) getIntent().getSerializableExtra("list");

        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete);

        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY)
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .setCountry("IND")
                .build();

        autocompleteFragment.setFilter(autocompleteFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.

                location = ""+place.getAddress();

                Log.i("MY_E", "Address "+place.getAddress());


            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("MY_E", "An error occurred: " + status);
            }
        });


        button = findViewById(R.id.subButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                projectType = projectTypeSpinner.getSelectedItem().toString();
                durationMonth = monthDuration.getSelectedItem().toString();
                duratonYear = yearDuration.getSelectedItem().toString();
                startDate = dateView.getText().toString();
                devAuthority = devInput.getEditText().getText().toString();
                description = descriptionInput.getEditText().getText().toString();

                RFQ sendData = new RFQ(location,projectType,durationMonth,duratonYear,startDate,devAuthority,description,equipList);

                sendRFQ(sendData);

            }
        });



    }

    private void sendRFQ(RFQ rfq) {

        progressDialog=new ProgressDialog(NeedEqSubmitActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();


        APIService mAPIService = ApiUtils.getAPIService();

        mAPIService.rfqSet(rfq).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if(response.isSuccessful()){

                    new AlertDialog.Builder(NeedEqSubmitActivity.this)
                            .setMessage("Quotation list submitted")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                }
                            })
                            .show();
                    progressDialog.cancel();

                }else {

                    Toast.makeText(NeedEqSubmitActivity.this,response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Log.d("MY_E",t.getLocalizedMessage());

            }
        });


    }
}
