package ch.bissbert.prebrocessing.file;


import javax.lang.model.type.TypeMirror;

/**
 * An abstract class that contains the basic information for an element as well as the producing the java code for a bare element.
 *
 * @author Bissbert
 * @version 1.0
 * @since 1.0
 */
public abstract class JavaElement implements JavaStringable {
    protected final boolean isStatic;
    protected final String name;
    protected final TypeMirror type;
    protected final Visibility visibility;

    public boolean isStatic() {
        return isStatic;
    }

    public JavaElement(boolean isStatic, String name, TypeMirror type, Visibility visibility) {
        //check the visibility is not null
        if (visibility == null) {
            throw new IllegalArgumentException("visibility must not be null");
        }

        this.isStatic = isStatic;
        this.name = name;
        this.type = type;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public TypeMirror getType() {
        return type;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    @Override
    public String toJavaString() {
        return (!visibility.equals(Visibility.PACKAGE_PRIVATE) ? visibility.value + " " : "") + (isStatic ? "static " : "") + (type != null ? type.toString() : "void") + " " + name;
    }

    @Override
    public String toString() {
        return "\"" + this.toJavaString() + "\"";
    }
}
