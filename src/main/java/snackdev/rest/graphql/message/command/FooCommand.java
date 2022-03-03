package snackdev.rest.graphql.message.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import snackdev.rest.graphql.base.ExecutableMessage;
import snackdev.rest.graphql.base.MessageResponse;
import snackdev.rest.graphql.domain.Foo;
import snackdev.rest.graphql.logic.FooLogic;
import snackdev.rest.graphql.message.CommandType;

@Getter
@Setter
@NoArgsConstructor
public class FooCommand implements ExecutableMessage {
    private CommandType commandType;
    private Foo foo;

    @Override
    public MessageResponse execute() {
        FooLogic logic = loadDI(FooLogic.class);

        switch (commandType) {
            case Create:
                return logic.register(foo);
            case Update:
                return logic.modify(foo);
            case Delete:
                return logic.delete(foo);
            default:
                return new MessageResponse(new IllegalArgumentException("command Type not valid "+ commandType));
        }
    }
}
