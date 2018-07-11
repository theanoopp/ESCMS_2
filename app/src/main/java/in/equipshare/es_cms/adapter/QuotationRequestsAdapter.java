package in.equipshare.es_cms.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.RFQSummary;

public class QuotationRequestsAdapter extends RecyclerView.Adapter<QuotationRequestsAdapter.MyViewHolder> {

    Context context;
    List<RFQSummary> rfqSummaries;
    AdapterCallback mCallback;



    public QuotationRequestsAdapter(Context context, List<RFQSummary> equipList,AdapterCallback callback) {

        this.context = context;
        this.rfqSummaries = equipList;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.bind(rfqSummaries.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onMethodCallback(position);

            }
        });

    }



    @Override
    public int getItemCount() {
        return rfqSummaries.size();
    }

    public interface AdapterCallback {
        void onMethodCallback(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rfqId;
        TextView companyName;
        TextView location;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            rfqId = itemView.findViewById(R.id.rfqId);
            companyName = itemView.findViewById(R.id.companyName);
            location = itemView.findViewById(R.id.location);

        }

        public void bind(RFQSummary quotation){

            rfqId.setText("company Name : "+quotation.getCompanyName());
            companyName.setText("RFQ Id : "+quotation.getRfqId());
            location.setText("Location : "+quotation.getLocation());

        }

    }



}
