package com.example.stylesmargincherepanov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static Spinner mLocaleSpinner;
    private static Spinner mThemeSwitchSpinner;
    private static Button mSwitchLangBtn;
    private static Button mSwitchIndentBtn;
    private static Locale localeRU = new Locale("ru");
    private static Locale localeEN = new Locale("en");
    private static Locale localeJP = new Locale("ja");
    private static Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mSwitchLangBtn = findViewById(R.id.switchLangBtn);
        mSwitchIndentBtn = findViewById(R.id.switchIndentBtn);
        mLocaleSpinner = findViewById(R.id.localeSpinner);
        mThemeSwitchSpinner = findViewById(R.id.themeSwitchSpinner);
        initLocaleSpinner();
        initThemeSwitchSpinner();
    }

    private void initLocaleSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this
                , R.array.language_selector
                , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mLocaleSpinner.setAdapter(adapter);

        mLocaleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] language = getResources().getStringArray(R.array.language_selector);
                changeLanguage(language[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initThemeSwitchSpinner() {
        ArrayAdapter<CharSequence> themeAdapter = ArrayAdapter.createFromResource(this
                , R.array.indents
                , android.R.layout.simple_spinner_item);
        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mThemeSwitchSpinner.setAdapter(themeAdapter);

        mThemeSwitchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeIndent(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void changeLanguage(String language) {
        switch (language) {
            case "Русский" :
                config.setLocale(localeRU);
                switchLanguageBtnAction();
                break;

            case "English" :
                config.setLocale(localeEN);
                switchLanguageBtnAction();
                break;

            case "日本語" :
                config.setLocale(localeJP);
                switchLanguageBtnAction();

            default:break;
        }
    }

    private void changeIndent(int position) {
        switch (position) {
            case 0 :
                Utils.changeToTheme(Utils.THEME_SMALL_INDENT);
                switchThemeBtnAction();
                break;
            case 1 :
                Utils.changeToTheme(Utils.THEME_MIDDLE_INDENT);
                switchThemeBtnAction();
                break;
            case 2 :
                Utils.changeToTheme(Utils.THEME_BIG_INDENT);
                switchThemeBtnAction();
                break;
            default:
        }
    }

    private void switchLanguageBtnAction() {
        mSwitchLangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                recreate();
            }
        });
    }

    private void switchThemeBtnAction() {
        mSwitchIndentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}