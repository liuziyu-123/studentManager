package com.studentManager.common.Utils;

import com.studentManager.common.Entry.UserEntry;

public class LocalThread {
    private static final ThreadLocal<UserEntry> LOCAL = new ThreadLocal<>();

    private LocalThread() {
    }

    public static void set(UserEntry user) {
        LOCAL.set(user);
    }

    public static UserEntry get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}