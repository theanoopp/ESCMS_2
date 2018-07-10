package in.equipshare.es_cms.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.adapter.QuotationRequestsAdapter;
import in.equipshare.es_cms.model.QuotationRequests;

public class QuotationRequestsActivity extends AppCompatActivity implements QuotationRequestsAdapter.AdapterCallback {

    private RecyclerView recyclerView;

    private QuotationRequestsAdapter adapter;

    private ArrayList<QuotationRequests> quotationRequests ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_requests);

        getSupportActionBar().setTitle("Received Quotations");

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        quotationRequests = new ArrayList<>();

        adapter = new QuotationRequestsAdapter(QuotationRequestsActivity.this,quotationRequests,this);
        recyclerView.setAdapter(adapter);

        for(int i = 0;i<50;i++){

            QuotationRequests requests = new QuotationRequests("name "+i,"equipment Name "+i,"location "+i);

            quotationRequests.add(requests);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onMethodCallback(int position) {

    }
}
