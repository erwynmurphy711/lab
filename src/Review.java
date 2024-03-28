package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Review {

    private static ArrayList<Words> words = new ArrayList<Words>();
    private static ArrayList<FakeWords> fakewords = new ArrayList<FakeWords>();

    public Review() {
    }

    public static double totalSentiment(String fileName) {
        String filePath = fileName;
        double num = 0;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String text = new String(bytes, "UTF-8").trim();
            num = sentimentVal(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    // normal words adding objects
    public static void addReview(Words obj) {
        words.add(obj);
    }

    public static void removeReview(Words obj) {
        words.remove(obj);
    }

    public static void addManyReviews(ArrayList<Words> list) {
        words.addAll(list);
    }

    // fakewords adding objects
    public static void addFakeReview(FakeWords obj) {
        fakewords.add(obj);
    }

    public static void removeFakeReview(FakeWords obj) {
        fakewords.remove(obj);
    }

    public static void addManyFakeReviews(ArrayList<FakeWords> list) {
        fakewords.addAll(list);
    }

    public static double sentimentVal(String review) {
        int lengthOfWord = 0;
        for (int i = 0; i < words.size(); i++) {
            lengthOfWord = words.get(i).getReviewWord().length();

            for (int j = 0; j < review.length() + 1 - lengthOfWord; j++) {
                if (review.substring(j, j + lengthOfWord).equals(words.get(i).getReviewWord())) {
                    // checks for if it contains a word in the review
                    return words.get(i).getWordRating();
                }
            }
        }
        return 0.0;
    }

    public static int starRating(String fileName) {
        double reviewNum = totalSentiment(fileName);
        if (reviewNum < 0) {
            return 1;
        } else if (reviewNum > 0 && reviewNum <= 1) {
            return 2;
        } else if (reviewNum > 1 && reviewNum <= 2) {
            return 3;
        } else if (reviewNum > 2 && reviewNum <= 3) {
            return 4;
        } else if (reviewNum > 3) {
            return 5;
        } else {
            return 0;
        }
    }

    public static String fakeReview(String fileName) {
        String filePath = fileName;
        String newString = new String();
        
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String text = new String(bytes, "UTF-8").trim();
            
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '*') {
                    // Detects the marker '*'
                    for (int wordpositionindex = 0; wordpositionindex < fakewords.size(); wordpositionindex++) {
                        String reviewWord = fakewords.get(wordpositionindex).getReviewWord();
                        int lengthOfWord = reviewWord.length();
                        
                        // Check if the following substring matches the review word
                        if (i + lengthOfWord <= text.length() &&
                            text.substring(i + 1, i + 1 + lengthOfWord).equals(reviewWord)) {
                            
                            // Replace with opposite word
                            newString+=fakewords.get(wordpositionindex).getOppositeWord();
                            
                            // Move i to the end of the review word
                            i += lengthOfWord;
                            break; // Exit inner loop
                        }
                    }
                } else {
                    newString+=text.charAt(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newString;
    }
    
}
