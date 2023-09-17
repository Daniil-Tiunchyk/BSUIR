package bsuir.labwork.Labwork.models;

import bsuir.labwork.Labwork.exceptions.InvalidCardException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@Setter
@ToString
@Entity
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private String cvc;

    private static final String CARD_NUMBER_PATTERN = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";
    private static final String CARD_HOLDER_PATTERN = "[A-Za-z\\s]+";
    private static final String EXPIRATION_DATE_PATTERN = "\\d{4}-\\d{2}";
    private static final String CVC_PATTERN = "\\d{3}";

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
}