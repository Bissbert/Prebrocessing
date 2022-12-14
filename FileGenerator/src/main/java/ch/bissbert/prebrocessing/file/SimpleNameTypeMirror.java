package ch.bissbert.prebrocessing.file;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class SimpleNameTypeMirror implements TypeMirror {
    private String name;

    public SimpleNameTypeMirror(String name) {
        this.name = name;
    }

    @Override
    public TypeKind getKind() {
        return TypeKind.OTHER;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public List<? extends AnnotationMirror> getAnnotationMirrors() {
        return Collections.emptyList();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        return null;
    }

    @Override
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
        return (A[]) new Annotation[0];
    }

    @Override
    public <R, P> R accept(TypeVisitor<R, P> v, P p) {
        return null;
    }
}
