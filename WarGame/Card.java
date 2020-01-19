package warGame;

public class Card {

    private char strength;
    private char suit;

    public Card(char strength, char suit) {
        this.setStrength(strength);
        this.setSuit(suit);
    }

    public char getStrength() {
        return this.strength;
    }

    public void setStrength(char strength) {
        if ((strength >= '2' && strength <= '9') ||
                strength == 'T' ||
                strength == 'J' ||
                strength == 'Q' ||
                strength == 'K' ||
                strength == 'A') {
            this.strength = strength;
        }
    }

    public char getSuit() {
        return this.suit;
    }

    public void setSuit(char suit) {
        if (suit == 's' || suit == 'c' || suit == 'd' || suit == 'h') {
            this.suit = suit;
        }
    }

    private static int strengthToNumber(char strength) {
        int number;

        if (strength >= '2' && strength <= '9') {
            number = strength - '0';
        } else {
            switch (strength) {
                case 'T':
                    number = 10;
                    break;
                case 'J':
                    number = 11;
                    break;
                case 'Q':
                    number = 12;
                    break;
                case 'K':
                    number = 13;
                    break;
                case 'A':
                    number = 14;
                    break;
                default:
                    number = 0;
                    break;
            }
        }
        return number;
    }

    //1 -> current card wins
    //0 -> current card and card2 are equal
    //-1 -> card2 wins
    public int getBiggerCard(Card card2) {
        if (strengthToNumber(this.strength) > strengthToNumber(card2.strength)) {
            return 1;
        }
        if (strengthToNumber(this.strength) == strengthToNumber(card2.strength)) {
            return 0;
        }
        return -1;
    }


}
