package bsuir.labwork.Labwork.interfaces;

import bsuir.labwork.Labwork.models.CreditCard;

public interface Visitor {
    void visit(CreditCard card);
}