package snackdev.rest.graphql.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse<T> {
    private T result;
    private boolean failed;
    private Throwable throwable;

    public MessageResponse(T result) {
        this();
        this.result = result;
    }

    public MessageResponse(Throwable throwable) {
        this();
        this.failed = true;
        this.throwable = throwable;
    }
}
