package bsuir.labwork.Labwork;

import bsuir.labwork.Labwork.configs.ParserConfig;
import bsuir.labwork.Labwork.patterns.CardValidationVisitor;
import bsuir.labwork.Labwork.models.CreditCard;
import bsuir.labwork.Labwork.patterns.DOMParserFactory;
import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.interfaces.ParserFactory;
import bsuir.labwork.Labwork.patterns.SAXParserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ParserFactory domFactory = new DOMParserFactory();
        Parser domParser = domFactory.createParser();

        ParserFactory saxFactory = new SAXParserFactory();
        Parser saxParser = saxFactory.createParser();

        try {
            ParserConfig config = ParserConfig.getInstance();
            String creditCardFilePath = config.getCreditCardFilePath();

            List<CreditCard> domCards = domParser.parseCreditCards(creditCardFilePath);
            List<CreditCard> saxCards = saxParser.parseCreditCards(creditCardFilePath);

            writeCardsToFile(domCards);
            validateAndPrintCards(saxCards);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateAndPrintCards(List<CreditCard> cards) {
        CardValidationVisitor visitor = new CardValidationVisitor();

        for (CreditCard card : cards) {
            card.accept(visitor);
        }
    }


    private static void writeCardsToFile(List<CreditCard> cards) throws IOException {
        List<String> outputLines = cards.stream().map(CreditCard::toString).collect(Collectors.toList());
        Files.write(Paths.get("output.txt"), outputLines);
    }
}
