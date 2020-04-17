package com.jamais404;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Jamais404ApplicationTests {

    @Autowired
    private HomeController controller;

	@Test
	void contextLoads() {
        assertThat(controller).isNotNull();
	}
}