package in.equipshare.es_cms.activities;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.NeedEquipment;
import in.equipshare.es_cms.model.RFQ;

public class NeedEqSubmitActivity extends AppCompatActivity {

    private ArrayList<NeedEquipment> equipList;

    private Button button;

    private PlaceAutocompleteFragment autocompleteFragment;

    //ui views
    private Spinner projectTypeSpinner;
    private Spinner monthDuration;
    private Spinner yearDuration;
    private TextView dateView;
    private TextInputLayout devInput;
    private TextInputLayout descriptionInput;

    private DatePickerDialog.OnDateSetListener date;
    private Calendar myCalendar;

    private long milli_req_date;

    private String location = "default";

    private String projectType;
    private String durationMonth,durationYear;
    private String startDate;
    private String devAuthority;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_eq_submit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        projectTypeSpinner = findViewById(R.id.projectTypeSpinner);
        monthDuration = findViewById(R.id.monthSpinner);
        yearDuration = findViewById(R.id.yearSpinner);
        dateView = findViewById(R.id.selectDate);
        devInput = findViewById(R.id.devAuthInput);
        descriptionInput = findViewById(R.id.projectDescriptionInput);

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        //equipList = (ArrayList<EquipmentSelect>) getIntent().getSerializableExtra("list");

        equipList = new ArrayList<>();

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

                location = ""+place.getAddress();

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


                if(checkInput()){

                    RFQ sendData = new RFQ(location,projectType,durationMonth,durationYear,startDate,devAuthority,description,equipList);

                    Intent intent = new Intent(NeedEqSubmitActivity.this,NeedEqActivity.class);
                    intent.putExtra("model",sendData);
                    startActivity(intent);

                }

            }
        });

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(NeedEqSubmitActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });



    }

    private boolean checkInput() {

        boolean cancel = true;

        projectType = projectTypeSpinner.getSelectedItem().toString();
        durationMonth = monthDuration.getSelectedItem().toString();
        durationYear = yearDuration.getSelectedItem().toString();
        startDate = dateView.getText().toString();

        devAuthority = devInput.getEditText().getText().toString();
        description = descriptionInput.getEditText().getText().toString();


        devInput.setError(null);
        descriptionInput.setError(null);


        if(location.equals("default")) {

            Toast.makeText(NeedEqSubmitActivity.this,"Please select location", Toast.LENGTH_SHORT).show();
            cancel = false;

        }else if(startDate.equals("Select Date")){

            Toast.makeText(NeedEqSubmitActivity.this,"Select date", Toast.LENGTH_SHORT).show();
            cancel = false;

        }else if (TextUtils.isEmpty(devAuthority)) {

            devInput.setError("Enter valid value");
            cancel = false;

        }else if (TextUtils.isEmpty(description)) {

            descriptionInput.setError("Enter valid value");
            cancel = false;

        }

        return cancel;

    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date date=myCalendar.getTime();

        milli_req_date = date.getTime();

        dateView.setText(sdf.format(myCalendar.getTime()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
        }

        return true;
    }

}
