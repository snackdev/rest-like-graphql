package snackdev.rest.graphql.message.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import snackdev.rest.graphql.base.ExecutableMessage;
import snackdev.rest.graphql.base.MessageResponse;
import snackdev.rest.graphql.domain.Bar;
import snackdev.rest.graphql.logic.BarLogic;
import snackdev.rest.graphql.message.CommandType;

@Getter
@Setter
@NoArgsConstructor
public class BarCommand implements ExecutableMessage {
    private CommandType commandType;
    private Bar bar;

    @Override
    public MessageResponse execute() {
        BarLogic logic = loadDI(BarLogic.class);

        switch (commandType) {
            case Create:
                return logic.register(bar);
            case Update:
                return logic.modify(bar);
            case Delete:
                return logic.delete(bar);
            default:
                return new MessageResponse(new IllegalArgumentException("command Type not valid "+ commandType));
        }
    }
}
