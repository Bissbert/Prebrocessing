package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MethodStringBuilderTest {
    @Before
    public void setUp() throws Exception {
        System.out.println();
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        TypeMirror stringType = new SimpleNameTypeMirror("java.lang.String");
        TypeMirror intType = new SimpleNameTypeMirror("java.lang.Integer");
        return new Object[][]{
                {false, "java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of()},
                {false, "java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of()},
                {true, "void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of()},
                {true, "void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of()},
                {false, "static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of(Modifier.STATIC)},
                {false, "static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of(Modifier.STATIC)},
                {true, "static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of(Modifier.STATIC)},
                {true, "static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of(Modifier.STATIC)},
                {false, "public java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of(Modifier.PUBLIC)},
                {false, "public java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of(Modifier.PUBLIC)},
                {true, "public void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of(Modifier.PUBLIC)},
                {true, "public void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of(Modifier.PUBLIC)},
                {false, "public static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of(Modifier.PUBLIC, Modifier.STATIC)},
                {false, "public static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of(Modifier.PUBLIC, Modifier.STATIC)},
                {true, "public static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of(Modifier.PUBLIC, Modifier.STATIC)},
                {true, "public static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of(Modifier.PUBLIC, Modifier.STATIC)},
                {false, "private java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of(Modifier.PRIVATE)},
                {false, "private java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of(Modifier.PRIVATE)},
                {true, "private void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of(Modifier.PRIVATE)},
                {true, "private void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of(Modifier.PRIVATE)},
                {false, "private static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of(Modifier.PRIVATE, Modifier.STATIC)},
                {false, "private static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of(Modifier.PRIVATE, Modifier.STATIC)},
                {true, "private static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of(Modifier.PRIVATE, Modifier.STATIC)},
                {true, "private static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of(Modifier.PRIVATE, Modifier.STATIC)},
                {false, "protected java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of(Modifier.PROTECTED)},
                {false, "protected java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of(Modifier.PROTECTED)},
                {true, "protected void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of(Modifier.PROTECTED)},
                {true, "protected void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of(Modifier.PROTECTED)},
                {false, "protected static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;", Set.of(Modifier.PROTECTED, Modifier.STATIC)},
                {false, "protected static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;", Set.of(Modifier.PROTECTED, Modifier.STATIC)},
                {true, "protected static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;", Set.of(Modifier.PROTECTED, Modifier.STATIC)},
                {true, "protected static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;", Set.of(Modifier.PROTECTED, Modifier.STATIC)},
        };
    }

    private final boolean isSetter;
    private final String expected;
    private final String body;

    private final MethodStringBuilder methodStringBuilder;

    public MethodStringBuilderTest(boolean isSetter, String expected, String name, TypeMirror type, String methodName, String body, Set<Modifier> modifiers) {
        this.isSetter = isSetter;
        this.expected = expected;
        this.body = body;

        if (isSetter) {
            methodStringBuilder = new MethodStringBuilder(methodName, null, body, modifiers, new JavaMethodParamStringBuilder(type, name));
        } else {
            methodStringBuilder = new MethodStringBuilder(methodName, type, body, modifiers);
        }

        System.out.printf(
                "isSetter: %s,%nexpected: %s,%nname: %s,%n type: %s,%nmethodName: %s,%nbody: %s,%nmodifiers: %s%n",
                isSetter,
                expected,
                name,
                type,
                methodName,
                body,
                modifiers
        );

    }

    @Test
    public void toJavaString() {
        assertEquals(expected, methodStringBuilder.toJavaString());
        System.out.println("Result\n:" + methodStringBuilder.toJavaString());
    }

    @Test
    public void getParamBuilderList() {
        if (isSetter) {
            assertEquals(1, methodStringBuilder.getParamBuilderList().size());
        } else {
            assertEquals(0, methodStringBuilder.getParamBuilderList().size());
        }
    }

    @Test
    public void getMethodContent() {
        assertEquals(body, methodStringBuilder.getMethodContent());
    }
}
