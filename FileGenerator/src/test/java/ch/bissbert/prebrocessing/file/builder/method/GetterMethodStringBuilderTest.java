package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import org.junit.Before;
import org.junit.Test;

import javax.lang.model.type.TypeMirror;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetterMethodStringBuilderTest {

    TypeMirror stringType;

    @Before
    public void setUp() {
        stringType = new SimpleNameTypeMirror("java.lang.String");
    }

    @Test
    public void toJavaString() {
        assertNotNull(stringType);
        GetterMethodStringBuilder getterMethodStringBuilder = new GetterMethodStringBuilder("name", stringType);
        System.out.println(
                getterMethodStringBuilder.toJavaString()
        );
        assertEquals("public java.lang.String getName(){return this.name;}", getterMethodStringBuilder.toJavaString());
    }
}
