import java.util.regex.Pattern;

class CreditCard {
    private static final String CARD_NUMBER_PATTERN = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";
    private static final String CARD_HOLDER_PATTERN = "[A-Za-z\\s]+";
    private static final String EXPIRATION_DATE_PATTERN = "\\d{4}-\\d{2}";
    private static final String CVC_PATTERN = "\\d{3}";

    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvc;

    public void validate() throws InvalidCardException {
        if (!Pattern.matches(CARD_NUMBER_PATTERN, cardNumber)) {
            throw new InvalidCardException("Invalid CardNumber");
        }
        if (!Pattern.matches(CARD_HOLDER_PATTERN, cardHolder)) {
            throw new InvalidCardException("Invalid CardHolder");
        }
        if (!Pattern.matches(EXPIRATION_DATE_PATTERN, expirationDate)) {
            throw new InvalidCardException("Invalid ExpirationDate");
        }
        if (!Pattern.matches(CVC_PATTERN, cvc)) {
            throw new InvalidCardException("Invalid CVC");
        }
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cvc='" + cvc + '\'' +
                '}';
    }
}