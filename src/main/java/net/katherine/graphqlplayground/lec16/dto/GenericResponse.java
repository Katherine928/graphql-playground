package net.katherine.graphqlplayground.lec16.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.graphql.ResponseError;

@ToString
@Getter
public class GenericResponse<T> {

    private final T data;
    private final ResponseError error;
    private final boolean dataPresent;

    public GenericResponse(T data) {
        this.data = data;
        this.error = null;
        this.dataPresent = true;
    }

    public GenericResponse(ResponseError error) {
        this.data = null;
        this.error = null;
        this.dataPresent = false;
    }
}
