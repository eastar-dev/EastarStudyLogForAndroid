package dev.eastar.studypush.di;

import dagger.Component;

@Component(modules = MyModule.class)
public interface MyComponent {
    void inject(MyClass myClass);
}
