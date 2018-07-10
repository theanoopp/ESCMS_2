package in.equipshare.es_cms.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_eq_submit);

        equipList = (ArrayList<EquipmentSelect>) getIntent().getSerializableExtra("list");


        button = findViewById(R.id.subButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RFQ rfq = new RFQ("dewas","projectType","1","2","date","dadflkdsaf","description demo",equipList);

                sendRFQ(rfq);

            }
        });



    }

    private void sendRFQ(RFQ rfq) {


        APIService mAPIService = ApiUtils.getAPIService();

        mAPIService.rfqSet(rfq).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                Log.d("MY_E",response.toString());
                Log.d("MY_E",response.body().getMsg());

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Log.d("MY_E",t.getLocalizedMessage());

            }
        });


    }
}
