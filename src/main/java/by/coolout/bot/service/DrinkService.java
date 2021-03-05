package by.coolout.bot.service;

import by.coolout.bot.entity.Drink;
import by.coolout.bot.repository.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public Drink getByNameAndVolume(String name, Integer volume) { return drinkRepository.findByNameAndVolume(name, volume).orElseThrow(() -> new NoSuchElementException("No drink present.")); }

    public void saveAll(List<Drink> drinks) { drinkRepository.saveAll(drinks); }
}
