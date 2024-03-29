package src;

public class FakeWords extends Words {

    private String oppositeWord;

    public FakeWords(String reviewWord, double wordRating, String oppositeWord){
        super(reviewWord, wordRating);
        this.oppositeWord = oppositeWord;
    }

    // getter
    public String getOppositeWord(){
        return this.oppositeWord;
    }

    // setter
    public void setOppositeWord(String word){
        this.oppositeWord = word;
    }
    
}
