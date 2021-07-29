import java.io.*;
import java.util.HashSet;

public class StopWords {

    public static HashSet<String> stopWordsSet;

    public static void stopWords() throws IOException {
        stopWordsSet = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\patha\\Desktop\\stop_words_english.txt")));
        String line;

        while((line = br.readLine()) != null){
            stopWordsSet.add(line);
        }
        //System.out.println(stopWordsSet.contains("two"));
        //System.out.println(stopWordsSet.size());
    }

}
