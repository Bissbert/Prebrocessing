package ch.bissbert.prebrocessing.file.builder.method;


import ch.bissbert.prebrocessing.file.JavaStringable;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.Set;

/**
 * A class that contains the basic information for a parameter for a method as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see JavaStringable
 * @since 1.0
 */
@Getter
@Setter
public class JavaMethodParamStringBuilder implements JavaStringable {
    private TypeMirror type;
    private String name;
    private boolean isFinal;

    /**
     * Creates a new instance of {@link JavaMethodParamStringBuilder}.
     * @param type the type of the parameter
     * @param name the name of the parameter
     * @param isFinal whether the parameter is final
     */
    public JavaMethodParamStringBuilder(TypeMirror type, String name, boolean isFinal) {
        this.type = type;
        this.name = name;
        this.isFinal = isFinal;
    }

    public JavaMethodParamStringBuilder(TypeMirror type, String name, Set<Modifier> modifiers) {
        this.type = type;
        this.name = name;
        this.isFinal = modifiers.contains(Modifier.FINAL);
    }

    /**
     * Creates a new instance of {@link JavaMethodParamStringBuilder} with the parameter not being final.
     * @param type the type of the parameter
     * @param name the name of the parameter
     */
    public JavaMethodParamStringBuilder(TypeMirror type, String name) {
        this(type, name, false);
    }

    /**
     * Creates the java code string for a parameter.
     *
     * @return the java code string for a parameter
     */
    @Override
    public String toJavaString() {
        return (isFinal ? "final " : "") + type.toString() + " " + name;
    }
}
