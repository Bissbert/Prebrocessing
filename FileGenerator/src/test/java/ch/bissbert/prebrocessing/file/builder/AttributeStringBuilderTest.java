package ch.bissbert.prebrocessing.file.builder;

import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import ch.bissbert.prebrocessing.file.Visibility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.lang.model.element.Modifier;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AttributeStringBuilderTest {

    private final String expectedVisibility;
    private final String type;
    private final String name;
    private final Set<Modifier> modifiers;
    private AttributeStringBuilder attributeStringBuilder;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                // expectedVisibility, type, name, modifiers
                {"public", "java.lang.String", "name", Set.of(Modifier.PUBLIC)},
                {"protected", "java.lang.String", "name", Set.of(Modifier.PROTECTED)},
                {"", "java.lang.String", "name", Set.of()},
                {"private", "java.lang.String", "name", Set.of(Modifier.PRIVATE)},
                {"public", "java.lang.Integer", "age", Set.of(Modifier.PUBLIC)},
                {"protected", "java.lang.Integer", "age", Set.of(Modifier.PROTECTED)},
                {"", "java.lang.Integer", "age", Set.of()},
                {"private", "java.lang.Integer", "age", Set.of(Modifier.PRIVATE)},
                {"public", "java.lang.String", "name", Set.of(Modifier.PUBLIC, Modifier.STATIC)},
                {"protected", "java.lang.String", "name", Set.of(Modifier.PROTECTED, Modifier.STATIC)},
                {"", "java.lang.String", "name", Set.of(Modifier.STATIC)},
                {"private", "java.lang.String", "name", Set.of(Modifier.PRIVATE, Modifier.STATIC)},
                {"public", "java.lang.Integer", "age", Set.of(Modifier.PUBLIC, Modifier.STATIC)},
                {"protected", "java.lang.Integer", "age", Set.of(Modifier.PROTECTED, Modifier.STATIC)},
                {"", "java.lang.Integer", "age", Set.of(Modifier.STATIC)},
                {"private", "java.lang.Integer", "age", Set.of(Modifier.PRIVATE, Modifier.STATIC)},
                {"public", "java.lang.String", "name", Set.of(Modifier.PUBLIC, Modifier.FINAL)},
                {"protected", "java.lang.String", "name", Set.of(Modifier.PROTECTED, Modifier.FINAL)},
                {"", "java.lang.String", "name", Set.of(Modifier.FINAL)},
                {"private", "java.lang.String", "name", Set.of(Modifier.PRIVATE, Modifier.FINAL)},
                {"public", "java.lang.Integer", "age", Set.of(Modifier.PUBLIC, Modifier.FINAL)},
                {"protected", "java.lang.Integer", "age", Set.of(Modifier.PROTECTED, Modifier.FINAL)},
                {"", "java.lang.Integer", "age", Set.of(Modifier.FINAL)},
                {"private", "java.lang.Integer", "age", Set.of(Modifier.PRIVATE, Modifier.FINAL)},
                {"public", "java.lang.String", "name", Set.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)},
                {"protected", "java.lang.String", "name", Set.of(Modifier.PROTECTED, Modifier.STATIC, Modifier.FINAL)},
                {"", "java.lang.String", "name", Set.of(Modifier.STATIC, Modifier.FINAL)},
                {"private", "java.lang.String", "name", Set.of(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)},
                {"public", "java.lang.Integer", "age", Set.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)},
                {"protected", "java.lang.Integer", "age", Set.of(Modifier.PROTECTED, Modifier.STATIC, Modifier.FINAL)},
                {"", "java.lang.Integer", "age", Set.of(Modifier.STATIC, Modifier.FINAL)},
                {"private", "java.lang.Integer", "age", Set.of(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)}
        };
    }

    public AttributeStringBuilderTest(String expectedVisibility, String type, String name, Set<Modifier> modifiers) {
        this.expectedVisibility = expectedVisibility;
        this.type = type;
        this.name = name;
        this.modifiers = modifiers;

        System.out.printf(
                "Testing: %ntype=%s, %nname=%s, %nmodifiers=%s, %nexpectedVisibility=%s%n",
                type,
                name,
                modifiers,
                expectedVisibility
        );
        attributeStringBuilder = new AttributeStringBuilder(
                name,
                new SimpleNameTypeMirror(type),
                modifiers
        );

    }

    @Test
    public void toJavaString() {
        assertEquals(
                (modifiers.contains(Modifier.FINAL) ? "final " : "")
                        + expectedVisibility
                        + (Visibility.getVisibility(modifiers).equals(Visibility.PACKAGE_PRIVATE) ? "" : " ")
                        + (modifiers.contains(Modifier.STATIC) ? "static " : "")
                        + type
                        + " "
                        + name
                        + ";",
                attributeStringBuilder.toJavaString()
        );
        System.out.println("Result:\n" + attributeStringBuilder.toJavaString());
    }

    @Test
    public void isFinal() {
        assertEquals(modifiers.contains(Modifier.FINAL), attributeStringBuilder.isFinal());
    }
}
