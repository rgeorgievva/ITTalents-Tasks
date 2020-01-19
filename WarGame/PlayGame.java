package warGame;

public class PlayGame {

    private static final int NUMBER_CARDS_IN_DECK = 52;

    public static void main(String[] args) {

        Player ivan = new Player("Ivan");
        Player petur = new Player("Petur");

        Card[][] card = generateCards();
        ivan.setCards(card[1]);
        petur.setCards(card[0]);

        while (true) {
            Card[] givenCards = new Card[NUMBER_CARDS_IN_DECK];

            row(ivan, petur, givenCards, 0);

            if (ivan.hasNoCards()) {
                System.out.println(ivan.getName() + " has no cards left");
                System.out.println(petur.getName() + " wins the game!");
                break;
            }
            else
            if (petur.hasNoCards()) {
                System.out.println(petur.getName() + " has no cards left");
                System.out.println(ivan.getName() + " wins the game!");
                break;
            }

            System.out.println(ivan.getName() + " has " + ivan.getCurrentNumberCards() + " cards" +
                    " and " + ivan.getNumberWonCards() + " won cards");
            System.out.println(petur.getName() + " has " + petur.getCurrentNumberCards() + " cards" +
                    " and " + petur.getNumberWonCards() + " won cards");

        }

    }

    public static void row(Player firstPlayer, Player secondPlayer, Card[] givenCards, int index) {
        Card card1 = firstPlayer.giveCard();
        Card card2 = secondPlayer.giveCard();

        if (card1 == null || card2 == null) {
            return;
        }

        if (firstPlayer.hasNoCards()) {
            giveAllWonHands(secondPlayer, givenCards, index);
            secondPlayer.winHand(card1, card2);
            return;
        }

        if (secondPlayer.hasNoCards()) {
            giveAllWonHands(firstPlayer, givenCards, index);
            firstPlayer.winHand(card1, card2);
            return;
        }

        //If the card of the first player is stronger
        // -> he gets all the other shown cards(if any) and the last two given cards
        if (card1.getBiggerCard(card2) == 1) {
            System.out.println(card1.getStrength() + "" + card1.getSuit() + " wins");
            giveAllWonHands(firstPlayer, givenCards, index);
            firstPlayer.winHand(card1, card2);
            return;
        }

        //If the cards are with same strength
        // -> both players give another 3 cards and than compare the last pair shown cards
        if (card1.getBiggerCard(card2) == 0) {
            System.out.println(card1.getStrength() + "=" + card2.getStrength());
            givenCards[index++] = card1;
            givenCards[index++] = card2;

            for (int i = 0; i < 2; i++) {
                Card firstPlayerCard = firstPlayer.giveCard();
                Card secondPlayerCard = secondPlayer.giveCard();
                if (firstPlayerCard != null) {
                    givenCards[index++] = firstPlayerCard;
                }
                else {
                    break;
                }
                if (secondPlayerCard != null) {
                    givenCards[index++] = secondPlayerCard;
                }
                else {
                    break;
                }
            }
            row(firstPlayer, secondPlayer, givenCards, index);
        }

        //If the card of the second player is stronger
        // -> he gets all the other shown cards(if any) and the last two given cards
        else {
            System.out.println(card2.getStrength() + "" + card2.getSuit() + " wins");
            giveAllWonHands(secondPlayer, givenCards, index);
            secondPlayer.winHand(card1, card2);
            return;
        }

    }

    public static void giveAllWonHands(Player player, Card[] cardsToGive, int index) {
        if (index > 0) {
            int i = 0;
            while (i < index - 1) {
                player.winHand(cardsToGive[i], cardsToGive[i + 1]);
                i += 2;
            }
        }
    }

    public static Card numberToCard(int number) {
        Card card;
        switch (number) {
            case 1:
                card = new Card('2','c');
                break;
            case 2:
                card = new Card('2','d');
                break;
            case 3:
                card = new Card('2','h');
                break;
            case 4:
                card = new Card('2','s');
                break;
            case 5:
                card = new Card('3','c');
                break;
            case 6:
                card = new Card('3','d');
                break;
            case 7:
                card = new Card('3','h');
                break;
            case 8:
                card = new Card('3','s');
                break;
            case 9:
                card = new Card('4','c');
                break;
            case 10:
                card = new Card('4','d');
                break;
            case 11:
                card = new Card('4','h');
                break;
            case 12:
                card = new Card('4','s');
                break;
            case 13:
                card = new Card('5','c');
                break;
            case 14:
                card = new Card('5','d');
                break;
            case 15:
                card = new Card('5','h');
                break;
            case 16:
                card = new Card('5','s');
                break;
            case 17:
                card = new Card('6','c');
                break;
            case 18:
                card = new Card('6','d');
                break;
            case 19:
                card = new Card('6','h');
                break;
            case 20:
                card = new Card('6','s');
                break;
            case 21:
                card = new Card('7','c');
                break;
            case 22:
                card = new Card('7','d');
                break;
            case 23:
                card = new Card('7','h');
                break;
            case 24:
                card = new Card('7','s');
                break;
            case 25:
                card = new Card('8','c');
                break;
            case 26:
                card = new Card('8','d');
                break;
            case 27:
                card = new Card('8','h');
                break;
            case 28:
                card = new Card('8','s');
                break;
            case 29:
                card = new Card('9','c');
                break;
            case 30:
                card = new Card('9','d');
                break;
            case 31:
                card = new Card('9','h');
                break;
            case 32:
                card = new Card('9','s');
                break;
            case 33:
                card = new Card('T','c');
                break;
            case 34:
                card = new Card('T','d');
                break;
            case 35:
                card = new Card('T','h');
                break;
            case 36:
                card = new Card('T','s');
                break;
            case 37:
                card = new Card('J','c');
                break;
            case 38:
                card = new Card('J','d');
                break;
            case 39:
                card = new Card('J','h');
                break;
            case 40:
                card = new Card('J','s');
                break;
            case 41:
                card = new Card('Q','c');
                break;
            case 42:
                card = new Card('Q','d');
                break;
            case 43:
                card = new Card('Q','h');
                break;
            case 44:
                card = new Card('Q','s');
                break;
            case 45:
                card = new Card('K','c');
                break;
            case 46:
                card = new Card('K','d');
                break;
            case 47:
                card = new Card('K','h');
                break;
            case 48:
                card = new Card('K','s');
                break;
            case 49:
                card = new Card('A','c');
                break;
            case 50:
                card = new Card('A','d');
                break;
            case 51:
                card = new Card('A','h');
                break;
            case 52:
                card = new Card('A','s');
                break;
            default:
                card = null;
                break;
        }

        return card;
    }

    public static Card[][] generateCards() {

        int[] numbersFirstPlayer = new int[NUMBER_CARDS_IN_DECK / 2];
        int currentNumberCardsFirstPlayer;

        //generate 26 unique numbers between 1 and 52 for the first player
        for (int i = 0; i < numbersFirstPlayer.length; i++) {
            do {
                currentNumberCardsFirstPlayer = (int)Math.floor(Math.random() * 52) + 1;
            } while (doesContain(currentNumberCardsFirstPlayer, numbersFirstPlayer));
            numbersFirstPlayer[i] = currentNumberCardsFirstPlayer;
        }

        int[] numbersSecondPlayer = new int[NUMBER_CARDS_IN_DECK / 2];
        int currentNumberCardsSecondPlayer = 0;

        //set the last 26 numbers between 1 and 52 that were not
        // given to the first player for the second player
        for (int i = 1; i <= NUMBER_CARDS_IN_DECK; i++) {
            if (!doesContain(i, numbersFirstPlayer)) {
                numbersSecondPlayer[currentNumberCardsSecondPlayer] = i;
                currentNumberCardsSecondPlayer++;
            }
        }

        //get cards for the players from the numbers set for each one of them
        Card[] cardsFirstPlayer = getCards(numbersFirstPlayer);
        Card[] cardsSecondPlayer = getCards(numbersSecondPlayer);

        Card[][] cards = {cardsFirstPlayer, cardsSecondPlayer};
        return cards;
    }

    public static Card[] getCards(int[] cardsAsNumbers) {
        Card[] cards = new Card[NUMBER_CARDS_IN_DECK / 2];
        for (int i = 0; i < cardsAsNumbers.length; i++) {
            cards[i] = numberToCard(cardsAsNumbers[i]);
        }
        return cards;
    }

    public static boolean doesContain(int number, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                return true;
            }
        }
        return false;
    }

}
