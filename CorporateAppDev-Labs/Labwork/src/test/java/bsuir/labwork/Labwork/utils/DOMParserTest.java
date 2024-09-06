package bsuir.labwork.Labwork.utils;

import static org.junit.jupiter.api.Assertions.*;

import bsuir.labwork.Labwork.models.CreditCard;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DOMParserTest {

    @Test
    public void testParseCreditCards() throws Exception {
        DOMParser parser = new DOMParser();
        String filePath = "test_credit_cards.xml";

        List<CreditCard> cards = parser.parseCreditCards(filePath);

        assertEquals(1, cards.size());

        assertEquals("1234-5678-9101-1121", cards.get(0).getCardNumber());
        assertEquals("John Doe", cards.get(0).getCardHolder());
        assertEquals("2024-09", cards.get(0).getExpirationDate());
        assertEquals("123", cards.get(0).getCvc());
    }
}
