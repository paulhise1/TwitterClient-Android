package Model;

import android.util.Log;
import org.json.JSONObject;

/**
 * Created by paulhise on 9/16/17.
 */

public class PHUser {

    private static final String TAG = "PHUser";

    public String name;
    public String profileImageUrl;
    public String location;

    public PHUser(JSONObject userObject) {

        try {

            this.name = userObject.getString("name");
            this.profileImageUrl = userObject.getString("profile_background_image_url_https");
            this.location = userObject.getString("location");

        } catch (Exception exception) {
            Log.d(TAG, "PHUser: User creation Exception - " + exception);
        }

    }

}

