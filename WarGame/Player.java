package warGame;

public class Player {

    private static final int NUMBER_CARDS_IN_DECK = 52;

    private String name;
    private Card[] cards;
    private Card[] wonCards;
    private int currentNumberCards;
    private int numberWonCards;

    public Player(String name) {
        this.setName(name);
        this.cards = new Card[NUMBER_CARDS_IN_DECK];
        this.wonCards = new Card[NUMBER_CARDS_IN_DECK];
        this.currentNumberCards = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name != null && !name.equals("")) {
            this.name = name;
        }
        else {
            this.name = "Player";
        }
    }

    public void setCards(Card[] cards) {
        if (cards != null) {
            this.cards = cards;
        }
        this.currentNumberCards = cards.length;
    }

    public int getCurrentNumberCards() {
        return this.currentNumberCards;
    }

    public int getNumberWonCards() {
        return this.numberWonCards;
    }

    public Card giveCard() {
        if (this.currentNumberCards == 0 && this.numberWonCards == 0) {
            return null;
        }
        if (this.currentNumberCards == 0 && this.numberWonCards != 0) {
            this.cards = this.wonCards;
            this.currentNumberCards = this.numberWonCards;
            this.wonCards = new Card[NUMBER_CARDS_IN_DECK];
            this.numberWonCards = 0;
        }

        Card cardToGive = this.cards[this.currentNumberCards - 1];
        this.cards[this.currentNumberCards - 1] = null;
        this.currentNumberCards--;

        System.out.println(this.getName() + ": " + cardToGive.getStrength() + "" + cardToGive.getSuit());
        return cardToGive;
    }

    public void winHand(Card card1, Card card2) {
        if (this.numberWonCards + 2 > NUMBER_CARDS_IN_DECK) {
            return;
        }

        if (card1 == null && card2 == null) {
            return;
        }

        this.wonCards[this.numberWonCards] = card1;
        this.wonCards[this.numberWonCards + 1] = card2;
        this.numberWonCards += 2;

    }

    public boolean hasNoCards() {
        if (this.numberWonCards + this.currentNumberCards == 0) {
            return true;
        }
        return false;
    }
}
