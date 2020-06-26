package dev.eastar.daystudylog;

import org.junit.Test;

import dev.eastar.studypush.di.DaggerMyComponent;
import dev.eastar.studypush.di.MyClass;
import dev.eastar.studypush.di.MyComponent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DaggerTest {
    @Test
    public void testMemberInjection() {
        MyClass myClass = new MyClass();
        String str = myClass.getStr();
        assertNull("처음에 그냥 받으면 null 이다", str);

        MyComponent myComponent = DaggerMyComponent.create();
        myComponent.inject(myClass);
        str = myClass.getStr();
        assertEquals("eastar", str);
    }
}