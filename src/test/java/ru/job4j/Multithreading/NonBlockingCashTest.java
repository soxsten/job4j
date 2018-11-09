package ru.job4j.Multithreading;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.Multithreading.NonBlockingCash.Base;

public class NonBlockingCashTest {

    @Test
    public void should_throw_exception_when_update() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonBlockingCash cash = new NonBlockingCash();
        cash.add(new Base(1, "first"));

        Thread thread1 = new Thread(() -> {
            try {
                Base base = cash.getById(1);
                base.value = "second";
                cash.update(base);
                throw new RuntimeException("Invalid version");
            } catch (Exception e) {
                ex.set(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Base base = cash.getById(1);
                base.value = "third";
                cash.update(base);
                throw new RuntimeException("Invalid version");
            } catch (Exception e) {
                ex.set(e);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(ex.get().getMessage(), is("Invalid version"));
    }
}
