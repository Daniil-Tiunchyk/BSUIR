package bsuir.labwork.Labwork.patterns;

import bsuir.labwork.Labwork.interfaces.Visitor;
import bsuir.labwork.Labwork.models.CreditCard;

public class CardValidationVisitor implements Visitor {
    @Override
    public void visit(CreditCard card) {
        try {
            card.validate();
            System.out.println("Valid card: " + card);
        } catch (Exception e) {
            System.out.println("Invalid card detected: " + card + " Reason: " + e.getMessage());
        }
    }
}
