package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import ch.bissbert.prebrocessing.file.Visibility;

import javax.lang.model.type.TypeMirror;

/**
 * A class that contains the basic information for a setter as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see JavaElement
 * @see JavaStringable
 * @since 1.0
 */
final public class SetterMethodStringBuilder extends MethodStringBuilder {
    public SetterMethodStringBuilder(String name, TypeMirror type) {
        super(false, generateSetterName(name), null, Visibility.PUBLIC, generateSetterText(name), generateParam(name, type));
    }

    private static String generateSetterName(final String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

    }

    private static String generateSetterText(final String name) {
        return "this." + name + " = " + name + ";";
    }

    private static JavaMethodParamStringBuilder generateParam(final String name, final TypeMirror type) {
        return new JavaMethodParamStringBuilder(type, name, false);
    }
}
