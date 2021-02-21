package uqam.inf5153.poker;

public class Error {

    public String isValidHand(String hand) {
        String[] cards = hand.split(" ");
        if (cards.length != 5)
            return "Erreur";
        for (String oneCard : cards) {
            if (oneCard.length() != 2)
                return "Erreur";
            if (!new CardHand().getSetOfValues().contains(oneCard.charAt(0)) || !new CardHand().getSetOfShapes().contains(oneCard.charAt(1)))
                return "Erreur";
        }
        return "Ok";
    }

}
