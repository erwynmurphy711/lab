package src;

public class Words {
    private String reviewWord;
    private double wordRating;

    public Words(String reviewWord, double wordRating){
        this.reviewWord = reviewWord;
        this.wordRating = wordRating;
    }

    public String getReviewWord(){
        return this.reviewWord;
    }
    public double getWordRating(){
        return this.wordRating;
    }
    public void setReviewWord(String word){
        this.reviewWord = word;
    }
    public void setWordRating(double rating){
        this.wordRating = rating;
    }
}
