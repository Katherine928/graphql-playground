package net.katherine.graphqlplayground.lec01;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class GraphqlController {
//      QueryMapping("sayHello")
//    public Mono<String> sayHello() {
//        return Mono.just("Hello World");
//    }

    @QueryMapping("sayHello")
    public Mono<String> helloWorld() {
        return Mono.just("Hello World")
                .delayElement(Duration.ofMillis(800));
    }

    @QueryMapping
    public Mono<String> sayHelloTo(@Argument("name") String name) { //if name is different than schema, then add "name", else not
        return Mono.fromSupplier(() -> "Hello " + name)
                .delayElement(Duration.ofMillis(900));
    }

    @QueryMapping
    public Mono<Integer> random() {
        return Mono.just(ThreadLocalRandom.current().nextInt(1, 100))
                .delayElement(Duration.ofMillis(1000));
    }
 }
