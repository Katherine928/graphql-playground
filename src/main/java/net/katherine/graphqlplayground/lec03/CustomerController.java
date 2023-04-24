package net.katherine.graphqlplayground.lec03;

import net.katherine.graphqlplayground.lec03.dto.Customer;
import net.katherine.graphqlplayground.lec03.dto.CustomerOrder;
import net.katherine.graphqlplayground.lec03.service.CustomerService;
import net.katherine.graphqlplayground.lec03.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;


    @SchemaMapping(
            typeName = "Query"
    )
    public Flux<Customer> customers() {
        return this.customerService.allCustomers();
    }


    @SchemaMapping(
            typeName = "Customer"
    )
    public Flux<CustomerOrder> orders(Customer customer, @Argument Integer limit) {
        return orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }

}

