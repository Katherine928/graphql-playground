package net.katherine.graphqlplayground.sec01.lec03.service;

import net.katherine.graphqlplayground.sec01.lec03.dto.CustomerOrder;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderService {

    private final Map<String, List<CustomerOrder>> map = Map.of(
            "Katherine", List.of(
                    CustomerOrder.create(UUID.randomUUID(), "Katherine-product-1"),
                    CustomerOrder.create(UUID.randomUUID(), "Katherine-product-2")
            ),
            "Alex", List.of(
                    CustomerOrder.create(UUID.randomUUID(), "Alex-product-1"),
                    CustomerOrder.create(UUID.randomUUID(), "Alex-product-2")
            )
    );

    public Flux<CustomerOrder> ordersByCustomerName(String name) {
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
    }

}
