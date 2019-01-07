package com.xyd.useful.ui.manager;



import com.xyd.useful.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * activity栈管理
 */

public class ActivityCollector {

    public static List<BaseActivity> sActivities = new ArrayList<>();

    public static void addAvtivity(BaseActivity activity) {
        sActivities.add(activity);
    }

    public static void removeAvtivity(BaseActivity activity) {
        sActivities.remove(activity);
    }

    public static void finishAll(){
        for (BaseActivity activity : sActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }

    }
}
