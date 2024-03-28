package src;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    
    public static void main (String[] args){
        
        Review sample = new Review();
        Words hot = new Words("hot", 3.0);
        Words terrible = new Words("terrible", -4.0);
        Words cold = new Words("cold", -0.04);
        Words delicious = new Words("delicious", 8.0);
        Review.addManyReviews(new ArrayList<>(Arrays.asList(delicious, cold, terrible, hot)));
        // System.out.println(Review.sentimentVal("this was very hot and cold yes"));
        // System.out.println(Review.totalSentiment("src/example.txt"));
        System.out.println(Review.starRating("src/example.txt"));
    }
}
