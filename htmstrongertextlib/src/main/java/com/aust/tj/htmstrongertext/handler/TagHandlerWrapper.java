package com.aust.tj.htmstrongertext.handler;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;

/**
 * wrapper StrongerHtmlText.TagHandler,support pass Attribute param
 * Created by tangjie on 10,十一月,2017
 */

public abstract class TagHandlerWrapper implements Html.TagHandler {

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        this.handleTag(opening, tag, output, xmlReader,null);
    }

    public abstract void handleTag(boolean opening, String tag,
                                   Editable output, XMLReader xmlReader, Attributes attributes);
}
