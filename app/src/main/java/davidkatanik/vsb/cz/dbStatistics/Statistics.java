package davidkatanik.vsb.cz.dbStatistics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Statistics extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "Statistics.db";
    private static final String STATISTICS_TABLE = "statistics";
    private static final String CREATE = "create table if not exists " + STATISTICS_TABLE + " (id integer primary key, home_player varchar, away_player varchar, date varchar, home_score integer, away_score integer, rounds integer)";

    public Statistics(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + STATISTICS_TABLE);
        onCreate(db);
    }

    public boolean insertStatistics(String home_player, String away_player, int home_score, int away_score, int rounds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("home_player", home_player);
        contentValues.put("away_player", away_player);
        contentValues.put("date", new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss", Locale.GERMAN).format(new Date()));
        contentValues.put("home_score", home_score);
        contentValues.put("away_score", away_score);
        contentValues.put("rounds", rounds);
        db.insert(STATISTICS_TABLE, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from " + STATISTICS_TABLE + " where id=" + id + "", null);
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, STATISTICS_TABLE);
    }

    public boolean updateStatistics(Integer id, String home_player, String away_player, int home_score, int away_score, int rounds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("home_player", home_player);
        contentValues.put("away_player", away_player);
        contentValues.put("date", (new Date()).toString());
        contentValues.put("home_score", home_score);
        contentValues.put("away_score", away_score);
        contentValues.put("rounds", rounds);
        db.update(STATISTICS_TABLE, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteStatistics(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(STATISTICS_TABLE, "id = ? ", new String[]{Integer.toString(id)});
    }

    public void deleteAllStatistics(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STATISTICS_TABLE, "1", null);
    }

    public List<StatisticsUnit> getStatistics()
    {
        List<StatisticsUnit> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+STATISTICS_TABLE, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            StatisticsUnit sb = new StatisticsUnit();
            sb.setId(Integer.valueOf(res.getString(res.getColumnIndex("id"))));
            sb.setHomePlayer(res.getString(res.getColumnIndex("home_player")));
            sb.setAwayPlayer(res.getString(res.getColumnIndex("away_player")));
            sb.setDate(res.getString(res.getColumnIndex("date")));
            sb.setHomeScore(res.getString(res.getColumnIndex("home_score")));
            sb.setAwayScore(res.getString(res.getColumnIndex("away_score")));
            sb.setRounds(Integer.valueOf(res.getString(res.getColumnIndex("rounds"))));

            array_list.add(sb);

            res.moveToNext();
        }
        res.close();
        return array_list;
    }
}
