package net.katherine.graphqlplayground.lec04.service;

import net.katherine.graphqlplayground.lec04.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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


};

