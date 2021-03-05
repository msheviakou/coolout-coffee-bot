package by.coolout.bot.service;

import by.coolout.bot.entity.Order;
import by.coolout.bot.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order order) { return orderRepository.save(order); }
}
