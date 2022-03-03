package snackdev.rest.graphql.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snackdev.rest.graphql.base.ExecutableMessage;
import snackdev.rest.graphql.base.MessageResponse;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@Slf4j
public class MessageEndpoint {
    private final ApplicationContext applicationContext;
    private ConcurrentHashMap<String, Class<? extends ExecutableMessage>> messageTable;

    @PostConstruct
    public void initMessageTable() {
        applicationContext.getBeansWithAnnotation(SpringBootApplication.class)
                .entrySet()
                .stream().findAny()
                .ifPresentOrElse(
                        entry -> {
                            String basePackageName = entry.getValue().getClass().getPackageName();
                            log.debug("base package : {}", basePackageName);
                            Reflections reflections = new Reflections(basePackageName);
                            this.messageTable = new ConcurrentHashMap<>();

                            reflections.getSubTypesOf(ExecutableMessage.class)
                                    .stream()
                                    .forEach(cls -> {
                                        messageTable.put(cls.getSimpleName(), cls);
                                    });
                        },
                        () -> {
                            throw new IllegalStateException("No Bean found with annotation @SpringBootApplication");
                        }
                );

    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> execute(@RequestBody String payload, @RequestHeader("message-name") String messageName) {
        Class<? extends ExecutableMessage> clazz = messageTable.get(messageName);

        if(Optional.ofNullable(clazz).isEmpty()) {
            return new ResponseEntity<>(
                    new MessageResponse(new IllegalArgumentException(String.format("Message not exists with name : %s", messageName))),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            ExecutableMessage message = new ObjectMapper().readValue(payload, clazz);

            return new ResponseEntity<>(
                    message.execute(),
                    HttpStatus.OK
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new MessageResponse(e),
                    HttpStatus.BAD_REQUEST
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new MessageResponse(e),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
