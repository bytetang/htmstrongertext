package com.aust.tj.htmstrongertext.handler;

import android.content.Context;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.aust.tj.htmstrongertext.StrongerHtmlText;
import com.aust.tj.htmstrongertext.actions.IAction;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangjie on 10,十一月,2017
 */

public class ActionTagHandler extends TagHandlerWrapper {

    private Context mContex;

    public ActionTagHandler(Context context) {
        mContex = context;
    }

    public static class Action {

        private String mName;
        private String mValue;

        public Action(String name, String value) {
            mName = name;
            mValue = value;
        }
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader, Attributes attributes) {
        if (tag.toLowerCase().equals("action")) {
            if (opening) {
                startAction(output, attributes);
            } else {
                endAction(output);
            }
        }
    }

    private void startAction(Editable text, Attributes attributes) {
        String name = attributes.getValue("name");
        String value = attributes.getValue("value");
        if (!TextUtils.isEmpty(name)) {
            start(text, new Action(name, value));
        }
    }

    private void endAction(final Editable text) {
        final Action action = getLast(text, Action.class);
        if (action != null) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (!TextUtils.isEmpty(action.mName)) {
                        handleAction(action);
                    }
                }
            };
            setSpanFromMark(text, action, clickableSpan);
        }
    }

    private void handleAction(Action action) {
        if (action == null || TextUtils.isEmpty(action.mName)) {
            return;
        }
        List<IAction> actionList = StrongerHtmlText.getInstance().getActions();
        if (actionList != null && actionList.size() > 0) {
            for (IAction realAction : actionList) {
                if (!TextUtils.isEmpty(realAction.actionName())
                        && realAction.actionName().equals(action.mName)) {
                    realAction.doAction(action.mValue);
                }
            }
        }
    }

    private void setSpanFromMark(Spannable text, Object mark, Object... spans) {
        int where = text.getSpanStart(mark);
        text.removeSpan(mark);
        int len = text.length();
        if (where != len) {
            for (Object span : spans) {
                text.setSpan(span, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private void start(Editable text, Object mark) {
        int len = text.length();
        text.setSpan(mark, len, len, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    private <T> T getLast(Spanned text, Class<T> kind) {
        /*
         * This knows that the last returned object from getSpans()
         * will be the most recently added.
         */
        T[] objs = text.getSpans(0, text.length(), kind);

        if (objs.length == 0) {
            return null;
        } else {
            return objs[objs.length - 1];
        }
    }
}
