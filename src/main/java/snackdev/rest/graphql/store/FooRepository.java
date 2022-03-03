package snackdev.rest.graphql.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import snackdev.rest.graphql.domain.Foo;

import java.util.List;

@Repository
public interface FooRepository extends CrudRepository<Foo, String> {
    List<Foo> findByAgeGreaterThan(int age);
}
