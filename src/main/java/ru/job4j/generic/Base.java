package ru.job4j.generic;

/**
 * @author Petr Arsentev (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class Base {
    private final String id;

    @SuppressWarnings("WeakerAccess")
    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
