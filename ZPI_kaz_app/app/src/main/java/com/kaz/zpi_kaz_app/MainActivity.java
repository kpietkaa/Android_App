package com.kaz.zpi_kaz_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView widok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    /*
    *   Wywoalnie polaczenia asychronicznego <Params, Progress, Result>
    *
    *   Params - the type of the parameters sent to the task upon execution.
    *   Progress - the type of the progress units published during the background computation.
    *   Result - the type of the result of the background computation.
    *
     */
    public class JSONTask extends  AsyncTask<String, String, String>
    {
        String apiKey = "ce6b4c1978015b537af377ac5792a923b9fe062f729d1558537d7e6bc7f9007f";

        @Override
        protected String doInBackground(String... params) {
            HttpsURLConnection polaczenie = null;
            BufferedReader reader = null;

            try
            {
                //  Nawiazanie polaczenia
                URL adres = new URL(params[0]);
                polaczenie = (HttpsURLConnection) adres.openConnection();
                polaczenie.setRequestProperty("Authorization", "Bearer " + apiKey);
                polaczenie.setRequestMethod("GET");

                //  Wyswietlanie kod od serwera
                System.out.println(polaczenie.getResponseCode());

                //  Zapisywanie odpowiedzi
                InputStream stream = polaczenie.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                }

                //  Zwraca wynik do onPostExecute
                return buffer.toString();
            }
            //  Rozne wyjatki - nie pytajcie czemu :P
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally {
                if (polaczenie != null)
                    polaczenie.disconnect();
                try
                {
                    if (reader != null)
                        reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            //  Zwracanie do widoku wyniku
            widok.setText(s);
        }
    }

}
