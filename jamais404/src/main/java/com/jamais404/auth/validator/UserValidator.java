package com.jamais404.auth.validator;

import com.jamais404.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jamais404.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String NOT_EMPTY_STRING = "NotEmpty";
    private static final String EMAIL_STRING = "email";
    private static final String USERNAME_STRING = "username";

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        
        // Email
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, EMAIL_STRING, NOT_EMPTY_STRING);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail());
        if (! matcher.find()) {
            errors.rejectValue(EMAIL_STRING, "Not a valid email address.");
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue(EMAIL_STRING, "Duplicate.userForm.email");
        }

        // Username
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME_STRING, NOT_EMPTY_STRING);
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue(USERNAME_STRING, "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue(USERNAME_STRING, "Duplicate.userForm.username");
        }

        // Password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", NOT_EMPTY_STRING);
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}