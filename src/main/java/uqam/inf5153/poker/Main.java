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

            for (String s: args) {
                System.out.println(s);
            }

            PokerPlayer player1 = new PokerPlayer("P1", args[0]);
            PokerPlayer player2 = new PokerPlayer("P2", args[1]);

            ArrayList<PokerPlayer> players = new ArrayList<>();
            players.add(player1);
            players.add(player2);

            PokerReferee pokerReferee = new PokerReferee(players);
            System.out.println(pokerReferee.getMessage());

        } else {
            // '1H 4H 6H JH 8H'
            // '2C 2D 5H TS KS'

            /* this works
            CardHand player1 = new CardHand("2C 4D 6H JS 8H");
            int x = Shape.D.ordinal();
            int y = Value.SEVEN.ordinal();
           */

            Scanner sc = new Scanner(System.in);
            System.out.print("p1? ");
            String hand1 = sc.nextLine().trim().toUpperCase();
            System.out.print("p2? ");
            String hand2 = sc.nextLine().trim().toUpperCase();
            sc.close();

            PokerPlayer player1 = new PokerPlayer("P1", hand1); // flush
            PokerPlayer player2 = new PokerPlayer("P2", hand2); // royalFlush

            ArrayList<PokerPlayer> players = new ArrayList<>();
            players.add(player1);
            players.add(player2);

            PokerReferee pokerReferee = new PokerReferee(players);
            System.out.println(pokerReferee.getMessage());

            /*
            // Do the comparison and store the result
            result = comp(findComb(p1), findComb(p2));

            // Check if error in the data
            if(result != null && result.equals("ERROR")) {
                System.out.println("Result: " + result);
                return;
            }

            //Display the winner.
            System.out.println("Result: " + result);
            */

        }

    }

    /**
     * Transform a data String into an array of Strings. Handle errors if not containing 5 cards,
     * or of a card is not a regular one (not a good value, not a good suit). If encountering an
     * error, we store "ERROR" in the result string.
     * @param s the hand of the player, e.g., "1C 2C TD JH JS"
     * @return the hand as separated card encoded as strings, ["1C", "2C", "TD", "JH", "JS"]
     */
    /*
    private static String[] str2Array(String s) {
        Character[] vSymb = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K' };
        Set<Character> vs = new HashSet<>(Arrays.asList(vSymb));
        Character[] sSymb = { 'C', 'D', 'H', 'S' };
        Set<Character> ss = new HashSet<>(Arrays.asList(sSymb));
        String[] data = s.split(" ");
        if (data.length != 5)
            result = "ERROR";
        for(String d: data) {
            if(d.length() != 2)
                result = "ERROR";
            if(!vs.contains(d.charAt(0)) || !ss.contains(d.charAt(1)))
                result = "ERROR";
        }
        return data;
    }
*/
    /**
     * Find the highest combination in a hand. We detect flush (F), pair (P), and highest card (H)
     * for now. The result is the combination detected, followed by its highest card (to avoid ex-aequo).
     * @param cs the hand, e.g., ["1C", "2C", "TD", "JH", "JS"]
     * @return the highest combination, e.g., "P J" (as the example contains a Pair of Jacks)
     */


    /**
     * Compare two combinations, and returns the winner among p1 or p2, or tie if ex-aequo.
     * We support full (F), pair (P), and highest card (H) for now.
     * @param p1 the combination of p1, e.g., "P J"
     * @param p2 the combination of p2, e.g., "F T"
     * @return P1 if p1 wins, P2 if p2 wins, TIE elsewhere (here P2)
     */
    /*
    private static String comp(String p1, String p2) {
        if(p1.startsWith("F")) {
            if (p2.startsWith("F"))
                return tie(p1,p2);
            else {
                return "P1";
            }
        } else if (p1.startsWith("P")) {
            if (p2.startsWith("F"))
                return "P2";
            else if (p2.startsWith("P"))
                return tie(p1,p2);
            else {
                return "P1";
            }
        } else { // P1 is "Highest card"
            if(p2.startsWith("F") || p2.startsWith("P")) {
                return "P2";
            }
            return tie(p1,p2);
        }
    }
*/
    /**
     * find the highest card in a given hand
     * @param cs the hand to analyse, e.g., ["1C", "2C", "TD", "JH", "JS"]
     * @return the highest card code, here "J"
     */
    /*
    private static char maxVal(String[] cs) {
        int max = getTable().get(cs[0].charAt(0));
        char result = cs[0].charAt(0);
        for(String c: cs) {
            if (getTable().get(c.charAt(0)) > max) {
                max = getTable().get(c.charAt(0));
                result = c.charAt(0);
            }
        }
        return result;
    }
*/


}
