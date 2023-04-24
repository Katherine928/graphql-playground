package net.katherine.graphqlplayground.lec05;


import graphql.schema.DataFetchingFieldSelectionSet;
import net.katherine.graphqlplayground.lec05.dto.Address;
import net.katherine.graphqlplayground.lec05.dto.Customer;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {


    @SchemaMapping(typeName = "Customer")
    public Mono<Address> address(Customer customer, DataFetchingFieldSelectionSet selectionSet) {
        System.out.println(
                "address : " + selectionSet.getFields()
        );
        return Mono.just(
                Address.create(customer.getName() + " street", customer.getName() + " city")
        );
    }

}
