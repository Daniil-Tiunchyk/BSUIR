package bsuir.labwork.Labwork.patterns;

import bsuir.labwork.Labwork.interfaces.Parser;
import bsuir.labwork.Labwork.interfaces.ParserFactory;
import bsuir.labwork.Labwork.utils.DOMParser;

public class DOMParserFactory implements ParserFactory {
    @Override
    public Parser createParser() {
        return new DOMParser();
    }
}
