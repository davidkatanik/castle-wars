package davidkatanik.vsb.cz.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import davidkatanik.vsb.cz.castlewars2.R;

public class SettingsDialog extends DialogFragment {
    private OnFragmentInteractionListener mListener;

    public SettingsDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_dialog, container);

        SeekBar sbMusic = (SeekBar) view.findViewById(R.id.sbMusic);
        SeekBar sbSound = (SeekBar) view.findViewById(R.id.sbSound);
        Switch swVibrations = (Switch) view.findViewById(R.id.swVibrations);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());

        boolean vibrations = sharedPreferences.getBoolean("vibrations", true);
        float music = sharedPreferences.getFloat("music", 0.5f);
        float sound = sharedPreferences.getFloat("sound", 0.5f);

        swVibrations.setChecked(vibrations);
        sbMusic.setProgress((int) (music * 10));
        sbSound.setProgress((int) (sound * 10));

        getDialog().setTitle("Nastavení");
        sbMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mListener.onMusicProgressChanged((float) progress / 10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sbSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mListener.onSoundProgressChanged((float) progress / 10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        swVibrations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListener.onVibrationsCheckChanged(isChecked);
            }
        });

        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
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
        void onMusicProgressChanged(float progress);

        void onSoundProgressChanged(float progress);

        void onVibrationsCheckChanged(boolean isChecked);
    }

}
