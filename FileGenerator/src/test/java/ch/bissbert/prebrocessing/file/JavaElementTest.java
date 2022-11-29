package ch.bissbert.prebrocessing.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.lang.model.type.TypeMirror;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JavaElementTest {

    private final boolean isStatic;
    private final String name;
    private final String type;
    private final Visibility visibility;
    private JavaElement javaElement;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                // isStatic, name, type, visibility
                {false, "name", "java.lang.String", Visibility.PUBLIC},
                {false, "name", "java.lang.String", Visibility.PROTECTED},
                {false, "name", "java.lang.String", Visibility.PACKAGE_PRIVATE},
                {false, "name", "java.lang.String", Visibility.PRIVATE},
                {false, "age", "java.lang.Integer", Visibility.PUBLIC},
                {false, "age", "java.lang.Integer", Visibility.PROTECTED},
                {false, "age", "java.lang.Integer", Visibility.PACKAGE_PRIVATE},
                {false, "age", "java.lang.Integer", Visibility.PRIVATE},
                {true, "name", "java.lang.String", Visibility.PUBLIC},
                {true, "name", "java.lang.String", Visibility.PROTECTED},
                {true, "name", "java.lang.String", Visibility.PACKAGE_PRIVATE},
                {true, "name", "java.lang.String", Visibility.PRIVATE},
                {true, "age", "java.lang.Integer", Visibility.PUBLIC},
                {true, "age", "java.lang.Integer", Visibility.PROTECTED},
                {true, "age", "java.lang.Integer", Visibility.PACKAGE_PRIVATE},
                {true, "age", "java.lang.Integer", Visibility.PRIVATE},
        };
    }

    public JavaElementTest(boolean isStatic, String name, String type, Visibility visibility) {
        this.javaElement = new JavaElementTestImplementation(name, new SimpleNameTypeMirror(type), visibility, isStatic);
        this.isStatic = isStatic;
        this.name = name;
        this.type = type;
        this.visibility = visibility;
    }


    @Test
    public void isStatic() {
        assertEquals(this.isStatic, this.javaElement.isStatic());
    }

    @Test
    public void getName() {
        assertEquals(this.name, this.javaElement.getName());
    }

    @Test
    public void getType() {
        assertEquals(this.type, this.javaElement.getType().toString());
    }

    @Test
    public void getVisibility() {
        assertEquals(this.visibility, this.javaElement.getVisibility());
    }

    @Test
    public void toJavaString() {
        assertEquals(
                (!this.visibility.equals(Visibility.PACKAGE_PRIVATE) ? this.visibility.value + " " : "")
                        + (this.isStatic ? "static " : "")
                        + (this.type != null ? this.type : "void")
                        + " "
                        + this.name,
                this.javaElement.toJavaString()
        );
    }

    @Test
    public void testToString() {
        assertEquals("\"" + this.javaElement.toJavaString() + "\"", this.javaElement.toString());
    }


    private static class JavaElementTestImplementation extends JavaElement {
        public JavaElementTestImplementation(String name, TypeMirror type, Visibility visibility, boolean isStatic) {
            super(isStatic, name, type, visibility);
        }
    }
}
