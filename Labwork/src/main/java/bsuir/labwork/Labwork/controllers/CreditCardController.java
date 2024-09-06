package bsuir.labwork.Labwork.controllers;

import bsuir.labwork.Labwork.models.CreditCard;
import bsuir.labwork.Labwork.services.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CreditCardController {
    private final CreditCardService service;

    @Autowired
    public CreditCardController(CreditCardService service) {
        this.service = service;
    }

    @GetMapping("/cards")
    public String getCards(Model model) {
        model.addAttribute("cards", service.getAllCards());
        return "cards";
    }

    @PostMapping("/addCard")
    public String addCard(CreditCard card) {
        service.saveCard(card);
        return "redirect:/cards";
    }

    @GetMapping("/updateCard/{id}")
    public String updateCardForm(@PathVariable("id") int id, Model model) {
        CreditCard card = service.getCardById(id);
        model.addAttribute("card", card);
        return "updateCard";
    }

    @PostMapping("/updateCard")
    public String updateCard(CreditCard card) {
        service.updateCard(card);
        return "redirect:/cards";
    }

    @GetMapping("/deleteCard/{id}")
    public String deleteCard(@PathVariable int id) {
        service.deleteCard(id);
        return "redirect:/cards";
    }
}
