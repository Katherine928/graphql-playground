package net.katherine.graphqlplayground.lec15.controller;

import net.katherine.graphqlplayground.lec15.dto.CustomerDto;
import net.katherine.graphqlplayground.lec15.dto.CustomerNotFound;
import net.katherine.graphqlplayground.lec15.dto.DeleteResponseDto;
import net.katherine.graphqlplayground.lec15.exceptions.ApplicationErrors;
import net.katherine.graphqlplayground.lec15.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @QueryMapping
    public Flux<CustomerDto> customers() {
        return customerService.allCustomers();
    }

    @QueryMapping
    public Mono<Object> customerById(@Argument Integer id) {
        return customerService.customerById(id)
                .cast(Object.class)
//                .switchIfEmpty(ApplicationErrors.noSuchUser(id));
                .switchIfEmpty(Mono.just(CustomerNotFound.create(id)));
    }

    @MutationMapping
    public Mono<CustomerDto> createCustomer(@Argument CustomerDto customer) {
        return Mono.just(customer)
                .filter(c -> c.getAge() >= 18)
                .flatMap(customerService::createCustomer)
                .switchIfEmpty(ApplicationErrors.mustBe18(customer));
//        return customerService.createCustomer(customer);
    }

    @MutationMapping
    public Mono<CustomerDto> updateCustomer(@Argument Integer id, @Argument CustomerDto customer) {
        return customerService.updateCustomer(id, customer);
    }

    @MutationMapping
    public Mono<DeleteResponseDto> deleteCustomer(@Argument Integer id) {
        return customerService.deleteCustomer(id);
    }
}
