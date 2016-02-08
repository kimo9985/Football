package barqsoft.footballscores;

import android.content.Context;

/**
 * Created by yehya khaled on 3/3/2015.
 */
public class Utilies
{
    public static final int SERIE_A = 357;
    public static final int PREMIER_LEGAUE = 354;
    public static final int CHAMPIONS_LEAGUE = 362;
    public static final int PRIMERA_DIVISION = 358;
    public static final int BUNDESLIGA = 351;
    public static String getLeague(Context context, int league_num)
    {
        switch (league_num)
        {
            case SERIE_A : return context.getString(R.string.SERIE_A);
            case PREMIER_LEGAUE : return context.getString(R.string.PREMIER_LEGAUE);
            case CHAMPIONS_LEAGUE : return context.getString(R.string.CHAMPIONS_LEAGUE);
            case PRIMERA_DIVISION : return context.getString(R.string.PRIMERA_DIVISION);
            case BUNDESLIGA : return context.getString(R.string.BUNDESLIGA);
            default: return context.getString(R.string.UNKNOWN);
        }
    }
    public static String getMatchDay(int match_day,int league_num)
    {
        if(league_num == CHAMPIONS_LEAGUE)
        {
            if (match_day <= 6)
            {
                return "Group Stages, Matchday : 6";
            }
            else if(match_day == 7 || match_day == 8)
            {
                return "First Knockout round";
            }
            else if(match_day == 9 || match_day == 10)
            {
                return "QuarterFinal";
            }
            else if(match_day == 11 || match_day == 12)
            {
                return "SemiFinal";
            }
            else
            {
                return "Final";
            }
        }
        else
        {
            return "Matchday : " + String.valueOf(match_day);
        }
    }

    public static String getScores(Context context, int home_goals, int awaygoals)
    {
        if(home_goals < 0 || awaygoals < 0)
        {
            return " - ";
        }
        else
        {
            return String.valueOf(home_goals) + " - " + String.valueOf(awaygoals);
        }
    }

    public static int getTeamCrestByTeamName (Context context, String teamname)
    {
        if (teamname==null){return R.drawable.no_icon;}
        switch (teamname)
        { //This is the set of icons that are currently in the app. Feel free to find and add more
            //as you go.
            case "Arsenal London FC" : return R.drawable.arsenal;
            case "Manchester United FC" : return R.drawable.manchester_united;
            case "Swansea City" : return R.drawable.swansea_city_afc;
            case "Leicester City" : return R.drawable.leicester_city_fc_hd_logo;
            case "Everton FC" : return R.drawable.everton_fc_logo1;
            case "West Ham United FC" : return R.drawable.west_ham;
            case "Tottenham Hotspur FC" : return R.drawable.tottenham_hotspur;
            case "West Bromwich Albion" : return R.drawable.west_bromwich_albion_hd_logo;
            case "Sunderland AFC" : return R.drawable.sunderland;
            case "Stoke City FC" : return R.drawable.stoke_city;
            case "Liverpool FC" : return R.drawable.liverpool;
            case "Chelsea FC" : return R.drawable.chelsea;
            case "Aston Villa FC" : return R.drawable.aston_villa;
            case "Crystal Palace FC" : return R.drawable.crystal_palace_fc;
            case "Leicester City FC" : return R.drawable.leicester_city_fc_hd_logo;
            case "Newcastle United FC" : return R.drawable.newcastle_united;
            case "Southampton FC" : return R.drawable.southampton_fc;
            case "Manchester City FC" : return R.drawable.manchester_city;
            case "West Bromwich Albion FC" : return R.drawable.west_bromwich_albion_hd_logo;
            default: return R.drawable.ic_launcher;
        }
    }
}