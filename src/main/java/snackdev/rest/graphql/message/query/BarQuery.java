package snackdev.rest.graphql.message.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import snackdev.rest.graphql.base.ExecutableMessage;
import snackdev.rest.graphql.base.MessageResponse;
import snackdev.rest.graphql.logic.BarLogic;

@Getter
@Setter
@NoArgsConstructor
public class BarQuery implements ExecutableMessage {
    private String id;
    private int age;

    @Override
    public MessageResponse execute() {
        BarLogic logic = loadDI(BarLogic.class);

        if (id != null) {
            return logic.findById(id);
        } else {
            return logic.findByAgeGreaterThan(age);
        }
    }
}
