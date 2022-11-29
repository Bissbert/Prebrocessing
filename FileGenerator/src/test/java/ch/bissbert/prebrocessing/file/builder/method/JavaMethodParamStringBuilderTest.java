package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.lang.model.type.TypeMirror;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JavaMethodParamStringBuilderTest {

    private final TypeMirror type;
    private final String name;
    private final boolean isFinal;

    private final JavaMethodParamStringBuilder javaMethodParamStringBuilder;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                // type, name, isFinal
                {"java.lang.String", "name", false},
                {"java.lang.String", "name", true},
                {"java.lang.Integer", "age", false},
                {"java.lang.Integer", "age", true},
        };
    }

    public JavaMethodParamStringBuilderTest(String type, String name, boolean isFinal) {
        this.type = new SimpleNameTypeMirror(type);
        this.name = name;
        this.isFinal = isFinal;

        this.javaMethodParamStringBuilder = new JavaMethodParamStringBuilder(this.type, this.name, this.isFinal);
    }

    @Test
    public void toJavaString() {
        assertEquals((isFinal ? "final " : "") + this.type.toString() + " " + this.name, this.javaMethodParamStringBuilder.toJavaString());
        System.out.println(javaMethodParamStringBuilder.toJavaString());
    }

    @Test
    public void getType() {
        assertEquals(this.type, this.javaMethodParamStringBuilder.getType());
    }

    @Test
    public void getName() {
        assertEquals(this.name, this.javaMethodParamStringBuilder.getName());
    }

    @Test
    public void isFinal() {
        assertEquals(this.isFinal, this.javaMethodParamStringBuilder.isFinal());
    }
}
