package crud.model;

import org.springframework.security.core.GrantedAuthority;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.

public enum Role implements GrantedAuthority {

    ADMIN,
    USER,
    OTHER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }

}
