package ch.bissbert.prebrocessing.file;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleNameTypeMirrorTest {

    @Test
    public void testToString() {
        SimpleNameTypeMirror typeMirror = new SimpleNameTypeMirror("java.lang.String");
        assertEquals("java.lang.String", typeMirror.toString());

        typeMirror = new SimpleNameTypeMirror("java.lang.Integer");
        assertEquals("java.lang.Integer", typeMirror.toString());
    }
}
