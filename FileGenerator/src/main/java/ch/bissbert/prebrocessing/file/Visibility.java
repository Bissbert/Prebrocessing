package ch.bissbert.prebrocessing.file;

/**
 * Enum for the visibility of a java element.
 *
 * @author Bissbert
 * @version 1.0
 * @since 1.0
 */
public enum Visibility {
    PRIVATE("private"),
    PROTECTED("protected"),
    PACKAGE_PRIVATE(""),
    PUBLIC("public");

    public final String value;

    Visibility(String value) {
        this.value = value;
    }
}
