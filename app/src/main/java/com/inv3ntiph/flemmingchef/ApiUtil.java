package com.inv3ntiph.flemmingchef;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE_API_URL =
            "https://api.edamam.com/search";
    public static final String KEY ="app_key";
    public static final String API_KEY ="1dcc5a8ffd46bfb33ec566516303ce26";
    public static final String API_ID ="app_id";
    public static final String API_ID_VAL ="83e01d25";
    public static final String QUERY_PARAMETER_KEY="q";

    public static URL BuildUrl(String title){
        URL urlLocal =null;
        Uri uribuild = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAMETER_KEY,title)
                .appendQueryParameter(API_ID,API_ID_VAL)
                .appendQueryParameter(KEY,API_KEY)
                .build();


        try {
            urlLocal=new URL(uribuild.toString());


        }
        catch ( Exception e)
        {
          e.printStackTrace();

        }
        return urlLocal;
    }

    public static String GetJson(URL requestUrl) throws IOException {

            HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();


        try {
            InputStream istream = conn.getInputStream();
            Scanner scannerI = new Scanner(istream);
            scannerI.useDelimiter("\\A");

            boolean hasdata = scannerI.hasNext();
            if (hasdata){
                return scannerI.next();
            }
            else
            {
                return null;
            }
        }
        catch (Exception e){

            Log.d("Error",e.toString());
            return null;
        }
        finally{
            conn.disconnect();
        }

    }

    public static ArrayList<Recipe> getRecipeFromJson(String json){
        final String URI= "uri";
        final String LABEL="label";
        final String IMAGE="image";
        final String SOURCE= "source";
        final String URL="url";
        final String SHAREAS="shareAs";
        final String YIELD="yield";
        final String DIETLABEL="dietLabels";
        final String HEALTH="healthLabels";
        final String CAUTION="cautions";
        final String INGREDIENTS="ingredientLines";
        final String HITS ="hits";
        final String RECIPESHIT="recipe";


        ArrayList<Recipe> recipe =new ArrayList<Recipe>();
        try{
            JSONObject jsonRecipe = new JSONObject(json);
            String HitsObj = jsonRecipe.get(HITS).toString();
            JSONArray recipeHits = new JSONArray(HitsObj);


          int  numberOfRecipes= recipeHits.length();

            for(int j=0; j<numberOfRecipes;j++) {
                JSONObject RecVal = recipeHits.getJSONObject(j);


                for (int i = 0; i < RecVal.length(); i++) {

                    JSONObject recipeJson = RecVal.getJSONObject(RECIPESHIT);
                    String Uridet = recipeJson.getString(URI);
                    String lbldet = recipeJson.getString(LABEL);
                    String imgdet = recipeJson.getString(IMAGE);
                    String Sourcdet = recipeJson.getString(SOURCE);
                    String Urldet = recipeJson.getString(URL);
                    String shareAs = recipeJson.getString(SHAREAS);
                    String yieldDet = recipeJson.getString(YIELD);
                    String dietDet = recipeJson.getString(DIETLABEL);
                    String healthDet = recipeJson.getString(HEALTH);
                    String CautDet = recipeJson.getString(CAUTION);
                    String IngredDet = recipeJson.getString(INGREDIENTS);


                  Recipe recipeDetail = new Recipe(Uridet,lbldet,
                          imgdet,Sourcdet,Urldet,shareAs,yieldDet,
                          dietDet,healthDet,CautDet,IngredDet);

                   recipe.add(recipeDetail);
                }
            }


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return recipe;
    }
}
