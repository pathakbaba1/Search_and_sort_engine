import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    // List to hold Movie Object
    private List<Movie> movieList = null;
    private Movie movie = null;

    public List<Movie> getMovieList() {
        return movieList;
    }

    boolean bName = false;
    boolean bDirector = false;
    boolean bYear = false;
    boolean bRatings = false;
    boolean bAbout = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("Movie")) {
            String id = attributes.getValue("id");
            movie = new Movie();
            movie.setId(Integer.parseInt(id));

            if(movieList == null)
                movieList = new ArrayList<>();
        }
        else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        }
        else if (qName.equalsIgnoreCase("director")) {
            bDirector = true;
        }
        else if (qName.equalsIgnoreCase("year")) {
            bYear = true;
        }
        else if (qName.equalsIgnoreCase("ratings")) {
            bRatings = true;
        }
        else if (qName.equalsIgnoreCase("about")) {
            bAbout = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equalsIgnoreCase("movie")) {
            movieList.add(movie);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if(bName) {
            movie.setName(new String(ch, start, length));
            bName = false;
        }
        else if (bDirector){
            movie.setDirector(new String(ch, start, length));
            bDirector = false;
        }
        else if (bYear){
            movie.setRelease_year(Integer.parseInt(new String(ch, start, length)));
            bYear = false;
        }
        else if(bRatings){
            movie.setRating(Float.parseFloat(new String(ch, start, length)));
            bRatings = false;
        }
        else if(bAbout){
            movie.setAbout(new String(ch, start, length));
            bAbout = false;
        }
    }
}
