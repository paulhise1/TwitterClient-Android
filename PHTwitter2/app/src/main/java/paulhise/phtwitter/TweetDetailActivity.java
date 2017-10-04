package paulhise.phtwitter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.twitter.sdk.android.core.models.Tweet;
import android.widget.TextView;
import android.widget.Button;

import java.io.InputStream;

import Model.PHTweet;

public class TweetDetailActivity extends AppCompatActivity {

    private static final String TAG = "Tweet Detail Activity";

    private Tweet mSelectedTweet;
    private ImageView mImageView;
    private TextView mUsernameView;
    private TextView mTweetTextView;
    private Button mViewFeedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        mSelectedTweet = PHTweet.selectedTweet;

        configureLayout();
    }

    private void configureLayout(){

        mImageView = (ImageView) findViewById(R.id.imageView_profile_image);

        mUsernameView = (TextView) findViewById(R.id.textView_username);
        mTweetTextView = (TextView) findViewById(R.id.textView_tweet_text);

        mViewFeedButton = (Button) findViewById(R.id.button_view_feed);

        mUsernameView.setText(mSelectedTweet.user.screenName);
        mTweetTextView.setText(mSelectedTweet.text);

        Log.d(TAG, "configureLayout: Profile Image URL" + mSelectedTweet.user.profileImageUrlHttps);
        new ImageDownloadTask().execute(mSelectedTweet.user.profileImageUrlHttps);

        mViewFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lab
            }
        });
    }

    class ImageDownloadTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            String profileImageURL = strings[0];

            Bitmap bitmap = null;

            try {

                InputStream input = new java.net.URL(profileImageURL).openStream();

                bitmap = BitmapFactory.decodeStream(input);

            } catch (Exception exception) {
                Log.d(TAG, "doInBackground: Image URL Malformed - " + exception.getMessage());
                return null;

            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            mImageView.setImageBitmap(bitmap);
        }
    }
}
