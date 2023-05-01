package net.katherine.graphqlplayground.lec14.controller;

import net.katherine.graphqlplayground.lec14.dto.CustomerEvent;
import net.katherine.graphqlplayground.lec14.service.CustomerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerEventController {

    @Autowired
    private CustomerEventService customerEventService;


    @SubscriptionMapping
    public Flux<CustomerEvent> customerEvents() {
        return customerEventService.subscribe();
    }

}
