package com.example.soccerplayers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.soccerplayers.R;

import java.util.ArrayList;
import java.util.Objects;

public class WebActivity extends AppCompatActivity {

    private WebView webView = null;
    private String url = null;

    // view elements for find function
    private ImageButton upButton, downButton, closeButton;
    private EditText findBox;
    private LinearLayout searchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // wire objects with widgets
        webView = findViewById(R.id.webView);

        upButton = findViewById(R.id.upButton);
        downButton = findViewById(R.id.downButton);
        closeButton = findViewById(R.id.closeButton);
        findBox = findViewById(R.id.findBox);

        // make data
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            url = (String) bundle.get("url");
        }

        if (url != null) {
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
        }

        findBox.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View view, int keyCode, KeyEvent event)
            {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER)))
                {
                    webView.findAll(findBox.getText().toString());
                }

                return false;
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.findNext(true);
            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.findNext(false);
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBox();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.find:
                searchBox();
                return true;
            case R.id.history:
                getHistory();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void close() {
        webView.findAll("");
        findBox.setText("");
        hideKeyboard();
    }

    public void searchBox() {
        searchLayout = findViewById(R.id.layoutFind);
        if (searchLayout.getVisibility() == RelativeLayout.GONE)
        {
            searchLayout.setVisibility(RelativeLayout.VISIBLE);
            webView.getLayoutParams().height = getWindowManager().getDefaultDisplay().getHeight();
        }
        else if (searchLayout.getVisibility() == RelativeLayout.VISIBLE)
        {
            webView.getLayoutParams().height = ConstraintLayout.LayoutParams.MATCH_PARENT;
            close();
            searchLayout.setVisibility(RelativeLayout.GONE);
        }
    }

    public void getHistory(){
        ArrayList<String> history = new ArrayList <> ();

        WebBackForwardList currentList = webView.copyBackForwardList();
        int currentSize = currentList.getSize();
        for(int i = 0; i < currentSize; ++i)
        {
            WebHistoryItem item = currentList.getItemAtIndex(i);
            history.add(item.getUrl());
        }

        // make an intent and a bundle
        Intent intent = new Intent(WebActivity.this, HistoryActivity.class);
        Bundle bundle = new Bundle();

        bundle.putStringArrayList("history", history);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputmanager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanager != null) {
                inputmanager.hideSoftInputFromWindow(Objects.requireNonNull(this.getCurrentFocus()).getWindowToken(), 0);
            }
        } catch (Exception ignored) {
        }

    }
}