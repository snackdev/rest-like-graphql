package snackdev.rest.graphql.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Bar")
public class Bar {
    @Id
    private String id;
    private int age;
}
