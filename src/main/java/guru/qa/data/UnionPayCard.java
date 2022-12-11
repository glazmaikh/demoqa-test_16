package guru.qa.data;

public class UnionPayCard extends Card {
    public UnionPayCard() {
        super(PaymentSystem.UNION_PAY);
    }

    @Override
    public void payInCountry(Country country, int amount) {

    }

    protected boolean isCountryValidForTheseCard(Country country) {
        return country == Country.CN;
    }
}
