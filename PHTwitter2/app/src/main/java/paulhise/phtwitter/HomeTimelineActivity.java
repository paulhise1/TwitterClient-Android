package paulhise.phtwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import Model.PHJson;
import Model.PHTweet;
import android.widget.TextView;

import com.twitter.sdk.android.core.Twitter;

public class HomeTimelineActivity extends AppCompatActivity {

    private static final String TAG = "HomeTimeLineActivity";

    private ListView tweetsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Twitter.initialize(this);

        Log.d(TAG, "onCreate: Twitter Instance - " + Twitter.getInstance());

        setContentView(R.layout.activity_home_timeline);

        setupListView();
    }

    private void setupListView() {

        tweetsListView = (ListView) findViewById(R.id.tweets_list_view);

        ArrayList<PHTweet> allTweets = PHJson.getTweets(this, true);

        TimeLineAdapter adapter = new TimeLineAdapter(allTweets);

        tweetsListView.setAdapter(adapter);

    }

    class TimeLineAdapter extends TweetListAdapter {

        public TimeLineAdapter(ArrayList<PHTweet> allTweets) {
            super(allTweets);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            PHTweet currentTweet = (PHTweet) getItem(i);

            view = getLayoutInflater().inflate(R.layout.tweet_list_item, null);

            TextView usernameView = (TextView) view.findViewById(R.id.textView_username);
            TextView tweetTextView = (TextView) view.findViewById(R.id.textView_tweet_text);

            usernameView.setText("Paul");
            tweetTextView.setText(currentTweet.text);

            return view;
        }
    }

    abstract class TweetListAdapter extends BaseAdapter{

        private ArrayList<PHTweet> allTweets;

        public TweetListAdapter(ArrayList<PHTweet> allTweets){
            super();

            this.allTweets = allTweets;

        }

        @Override
        public int getCount() {
            return allTweets.size();
        }

        @Override
        public Object getItem(int i) {
            return allTweets.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

    }

}