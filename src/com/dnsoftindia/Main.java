package com.dnsoftindia;

import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Class reflectDemo = Demo.class;
        String reflectDemoName = reflectDemo.getSimpleName();
        System.out.println("Class Name: "+reflectDemoName);

        // Modifiers: isPublic, isAbstract, isFinal, isInterface, IsPrivate, isProtected
        // isStatic, isSynchronized, isStrict, isVolatile, isNative, isTransient
        int classModifier = reflectDemo.getModifiers();
        System.out.println("isPublic: "+ Modifier.isPublic(classModifier));

        // Check for interfaces implemented by a class
        Class[] interfaces = reflectDemo.getInterfaces();

        // Access super class name
        Class reflectDemoSuperClass = reflectDemo.getSuperclass();
        System.out.println("Reflect Demo Super Class: "+reflectDemoSuperClass.getName());

        // Access all class methods with their return type
        Method[] demoClassMethods = reflectDemo.getMethods();
        for (Method m: demoClassMethods) {
            System.out.println("Method Name: "+m.getName()+" Return Type: "+m.getReturnType());
            if (m.getName().startsWith("get")) {
                System.out.println("Getter method");
            }
            else if (m.getName().startsWith("set")) {
                System.out.println("Setter method");
            }

            Parameter[] parameters = m.getParameters();
            for (Parameter p: parameters) {
                System.out.println("Param Name: "+p.getName()+" Type: "+p.getType().getSimpleName());
            }

        }
        System.out.println();

        // Access class constructor
        Constructor constructor = null;
        Object constructor2 = null;
        try {
            constructor = reflectDemo.getConstructor(new Class[] { int.class, String.class});

            constructor2 = reflectDemo.getConstructor(float.class).newInstance(12.0f);
        }
        catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | InstantiationException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        // Access class constructor
        Class[] constructParams = constructor.getParameterTypes();
        for (Class p: constructParams) {
            System.out.println(p.getName());
        }
        try {
            Demo demo = (Demo) constructor.newInstance(42, "Answer to the universe");
        }
        catch (InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        Field privateFieldName = null;
        Demo privateDemo = new Demo(3.142f);
        try {
            // Access private field
            privateFieldName = Demo.class.getDeclaredField("idCode");
            privateFieldName.setAccessible(true);
            String privateFieldData = (String) privateFieldName.get(privateDemo);
            System.out.println("Private field name: "+privateFieldData);

            // Access private method
            String privateMethodName = "getPrivate";
            Method privateMethod = Demo.class.getDeclaredMethod(privateMethodName, null);
            privateMethod.setAccessible(true);
            String privateReturnValue = (String) privateMethod.invoke(privateDemo, null);
            System.out.println("Demo private method return value: "+privateReturnValue);


            // Access private method and pass on arguments
            Class[] methodParams = new Class[] {Integer.TYPE, String.class};
            Object[] params = new Object[]{new Integer(10), new String("Random String")};

            privateMethodName = "getOtherPrivate";
            privateMethod = Demo.class.getDeclaredMethod(privateMethodName, methodParams);
            privateMethod.setAccessible(true);
            privateReturnValue = (String) privateMethod.invoke(privateDemo, params);
            System.out.println("Demo another private method return value: "+privateReturnValue);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
