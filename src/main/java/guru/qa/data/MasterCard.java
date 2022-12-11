package guru.qa.data;

public class MasterCard extends Card {
    public MasterCard() {
        super(PaymentSystem.MASTERCARD);
    }

    @Override
    public void payInCountry(Country country, int amount) {

    }

    protected boolean isCountryValidForTheseCard(Country country) {
        return true;
    }
}
