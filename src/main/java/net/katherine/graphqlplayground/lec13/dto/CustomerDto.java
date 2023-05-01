package net.katherine.graphqlplayground.lec13.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CustomerDto {

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String city;


}
