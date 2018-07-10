package in.equipshare.es_cms.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.activities.NeedEqActivity;
import in.equipshare.es_cms.activities.SplashScreen;
import in.equipshare.es_cms.dialog.AddEqFragment;
import in.equipshare.es_cms.model.EquipmentSelect;

public class EquipmentListAdapter extends RecyclerView.Adapter<EquipmentListAdapter.MyViewHolder> {

    List<EquipmentSelect> equipList;
    Context context;
    AdapterCallback mCallback;

    public EquipmentListAdapter(Context context, List<EquipmentSelect> equipList,AdapterCallback callback) {

        this.context = context;
        this.equipList = equipList;
        this.mCallback = callback;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_equipment_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // set the data in items

        holder.bind(equipList.get(position));

        // implement setOnClickListener event on item view.
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onMethodCallback(position,0);


            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mCallback.onMethodCallback(position,1);

                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();



            }
        });



    }

    @Override
    public int getItemCount() {
        return equipList.size();
    }

    public interface AdapterCallback {
        void onMethodCallback(int position,int n);
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView equipmentName;
        TextView quantityView;
        TextView durationView;
        TextView startDateView;
        ImageButton editButton;
        ImageButton deleteButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            equipmentName = itemView.findViewById(R.id.equipmentName);
            quantityView = itemView.findViewById(R.id.quantityView);
            durationView = itemView.findViewById(R.id.durationView);
            startDateView = itemView.findViewById(R.id.startDateView);

            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);


        }

        public void bind(EquipmentSelect equipmentSelect){

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(equipmentSelect.getStartDate()));
            startDateView.setText(dateString);

            equipmentName.setText("Name : "+equipmentSelect.getEquipmentName());
            quantityView.setText("Quantity : "+equipmentSelect.getEquipmentQuantity());
            durationView.setText("Duration : "+equipmentSelect.getDurationYear()+" , "+equipmentSelect.getDurationMonth());
            startDateView.setText("Start date : "+dateString);


        }

    }

}
