package bsuir.labwork.Labwork.factories;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.interfaces.ParserFactory;
import bsuir.labwork.Labwork.utils.SAXParser;

public class SAXParserFactory implements ParserFactory {
    @Override
    public Parser createParser() {
        return new SAXParser();
    }
}
