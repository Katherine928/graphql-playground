package net.katherine.graphqlplayground.lec16.clientapp.service;

import net.katherine.graphqlplayground.lec16.clientapp.client.CustomerClient;
import net.katherine.graphqlplayground.lec16.clientapp.client.SubscriptionClient;
import net.katherine.graphqlplayground.lec16.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class ClientDemo implements CommandLineRunner {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private SubscriptionClient subscriptionClient;

    @Override
    public void run(String... args) throws Exception {
        this.subscriptionClient
                .customerEvents()
                .doOnNext(e -> System.out.println(" ** " + e.toString() + " ** "))
                .subscribe();
        allCustomerDemo()
                .then(this.customerByIdDemo())
                .then(this.createCustomerDemo())
                .then(this.updateCustomerDemo())
                .then(this.deleteCustomerDemo())
                .subscribe();

    }


    public Mono<Void> rawQueryDemo() {
        String query = """
                {
                    customers{
                        id
                        name
                        age
                        city
                    }
                }
                """;
        Mono<List<CustomerDto>> mono = this.customerClient.rawQuery(query)
                .map(clientGraphQlResponse -> clientGraphQlResponse.field("customers").toEntityList(CustomerDto.class));
//        return Mono.delay(Duration.ofSeconds(1))
//                .doFirst(() -> System.out.println("Raw Query"))
//                .then(mono)
//                .doOnNext(System.out::println)
//                .then();
        return executor("Raw Query", mono);
    }


    public Mono<Void> getCustomerById() {
        return executor("getCustomerById", customerClient.getCustomerByIdWithUnion(1));
    }

    private Mono<Void> allCustomerDemo() {
        return executor("allCustomersDemo", customerClient.allCustomers());
    }

    private Mono<Void> customerByIdDemo(){
        return this.executor("customerByIdDemo", customerClient.customerById(1));
    }

    private Mono<Void> createCustomerDemo(){
        return this.executor("createCustomerDemo", customerClient.createCustomer(CustomerDto.create(null, "obie", 45, "detroit")));
    }

    private Mono<Void> updateCustomerDemo(){
        return this.executor("updateCustomerDemo", customerClient.updateCustomer(
                2,
                CustomerDto.create(null, "jackson", 15, "dallas"))
        );
    }

    private Mono<Void> deleteCustomerDemo(){
        return this.executor("deleteCustomerDemo", customerClient.deleteCustomer(3));
    }



    private <T> Mono<Void> executor(String message, Mono<T> mono) {
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println(message))
                .then(mono)
                .doOnNext(System.out::println)
                .then();
    }

}


