package bsuir.labwork.Labwork.interfaces;

import bsuir.labwork.Labwork.models.CreditCard;

import java.util.List;

public interface Parser {
    List<CreditCard> parseCreditCards(String filePath) throws Exception;
}

