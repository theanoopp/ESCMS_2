package in.equipshare.es_cms.dialog;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import in.equipshare.es_cms.R;
import in.equipshare.es_cms.model.EquipmentSelect;
import in.equipshare.es_cms.model.HaveEquipment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaveEqFragment extends DialogFragment {

    private HaveEquipment haveEquipment;


    private Calendar myCalendar;

    private OnFragmentInteractionListener mListener;

    private DatePickerDialog.OnDateSetListener dateDialog;

    private long milli_req_date;


    private String equipmentName,rate,date;


    //ui views
    private Spinner equipmentSpinner;

    private TextInputLayout rateInput;

    private TextView dateView;

    private Button addButton;

    public HaveEqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_have_eq, container, false);


        myCalendar = Calendar.getInstance();

        addButton = v.findViewById(R.id.addButton);

        equipmentSpinner = v.findViewById(R.id.eqSpinner);

        rateInput = v.findViewById(R.id.rateInput);


        addButton = v.findViewById(R.id.addButton);

        dateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateView = v.findViewById(R.id.selectDate);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getContext(), dateDialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkInput()){

                    HaveEquipment equipment = new HaveEquipment(equipmentName,date,rate);
                    onButtonPressed(equipment);
                    dismiss();

                }


            }
        });


        return v;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(HaveEquipment equipment) {
        if (mListener != null) {
            mListener.onFragmentInteraction(equipment);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }


    private boolean checkInput() {

        boolean cancel = true;

        equipmentName = equipmentSpinner.getSelectedItem().toString();
        date = dateView.getText().toString();
        rate = rateInput.getEditText().getText().toString();

        if(equipmentName.equals("Select Equipment")){

            Toast.makeText(getContext(),"Select equipment first", Toast.LENGTH_SHORT).show();
            cancel = false;

        }else if(date.equals("Select Date")){

            Toast.makeText(getContext(),"Select date", Toast.LENGTH_SHORT).show();
            cancel = false;

        }else if((milli_req_date<System.currentTimeMillis())) {

            Toast.makeText(getContext(),"Date Entered Should More From Current Date", Toast.LENGTH_SHORT).show();
            cancel = false;
        }else if(rate.equals("0") || rate.equals("")){

            Toast.makeText(getContext(),"Enter rate", Toast.LENGTH_SHORT).show();
            cancel = false;

        }

        return cancel;


    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date date=myCalendar.getTime();
        milli_req_date=date.getTime();
        dateView.setText(sdf.format(myCalendar.getTime()));

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(HaveEquipment equipment);
    }

}
