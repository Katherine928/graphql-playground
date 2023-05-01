package net.katherine.graphqlplayground.lec15.controller;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import net.katherine.graphqlplayground.lec15.exceptions.ApplicationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {
    @Override
    public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
        var ApplicationException = toApplicationException(exception);
        return Mono.just(
                List.of(
                        GraphqlErrorBuilder.newError(environment)
                                .message(ApplicationException.getMessage())
                                .errorType(ApplicationException.getErrorType())
                                .extensions(ApplicationException.getExtensions())
                                .build()
                )
        );
    }

    private ApplicationException toApplicationException(Throwable throwable) {
        return ApplicationException.class.equals(throwable.getClass()) ?
                (ApplicationException) throwable:
                new ApplicationException(ErrorType.INTERNAL_ERROR, throwable.getMessage(), Collections.emptyMap());
    }
}
