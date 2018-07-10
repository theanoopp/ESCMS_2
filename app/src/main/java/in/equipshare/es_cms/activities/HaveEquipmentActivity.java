package in.equipshare.es_cms.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.adapter.EquipmentListAdapter;
import in.equipshare.es_cms.adapter.HaveListAdapter;
import in.equipshare.es_cms.dialog.AddEqFragment;
import in.equipshare.es_cms.dialog.HaveEqFragment;
import in.equipshare.es_cms.model.EquipmentSelect;
import in.equipshare.es_cms.model.HaveEquipment;

public class HaveEquipmentActivity extends AppCompatActivity implements HaveEqFragment.OnFragmentInteractionListener,HaveListAdapter.AdapterCallback {

    private RecyclerView recyclerView;

    private Button addButton;

    private Button nextButton;

    private ArrayList<HaveEquipment> equipList;

    private HaveListAdapter haveListAdapter;

    private TextView tipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_equipment);

        nextButton = findViewById(R.id.nextButton);
        addButton = findViewById(R.id.addFab);

        tipView = findViewById(R.id.tipView);

        recyclerView = findViewById(R.id.equipmentListView);

        recyclerView = findViewById(R.id.equipmentListView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        equipList = new ArrayList<>();

        haveListAdapter = new HaveListAdapter(HaveEquipmentActivity.this,equipList,this);
        recyclerView.setAdapter(haveListAdapter);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HaveEquipmentActivity.this, "todo next screen", Toast.LENGTH_SHORT).show();

            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                HaveEqFragment haveEqFragment = new HaveEqFragment();
                haveEqFragment.show(fm,"have_eq_dialog");

            }
        });




    }

    @Override
    public void onFragmentInteraction(HaveEquipment equipment) {

        equipList.add(equipment);
        haveListAdapter.notifyDataSetChanged();
        tipView.setVisibility(View.GONE);



    }

    @Override
    public void onMethodCallback(int position) {

        equipList.remove(position);
        haveListAdapter.notifyDataSetChanged();

    }
}
