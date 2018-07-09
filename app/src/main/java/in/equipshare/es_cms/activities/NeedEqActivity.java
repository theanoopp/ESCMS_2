package in.equipshare.es_cms.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.adapter.EquipmentListAdapter;
import in.equipshare.es_cms.dialog.AddEqFragment;
import in.equipshare.es_cms.model.EquipmentSelect;

public class NeedEqActivity extends AppCompatActivity implements AddEqFragment.OnFragmentInteractionListener , EquipmentListAdapter.AdapterCallback {

    private static final String TAG = "NeedEqActivity";

    private RecyclerView recyclerView;

    private Button addButton;

    private Button nextButton;

    private ArrayList<EquipmentSelect> equipList;

    private EquipmentListAdapter customAdapter;

    private TextView tipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_eq);

        equipList = new ArrayList<>();

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
                AddEqFragment addEqFragment = new AddEqFragment();
                addEqFragment.show(fm,"add_eq_dialog");

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(equipList.size() == 0){

                    Toast.makeText(NeedEqActivity.this, "Please add at least one equipment.", Toast.LENGTH_SHORT).show();

                }else {

                    Intent intent = new Intent(NeedEqActivity.this,NeedEqSubmitActivity.class);
                    intent.putExtra("list",equipList);

                    startActivity(intent);

                }

            }
        });




    }

    @Override
    public void onFragmentInteraction(EquipmentSelect equipmentSelect) {

        equipList.add(equipmentSelect);
        customAdapter.notifyDataSetChanged();
        tipView.setVisibility(View.GONE);
    }

    @Override
    public void onMethodCallback(int position,int n) {

        if(n == 0){

            EquipmentSelect equipmentSelect = equipList.get(position);

            FragmentManager fm = getSupportFragmentManager();
            AddEqFragment addEqFragment = AddEqFragment.newInstance(equipmentSelect);
            addEqFragment.show(fm,"add_eq_dialog");


        }else {

            equipList.remove(position);
            customAdapter.notifyDataSetChanged();

        }

    }
}
