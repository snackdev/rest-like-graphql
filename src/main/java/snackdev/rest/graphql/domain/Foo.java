package snackdev.rest.graphql.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Foo")
public class Foo {
    @Id
    private String id;
    private int age;
}
