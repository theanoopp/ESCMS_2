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
import in.equipshare.es_cms.model.NeedEquipment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NeedEqFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NeedEqFragment extends DialogFragment {

    private NeedEquipment equipmentSelect;

    private TextView dateView;

    private Calendar myCalendar;

    private OnFragmentInteractionListener mListener;

    private DatePickerDialog.OnDateSetListener date;

    private long milli_req_date;

    private Button addButton;

    private String equipmentName,equipmentQuantity,durationMonth,durationYear,startDate;


    private Spinner equipmentSpinner,quantitySpinner,monthSpinner,yearSpinner;

    private TextInputLayout briefInput;


    public NeedEqFragment() {
        // Required empty public constructor
    }

    public static NeedEqFragment newInstance(NeedEquipment equipmentSelect) {
        NeedEqFragment fragment = new NeedEqFragment();
        Bundle args = new Bundle();
        args.putSerializable("model",equipmentSelect);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            equipmentSelect = (NeedEquipment) getArguments().getSerializable("model");

        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_eq, container, false);

        myCalendar = Calendar.getInstance();

        addButton = v.findViewById(R.id.addButton);

        equipmentSpinner = v.findViewById(R.id.eqSpinner);
        quantitySpinner = v.findViewById(R.id.quantitySpinner);
        monthSpinner = v.findViewById(R.id.monthSpinner);
        yearSpinner = v.findViewById(R.id.yearSpinner);

        briefInput = v.findViewById(R.id.briefInput);

        date = new DatePickerDialog.OnDateSetListener() {

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

                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkInput()){

                    String brief = briefInput.getEditText().getText().toString();

                    NeedEquipment equipment = new NeedEquipment(equipmentName,equipmentQuantity,durationMonth,durationYear,brief,milli_req_date);
                    onButtonPressed(equipment);
                    dismiss();

                }


            }
        });

        return v;
    }

    private boolean checkInput() {

        boolean cancel = true;

        equipmentName = equipmentSpinner.getSelectedItem().toString();
        // equipmentName,equipmentQuantity,durationMonth,durationYear,startDate;
        equipmentQuantity = quantitySpinner.getSelectedItem().toString();
        durationMonth = monthSpinner.getSelectedItem().toString();
        durationYear = yearSpinner.getSelectedItem().toString();
        startDate = dateView.getText().toString();

        if(equipmentName.equals("Select Equipment")){

            Toast.makeText(getContext(),"Select equipment first", Toast.LENGTH_SHORT).show();
            cancel = false;

        }else if(startDate.equals("Select Date")){

            Toast.makeText(getContext(),"Select date", Toast.LENGTH_SHORT).show();
            cancel = false;

        }else if((milli_req_date<System.currentTimeMillis())) {

            Toast.makeText(getContext(),"Date Entered Should More From Current Date", Toast.LENGTH_SHORT).show();
            cancel = false;
        }

        return cancel;


    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date date=myCalendar.getTime();

        milli_req_date = date.getTime();

        dateView.setText(sdf.format(myCalendar.getTime()));

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(NeedEquipment equipment) {
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



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(NeedEquipment equipment);
    }
}
