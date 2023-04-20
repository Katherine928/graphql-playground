package net.katherine.graphqlplayground.sec01.lec04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class Customer {

        private Integer id;
        private String name;
        private Integer age;
        private String city;




}
