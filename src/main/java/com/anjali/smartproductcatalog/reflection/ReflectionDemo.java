package com.anjali.smartproductcatalog.reflection;

import com.anjali.smartproductcatalog.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.lang.reflect.Field;

public class ReflectionDemo {

    public static void main(String[] args) throws Exception {

        // Get Class Object
        Class<User> userClass = User.class;

        // Create User Object
        User user = new User();
        user.setName("Anjali");
        user.setEmail("anjali@gmail.com");
        user.setAge(22);

        // Read Private Field using Reflection
        Field nameField = userClass.getDeclaredField("name");
        nameField.setAccessible(true);

        System.out.println("Name = " + nameField.get(user));
        System.out.println("Before Change : " + nameField.get(user));

        nameField.set(user, "Rahul");

        System.out.println("After Change : " + nameField.get(user));

        // Check @Entity Annotation
        if (userClass.isAnnotationPresent(Entity.class)) {
            System.out.println("Entity Present : Yes");
        } else {
            System.out.println("Entity Present : No");
        }

        // Read @Table Annotation
        Table table = userClass.getAnnotation(Table.class);

        if (table != null) {
            System.out.println("Table Name : " + table.name());
        }

        // Print All Fields
        System.out.println("\nAll Fields:");

        Field[] fields = userClass.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}