package ru.job4j.Multithreading;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static ru.job4j.Multithreading.UserStorage.User;

public class UserStorageTest {

    @Test
    public void should_transfer_correctly() throws InterruptedException {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 200));
        ThreadUser thread1 = new ThreadUser(storage);
        ThreadUser thread2 = new ThreadUser(storage);
        thread1.run();
        thread2.run();
        thread1.join();
        thread2.join();
        User firstUser = storage.getUserBy(1);
        User secondUser = storage.getUserBy(2);
        Assert.assertThat(firstUser.getAmount(), is(0));
        Assert.assertThat(secondUser.getAmount(), is(300));
    }

    private class ThreadUser extends Thread {
        private UserStorage storage;

        private ThreadUser(UserStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            storage.transfer(1, 2, 50);
        }
    }
}
