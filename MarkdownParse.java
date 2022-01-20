// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
           // System.out.println(currentIndex);
            int nextOpenBracket = markdown.indexOf("[", currentIndex); //if curent index is moved, it will search from there (i.e first current = 0, then current = 41)
            if(nextOpenBracket == -1) {
                break;
            }
            if (markdown.indexOf("!") == nextOpenBracket - 1) {
                break;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
           // System.out.println("nextCloseBracket: " + nextCloseBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(nextCloseBracket + 1 != closeParen) { //if there is space [] ()
                break;
            }
            if (nextCloseBracket == -1 || closeParen == -1 || openParen == -1 ) { //if no [], ()
                break;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}