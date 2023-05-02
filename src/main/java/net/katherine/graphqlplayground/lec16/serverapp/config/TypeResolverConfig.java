package net.katherine.graphqlplayground.lec16.serverapp.config;


import graphql.schema.TypeResolver;
import net.katherine.graphqlplayground.lec16.dto.CustomerDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class TypeResolverConfig {

    @Bean
    public TypeResolver typeResolver() {
        ClassNameTypeResolver resolver = new ClassNameTypeResolver();
        resolver.addMapping(CustomerDto.class, "Customer");
        return resolver;
    }

    @Bean
    public RuntimeWiringConfigurer configurer(TypeResolver typeResolver) {
        return c -> c.type("CustomerResponse", b -> b.typeResolver(typeResolver));
    }

}
