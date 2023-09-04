import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SAXParser {

    public static List<CreditCard> parseCreditCards(String filePath) throws Exception {
        List<CreditCard> cards = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();

        DefaultHandler handler = new DefaultHandler() {
            CreditCard card;
            String currentElement;

            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                currentElement = qName;
                if ("credit_card".equals(currentElement)) {
                    card = new CreditCard();
                }
            }

            public void endElement(String uri, String localName, String qName) {
                if ("credit_card".equals(qName)) {
                    cards.add(card);
                }
                currentElement = "";
            }

            public void characters(char[] ch, int start, int length) {
                String value = new String(ch, start, length).trim();
                if (value.isEmpty()) return;

                if ("card_number".equals(currentElement)) {
                    card.setCardNumber(value);
                } else if ("card_holder".equals(currentElement)) {
                    card.setCardHolder(value);
                } else if ("expiration_date".equals(currentElement)) {
                    card.setExpirationDate(value);
                } else if ("cvc".equals(currentElement)) {
                    card.setCvc(value);
                }
            }
        };

        saxParser.parse(filePath, handler);
        return cards;
    }

    public static void main(String[] args) {
        try {
            List<CreditCard> cards = parseCreditCards("credit_cards.xml");
            writeCardsToFile(cards);
            validateAndPrintCards(cards);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintCards(List<CreditCard> cards) {
        for (CreditCard card : cards) {
            try {
                card.validate();
                System.out.println("Valid card: " + card);
            } catch (Exception e) {
                System.out.println("Invalid card detected: " + card + " Reason: " + e.getMessage());
            }
        }
    }

    private static void writeCardsToFile(List<CreditCard> cards) throws IOException {
        List<String> outputLines = cards.stream().map(CreditCard::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}
