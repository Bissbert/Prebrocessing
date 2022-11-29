package ch.bissbert.prebrocessing.file;

/**
 * Interface for all classes that can be converted to a java code string.
 */
public interface JavaStringable {
    /**
     * Creates the java code string for given implementation.
     *
     * @return the java code string
     */
    String toJavaString();
}
