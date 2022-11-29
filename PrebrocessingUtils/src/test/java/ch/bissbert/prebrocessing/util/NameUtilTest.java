package ch.bissbert.prebrocessing.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameUtilTest {
    @Test
    public void getBuilderClassName() {
        assertEquals("ExampleClassBuilder", NameUtil.getBuilderClassName("ExampleClass"));
    }

    @Test
    public void getSetterNameFromAttribute() {
        assertEquals("setName", NameUtil.getSetterNameFromAttribute("name"));
    }

    @Test
    public void getAttributeNameFromSetter() {
        assertEquals("name", NameUtil.getAttributeNameFromSetter("setName"));
    }

    @Test
    public void getGetterNameFromAttribute() {
        assertEquals("getName", NameUtil.getGetterNameFromAttribute("name"));
    }

    @Test
    public void getAttributeNameFromGetter() {
        assertEquals("name", NameUtil.getAttributeNameFromGetter("getName"));
    }

    @Test
    public void removeClassPackage() {
        assertEquals("ExampleClass", NameUtil.removeClassPackage("ch.bissbert.simpleBuilderPreprocessor.ExampleClass"));
    }
}
