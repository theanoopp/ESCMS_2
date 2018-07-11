package in.equipshare.es_cms.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.adapter.HaveListAdapter;
import in.equipshare.es_cms.model.HaveEquipment;
import in.equipshare.es_cms.model.NeedEquipment;
import in.equipshare.es_cms.model.ViewQuotation;

public class ViewRfqDetailsActivity extends AppCompatActivity implements HaveListAdapter.AdapterCallback {


    private TextView projectTypeView;
    private TextView projectDurationView;
    private TextView companyName;
    private TextView rfqIdView;
    private TextView locationView;
    private TextView projectStartingDateView;
    private TextView devAuthorityView;

    private RecyclerView recyclerView;

    private ArrayList<HaveEquipment> equipList;

    private HaveListAdapter haveListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rfq_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        projectTypeView = findViewById(R.id.projectTypeView);
        projectDurationView = findViewById(R.id.projectDurationView);
        companyName = findViewById(R.id.companyName);
        rfqIdView = findViewById(R.id.rfqIdView);
        locationView = findViewById(R.id.locationView);
        projectStartingDateView = findViewById(R.id.projectStartingDateView);
        devAuthorityView = findViewById(R.id.devAuthorityView);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        equipList = new ArrayList<>();

        haveListAdapter = new HaveListAdapter(ViewRfqDetailsActivity.this,equipList,this);
        recyclerView.setAdapter(haveListAdapter);

        for(int i = 0;i<10;i++){

            HaveEquipment equipment = new HaveEquipment("name ","date ","end date","rate ","brief");
            equipList.add(equipment);

        }

        haveListAdapter.notifyDataSetChanged();




        ArrayList<NeedEquipment> equipment = new ArrayList<>();


        ViewQuotation quotation = new ViewQuotation("Dewas","Type of project","4","2","08/02/2018","Dev auth","Description here",equipment,"company name here","rfq_id 1");

        setUpUi(quotation);


    }

    private void setUpUi(ViewQuotation quotation){

        projectTypeView.setText("Project Type : "+quotation.getProjectType());
        projectDurationView.setText("Duration : "+quotation.getDurationYear()+" years and "+quotation.getDurationMonth()+" months");
        companyName.setText("Company : "+quotation.getCompanyName());
        rfqIdView.setText("RFQ ID : "+quotation.getRfqId());
        locationView.setText("Location : "+quotation.getLocation());
        projectStartingDateView.setText("Starting date : "+quotation.getStartDate());
        devAuthorityView.setText("Dev Authority : "+quotation.getDevAuthority());


    }


    @Override
    public void onMethodCallback(int position) {



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
