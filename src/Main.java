package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    
    public static void main (String[] args){

        Words hate = new Words("hate", -5.0);
        Words love= new Words("love", 5.0);
        PositiveFake like = new PositiveFake("like", 3.0, "love");
        NegativeFake dislike = new NegativeFake("dislike", 1.0, "hate");
        Review.addNegativeFakeReview(dislike);
        Review.addPositiveFakeReview(like);
        Review.addManyReviews(new ArrayList<>(Arrays.asList(hate, love, like, dislike)));
        Review.aCompleteReview("src/example.txt");
        // this code will output:
        //
        // The original rating is 4/5
        // The fake review is 'I love eating apples'
        // The fake review rating is 5

    }
}
