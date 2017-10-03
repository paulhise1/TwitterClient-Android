package paulhise.phtwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import Model.PHJson;
import Model.PHTweet;
import retrofit2.Call;
import android.widget.TextView;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.ActivityLifecycleManager;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.Timeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;
import android.content.Intent;


public class HomeTimelineActivity extends AppCompatActivity {

    private static final String TAG = "HomeTimeLineActivity";

    private ListView tweetsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_timeline);

        Twitter.initialize(this);

        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();

        //TwitterCore.getInstance().getSessionManager().clearActiveSession();

        Log.d(TAG, "onCreate: Twitter Instance - " + Twitter.getInstance());

        if (session != null) {
            setupListView();
        } else {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
        Log.d(TAG, "onCreate: Twitter Instance - " + Twitter.getInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
    }

    private void setupListView() {

        tweetsListView = (ListView) findViewById(R.id.tweets_list_view);

        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();

        Call<List<Tweet>> homeTimelineCall = apiClient.getStatusesService().homeTimeline(null, null, null, null, null, null, null);

        homeTimelineCall.enqueue(new Callback<List<Tweet>>() {

            @Override
            public void success(Result<List<Tweet>> result) {
                for (Tweet tweet : result.data) {
                    Log.d(TAG, "success: Tweet Text - " + tweet.text);
                }

                FixedTweetTimeline timeline = new FixedTweetTimeline.Builder().setTweets(result.data).build();

                updateViewWithList(timeline);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "failure: " + exception.getMessage());
            }
        });


    }

    private void updateViewWithList(Timeline<Tweet> tweets) {
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(this, tweets);
        tweetsListView.setAdapter(adapter);

    }

}