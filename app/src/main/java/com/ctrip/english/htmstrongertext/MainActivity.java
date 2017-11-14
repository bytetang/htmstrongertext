package com.ctrip.english.htmstrongertext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.TextView;

import com.aust.tj.htmstrongertext.ClickableMovementMethod;
import com.aust.tj.htmstrongertext.StrongerHtmlText;
import com.aust.tj.htmstrongertext.actions.ToastAction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActions();

        Spanned spannedString = StrongerHtmlText.getInstance().parseHtmText(this, "<font color='red' size='100'><action name='toast' value='hello world'>hello world</action></font><font color='blue' size='80'><action name='toast' value='faq'>faq</action></font>");
        TextView sysTextView = findViewById(R.id.sysText);
        sysTextView.setText(spannedString);
        sysTextView.setMovementMethod(ClickableMovementMethod.getInstance());

    }

    // example for add handle action
    private void initActions() {
        StrongerHtmlText.getInstance().addAction(new ToastAction(this));
    }
}