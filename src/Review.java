package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Review {

    private static ArrayList<Words> words = new ArrayList<Words>();

    public Review() {
    }

    public static double totalSentiment(String fileName) {
        String filePath = fileName;
        double num = 0;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String text = new String(bytes, "UTF-8").trim();   
            // System.out.println(text);
            num = sentimentVal(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static void addReview(Words obj){
        words.add(obj);
    }
    public static void removeReview(Words obj){
        words.remove(obj);
    }
    public static void addManyReviews(ArrayList<Words> list){
        words.addAll(list);
    }

    public static double sentimentVal(String review) {
        int lengthOfWord = 0;
        for (int i = 0; i < words.size(); i++) {
            lengthOfWord = words.get(i).getReviewWord().length();

            for (int j = 0; j < review.length()+1-lengthOfWord; j++) {
                if (review.substring(j, j + lengthOfWord).equals(words.get(i).getReviewWord())) { 
                    // checks for if it contains a word in the review
                    return words.get(i).getWordRating();
                }
            }
        }
        return 0.0;
    }

    public static int starRating(String fileName){
        double reviewNum = totalSentiment(fileName);
        if(reviewNum < 0){
            return 1;
        } else if (reviewNum > 0 && reviewNum <= 1){
            return 2;
        } else if (reviewNum > 1 && reviewNum <= 2){
            return 3;
        } else if (reviewNum > 2 && reviewNum <= 3){
            return 4;
        } else if (reviewNum > 3){
            return 5;
        } else {
            return 0;
        }
    }
}
