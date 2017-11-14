package com.aust.tj.htmstrongertext;

import android.content.Context;
import android.text.Spanned;


import com.aust.tj.htmstrongertext.actions.IAction;
import com.aust.tj.htmstrongertext.handler.ActionTagHandler;

import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 13,十一月,2017
 */

public class StrongerHtmlText {

    private static List<IAction> actions;

    private static StrongerHtmlText instance;

    private StrongerHtmlText() {
        if (actions == null) {
            actions = new ArrayList<>();
        }
    }

    public static StrongerHtmlText getInstance() {
        if (instance == null) {
            instance = new StrongerHtmlText();
        }
        return instance;
    }

    public void addAction(IAction action) {
        if (actions != null) {
            actions.add(action);
        }
    }

    public static List<IAction> getActions() {
        return actions;
    }

    private static class HtmlParser {
        private static final HTMLSchema schema = new HTMLSchema();
    }


    public Spanned parseHtmText(Context context, String source) {
        Parser parser = new Parser();
        try {
            parser.setProperty(Parser.schemaProperty, HtmlParser.schema);
        } catch (org.xml.sax.SAXNotRecognizedException e) {
            // Should not happen.
            throw new RuntimeException(e);
        } catch (org.xml.sax.SAXNotSupportedException e) {
            // Should not happen.
            throw new RuntimeException(e);
        }

        HtmlToSpannedConverter converter =
                new HtmlToSpannedConverter(source, null, new ActionTagHandler(context), parser, android.text.Html.FROM_HTML_MODE_LEGACY);
        return converter.convert();
    }

}
