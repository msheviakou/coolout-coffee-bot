package by.coolout.bot.repository;

import by.coolout.bot.entity.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, String> {

    Optional<Drink> findByNameAndVolume(String name, Integer volume);
}
