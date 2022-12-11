package guru.qa;

import guru.qa.data.Card;
import guru.qa.data.Country;
import guru.qa.data.MasterCard;

public class Main {
    public static void main(String[] args) {
        Card masterCard = new MasterCard();
        masterCard.setBalance(100);
        masterCard.payInCountry(Country.RU, 75);
        System.out.println(masterCard.getBalance());

    }
}
