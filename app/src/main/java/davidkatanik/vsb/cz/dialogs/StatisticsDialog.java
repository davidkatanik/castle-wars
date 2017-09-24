package davidkatanik.vsb.cz.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import davidkatanik.vsb.cz.castlewars2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StatisticsDialog.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StatisticsDialog} factory method to
 * create an instance of this fragment.
 */
public class StatisticsDialog extends DialogFragment {
    private OnFragmentInteractionListener mListener;

    public StatisticsDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_statistics_dialog, container);
        getDialog().setTitle("Uložení do statistik");

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String red = ((EditText) view.findViewById(R.id.etRedPlayer)).getText().toString();
                final String blue = ((EditText) view.findViewById(R.id.etBluePlayer)).getText().toString();

                if (red.isEmpty() || blue.isEmpty()){
                    Toast.makeText(view.getContext(),"Musíš zadat obě jména hráčů",Toast.LENGTH_SHORT).show();
                }else {
                    mListener.onStatisticsSet(red, blue);
                    getDialog().cancel();
                }
            }
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onStatisticsSet(String redPlayer, String bluePlayer);
    }
}
