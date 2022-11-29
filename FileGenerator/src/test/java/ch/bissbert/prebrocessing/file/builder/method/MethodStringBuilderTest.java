package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import ch.bissbert.prebrocessing.file.Visibility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.lang.model.type.TypeMirror;

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
                {false, false, Visibility.PACKAGE_PRIVATE, "java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, false, Visibility.PACKAGE_PRIVATE, "java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, false, Visibility.PACKAGE_PRIVATE, "void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, false, Visibility.PACKAGE_PRIVATE, "void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
                {false, true, Visibility.PACKAGE_PRIVATE, "static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, true, Visibility.PACKAGE_PRIVATE, "static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, true, Visibility.PACKAGE_PRIVATE, "static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, true, Visibility.PACKAGE_PRIVATE, "static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
                {false, false, Visibility.PUBLIC, "public java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, false, Visibility.PUBLIC, "public java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, false, Visibility.PUBLIC, "public void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, false, Visibility.PUBLIC, "public void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
                {false, true, Visibility.PUBLIC, "public static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, true, Visibility.PUBLIC, "public static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, true, Visibility.PUBLIC, "public static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, true, Visibility.PUBLIC, "public static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
                {false, false, Visibility.PRIVATE, "private java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, false, Visibility.PRIVATE, "private java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, false, Visibility.PRIVATE, "private void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, false, Visibility.PRIVATE, "private void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
                {false, true, Visibility.PRIVATE, "private static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, true, Visibility.PRIVATE, "private static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, true, Visibility.PRIVATE, "private static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, true, Visibility.PRIVATE, "private static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
                {false, false, Visibility.PROTECTED, "protected java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, false, Visibility.PROTECTED, "protected java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, false, Visibility.PROTECTED, "protected void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, false, Visibility.PROTECTED, "protected void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
                {false, true, Visibility.PROTECTED, "protected static java.lang.String getName(){return this.name;}", "name", stringType, "getName", "return this.name;"},
                {false, true, Visibility.PROTECTED, "protected static java.lang.Integer getAge(){return this.age;}", "age", intType, "getAge", "return this.age;"},
                {true, true, Visibility.PROTECTED, "protected static void setName(java.lang.String name){return this.name;}", "name", stringType, "setName", "return this.name;"},
                {true, true, Visibility.PROTECTED, "protected static void setAge(java.lang.Integer age){return this.age;}", "age", intType, "setAge", "return this.age;"},
        };
    }

    private final boolean isSetter;
    private final String expected;
    private final String body;

    private final MethodStringBuilder methodStringBuilder;

    public MethodStringBuilderTest(boolean isSetter, boolean isStatic, Visibility visibility, String expected, String name, TypeMirror type, String methodName, String body) {
        this.isSetter = isSetter;
        this.expected = expected;
        this.body = body;

        if (isSetter) {
            methodStringBuilder = new MethodStringBuilder(isStatic, methodName, null, visibility, body, new JavaMethodParamStringBuilder(type, name, false));
        } else {
            methodStringBuilder = new MethodStringBuilder(isStatic, methodName, type, visibility, body);
        }

        System.out.printf(
                "isSetter: %s, isStatic: %s, visibility: %s, expected: %s, name: %s, type: %s, methodName: %s, body: %s%n",
                isSetter,
                isStatic,
                visibility,
                expected,
                name,
                type,
                methodName,
                body
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
