package snackdev.rest.graphql.logic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snackdev.rest.graphql.base.MessageResponse;
import snackdev.rest.graphql.domain.Bar;
import snackdev.rest.graphql.store.BarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class BarLogic {
    private final BarRepository barRepository;

    public MessageResponse<Bar> register(Bar bar) {
        Bar result = barRepository.save(bar);
        return new MessageResponse(result);
    }

    public MessageResponse<Bar> modify(Bar bar) {
        if (!barRepository.existsById(bar.getId())) {
            throw new NoSuchElementException("No Such Bar with ID " + bar.getId());
        }
        Bar result = barRepository.save(bar);
        return new MessageResponse(result);
    }

    public MessageResponse<Bar> delete(Bar bar) {
        if (!barRepository.existsById(bar.getId())) {
            throw new NoSuchElementException("No Such Bar with ID " + bar.getId());
        }
        barRepository.delete(bar);
        return new MessageResponse(bar);
    }


    public MessageResponse<Bar> findById(String id) {
        return new MessageResponse<>(barRepository.findById(id).orElse(null));
    }

    public MessageResponse<List<Bar>> findByAgeGreaterThan(int age) {
        return new MessageResponse<>(barRepository.findByAgeGreaterThan(age));
    }
}
