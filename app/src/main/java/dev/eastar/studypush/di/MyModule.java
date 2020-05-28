package dev.eastar.studypush.di;

import androidx.annotation.Nullable;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

    @Provides
    String provideName() {
        return "eastar";
    }
}
