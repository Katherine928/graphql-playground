package net.katherine.graphqlplayground.sec01.lec05;

import net.katherine.graphqlplayground.sec01.lec05.dto.Customer;
import net.katherine.graphqlplayground.sec01.lec05.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @QueryMapping
    public Flux<Customer> customers() {
        return this.customerService.allCustomers();
    }

}
