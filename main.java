package codility1;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.lang.Object;
import org.json.JSONArray;;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		*	Trzeba dodac java-json.jar, aby dzialaly JSONy.
		*	To Add this Jar to your build path Right click the Project > Build Path > Configure build path> 
		*	Select Libraries tab > Click Add External Libraries > Select the Jar file Download
		*/
		System.out.println(list());
		
	}

	static String apiKey = "ce6b4c1978015b537af377ac5792a923b9fe062f729d1558537d7e6bc7f9007f";
	
	public static JSONArray list()
	{
		try
		{
			URL adres = new URL("https://api.lifx.com/v1/lights/all");
			HttpsURLConnection polaczenie = (HttpsURLConnection) adres.openConnection();
			polaczenie.setRequestProperty("Authorization", "Bearer " + apiKey);
			polaczenie.setRequestMethod("GET");
			
			System.out.println(polaczenie.getResponseCode());
			
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
