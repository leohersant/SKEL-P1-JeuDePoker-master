package uqam.inf5153.poker;

public class Player {

    private String name;
    private Ranking pokerCombination;
    private CardHand pokerHand;
    private int score;

    public Player(){};
    public Player(String name, String hand) {
        this.name = name;
        this.pokerHand = new CardHand(hand);
        this.pokerCombination = new PokerCombination().detect(this.pokerHand);
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ranking getPokerCombination() {
        return pokerCombination;
    }

    public void setPokerCombination(Ranking handRanking) {
        this.pokerCombination = handRanking;
    }

    public CardHand getPokerHand() {
        return pokerHand;
    }

    public void setPokerHand(CardHand pokerHand) {
        this.pokerHand = pokerHand;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
