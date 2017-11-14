package com.aust.tj.htmstrongertext.actions;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by tangjie on 14,十一月,2017
 */

public class ToastAction extends IAction {

    public ToastAction(Context context) {
        super(context);
    }

    @Override
    public String actionName() {
        return "toast";
    }

    @Override
    public void doAction(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
