package ch.bissbert.prebrocessing.file.builder.method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.lang.model.type.TypeMirror;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SetterMethodStringBuilderTest {

    @Mock
    private TypeMirror stringType;

    @Before
    public void setUp() throws Exception {
        Mockito.when(stringType.toString()).thenReturn("java.lang.String");
    }

    @Test
    public void toJavaString() {
        assertNotNull(stringType);
        SetterMethodStringBuilder setterMethodStringBuilder = new SetterMethodStringBuilder("name", stringType);
        System.out.println(
                setterMethodStringBuilder.toJavaString()
        );
        assertEquals("public void setName(java.lang.String name){this.name = name;}", setterMethodStringBuilder.toJavaString());
    }
}
