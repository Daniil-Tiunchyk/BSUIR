import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DOMParser {
    public static void main(String[] args) throws IOException {
        List<CreditCard> cards = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("credit_cards.xml");

            NodeList nList = doc.getElementsByTagName("credit_card");
            cards = new ArrayList<>();

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    CreditCard card = new CreditCard();
                    card.setCardNumber(eElement.getElementsByTagName("card_number").item(0).getTextContent());
                    card.setCardHolder(eElement.getElementsByTagName("card_holder").item(0).getTextContent());
                    card.setExpirationDate(eElement.getElementsByTagName("expiration_date").item(0).getTextContent());
                    card.setCvc(eElement.getElementsByTagName("cvc").item(0).getTextContent());

                    cards.add(card);
                }
            }

            for (CreditCard card : cards) {
                try {
                    card.isValid();
                    System.out.println("Valid card: " + card);
                } catch (Exception e) {
                    System.out.println("Invalid card detected: " + card + " Reason: " + e.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert cards != null;
        List<String> outputLines = cards.stream().map(CreditCard::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);

    }
}