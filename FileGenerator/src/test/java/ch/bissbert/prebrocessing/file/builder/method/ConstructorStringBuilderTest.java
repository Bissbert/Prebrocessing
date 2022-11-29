package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.ExampleClass;
import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import ch.bissbert.prebrocessing.file.Visibility;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.type.TypeMirror;

import static org.junit.Assert.assertEquals;

public class ConstructorStringBuilderTest {

    private TypeMirror stringType;

    @Before
    public void setUp() {
        stringType = new SimpleNameTypeMirror("java.lang.String");
    }

    @Test
    public void toJavaString() {
        JavaMethodParamStringBuilder paramBuilder = new JavaMethodParamStringBuilder(stringType, "name", true);
        ConstructorStringBuilder builder = new ConstructorStringBuilder(
                ExampleClass.class.getName(),
                Visibility.PUBLIC,
                "this.name = name;",
                paramBuilder
        );
        assertEquals("public ExampleClass(final java.lang.String name){this.name = name;}", builder.toJavaString());
        System.out.println(builder.toJavaString());
    }
}
