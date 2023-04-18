package net.katherine.graphqlplayground.sec01.lec03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class CustomerOrder {

    private UUID id;
    private String description;

}
