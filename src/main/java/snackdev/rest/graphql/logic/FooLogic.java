package snackdev.rest.graphql.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snackdev.rest.graphql.base.MessageResponse;
import snackdev.rest.graphql.domain.Foo;
import snackdev.rest.graphql.store.FooRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class FooLogic {
    private final FooRepository fooRepository;

    public MessageResponse<Foo> register(Foo foo) {
        Foo result = fooRepository.save(foo);
        return new MessageResponse(result);
    }

    public MessageResponse<Foo> modify(Foo foo) {
        if (!fooRepository.existsById(foo.getId())) {
            throw new NoSuchElementException("No Such Foo with ID " + foo.getId());
        }
        Foo result = fooRepository.save(foo);
        return new MessageResponse(result);
    }

    public MessageResponse<Foo> delete(Foo foo) {
        if (!fooRepository.existsById(foo.getId())) {
            throw new NoSuchElementException("No Such Foo with ID " + foo.getId());
        }
        fooRepository.delete(foo);
        return new MessageResponse(foo);
    }

    public MessageResponse<Foo> findById(String id) {
        return new MessageResponse<>(fooRepository.findById(id).orElse(null));
    }

    public MessageResponse<List<Foo>> findByAgeGreaterThan(int age) {
        return new MessageResponse<>(fooRepository.findByAgeGreaterThan(age));
    }
}
