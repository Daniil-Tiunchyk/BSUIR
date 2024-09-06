package bsuir.labwork.Labwork.utils;

import bsuir.labwork.Labwork.models.CreditCard;
import bsuir.labwork.Labwork.interfaces.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class DOMParser implements Parser {

    @Override
    public List<CreditCard> parseCreditCards(String filePath) throws Exception {
        List<CreditCard> cards = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(filePath);

        NodeList nList = doc.getElementsByTagName("credit_card");

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
        return cards;
    }
}
