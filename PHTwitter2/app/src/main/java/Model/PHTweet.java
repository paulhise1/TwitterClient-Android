package Model;

import org.json.JSONObject;
import android.util.Log;
/**
 * Created by paulhise on 9/16/17.
 */

public class PHTweet {

    private static final String TAG = "PHTWeet";

    public String text;
    public String id;

    public PHUser user;

    public PHTweet(JSONObject tweetObject) {

        try {

            this.text = tweetObject.getString("text");
            this.id = tweetObject.getString("id_str");

            this.user = new PHUser(tweetObject.getJSONObject("user"));

        } catch (Exception exception) {
            Log.d(TAG, "PHTweet: Tweet Creation Exception - " + exception);
        }

    }

}
