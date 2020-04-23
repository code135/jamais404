package com.jamais404.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jamais404.model.*;

import org.junit.jupiter.api.Test;

public class PageTest {

    @Test
    public void getAPageFromAUser_ReturnTrue() {
        User user = new User();
        Page page = new Page();
        page.setOwner(user);

        assertTrue(page.getOwner().equals(user));   
    }

    @Test
    public void getAllPageFromAUser_ReturnTrue() {
        User user = new User();
        Page page = new Page();
        page.setOwner(user);
        Page page1 = new Page();
        page1.setOwner(user);
        Page page2 = new Page();
        page2.setOwner(user);

        assertTrue(user.getPages().size() == 3);   
    }

    @Test
    public void getAPageFromUserNotOwner_ReturnFalse() {
        User user = new User();
        User anyone = new User();
        Page page = new Page();
        page.setOwner(anyone);
        
        assertFalse(page.getOwner().equals(user));   
    }
}