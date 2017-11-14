package com.aust.tj.htmstrongertext.actions;

import android.content.Context;

/**
 * Created by tangjie on 10,十一月,2017
 */

public abstract class IAction {

    protected Context mContext;

    public IAction(Context context) {
        mContext = context;
    }

    /**
     * @return action name , must be equal html tag action attribute name.
     */
    public abstract String actionName();

    /**
     * action will be trig by framework automatically when action name be mapped.
     *
     * @param msg ,map action tag attribute value
     */
    public abstract void doAction(String msg);
}
