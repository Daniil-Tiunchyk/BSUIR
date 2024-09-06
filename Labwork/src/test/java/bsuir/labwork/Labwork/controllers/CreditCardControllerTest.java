package bsuir.labwork.Labwork.controllers;

import static org.mockito.Mockito.*;

import bsuir.labwork.Labwork.models.CreditCard;
import bsuir.labwork.Labwork.services.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardControllerTest {

    @InjectMocks
    private CreditCardController controller;

    @Mock
    private CreditCardService service;

    @Mock
    private Model model;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCards() {
        List<CreditCard> cards = Arrays.asList(new CreditCard(), new CreditCard());
        when(service.getAllCards()).thenReturn(cards);

        String viewName = controller.getCards(model);
        assertEquals("cards", viewName);
        verify(model, times(1)).addAttribute("cards", cards);
        verify(service, times(1)).getAllCards();
    }

    @Test
    public void testAddCard() {
        CreditCard card = new CreditCard();
        String viewName = controller.addCard(card);
        assertEquals("redirect:/cards", viewName);
        verify(service, times(1)).saveCard(card);
    }

    @Test
    public void testUpdateCardForm() {
        CreditCard card = new CreditCard();
        int id = 1;
        when(service.getCardById(id)).thenReturn(card);

        String viewName = controller.updateCardForm(id, model);
        assertEquals("updateCard", viewName);
        verify(model, times(1)).addAttribute("card", card);
    }

    @Test
    public void testUpdateCard() {
        CreditCard card = new CreditCard();
        String viewName = controller.updateCard(card);
        assertEquals("redirect:/cards", viewName);
        verify(service, times(1)).updateCard(card);
    }

    @Test
    public void testDeleteCard() {
        int id = 1;
        String viewName = controller.deleteCard(id);
        assertEquals("redirect:/cards", viewName);
        verify(service, times(1)).deleteCard(id);
    }
}
