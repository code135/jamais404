package com.jamais404.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.jamais404.model.*;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void commentBelongsToUserAndPage_ReturnTrue() {
        User user = new User();
        Page page = new Page();
        page.setOwner(user);

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPage(page);

        assertTrue(page.getOwner().equals(user));   
        assertTrue(comment.getUser().equals(user));
        assertTrue(comment.getPage().equals(page));
    }

    @Test
    public void getAllCommentsFromAPage_ReturnTrue() {
        Page page = new Page();

        List<Comment> comments = new ArrayList<>();

        Comment comment1 = new Comment();
        comment1.setText(("Comment1"));
        comment1.setPage(page);
        comments.add(comment1);

        Comment comment2 = new Comment();
        comment2.setText(("Comment2"));
        comment2.setPage(page);
        comments.add(comment2);

        Comment comment3 = new Comment();
        comment3.setText(("Comment3"));
        comment3.setPage(page);
        comments.add(comment3);

        int count = 0;
        for (Comment comment : comments) {
            if (comment.getPage().equals(page)) {
                ++count;
            }
        }

        assertTrue(comments.size() == count);
    }
}