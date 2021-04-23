package com.example.dddstart.order.application;

import com.example.dddstart.order.domain.Order;
import com.example.dddstart.order.domain.OrderRepository;
import com.example.dddstart.order.exception.NoOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelOrderService {

  private final OrderRepository orderRepository;

  @Transactional
  public void cancel(Long id) {
    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new NoOrderException(id));
    order.cancel();
  }

}
