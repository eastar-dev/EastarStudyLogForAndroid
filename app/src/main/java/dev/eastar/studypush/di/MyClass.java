package dev.eastar.studypush.di;

import javax.inject.Inject;

public class MyClass {
    @Inject
    String str;

    public String getStr() {
        return str;
    }
}
