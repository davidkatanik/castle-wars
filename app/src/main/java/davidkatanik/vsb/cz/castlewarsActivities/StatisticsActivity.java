package davidkatanik.vsb.cz.castlewarsActivities;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import davidkatanik.vsb.cz.castlewars2.R;
import davidkatanik.vsb.cz.dbStatistics.Statistics;
import davidkatanik.vsb.cz.dbStatistics.StatisticsAdapter;
import davidkatanik.vsb.cz.dbStatistics.StatisticsUnit;

public class StatisticsActivity extends Activity implements View.OnClickListener {

    Statistics statistics;
    ArrayAdapter<StatisticsUnit> adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        statistics = new Statistics(this);
        setAdapter();
    }

    private void setAdapter() {
        adapter = new StatisticsAdapter(this, statistics.getStatistics());
        list = (ListView) findViewById(R.id.listStatistics);
        list.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDeleteAllStat) {
            statistics.deleteAllStatistics();
            setAdapter();
        }
    }
}
