package in.equipshare.es_cms.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.adapter.EquipmentListAdapter;
import in.equipshare.es_cms.dialog.NeedEqFragment;
import in.equipshare.es_cms.model.NeedEquipment;
import in.equipshare.es_cms.model.RFQ;
import in.equipshare.es_cms.model.Result;
import in.equipshare.es_cms.rest.APIService;
import in.equipshare.es_cms.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeedEqActivity extends AppCompatActivity implements NeedEqFragment.OnFragmentInteractionListener , EquipmentListAdapter.AdapterCallback {

    private static final String TAG = "NeedEqActivity";

    private ProgressDialog progressDialog;

    private RecyclerView recyclerView;

    private Button addButton;

    private Button nextButton;

    private ArrayList<NeedEquipment> equipList;

    private EquipmentListAdapter customAdapter;

    private TextView tipView;

    private RFQ sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_eq);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        equipList = new ArrayList<>();

        sendData = (RFQ) getIntent().getSerializableExtra("model");

        tipView = findViewById(R.id.tipView);

        addButton = findViewById(R.id.addFab);
        nextButton = findViewById(R.id.nextButton);

        recyclerView = findViewById(R.id.equipmentListView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        customAdapter = new EquipmentListAdapter(NeedEqActivity.this,equipList,this);
        recyclerView.setAdapter(customAdapter);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                NeedEqFragment addEqFragment = new NeedEqFragment();
                addEqFragment.show(fm,"add_eq_dialog");

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(equipList.size() == 0){

                    Toast.makeText(NeedEqActivity.this, "Please add at least one equipment.", Toast.LENGTH_SHORT).show();

                }else {

                    sendData.setEquipmentList(equipList);
                    sendRFQ(sendData);


                }

            }
        });




    }

    @Override
    public void onFragmentInteraction(NeedEquipment equipmentSelect) {

        equipList.add(equipmentSelect);
        customAdapter.notifyDataSetChanged();
        tipView.setVisibility(View.GONE);
    }

    @Override
    public void onMethodCallback(int position,int n) {

        if(n == 0){

            NeedEquipment equipmentSelect = equipList.get(position);

            FragmentManager fm = getSupportFragmentManager();
            NeedEqFragment addEqFragment = NeedEqFragment.newInstance(equipmentSelect);
            addEqFragment.show(fm,"add_eq_dialog");


        }else {

            equipList.remove(position);
            customAdapter.notifyDataSetChanged();

        }

    }


    private void sendRFQ(RFQ rfq) {


        progressDialog=new ProgressDialog(NeedEqActivity.this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        APIService mAPIService = ApiUtils.getAPIService();

        mAPIService.rfqSet(rfq).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if(response.code() == 200){


                    if(response.isSuccessful()){

                        new AlertDialog.Builder(NeedEqActivity.this)
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

                        progressDialog.cancel();
                        Toast.makeText(NeedEqActivity.this,response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }

                }else {

                    progressDialog.cancel();
                    Toast.makeText(NeedEqActivity.this,"Some server error.", Toast.LENGTH_SHORT).show();

                }




            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Log.d("MY_E",t.getLocalizedMessage());

            }
        });


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
