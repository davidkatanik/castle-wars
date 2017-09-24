package davidkatanik.vsb.cz.dbStatistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import davidkatanik.vsb.cz.castlewars2.R;

/**
 * Created by David on 12.12.2015.
 */
public class StatisticsAdapter extends ArrayAdapter<StatisticsUnit> {
    private final Context context;
    private final List<StatisticsUnit> values;


    public StatisticsAdapter(Context context, List<StatisticsUnit> values) {
        super(context, R.layout.activity_statistics_unit, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_statistics_unit, parent, false);
        StatisticsUnit su = values.get(position);
        ((TextView) rowView.findViewById(R.id.tvHomePlayerStat)).setText(su.getHomePlayer());
        ((TextView) rowView.findViewById(R.id.tvAwayPlayerStat)).setText(su.getAwayPlayer());
        ((TextView) rowView.findViewById(R.id.tvHomeScoreStat)).setText(su.getHomeScore());
        ((TextView) rowView.findViewById(R.id.tvAwayScoreStat)).setText(su.getAwayScore());
        ((TextView) rowView.findViewById(R.id.tvDateStat)).setText(su.getDate());
        ((TextView) rowView.findViewById(R.id.tvRoundsStat)).setText(su.getRounds().toString());
        return rowView;
    }
}
