package com.inv3ntiph.flemmingchef;

public class Recipe {

    public String uri;
    public String label;
    public String image;
    public String source;
    public String url;
    public String shareAs;
    public String yield;
    public String dietLabels;
    public String healthLabels;
    public String caution;
    public String ingredientLines;


    public Recipe(String uri,String label,String image,String source,String url,String shareAs,String yield,String dietLabels,
                  String healthLabels,String caution,String ingredientLines){

        this.uri=uri;
        this.label =label;
                this.image=image;
                this.source=source;
                this.url=url;
                this.shareAs=shareAs;
                this.yield=yield;
                this.dietLabels=dietLabels;
                this.healthLabels=healthLabels;
                this.caution=caution;
                this.ingredientLines=ingredientLines;



    }
}
