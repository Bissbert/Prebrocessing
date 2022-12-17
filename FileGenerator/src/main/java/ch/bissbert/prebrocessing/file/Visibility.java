package ch.bissbert.prebrocessing.file;

import javax.lang.model.element.Modifier;
import java.util.Set;

/**
 * Enum for the visibility of a java element.
 *
 * @author Bissbert
 * @version 1.0
 * @since 1.0
 */
public enum Visibility {
    PRIVATE("private", Modifier.PRIVATE),
    PROTECTED("protected", Modifier.PROTECTED),
    PACKAGE_PRIVATE("", null),
    PUBLIC("public", Modifier.PUBLIC);

    public final String value;
    private final Modifier modifier;

    Visibility(String value, Modifier modifier) {
        this.value = value;
        this.modifier = modifier;
    }

    /**
     * Returns the visibility of an element.
     *
     * @param modifiers the modifiers of the element
     * @return the visibility of the element
     */
    public static Visibility getVisibility(Set<Modifier> modifiers) {
        if (modifiers.contains(Modifier.PUBLIC)) {
            return Visibility.PUBLIC;
        } else if (modifiers.contains(Modifier.PROTECTED)) {
            return Visibility.PROTECTED;
        } else if (modifiers.contains(Modifier.PRIVATE)) {
            return Visibility.PRIVATE;
        } else {
            return Visibility.PACKAGE_PRIVATE;
        }
    }

    public Modifier toModifier() {
        return this.modifier;
    }
}
