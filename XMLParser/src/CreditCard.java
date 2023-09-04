import java.util.regex.Pattern;

class CreditCard {
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvc;

    public void isValid() throws Exception {
        boolean isValidCardNumber = Pattern.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}", this.cardNumber);
        boolean isValidCardHolder = Pattern.matches("[A-Za-z\\s]+", this.cardHolder);
        boolean isValidExpirationDate = Pattern.matches("\\d{4}-\\d{2}", this.expirationDate);
        boolean isValidCvc = Pattern.matches("\\d{3}", this.cvc);

        if (!isValidCardNumber) {
            throw new Exception("Invalid CardNumber");
        }

        if (!isValidCardHolder) {
            throw new Exception("Invalid CardHolder");
        }

        if (!isValidExpirationDate) {
            throw new Exception("Invalid ExpirationDate");
        }

        if (!isValidCvc) {
            throw new Exception("Invalid Cvc");
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