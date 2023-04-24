package net.katherine.graphqlplayground.lec05;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import net.katherine.graphqlplayground.lec05.dto.Customer;
import net.katherine.graphqlplayground.lec05.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @QueryMapping
    public Flux<Customer> customers(DataFetchingFieldSelectionSet selectionSet, DataFetchingEnvironment environment) {
        System.out.println(
                "customer : " + selectionSet.getFields()
        );
        System.out.println(
                "environment : " + environment.getDocument()
        );
        System.out.println(
                "environment : " + environment.getOperationDefinition()
        );
        return this.customerService.allCustomers();
    }

}
