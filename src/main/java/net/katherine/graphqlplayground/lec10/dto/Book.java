package net.katherine.graphqlplayground.lec10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Book {

    private final UUID id = UUID.randomUUID();
    private String description;
    private Integer price;
    private String author;

}
