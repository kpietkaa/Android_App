package com.kaz.zpi_kaz_app.tools;

import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class LIFX {

    String apiKey;

    // Pobierania tokena potrzebnego do autoryzacji przy polczeniu z zarkowka - na przysloszc
    /*
    public LIFX(String klucz)
    {
        apiKey = klucz;
    }
    */

    // Na nasze poczatkowe potrzeby zapisuje tu na stale
    public LIFX()
    {
        apiKey = "ce6b4c1978015b537af377ac5792a923b9fe062f729d1558537d7e6bc7f9007f";
    }

    public JSONArray listLights()
    {
        try
        {
            URL adres = new URL("https://api.lifx.com/v1/lights/all");
            HttpURLConnection polaczenie = (HttpURLConnection) adres.openConnection();
            polaczenie.setRequestProperty("Authorization", " Bearer " + apiKey);
            polaczenie.setRequestMethod("GET");

            BufferedReader wejscie = new BufferedReader(new InputStreamReader((polaczenie.getInputStream())));
            StringBuilder odpowiedz = new StringBuilder();

            for (String json; (json = wejscie.readLine()) != null;)
            {
                odpowiedz.append(json);
            }
            wejscie.close();

            return new JSONArray(odpowiedz.toString());
        }
        catch (Exception wyjatek)
        {
            wyjatek.printStackTrace();
        }
        return new JSONArray();
    }
}
