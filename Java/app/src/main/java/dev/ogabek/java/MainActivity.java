package dev.ogabek.java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        tv_main = findViewById(R.id.tv_main);
        setTags(tv_main, tv_main.getText().toString());
    }

    private void setTags(TextView tv_main, String text) {
        SpannableString string = new SpannableString(text);
        int start = -1;
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            if (c == '#') {
                start = i;
            } else if (c == ' ' || c == '\n' || i == text.length() - 1 && start != -1) {
                if (start != -1) {
                    if (i == text.length() - 1) {
                        i++;
                    }
                    String tag = text.substring(start, i);
                    string.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            Log.d("Hash", String.format("Clicked %s!", tag));
                        }

                        @Override
                        public void updateDrawState(@NonNull TextPaint ds) {
                            ds.setColor(Color.parseColor("#33b5e5"));
                            ds.setUnderlineText(false);
                        }
                    }, start, i, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                    start = -1;
                }
            }
            i++;
        }
        tv_main.setMovementMethod(new LinkMovementMethod());
        tv_main.setText(string);
    }
}