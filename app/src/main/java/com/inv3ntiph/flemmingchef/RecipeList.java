package com.inv3ntiph.flemmingchef;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class RecipeList extends AppCompatActivity {

    private ProgressBar mprogressloading;
    private RecyclerView mrecipeRv;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list_item);

        mrecipeRv = findViewById(R.id.RVRecipes);

        mprogressloading = findViewById(R.id.progressBar);

        try {
            mrecipeRv.setHasFixedSize(true);
            mrecipeRv.setLayoutManager(new LinearLayoutManager(this));
URL recipeUrl = ApiUtil.BuildUrl("chicken");
            new RecipeQuery().execute(recipeUrl);
        }
        catch(Exception e){
            Log.d("Error",e.getMessage()) ;
        }

    }
    public class RecipeQuery extends AsyncTask<URL,Void,String> {


        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl =urls[0];
            String result = null;
            try{
              result =ApiUtil.GetJson(searchUrl);
            }
            catch (IOException e){
                Log.e("Error",e.getMessage());
            }
            return result;
        }
        @Override
        protected  void onPostExecute(String result){
            TextView TvError = findViewById(R.id.TvError);

            if (result== null){
                mprogressloading.setVisibility(View.INVISIBLE);
                TvError.setVisibility(View.VISIBLE);
                mrecipeRv.setVisibility(View.INVISIBLE);
            }
            else{
                mprogressloading.setVisibility(View.VISIBLE);
                TvError.setVisibility(View.INVISIBLE);
                mrecipeRv.setVisibility(View.VISIBLE);
            }

            ArrayList<Recipe> recipelist = ApiUtil.getRecipeFromJson(result);

            RecipeAdapter recipesA = new RecipeAdapter(recipelist);
            mrecipeRv.setAdapter(recipesA);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogressloading.setVisibility(View.VISIBLE);

        }
    }
}
