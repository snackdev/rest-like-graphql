package snackdev.rest.graphql.store;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import snackdev.rest.graphql.domain.Bar;

import java.util.List;

@Repository
public interface BarRepository extends CrudRepository<Bar, String> {
    List<Bar> findByAgeGreaterThan(int age);
}
