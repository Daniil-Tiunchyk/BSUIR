package bsuir.labwork.Labwork.services;

import bsuir.labwork.Labwork.models.CreditCard;
import bsuir.labwork.Labwork.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {
    private final CreditCardRepository repository;

    @Autowired
    public CreditCardService(CreditCardRepository repository) {
        this.repository = repository;
    }

    public List<CreditCard> getAllCards() {
        return repository.findAll();
    }

    public void saveCard(CreditCard card) {
        repository.save(card);
    }

    public void updateCard(CreditCard card) {
        repository.save(card);
    }

    public void deleteCard(int id) {
        repository.deleteById(id);
    }

    public CreditCard getCardById(int id) {
        return repository.findById(id).orElse(null);
    }
}
