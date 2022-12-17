package ch.bissbert.prebrocessing.file.builder;


import ch.bissbert.prebrocessing.file.JavaElement;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.Set;

/**
 * A class that contains the basic information for an attribute as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see JavaElement
 * @since 1.0
 */
@Getter
@Setter
public class AttributeStringBuilder extends JavaElement {
    public AttributeStringBuilder(String name, TypeMirror type, Set<Modifier> modifiers) {
        super(name, type, modifiers);

        //check that name is not empty or null or that type is null
        if (name == null || name.isEmpty() || type == null) {
            throw new IllegalArgumentException("name and type must not be null or empty");
        }
    }


    public boolean isFinal() {
        return modifiers.contains(Modifier.FINAL);
    }


    /**
     * Creates the java code string for a method.
     *
     * @return the java code string for a method
     */
    @Override
    public String toJavaString() {
        return (this.isFinal() ? "final " : "") + super.toJavaString() + ";";
    }
}
