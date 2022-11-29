package ch.bissbert.prebrocessing.file.builder;


import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.Visibility;

import javax.lang.model.type.TypeMirror;

/**
 * A class that contains the basic information for an attribute as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see JavaElement
 * @since 1.0
 */
final public class AttributeStringBuilder extends JavaElement {

    final boolean isFinal;

    public AttributeStringBuilder(boolean isStatic, boolean isFinal, String name, TypeMirror type, Visibility visibility) {
        super(isStatic, name, type, visibility);
        this.isFinal = isFinal;

        //check that name is not empty or null or that type is null
        if (name == null || name.isEmpty() || type == null) {
            throw new IllegalArgumentException("name and type must not be null or empty");
        }
    }

    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Creates the java code string for a method.
     *
     * @return the java code string for a method
     */
    @Override
    public String toJavaString() {
        return (this.isFinal ? "final " : "") + super.toJavaString() + ";";
    }
}
