import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SAXParse {

    public static List<Movie> movieList;
    static TreeMap<String, HashSet<StringBuilder>> invertedIndex = new TreeMap<>();
    static HashMap<Integer, Movie> inverseMap = new HashMap<>();

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        Scanner sc = new Scanner(System.in);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        StopWords.stopWords();
        javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
        MyHandler handler = new MyHandler();
        saxParser.parse(new File("C:\\Users\\patha\\IdeaProjects\\UnigramSearchCumSortEngine\\src\\movies.xml"), handler);

        movieList = handler.getMovieList();

        for(Movie movie: movieList){
            String title = movie.getName().toString();
            String director = movie.getDirector().toString();
            String about = movie.getAbout().toString();
            movie.wordmap = new HashMap<>();
            HashMap<String, Integer[]> wordMap = movie.wordmap;
            parse(title.toLowerCase(), wordMap, 0);
            parse(director.toLowerCase(), wordMap, 1);
            parse(about.toLowerCase(), wordMap, 2);
            inverseMap.put(movie.getId(), movie);
            for(String token : wordMap.keySet()){
                HashSet<StringBuilder> movieListId = invertedIndex.get(token);
                if(movieListId == null){
                    movieListId = new HashSet<>();
                }
                StringBuilder s = new StringBuilder();
                s.append(movie.getId()).append(" ").append(calculateTermFrequency(wordMap.get(token)));
                movieListId.add(s);
                invertedIndex.put(token, movieListId);
            }
        }


        // can be used to show the contents of xml file.
//        for(Movie movie: movieList){
//            System.out.println(movie.getId() + ". " + movie.getName());
//            System.out.println("Director: " + movie.getDirector());
//            System.out.println("Release Year: " + movie.getRelease_year());
//            System.out.println("Ratings(Imdb): " + movie.getRating());
//            System.out.println("Spoilers: " + movie.getAbout());
//            System.out.println();
//            System.out.println();
//        }


        // Can be used to print weighted HashMap.
//        for(Movie movie: movieList){
//            for (String name: movie.wordmap.keySet()) {
//                String key = name.toString();
//                Integer[] value = movie.wordmap.get(name);
//                System.out.println(key + " " + Arrays.toString(value));
//            }
//        }


        //Can be used to print invertedIndex HashMap.
//        for (String name: invertedIndex.keySet()){
//            String key = name.toString();
//            HashSet<StringBuilder> value = invertedIndex.get(name);
//            System.out.println(key + " " + value);
//        }

        
        //Demo
//        System.out.print("Enter any word: ");
//        String text = sc.nextLine().toLowerCase();
//        if(invertedIndex.containsKey(text)){
//            HashSet<StringBuilder> temp = invertedIndex.get(text);
//            int[][] sorter = new int[temp.size()][2];
//            int i = 0;
//            for(StringBuilder str : temp){
//                String[] s = str.toString().split(" ");
//                sorter[i][0] = Integer.parseInt(s[0]);
//                sorter[i][1] = Integer.parseInt(s[1]);
//                i++;
//            }
//            Arrays.sort(sorter, new Comparator<int[]>() {
//                @Override
//                public int compare(int[] o1, int[] o2) {
//                    if(o1[1] < o2[1])
//                        return 1;
//                    else if(o1[1] == o2[1])
//                        return o1[0] > o2[0] ? 1 : -1;
//                    else
//                        return -1;
//                }
//            });
//            for(int[] arr: sorter){
//                Movie movie = inverseMap.get(arr[0]);
//                System.out.println();
//                System.out.println(movie.getId() + ". " + movie.getName());
//                System.out.println("Director: " + movie.getDirector());
//                System.out.println("Release Year: " + movie.getRelease_year());
//                System.out.println("Ratings(Imdb): " + movie.getRating());
//                System.out.println("About: " + movie.getAbout());
//                System.out.println();
//            }
//        } else {
//            System.out.println("No Information available!!");
//        }



        sc.close();

    }

    public static int calculateTermFrequency(Integer[] count) {
        int termFrequency = count[0]*100 + count[1]*50 + count[2];
        return termFrequency;
    }

    private static void parse(String text, Map<String, Integer[]> wordMap, int k) {
        String[] tokens = text.replaceAll("[^a-zA-Z ]", "").split("\\s+");
        Integer[] count;

        for(int i=0; i< tokens.length-1; i++){
            String token = tokens[i] + " " + tokens[i+1];

            count = wordMap.get(token);
            if(count == null){
                count = new Integer[]{0, 0, 0};
            }
            count[k]++;
            wordMap.put(token, count);

        }


        for(String token: tokens){
            if(token.isEmpty())
                continue;
            if(StopWords.stopWordsSet.contains(token))
                continue;

            count = wordMap.get(token);
            if(count == null){
                count = new Integer[]{0, 0, 0};
            }
            count[k]++;
            wordMap.put(token, count);
        }
    }
}
