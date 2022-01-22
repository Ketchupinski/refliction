import java.util.*;
import java.lang.reflect.*;

/**
 * This class using reflection methods output all information about class which was inputted by user
 * @author Arsen
 * @version 1.0
 */

public class ReflectionTest {
    public static void main(String... args) throws ClassNotFoundException {
        String name;

        // getting class name from command line arguments or
        // arguments that were inputted by user
        if (args.length > 0) {
            name = args[0];
        }
        else {
            var in = new Scanner(System.in);
            System.out.println("Enter class name " +
                    " (e.g java.util.Date): ");
            name = in.next();
        }

        Class cl = Class.forName(name);
        Class supercl = cl.getSuperclass();
        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length() > 0) {
            System.out.print(modifiers + " ");
        }

        System.out.print("class " + name);
        if (supercl != null && supercl != Object.class)
            System.out.print(" extends "
                            + supercl.getName());

        System.out.print("\n{\n");
        printConstructors(cl);
        System.out.println();
        printMethods(cl);
        System.out.println();
        printFields(cl);
        System.out.println();
    }

    /**
     * Output all constructors of the class
     * @param cl class
     */
    public static void printConstructors(Class cl) {
        Constructor[] constructors =
                cl.getDeclaredConstructors();
        for (Constructor c: constructors) {
            String name = cl.getName();
            System.out.print(" ");
            String modiflers = Modifier.toString(c.getModifiers());
            if (modiflers.length() > 0) {
                System.out.print(modiflers + " ");
            }
            System.out.print(name + "(");

            // output type of parameters
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++)
            {
                if (i > 0) {
                    System.out.print(", ");
                }
                    System.out.print(paramTypes[i].getName());
            }
                System.out.println(");");
        }
    }

    /**
     * Output all methods of the class
     * @param cl class
     */
    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print(" ");

            // output modifiers, returning type and method name
            String modifiers = Modifier.toString(m.getModifiers());

            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " "
                                + name + "(");

            // output type of parameters
            Class[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * Outputs all fields
     * @param cl class
     */
    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for(Field f : fields)
        {
            Class type = f.getType();
            String name = f.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(f.getModifiers());

            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }

            System.out.println(type.getName() + " " + name + ";");
        }
    }

}
