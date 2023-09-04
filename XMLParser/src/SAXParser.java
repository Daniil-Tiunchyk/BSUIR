import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParser {
    public static void main(String[] args) {
        List<CreditCard> cards = new ArrayList<>();
        try {
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
                        try {
                            card.isValid();
                            System.out.println("Valid card: " + card);
                        } catch (Exception e) {
                            System.out.println("Invalid card detected: " + card + " Reason: " + e.getMessage());
                        }
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

            saxParser.parse("credit_cards.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<String> outputLines = cards.stream().map(CreditCard::toString).collect(Collectors.toList());
            Files.write(Paths.get("output.txt"), outputLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
