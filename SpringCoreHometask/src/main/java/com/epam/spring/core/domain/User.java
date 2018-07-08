package com.epam.spring.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@ToString(callSuper = true)
public class User extends BaseDomainObject {

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private Set<Ticket> tickets = new HashSet<>();

    @Getter
    @Setter
    private LocalDate birthday;

    public static User create(
            String firstName,
            String lastName,
            String email,
            LocalDate birthday) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthday(birthday);
        return user;
    }
}
