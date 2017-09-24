package davidkatanik.vsb.cz.castlewarsActivities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import davidkatanik.vsb.cz.castlewars2.R;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SeekBar sbMusic = (SeekBar) findViewById(R.id.sbMusicSet);
        SeekBar sbSound = (SeekBar) findViewById(R.id.sbSoundSet);
        Switch swVibrations = (Switch) findViewById(R.id.swVibrationsSet);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor edit = sharedPreferences.edit();

        boolean vibrations = sharedPreferences.getBoolean("vibrations", true);
        float music = sharedPreferences.getFloat("music", 0.5f);
        float sound = sharedPreferences.getFloat("sound", 0.5f);

        swVibrations.setChecked(vibrations);
        sbMusic.setProgress((int) (music * 10));
        sbSound.setProgress((int) (sound * 10));


        sbMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edit.putFloat("music", (float) progress / 10);
                edit.apply();
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
                edit.putFloat("sound", (float) progress / 10);
                edit.apply();
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
                edit.putBoolean("vibrations", isChecked);
                edit.apply();
            }
        });

    }
}
