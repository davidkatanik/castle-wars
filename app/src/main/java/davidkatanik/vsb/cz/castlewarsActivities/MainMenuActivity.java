package davidkatanik.vsb.cz.castlewarsActivities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

import java.util.ArrayList;

import davidkatanik.vsb.cz.castlewars2.R;
import davidkatanik.vsb.cz.dbStatistics.Statistics;
import davidkatanik.vsb.cz.game.Game;

public class MainMenuActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("vibrations", true);
        edit.apply();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMultiplayer) {
            Intent intent = new Intent(this, MultiplayerActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btnSettings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.btnAbout) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://homel.vsb.cz/~kat0013/tamz2/"));
            startActivity(intent);
        }
        if (v.getId() == R.id.btnStatistics) {
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        }
    }

}
