package in.equipshare.es_cms.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.EquipmentSelect;
import in.equipshare.es_cms.model.HaveEquipment;

public class HaveListAdapter extends RecyclerView.Adapter<HaveListAdapter.MyViewHolder> {

    ArrayList<HaveEquipment> equipList;
    Context context;
    AdapterCallback mCallback;

    public HaveListAdapter(Context context, ArrayList<HaveEquipment> equipList,AdapterCallback callback) {

        this.context = context;
        this.equipList = equipList;
        this.mCallback = callback;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.have_equipment_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        // set the data in items

        holder.bind(equipList.get(position));
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onMethodCallback(position);

            }
        });

    }



    @Override
    public int getItemCount() {
        return equipList.size();
    }

    public interface AdapterCallback {
        void onMethodCallback(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView equipmentName;
        TextView rateView;
        TextView startDateView;

        ImageButton deleteButton ;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            equipmentName = itemView.findViewById(R.id.equipmentName);
            rateView = itemView.findViewById(R.id.rateView);
            startDateView = itemView.findViewById(R.id.startDateView);
            deleteButton = itemView.findViewById(R.id.deleteButton2);


        }

        public void bind(HaveEquipment equipmentSelect){

            equipmentName.setText("Name : "+equipmentSelect.getEquipmentName());
            rateView.setText("Quantity : "+equipmentSelect.getRate());
            startDateView.setText("Start date : "+equipmentSelect.getStartDate());


        }

    }
}
