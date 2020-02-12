package com.monnfamily.aptwitterclone.Utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class Utilities {
    private static Utilities mInstance = null;
    private Context context;

    public static Utilities get() {
        if (mInstance == null) {
            mInstance = new Utilities();
        }
        return mInstance;
    }

    public String follow(String mUser, final Context context) {
        ParseUser.getCurrentUser().add("fanOf", mUser.trim());
        this.context = context;
        savBackToBack4App();
        return "Started following ";
    }

    public String unFollow(String mUser, Context context) {

        /**
         * Steps to remove an element from a list and update that list
         * Basically
         */
        // Todo:1. remove the user from the list locally
        ParseUser.getCurrentUser().getList("fanOf").remove(mUser.trim());
        // Todo:2. Get the all the user that are in the persent list after removal
        List currentUserFanOfList = ParseUser.getCurrentUser().getList("fanOf");
        // Todo:3. Remove all the data to that particular cell on the back4app
        ParseUser.getCurrentUser().remove("fanOf");
        // Todo:4. Again add the list of user from step 2.
        ParseUser.getCurrentUser().put("fanOf", currentUserFanOfList);
        // Todo:5. save the data back to back4App
        this.context = context;
        savBackToBack4App();
        return "Unfollowed ";
    }

    /**
     *  The Parse user with it's current data set will be uploaded to the server
     */
    private void savBackToBack4App() {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Saving...");
        dialog.setCancelable(false);
        dialog.show();

        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) return;
                Toast.makeText(context, "New Content Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
