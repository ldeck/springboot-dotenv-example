package com.example.testenv;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HelloController.class)
public class HelloControllerTest {

    @Autowired
    HelloController controller;

    @Test
    public void shouldLoadEnv() {
        Dotenv dotJava = Dotenv.load();
        assertThat(controller.hello()).isEqualTo(dotJava.get("HELLO_WORLD"));
    }
}