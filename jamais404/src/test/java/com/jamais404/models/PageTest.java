package com.jamais404.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.jamais404.auth.repository.PageRepository;
import com.jamais404.model.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        user.setEmail("test@test.com");
        user.setUsername("testuser");
        user.setPassword("testtest");

        List<Page> pages = new ArrayList<>();

        Page page1 = new Page();
        page1.setName("Page1");
        page1.setOwner(user);
        pages.add(page1);

        Page page2 = new Page();
        page2.setName("Page2");
        page2.setOwner(user);
        pages.add(page2);

        Page page3 = new Page();
        page3.setName("Page3");
        page3.setOwner(user);
        pages.add(page3);

        int count = 0;
        for (Page page : pages) {
            if (page.getOwner().equals(user)) {
                ++count;
            }
        }

        assertTrue(pages.size() == count);
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