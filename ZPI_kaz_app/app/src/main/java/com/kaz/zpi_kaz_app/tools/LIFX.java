package com.kaz.zpi_kaz_app.tools;

import android.widget.TextView;

import com.kaz.zpi_kaz_app.MainActivity;
import com.kaz.zpi_kaz_app.R;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

public class LIFX {

    String apiKey;
    String str = "[{\"name\":\"name1\",\"url\":\"url1\"},{\"name\":\"name2\",\"url\":\"url2\"}]";

    // Pobierania tokena potrzebnego do autoryzacji przy polczeniu z zarkowka - na przysloszc
    /*
    public LIFX(String klucz)
    {
        apiKey = klucz;
    }
    */

    // Na nasze poczatkowe potrzeby zapisuje tu na stale
//    public LIFX()
//    {
//        apiKey = "ce6b4c1978015b537af377ac5792a923b9fe062f729d1558537d7e6bc7f9007f";
//    }

    public JSONArray listLights()
    {
        String apiKey = "ce6b4c1978015b537af377ac5792a923b9fe062f729d1558537d7e6bc7f9007f";

        try
        {
            URL adres = new URL("https://api.lifx.com/v1/lights/all");
            HttpsURLConnection polaczenie = (HttpsURLConnection) adres.openConnection();
            polaczenie.setRequestProperty("Authorization", "Bearer " + apiKey);
            polaczenie.setRequestMethod("GET");

            BufferedReader wejscie = new BufferedReader(new InputStreamReader(polaczenie.getInputStream()));
            StringBuilder odpowiedz = new StringBuilder();

            String json;
            while ((json = wejscie.readLine()) != null)
                odpowiedz.append(json);
            wejscie.close();
            return new JSONArray(odpowiedz.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new JSONArray();
    }
}
