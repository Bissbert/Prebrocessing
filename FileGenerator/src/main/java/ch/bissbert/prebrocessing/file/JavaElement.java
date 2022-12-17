package ch.bissbert.prebrocessing.file;


import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.Set;

/**
 * An abstract class that contains the basic information for an element as well as the producing the java code for a bare element.
 *
 * @author Bissbert
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
public abstract class JavaElement implements JavaStringable {
    protected Set<Modifier> modifiers;
    protected String name;
    protected TypeMirror type;

    public boolean isStatic() {
        return modifiers.contains(Modifier.STATIC);
    }

    protected JavaElement(String name, TypeMirror type, Set<Modifier> modifiers) {
        this.modifiers = modifiers;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toJavaString() {
        Visibility visibility = Visibility.getVisibility(modifiers);
        return (!visibility.equals(Visibility.PACKAGE_PRIVATE) ? visibility.value + " " : "") + (isStatic() ? "static " : "") + (type != null ? type.toString() : "void") + " " + name;
    }

    @Override
    public String toString() {
        return "\"" + this.toJavaString() + "\"";
    }
}
