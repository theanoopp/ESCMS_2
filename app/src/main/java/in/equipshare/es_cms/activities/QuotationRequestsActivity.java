package in.equipshare.es_cms.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.adapter.QuotationRequestsAdapter;
import in.equipshare.es_cms.model.RFQSummary;

public class QuotationRequestsActivity extends AppCompatActivity implements QuotationRequestsAdapter.AdapterCallback {

    private RecyclerView recyclerView;

    private QuotationRequestsAdapter adapter;

    private ArrayList<RFQSummary> rfcSummariesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_requests);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Received Quotations");

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        rfcSummariesList = new ArrayList<>();

        adapter = new QuotationRequestsAdapter(QuotationRequestsActivity.this, rfcSummariesList,this);
        recyclerView.setAdapter(adapter);

        for(int i = 0;i<50;i++){

            RFQSummary requests = new RFQSummary("company name "+i,"rfqId "+i,"location "+i);

            rfcSummariesList.add(requests);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onMethodCallback(int position) {

        startActivity(new Intent(QuotationRequestsActivity.this,ViewRfqDetailsActivity.class));

        Toast.makeText(QuotationRequestsActivity.this,rfcSummariesList.get(position).getCompanyName(), Toast.LENGTH_SHORT).show();

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
