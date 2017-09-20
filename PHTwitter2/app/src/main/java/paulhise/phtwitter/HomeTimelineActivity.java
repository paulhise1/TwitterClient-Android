package paulhise.phtwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import Model.PHJson;
import Model.PHTweet;
import android.widget.ArrayAdapter;

public class HomeTimelineActivity extends AppCompatActivity {

    private static final String TAG = "HomeTimeLineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_timeline);

        ListView tweetsListView = (ListView) findViewById(R.id.tweets_list_view);
        ArrayList<PHTweet> allTweets = PHJson.getTweets(this, true);
        ArrayList<String> tweetsAsString = new ArrayList<String>();

        for (PHTweet tweet: allTweets) {
            Log.d(TAG, "Tweet Text: " + tweet.text);
            tweetsAsString.add(tweet.text);

        }

        Log.d(TAG, "onCreate: "  + PHJson.getJSONAsString(this));

        ArrayAdapter<String> tweetsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tweetsAsString);

        // Assign adapter to ListView
        tweetsListView.setAdapter(tweetsAdapter);

    }

}
