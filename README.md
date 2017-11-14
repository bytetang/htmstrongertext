# htmstrongertext
Android rich Text  support components.

## feature list
+ support all html tag what android system support
+ support font tag size attribute
+ add action tag to custom your event everywhere in string

## manual

Gradle compile
```java
compile 'com.aust:htmstrongtext:0.1.0'
```
Android code example
```java
Spanned spannedString = StrongerHtmlText.getInstance().parseHtmText(this, "<font color='red' size='100'>hellow world</font>");
TextView textView = findViewById(R.id.textView);
textView.setText(spannedString);
textView.setMovementMethod(ClickableMovementMethod.getInstance());
```
Action tag exmple
```java
<font color='read'><action name='toast' value='hello world'>Click here!</action></font>
```

Handle event example
```java
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
```
> actionName() support name must match with html tag action attribute name

then add your customer Action into list,framework find  it  and trig it's action automatically.

```java
StrongerHtmlText.getInstance().addAction(new ToastAction(this));
```

You can final more case in exmaple app 

