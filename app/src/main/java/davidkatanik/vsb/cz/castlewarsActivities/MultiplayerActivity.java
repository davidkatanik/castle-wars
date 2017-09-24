package davidkatanik.vsb.cz.castlewarsActivities;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import davidkatanik.vsb.cz.gameUtils.CardTypes;
import davidkatanik.vsb.cz.gameCard.Card;
import davidkatanik.vsb.cz.castlewars2.R;
import davidkatanik.vsb.cz.fragments.CardFragment;
import davidkatanik.vsb.cz.fragments.PlayerProperties.OnFragmentInteractionListener;
import davidkatanik.vsb.cz.dbStatistics.Statistics;
import davidkatanik.vsb.cz.dialogs.SettingsDialog;
import davidkatanik.vsb.cz.dialogs.StatisticsDialog;
import davidkatanik.vsb.cz.game.Game;
import davidkatanik.vsb.cz.game.Player;

public class MultiplayerActivity extends Activity implements OnFragmentInteractionListener, CardFragment.OnFragmentInteractionListener, SettingsDialog.OnFragmentInteractionListener, StatisticsDialog.OnFragmentInteractionListener {
    private final float INITIAL_VOLUME = (float) 0.5;

    private Game game;

    private List<ImageButton> cards;

    private View frRed;
    private View frBlue;
    private View frCards;

    private DialogFragment soundDialog;

    private MediaPlayer mpMusic = null;
    private MediaPlayer mpCards = null;

    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;

    private void loadPlayerIndicator(Player actualPlayer) {
        if (game.isPlayerHome(actualPlayer)) {
            (findViewById(R.id.ivActualPlayerColor)).setBackgroundColor(Color.RED);
        } else {
            (findViewById(R.id.ivActualPlayerColor)).setBackgroundColor(Color.BLUE);
        }
    }

    private void loadPlayersProperties() {
        loadPlayerProperties(frRed, game.getRed());
        loadPlayerProperties(frBlue, game.getBlue());
    }

    private void loadPlayerProperties(View view, Player player) {
        ((TextView) view.findViewById(R.id.tvBuilders)).setText(player.getBuilders() + "");
        ((TextView) view.findViewById(R.id.tvBricks)).setText(player.getBricks() + "");
        ((TextView) view.findViewById(R.id.tvSoldiers)).setText(player.getSoldiers() + "");
        ((TextView) view.findViewById(R.id.tvWeapons)).setText(player.getWeapons() + "");
        ((TextView) view.findViewById(R.id.tvMagi)).setText(player.getMagi() + "");
        ((TextView) view.findViewById(R.id.tvCrystals)).setText(player.getCrystals() + "");
        ((TextView) view.findViewById(R.id.tvCastle)).setText(player.getCastle() + "");
        ((TextView) view.findViewById(R.id.tvFence)).setText(player.getFence() + "");
    }


    private void loadPlayerCards(Player player) {
        for (int i = 0; i < player.getCards().size(); i++) {
            Card c = player.getCards().get(i);
            String s;
            if (game.checkIsAvailable(c)) {
                s = c.getType().toString().toLowerCase();
            } else {
                s = c.getType().toString().toLowerCase() + "x";
            }
            int resID = getResources().getIdentifier(s, "drawable", getPackageName());

            try {
                cards.get(i).setImageDrawable(this.getDrawable(resID));
            } catch (Resources.NotFoundException e) {
                Log.e("Error in card loading:", resID + "");
            }
        }
    }

    private void loadPlayersCastleAndWall() {
        ImageView hCastle = (ImageView) findViewById(R.id.ivHCastle);
        ImageView hFence = (ImageView) findViewById(R.id.ivHFence);
        ImageView eCastle = (ImageView) findViewById(R.id.ivECastle);
        ImageView eFence = (ImageView) findViewById(R.id.ivEFence);

        Player red = game.getRed();
        Player blue = game.getBlue();


        float scale = getResources().getDisplayMetrics().density;

        int paddingPixel = (int) (380 - 2.5 * red.getCastle());
        int paddingDp = (int) (paddingPixel * scale);
        hCastle.setPadding(0, paddingDp, 0, 0);
        paddingPixel = 400 - 3 * red.getFence();
        paddingDp = (int) (paddingPixel * scale);
        hFence.setPadding(0, paddingDp, 0, 0);

        paddingPixel = (int) (380 - 2.5 * blue.getCastle());
        paddingDp = (int) (paddingPixel * scale);
        eCastle.setPadding(0, paddingDp, 0, 0);
        paddingPixel = 400 - 3 * blue.getFence();
        paddingDp = (int) (paddingPixel * scale);
        eFence.setPadding(0, paddingDp, 0, 0);
    }

    private void loadUsedCard(Card card) {
        try {
            ((ImageView) findViewById(R.id.ivLastCard)).setImageDrawable(this.getDrawable(getResources().getIdentifier(card.getType().toString().toLowerCase(), "drawable", getPackageName())));
        } catch (Resources.NotFoundException e) {
            Log.e("Error in loading:", "Used card loading");
        }
    }

    private void initCardsButtons() {
        cards = new ArrayList<>();

        cards.add((ImageButton) frCards.findViewById(R.id.ibC1));
        cards.add((ImageButton) frCards.findViewById(R.id.ibC2));
        cards.add((ImageButton) frCards.findViewById(R.id.ibC3));
        cards.add((ImageButton) frCards.findViewById(R.id.ibC4));
        cards.add((ImageButton) frCards.findViewById(R.id.ibC5));
        cards.add((ImageButton) frCards.findViewById(R.id.ibC6));
    }


    private void makeWinnerSound() {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.aplaus);
        float x = sharedPreferences.getFloat("sound", INITIAL_VOLUME);
        mp.setVolume(x, x);
        mp.start();
    }

    private void makeSound(Card card) {
        CardTypes type = card.getType();
        if (mpCards != null)
            mpCards.release();
        if (type == CardTypes.PLATOON || type == CardTypes.RIDER || type == CardTypes.DRAGON || type == CardTypes.ARCHER || type == CardTypes.CATAPULT || type == CardTypes.BANSHEE || type == CardTypes.WAIN) {
            mpCards = MediaPlayer.create(this, (game.getActualPlayer().getFence() >= card.getAbilities().get(0).getPower()) ? R.raw.low_fence : R.raw.low_castle);
        } else if (type == CardTypes.RECRUIT || type == CardTypes.SCHOOL || type == CardTypes.SORCERER) {
            mpCards = MediaPlayer.create(this, R.raw.high_strenght);
        } else if (type == CardTypes.SABOTEUR || type == CardTypes.CRUSH_BRICKS || type == CardTypes.CRUSH_CRYSTALS || type == CardTypes.CRUSH_WEAPONS) {
            mpCards = MediaPlayer.create(this, R.raw.low_stock);
        } else if (type == CardTypes.BASE || type == CardTypes.BABYLON || type == CardTypes.PIXIES || type == CardTypes.FORT || type == CardTypes.RESERVE || type == CardTypes.TOWER) {
            mpCards = MediaPlayer.create(this, R.raw.high_castle);
        } else if (type == CardTypes.FENCE || type == CardTypes.WALL || type == CardTypes.DEFENCE) {
            mpCards = MediaPlayer.create(this, R.raw.high_fence);
        } else if (type == CardTypes.THIEF || type == CardTypes.CONJURE_BRICKS || type == CardTypes.CONJURE_CRYSTALS || type == CardTypes.CONJURE_WEAPONS) {
            mpCards = MediaPlayer.create(this, R.raw.high_stock);
        } else if (type == CardTypes.CURSE) {
            mpCards = MediaPlayer.create(this, R.raw.course);
        } else if (type == CardTypes.SWAT) {
            mpCards = MediaPlayer.create(this, R.raw.low_castle);
        } else {
            return;
        }
        float x = sharedPreferences.getFloat("sound",INITIAL_VOLUME);
        mpCards.setVolume(x, x);
        mpCards.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        frRed = findViewById(R.id.frRedPlayer);
        frBlue = findViewById(R.id.frBluePlayer);
        frCards = findViewById(R.id.frCards);

        initCardsButtons();
        initGame();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        soundDialog = new SettingsDialog();

        mpMusic = MediaPlayer.create(this, R.raw.adrian_von_ziegler_android);
        float x = sharedPreferences.getFloat("music",INITIAL_VOLUME);
        mpMusic.setVolume(x, x);
        mpMusic.start();


    }

    private void initGame() {
        game = new Game("", "");
        loadPlayerIndicator(game.getActualPlayer());
        loadPlayerCards(game.getActualPlayer());
        loadPlayersProperties();
        loadPlayersCastleAndWall();
    }

    public void onCardClick(View v) {
        if (game.checkWinner() != null) {
            return;
        }
        int cardPosition = 0;
        if (v.getId() == R.id.ibC1) {
            cardPosition = 0;
        } else if (v.getId() == R.id.ibC2) {
            cardPosition = 1;
        } else if (v.getId() == R.id.ibC3) {
            cardPosition = 2;
        } else if (v.getId() == R.id.ibC4) {
            cardPosition = 3;
        } else if (v.getId() == R.id.ibC5) {
            cardPosition = 4;
        } else if (v.getId() == R.id.ibC6) {
            cardPosition = 5;
        }
        Card card = game.getActualPlayer().getCards().get(cardPosition);
        boolean avilible = false;
        if (game.checkIsAvailable(card)) {
            makeSound(game.getActualPlayer().getCards().get(cardPosition));
            makeVibration();
            avilible = true;
        }
        game.nextMove(card);
        if (avilible)
            loadUsedCard(card);
        loadPlayersProperties();
        loadPlayersCastleAndWall();
        if (game.checkWinner() != null) {
            makeWinnerSound();

            StatisticsDialog statisticsDialog = new StatisticsDialog();
            statisticsDialog.show(getFragmentManager(), "statistics");
            return;
        }
        loadPlayerIndicator(game.getActualPlayer());
        loadPlayerCards(game.getActualPlayer());
    }

    private void makeVibration() {

        boolean vibrations = sharedPreferences.getBoolean("vibrations", false);
        if (vibrations) {
            Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(200);
        }
    }

    public void onClickPause(View v) {
        soundDialog.show(getFragmentManager(), "sound settings");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onMusicProgressChanged(float progress) {
        mpMusic.setVolume(progress,progress);
        edit = sharedPreferences.edit();
        edit.putFloat("music", progress);
        edit.apply();
    }

    @Override
    public void onSoundProgressChanged(float progress) {
        if (mpCards != null){
            mpCards.setVolume(progress,progress);
        }
        edit = sharedPreferences.edit();
        edit.putFloat("sound", progress);
        edit.apply();
    }

    @Override
    public void onVibrationsCheckChanged(boolean isChecked) {
        edit = sharedPreferences.edit();
        edit.putBoolean("vibrations", isChecked);
        edit.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpCards != null)
            mpCards.release();
        if (mpMusic != null)
            mpMusic.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mpMusic != null)
            mpMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mpMusic != null)
            mpMusic.start();
    }

    @Override
    public void onStatisticsSet(String redPlayer, String bluePlayer) {
        Statistics statistics = new Statistics(this);
        statistics.insertStatistics(redPlayer, bluePlayer, game.getRed().getCastle(), game.getBlue().getCastle(), game.getRound());
        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
    }
}
