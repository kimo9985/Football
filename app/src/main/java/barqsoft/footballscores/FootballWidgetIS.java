package barqsoft.footballscores;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;

/**
 * Created by Kimo on 1/30/2016.
 * sunshine widget
 */
public class FootballWidgetIS extends IntentService {

    //Creates an IntentService.  Invoked by your subclass's constructor.//
    public FootballWidgetIS() {
        super("FootballWidgetIS");
    }

    //Specify columns that are needed//
    private static final String[] FOOTBALL_COLUMNS = {
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL,
            DatabaseContract.scores_table.TIME_COL
    };

    //These indicies are tied to FOOTBALL_COLUMNS//
    static final int COL_HOME_TEAM = 0;
    static final int COL_AWAY_TEAM = 1;
    static final int COL_HOME_GOAL = 2;
    static final int COL_AWAY_GOAL = 3;
    static final int COL_TIME = 4;


    @Override
    protected void onHandleIntent(Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, FootballWidget.class));

        Cursor cursor = getContentResolver().query(DatabaseContract.BASE_CONTENT_URI, FOOTBALL_COLUMNS, null, null, null);

        if (cursor == null) {
            return;
        }

        if (!cursor.moveToFirst()) {
            cursor.close();
            return;
        }

        String homeName = cursor.getString(COL_HOME_TEAM);
        String awayName = cursor.getString(COL_AWAY_TEAM);
        int homeGoal = cursor.getInt(COL_HOME_GOAL);
        int awayGoal = cursor.getInt(COL_AWAY_GOAL);
        String gameScores = Utilies.getScores(this, homeGoal, awayGoal);
        String time = cursor.getString(COL_TIME);

        int homeCrest = Utilies.getTeamCrestByTeamName(this, homeName);
        int awayCrest = Utilies.getTeamCrestByTeamName(this, awayName);
        cursor.close();

        //Perform this loop procedure for each widget//
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);

            //Add data to RemoteViews//
            views.setTextViewText(R.id.home_name, homeName);
            views.setImageViewResource(R.id.home_crest, homeCrest);
            views.setTextViewText(R.id.away_name, awayName);
            views.setImageViewResource(R.id.away_crest, awayCrest);
            views.setTextViewText(R.id.score_textview, gameScores);
            views.setTextViewText(R.id.data_textview, time);

            //Create Intent to launch MainActivity//
            Intent launchIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, launchIntent, 0);
            views.setOnClickPendingIntent(R.id.widget, pendingIntent);

            //Tell AppWidgetManager to perform an update on current app widget//
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
