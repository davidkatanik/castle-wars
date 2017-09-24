package davidkatanik.vsb.cz.castlewarsActivities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import davidkatanik.vsb.cz.castlewars2.R;

public class StatisticsUnitActivity extends Activity {

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("vibrations", true);
        edit.apply();
        setContentView(R.layout.activity_statistics_unit);
    }
}
