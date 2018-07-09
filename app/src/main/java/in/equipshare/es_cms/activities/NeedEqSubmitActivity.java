package in.equipshare.es_cms.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.EquipmentSelect;

public class NeedEqSubmitActivity extends AppCompatActivity {

    private ArrayList<EquipmentSelect> equipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_eq_submit);

        equipList = (ArrayList<EquipmentSelect>) getIntent().getSerializableExtra("list");
        

    }
}
