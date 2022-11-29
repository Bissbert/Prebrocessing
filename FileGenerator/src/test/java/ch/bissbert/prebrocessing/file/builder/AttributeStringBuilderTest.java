package ch.bissbert.prebrocessing.file.builder;

import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import ch.bissbert.prebrocessing.file.Visibility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AttributeStringBuilderTest {

    private final boolean isStatic;
    private final Visibility visibility;
    private final String expectedVisibility;
    private final String type;
    private final String name;
    private final boolean isFinal;
    private AttributeStringBuilder attributeStringBuilder;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                // isFinal, isStatic, visibility, expectedVisibility, type, name
                {false, false, Visibility.PUBLIC, "public", "java.lang.String", "name"},
                {false, false, Visibility.PROTECTED, "protected", "java.lang.String", "name"},
                {false, false, Visibility.PACKAGE_PRIVATE, "", "java.lang.String", "name"},
                {false, false, Visibility.PRIVATE, "private", "java.lang.String", "name"},
                {false, false, Visibility.PUBLIC, "public", "java.lang.Integer", "age"},
                {false, false, Visibility.PROTECTED, "protected", "java.lang.Integer", "age"},
                {false, false, Visibility.PACKAGE_PRIVATE, "", "java.lang.Integer", "age"},
                {false, false, Visibility.PRIVATE, "private", "java.lang.Integer", "age"},
                {false, true, Visibility.PUBLIC, "public", "java.lang.String", "name"},
                {false, true, Visibility.PROTECTED, "protected", "java.lang.String", "name"},
                {false, true, Visibility.PACKAGE_PRIVATE, "", "java.lang.String", "name"},
                {false, true, Visibility.PRIVATE, "private", "java.lang.String", "name"},
                {false, true, Visibility.PUBLIC, "public", "java.lang.Integer", "age"},
                {false, true, Visibility.PROTECTED, "protected", "java.lang.Integer", "age"},
                {false, true, Visibility.PACKAGE_PRIVATE, "", "java.lang.Integer", "age"},
                {false, true, Visibility.PRIVATE, "private", "java.lang.Integer", "age"},
                {true, false, Visibility.PUBLIC, "public", "java.lang.String", "name"},
                {true, false, Visibility.PROTECTED, "protected", "java.lang.String", "name"},
                {true, false, Visibility.PACKAGE_PRIVATE, "", "java.lang.String", "name"},
                {true, false, Visibility.PRIVATE, "private", "java.lang.String", "name"},
                {true, false, Visibility.PUBLIC, "public", "java.lang.Integer", "age"},
                {true, false, Visibility.PROTECTED, "protected", "java.lang.Integer", "age"},
                {true, false, Visibility.PACKAGE_PRIVATE, "", "java.lang.Integer", "age"},
                {true, false, Visibility.PRIVATE, "private", "java.lang.Integer", "age"},
                {true, true, Visibility.PUBLIC, "public", "java.lang.String", "name"},
                {true, true, Visibility.PROTECTED, "protected", "java.lang.String", "name"},
                {true, true, Visibility.PACKAGE_PRIVATE, "", "java.lang.String", "name"},
                {true, true, Visibility.PRIVATE, "private", "java.lang.String", "name"},
                {true, true, Visibility.PUBLIC, "public", "java.lang.Integer", "age"},
                {true, true, Visibility.PROTECTED, "protected", "java.lang.Integer", "age"},
                {true, true, Visibility.PACKAGE_PRIVATE, "", "java.lang.Integer", "age"},
                {true, true, Visibility.PRIVATE, "private", "java.lang.Integer", "age"},
        };
    }

    public AttributeStringBuilderTest(boolean isFinal, boolean isStatic, Visibility visibility, String expectedVisibility, String type, String name) {
        this.isFinal = isFinal;
        this.isStatic = isStatic;
        this.visibility = visibility;
        this.expectedVisibility = expectedVisibility;
        this.type = type;
        this.name = name;

        System.out.printf(
                "Testing: %nisFinal=%s, %nisStatic=%s, %nvisibility=%s, %ntype=%s, %nname=%s%n",
                isFinal,
                isStatic,
                visibility,
                type,
                name
        );
        attributeStringBuilder = new AttributeStringBuilder(
                isStatic,
                isFinal,
                name,
                new SimpleNameTypeMirror(type),
                visibility
        );

    }

    @Test
    public void toJavaString() {
        assertEquals(
                (isFinal ? "final " : "")
                        + expectedVisibility
                        + (visibility.equals(Visibility.PACKAGE_PRIVATE) ? "" : " ")
                        + (isStatic ? "static " : "")
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
        assertEquals(isFinal, attributeStringBuilder.isFinal());
    }
}
