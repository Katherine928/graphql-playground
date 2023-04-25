package net.katherine.graphqlplayground.lec06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerWithOrder {
    private Integer id;
    private String name;
    private Integer age;
    private String city;
    private List<CustomerOrder> orders;
}