package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import ch.bissbert.prebrocessing.file.Visibility;

import javax.lang.model.type.TypeMirror;

/**
 * A class that contains the basic information for a getter as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see MethodStringBuilder
 * @see JavaElement
 * @see JavaStringable
 * @since 1.0
 */
final public class GetterMethodStringBuilder extends MethodStringBuilder {
    public GetterMethodStringBuilder(String name, TypeMirror type) {
        super(false, generateGetterName(name), type, Visibility.PUBLIC, generateGetterText(name));
    }

    private static String generateGetterName(final String name) {
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);

    }

    private static String generateGetterText(final String name) {
        return "return this." + name + ";";
    }
}
