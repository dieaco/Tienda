package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Cause implements Serializable{

	private String title;
	private int daysRemaining;
	private int percentage;
    private String urlImage;
    private ArrayList<Integer> supporters;
    private String id;
	
	public Cause(String title, int daysRemaining, int percentage, String urlImage, ArrayList<Integer> supporters, String id){
		this.title = title;
		this.daysRemaining = daysRemaining;
		this.percentage = percentage;
        this.urlImage = urlImage;
        this.supporters = supporters;
        this.id = id;
	}

    //Constructor para los que vienen de la base de datos
    public Cause(String title, int daysRemaining, int percentage, String id){
        this.title = title;
        this.daysRemaining = daysRemaining;
        this.percentage = percentage;
        this.supporters = new ArrayList<Integer>();
        this.id = id;
    }
	
	public String getTitle(){

        return title;
	}
	
	public int getDaysRemaining(){

        return daysRemaining;
	}
	
	public int getPercentage(){

        return percentage;
	}

    public String getUrlImage(){
        return urlImage;
    }

    public ArrayList<Integer> getSupporters(){
        return supporters;
    }

    public String getId(){
        return id;
    }
	
}
