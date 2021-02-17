package uqam.inf5153.poker;

public class PokerPlayer extends Player implements Poker {

    private Ranking pokerCombination;
    private CardHand pokerHand;
    private Card highestCard;

    PokerPlayer(String name, String cardHand){
        super(name);
        this.pokerHand = createCardHand(cardHand);
        this.pokerCombination = findCardCombination(cardHand);
        this.highestCard = findHighestCard(getPokerHand());
    }

    public Ranking getPokerCombination() { return this.pokerCombination; }
    public CardHand getPokerHand() { return this.pokerHand; }
    public Card getHighestCard() { return highestCard; }

    @Override
    public CardHand createCardHand(String cardHand) {
        return (new CardHand(cardHand));
    }

    @Override
    public Ranking findCardCombination(String cardHand) {
        PokerCombination combination = new PokerCombination(cardHand);
        return (combination.detect(combination));
    }

    @Override
    public Card findHighestCard(CardHand cardHand){
        return(cardHand.getHighestCard());
    }
}
