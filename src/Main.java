package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    
    public static void main (String[] args){

        Words hate = new Words("hate", -5.0);
        Words love= new Words("love", 5.0);
        // Words cold = new Words("cold", -0.04);
        // Words delicious = new Words("delicious", 8.0);
        PositiveFake like = new PositiveFake("like", 3.0, "love");
        NegativeFake dislike = new NegativeFake("dislike", 1.0, "hate");
        Review.addNegativeFakeReview(dislike);
        Review.addPositiveFakeReview(like);
        Review.addManyReviews(new ArrayList<>(Arrays.asList(hate, love, like, dislike)));
        Review.aCompleteReview("src/example.txt");

        // Review.addManyReviews(new ArrayList<>(Arrays.asList(delicious, cold, terrible, hot)));
        // Review.addPositiveFakeReview(love);
        // System.out.println(Review.fakeReview("src/example.txt"));
        // System.out.println(Review.sentimentVal("this was very hot and cold yes"));
        // System.out.println(Review.totalSentiment("src/example.txt"));
        //System.out.println(Review.starRating("src/example.txt"));

    }
}
