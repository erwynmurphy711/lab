package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Review {

    private static ArrayList<Words> words = new ArrayList<Words>();
    private static ArrayList<PositiveFake> positivefakes = new ArrayList<PositiveFake>();
    private static ArrayList<NegativeFake> negativefakes = new ArrayList<NegativeFake>();

    public Review() {
    }

    public static double totalSentiment(String fileName) {
        String filePath = fileName;
        double num = 0;
        try { // This try is for extracting the message from the txt file
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

    // postivefakes adding stuffs
    public static void addPositiveFakeReview(PositiveFake obj) {
        positivefakes.add(obj);
    }

    public static void removePositiveFakeReview(PositiveFake obj) {
        positivefakes.remove(obj);
    }

    public static void addManyPositiveFakeReviews(ArrayList<PositiveFake> list) {
        positivefakes.addAll(list);
    }

    // negativefakes adding stuffs
    public static void addNegativeFakeReview(NegativeFake obj) {
        negativefakes.add(obj);
    }

    public static void removeNegativeFakeReview(NegativeFake obj) {
        negativefakes.remove(obj);
    }

    public static void addManyNegativeFakeReviews(ArrayList<NegativeFake> list) {
        negativefakes.addAll(list);
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
    public static int stringStarRating(String review) {
        double reviewNum = sentimentVal(review);
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
                    // Detection
                    for (int wordpositionindex = 0; wordpositionindex < positivefakes.size(); wordpositionindex++) {
                        String reviewWord = positivefakes.get(wordpositionindex).getReviewWord();
                        int lengthOfWord = reviewWord.length();
                        
                        // Check if the following substring matches the review word
                        if (i + lengthOfWord <= text.length() &&
                            text.substring(i + 1, i + 1 + lengthOfWord).equals(reviewWord)) {
                            
                            // Replace with opposite word
                            newString+=positivefakes.get(wordpositionindex).getOppositeWord();
                            
                            // Move i to the end of the review word
                            i += lengthOfWord;
                            break; // Exit inner loop
                        }
                    }

                    for (int wordpositionindex = 0; wordpositionindex < negativefakes.size(); wordpositionindex++) {
                        String reviewWord = negativefakes.get(wordpositionindex).getReviewWord();
                        int lengthOfWord = reviewWord.length();
                        
                        // Check if the following substring matches the review word
                        if (i + lengthOfWord <= text.length() &&
                            text.substring(i + 1, i + 1 + lengthOfWord).equals(reviewWord)) {
                            
                            // Replace with opposite word
                            newString+=negativefakes.get(wordpositionindex).getOppositeWord();
                            
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

    public static void aCompleteReview(String filename){
        System.out.println("The original rating is "+ starRating(filename) + "/5");
        
            System.out.println("The fake review is '" +fakeReview(filename)+"'");
            System.out.println("The fake review rating is "+ stringStarRating(fakeReview(filename)));
        
    }
    
}
