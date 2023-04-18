package net.katherine.graphqlplayground.sec01.lec02;

import net.katherine.graphqlplayground.sec01.lec02.dto.AgeRangeFilter;
import net.katherine.graphqlplayground.sec01.lec02.dto.Customer;
import net.katherine.graphqlplayground.sec01.lec02.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @QueryMapping
    public Flux<Customer> customers() {
        return this.customerService.allCustomers();
    }

    @QueryMapping
    public Mono<Customer> customerById(@Argument Integer id) {
        return this.customerService.customerById(id);
    }

    @QueryMapping
    public Flux<Customer> customersNameContains(@Argument String name) {
        return customerService.nameContains(name);
    }

    @QueryMapping
    public Flux<Customer> customersByAgeRange(@Argument AgeRangeFilter filter) {
        return customerService.withinAge(filter);
    }

}
