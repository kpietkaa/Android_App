package com.kaz.zpi_kaz_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView widok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new JSONTask().execute("https://api.lifx.com/v1/lights/all");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        widok = (TextView) findViewById(R.id.odpowiedz);
    }

    // Wywolanie przycisku
    public void sendGetRequest(View view) {
        new JSONTask().execute("https://api.lifx.com/v1/lights/all");
    }


    public class JSONTask extends com.kaz.zpi_kaz_app.tools.JSONTask {

        @Override
        protected void onPostExecute(String s)
        {
            //  Zwracanie do widoku wyniku
            widok.setText(s);
        }
    }

}
