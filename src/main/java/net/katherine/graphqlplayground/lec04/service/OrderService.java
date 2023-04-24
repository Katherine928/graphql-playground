package net.katherine.graphqlplayground.lec04.service;

import net.katherine.graphqlplayground.lec04.dto.Customer;
import net.katherine.graphqlplayground.lec04.dto.CustomerOrder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
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

//    public Flux<List<CustomerOrder>> ordersByCustomerNames(List<String> names) {
//        return Flux.fromIterable(names)
//                .map(n -> map.getOrDefault(n, Collections.emptyList()));
//    }

    public Flux<List<CustomerOrder>> ordersByCustomerNames(List<String> names) {
        return Flux.fromIterable(names)
                .flatMapSequential(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
    }

    // Some source
    public Mono<List<CustomerOrder>> fetchOrders(String name) {
        return Mono.justOrEmpty(map.get(name));
    }

    public Mono<Map<Customer, List<CustomerOrder>>> fetchOrdersAsMap(List<Customer> customers) {
        return Flux.fromIterable(customers)
                .map(c -> Tuples.of(c, map.getOrDefault(c.getName(), Collections.emptyList())))
                .collectMap(
                        Tuple2::getT1,
                        Tuple2::getT2
                );
    }

}
