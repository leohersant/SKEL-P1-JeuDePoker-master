package uqam.inf5153.poker;

import java.util.*;

/**
 * A class to find the winner in a poker game
 */
public class Main {
    /**
     * The main function. If no arguments given, we will use stdin to read the data.
     * @param args the arguments (the two hands).
     */
    public static void main(String[] args) {

        // Data origin
        if (args.length == 2) {

            ArrayList<String> errors = new ArrayList<>();
            for (String s: args) {
                errors.add(new Error().isValidHand(s));
            }

            if (!errors.contains("Erreur")){
                PokerPlayer player1 = new PokerPlayer("P1", args[0]);
                PokerPlayer player2 = new PokerPlayer("P2", args[1]);

                ArrayList<PokerPlayer> players = new ArrayList<>();
                players.add(player1);
                players.add(player2);

                PokerReferee pokerReferee = new PokerReferee(players);
                System.out.println(pokerReferee.getMessage());
            } else
                System.out.println("Erreur");

        } else {

            Scanner sc = new Scanner(System.in);
            System.out.print("p1? ");
            String hand1 = sc.nextLine().trim().toUpperCase(); // Read line
            System.out.print("p2? ");
            String hand2 = sc.nextLine().trim().toUpperCase();
            sc.close();

            ArrayList<String> errors = new ArrayList<>();
            errors.add(new Error().isValidHand(hand1));
            errors.add(new Error().isValidHand(hand2));

            if (!errors.contains("Erreur")){
                PokerPlayer player1 = new PokerPlayer("P1", hand1);
                PokerPlayer player2 = new PokerPlayer("P2", hand2);

                ArrayList<PokerPlayer> players = new ArrayList<>();
                players.add(player1);
                players.add(player2);

                PokerReferee pokerReferee = new PokerReferee(players);
                System.out.println(pokerReferee.getMessage());
            }
            else
                System.out.println("Erreur");
        }
    }

}
