package net.katherine.graphqlplayground.lec02.service;

import net.katherine.graphqlplayground.lec02.dto.AgeRangeFilter;
import net.katherine.graphqlplayground.lec02.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final Flux<Customer> flux = Flux.just(
            Customer.create(1,"Katherine",18,"Columbus"),
            Customer.create(2,"Alex",25,"Columbus")
    );

    public Flux<Customer> allCustomers()
    {
        return flux;
    }



    public Mono<Customer> customerById(Integer id) {
        return flux.filter(c -> c.getId().equals(id))
                .next();
    }

    public Flux<Customer> nameContains(String name) {
        return flux.filter(c -> c.getName().contains(name));
    }

    public Flux<Customer> withinAge(AgeRangeFilter filter) {
        return flux
                .filter(c -> c.getAge() >= filter.getMinAge() && c.getAge() <= filter.getMaxAge());
    }
};

