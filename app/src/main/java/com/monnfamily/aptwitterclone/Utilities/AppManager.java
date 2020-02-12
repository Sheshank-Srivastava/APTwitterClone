package com.monnfamily.aptwitterclone.Utilities;

import java.util.List;

public class AppManager {

    private List<String> mUserList ;
    private List<String> mFanOf ;

    private static AppManager mInstance = null;

    public static AppManager get() {
        if (mInstance == null) {
            mInstance = new AppManager();
        }
        return mInstance;
    }

    /**
     * From here getter Function's start
     */
    public List<String> getmUserList() {
        return mUserList;
    }

    public List<String> getmFanOf() {
        return mFanOf;
    }

    /**
     * From here setter Function's start
     */
    public void setmUserList(List<String> mUserList) {
        this.mUserList = mUserList;
    }


    public void setmFanOf(List<String> mFanOf) {
        this.mFanOf = mFanOf;
    }
}
