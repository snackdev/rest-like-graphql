package snackdev.rest.graphql.base;

public interface DILoader {
    default <T extends Object> T loadDI(Class<T> clazz) {
        return DIFactory.getBean(clazz);
    }
}
