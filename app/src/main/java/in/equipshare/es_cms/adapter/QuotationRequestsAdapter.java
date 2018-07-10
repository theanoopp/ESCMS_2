package in.equipshare.es_cms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.HaveEquipment;
import in.equipshare.es_cms.model.QuotationRequests;

public class QuotationRequestsAdapter extends RecyclerView.Adapter<QuotationRequestsAdapter.MyViewHolder> {

    Context context;
    List<QuotationRequests> quotationList;
    AdapterCallback mCallback;



    public QuotationRequestsAdapter(Context context, List<QuotationRequests> equipList,AdapterCallback callback) {

        this.context = context;
        this.quotationList = equipList;
        this.mCallback = callback;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotation_row_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bind(quotationList.get(position));

    }



    @Override
    public int getItemCount() {
        return quotationList.size();
    }

    public interface AdapterCallback {
        void onMethodCallback(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView equipmentName;
        TextView companyName;
        TextView location;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            equipmentName = itemView.findViewById(R.id.equipmentName);
            companyName = itemView.findViewById(R.id.companyName);
            location = itemView.findViewById(R.id.location);

        }

        public void bind(QuotationRequests quotation){

            equipmentName.setText("Equipment Name : "+quotation.getEquipmentName());
            companyName.setText("Company Name : "+quotation.getCompanyName());
            location.setText("Start date : "+quotation.getLocation());

        }

    }



}
