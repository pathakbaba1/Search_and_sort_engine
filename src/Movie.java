import java.util.HashMap;

public class Movie {
    private int id;
    private String name;
    private String director;
    private int release_year;
    private float rating;
    private String about;
    public HashMap<String, Integer[]> wordmap;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getRelease_year(){
        return release_year;
    }
    public void setRelease_year(int release_year){
        this.release_year = release_year;
    }

    public String getDirector(){
        return director;
    }
    public void setDirector(String director){
        this.director = director;
    }

    public float getRating(){
        return rating;
    }
    public void setRating(float rating){
        this.rating = rating;
    }

    public String getAbout(){
        return about;
    }
    public void setAbout(String about){
        this.about = about;
    }
}
