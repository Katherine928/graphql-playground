package net.katherine.graphqlplayground.sec01.lec04;

import net.katherine.graphqlplayground.sec01.lec04.dto.Customer;
import net.katherine.graphqlplayground.sec01.lec04.dto.CustomerOrder;
import net.katherine.graphqlplayground.sec01.lec04.service.CustomerService;
import net.katherine.graphqlplayground.sec01.lec04.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


//    @BatchMapping(
//            typeName = "Customer"
//    )
//    public Flux<List<CustomerOrder>> orders(List<Customer> list) {
//        return orderService.ordersByCustomerNames(list.stream().map(Customer::getName).collect(Collectors.toList()));
//    }

    @BatchMapping(
            typeName = "Customer"
    )
    public Mono<Map<Customer, List<CustomerOrder>>> orders(List<Customer> list) {
        return orderService.fetchOrdersAsMap(list);
    }

}

