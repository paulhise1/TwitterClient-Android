package Model;

import android.util.Log;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by paulhise on 9/16/17.
 */

public class PHJson {

    private static final String TAG = "PHJson";

    public static String getJSONAsString(Context context) {

        String jsonString = null;

        try {
            InputStream stream = context.getAssets().open("Tweets.json");

            byte[] buffer = new byte[stream.available()];

            stream.read(buffer);
            stream.close();

            jsonString = new String(buffer, "UTF-8");

        } catch (Exception exception) {
            Log.d(TAG, "getSampleJSONAsString: " + exception);
        }

        return jsonString;


    }

    public static ArrayList<PHTweet> getTweets(Context context, Boolean useSampleJSON) {

        ArrayList<PHTweet> tweets = new ArrayList<>();

        if(useSampleJSON = true){

            String tweetsJSONString = getJSONAsString(context);

            try {

                JSONArray tweetsJson = new JSONArray(tweetsJSONString);

                for (int i = 0; i < tweetsJson.length(); i++){

                    JSONObject singleTweetObject = tweetsJson.getJSONObject(i);

                    tweets.add(new PHTweet(singleTweetObject));
                }

            } catch (Exception exception){
                Log.d(TAG, "getTweets: Exception Parsing Tweets Array - " + exception);
            }


        }

        return tweets;

    }

}
