package uqam.inf5153.poker;

public interface Poker {
     CardHand createCardHand(String cardHand);
     Ranking findCardCombination(String cardHand);
     Card findHighestCard(CardHand cardHand);
}
