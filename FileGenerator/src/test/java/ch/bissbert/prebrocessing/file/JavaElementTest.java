package ch.bissbert.prebrocessing.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JavaElementTest {

    private final String name;
    private final String type;
    private final Set<Modifier> modifiers;
    private JavaElement javaElement;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                // name, type, modifiers
                {"name", "java.lang.String", new HashSet<>() {{
                    add(Modifier.PUBLIC);
                }}},
                {"name", "java.lang.String", new HashSet<>() {{
                    add(Modifier.PROTECTED);
                }},},
                {"name", "java.lang.String", new HashSet<>()},
                {"name", "java.lang.String", new HashSet<>() {{
                    add(Modifier.PRIVATE);
                }},},
                {"age", "java.lang.Integer", new HashSet<>() {{
                    add(Modifier.PUBLIC);
                }},},
                {"age", "java.lang.Integer", new HashSet<>() {{
                    add(Modifier.PROTECTED);
                }},},
                {"age", "java.lang.Integer", new HashSet<>(),},
                {"age", "java.lang.Integer", new HashSet<>() {{
                    add(Modifier.PRIVATE);
                }},},
                {"name", "java.lang.String", new HashSet<>() {{
                    add(Modifier.PUBLIC);
                    add(Modifier.STATIC);
                }},},
                {"name", "java.lang.String", new HashSet<>() {{
                    add(Modifier.PROTECTED);
                    add(Modifier.STATIC);
                }},},
                {"name", "java.lang.String", new HashSet<>() {{
                    add(Modifier.STATIC);
                }},},
                {"name", "java.lang.String", new HashSet<>() {{
                    add(Modifier.PRIVATE);
                    add(Modifier.STATIC);
                }},},
                {"age", "java.lang.Integer", new HashSet<>() {{
                    add(Modifier.PUBLIC);
                    add(Modifier.STATIC);
                }},},
                {"age", "java.lang.Integer", new HashSet<>() {{
                    add(Modifier.PROTECTED);
                    add(Modifier.STATIC);
                }},},
                {"age", "java.lang.Integer", new HashSet<>() {{
                    add(Modifier.STATIC);
                }},},
                {"age", "java.lang.Integer", new HashSet<>() {{
                    add(Modifier.PRIVATE);
                    add(Modifier.STATIC);
                }},},
        };
    }

    public JavaElementTest(String name, String type, Set<Modifier> modifiers) {
        this.javaElement = new JavaElementTestImplementation(name, new SimpleNameTypeMirror(type), modifiers);
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
    }


    @Test
    public void isStatic() {
        assertEquals(this.modifiers.contains(Modifier.STATIC), this.javaElement.isStatic());
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
    public void toJavaString() {
        assertEquals(
                (!Visibility.getVisibility(modifiers).equals(Visibility.PACKAGE_PRIVATE) ? Visibility.getVisibility(modifiers).value + " " : "")
                        + (this.modifiers.contains(Modifier.STATIC) ? "static " : "")
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
        public JavaElementTestImplementation(String name, TypeMirror type, Set<Modifier> modifiers) {
            super(name, type, modifiers);
        }
    }
}
