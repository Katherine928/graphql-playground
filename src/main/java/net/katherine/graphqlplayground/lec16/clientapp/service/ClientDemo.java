package net.katherine.graphqlplayground.lec16.clientapp.service;

import net.katherine.graphqlplayground.lec16.clientapp.client.CustomerClient;
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

    @Override
    public void run(String... args) throws Exception {
        rawQueryDemo().subscribe();

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
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println("Raw Query"))
                .then(mono)
                .doOnNext(System.out::println)
                .then();
    }

}


