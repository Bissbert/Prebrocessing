package ch.bissbert.prebrocessing.file.builder;


import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import ch.bissbert.prebrocessing.file.Visibility;
import ch.bissbert.prebrocessing.file.builder.method.ConstructorStringBuilder;
import ch.bissbert.prebrocessing.file.builder.method.MethodStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * A class that contains the basic information for a class as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see JavaStringable
 * @since 1.0
 */
public final class ClassStringBuilder implements JavaStringable {
    private final List<ConstructorStringBuilder> constructorStringBuilders;
    private final List<AttributeStringBuilder> attributeStringBuilders;
    private final List<MethodStringBuilder> methodStringBuilders;
    private final boolean isAbstract;
    private final boolean isFinal;
    private final Visibility visibility;
    private final String name;
    private final String packageName;

    public ClassStringBuilder(
            String name,
            String packageName,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders,
            boolean isAbstract,
            boolean isFinal,
            Visibility visibility
    ) {
        List<String> errors = new ArrayList<>();

        if (isFinal && isAbstract) {
            errors.add("A class can't be final and abstract at the same time.");
        }

        if (visibility == Visibility.PRIVATE) {
            errors.add("A class can't be private.");
        }

        if (visibility == Visibility.PROTECTED) {
            errors.add("A class can't be protected.");
        }

        if (visibility == Visibility.PACKAGE_PRIVATE && (packageName == null || packageName.isBlank())) {
            errors.add("A class can't be package private if it is not in a package.");
        }

        if (name == null || name.isBlank()) {
            errors.add("A class needs a name.");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(System.lineSeparator(), errors));
        }

        this.constructorStringBuilders = Objects.requireNonNullElseGet(constructorStringBuilders, ArrayList::new);
        this.attributeStringBuilders = Objects.requireNonNullElseGet(attributeStringBuilders, ArrayList::new);
        this.methodStringBuilders = Objects.requireNonNullElseGet(methodStringBuilders, ArrayList::new);
        this.isAbstract = isAbstract;
        this.isFinal = isFinal;
        this.visibility = visibility;
        this.name = name;
        this.packageName = packageName;
    }

    public ClassStringBuilder(
            String name,
            String packageName,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders,
            Visibility visibility
    ) {
        this(name, packageName, constructorStringBuilders, attributeStringBuilders, methodStringBuilders, false, false, visibility);
    }

    public ClassStringBuilder(
            String name,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders,
            boolean isAbstract,
            boolean isFinal,
            Visibility visibility
    ) {
        this(name, null, constructorStringBuilders, attributeStringBuilders, methodStringBuilders, isAbstract, isFinal, visibility);
    }

    public ClassStringBuilder(
            String name,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders,
            boolean isAbstract,
            boolean isFinal
    ) {
        this(name, null, constructorStringBuilders, attributeStringBuilders, methodStringBuilders, isAbstract, isFinal, Visibility.PUBLIC);
    }

    public ClassStringBuilder(
            String name,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders,
            boolean isAbstract
    ) {
        this(name, null, constructorStringBuilders, attributeStringBuilders, methodStringBuilders, isAbstract, false, Visibility.PUBLIC);
    }

    public ClassStringBuilder(
            String name,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders
    ) {
        this(name, null, constructorStringBuilders, attributeStringBuilders, methodStringBuilders, false, false, Visibility.PUBLIC);
    }

    public List<ConstructorStringBuilder> getConstructorStringBuilders() {
        return constructorStringBuilders;
    }

    public List<AttributeStringBuilder> getAttributeStringBuilders() {
        return attributeStringBuilders;
    }

    public List<MethodStringBuilder> getMethodStringBuilders() {
        return methodStringBuilders;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    /**
     * Creates the java code string for a class.
     *
     * @return the java code string for a class
     */
    @Override
    public String toJavaString() {
        StringJoiner sectionJoiner = new StringJoiner(System.lineSeparator() + System.lineSeparator());

        List<AttributeStringBuilder> staticAttributeStringBuilders = getStaticAttributeStringBuilders();
        List<AttributeStringBuilder> nonStaticAttributeStringBuilders = getNonStaticAttributeStringBuilders();
        List<MethodStringBuilder> staticMethodStringBuilders = getStaticMethodStringBuilders();
        List<MethodStringBuilder> nonStaticMethodStringBuilders = getNonStaticMethodStringBuilders();

        if (!staticAttributeStringBuilders.isEmpty()) {
            sectionJoiner.add(getSectionString("static attributes", staticAttributeStringBuilders));
        }
        if (!staticMethodStringBuilders.isEmpty()) {
            sectionJoiner.add(getSectionString("static methods", staticMethodStringBuilders));
        }
        if (!nonStaticAttributeStringBuilders.isEmpty()) {
            sectionJoiner.add(getSectionString("attributes", nonStaticAttributeStringBuilders));
        }
        if (!constructorStringBuilders.isEmpty()) {
            sectionJoiner.add(getSectionString("constructors", constructorStringBuilders));
        }
        if (!nonStaticMethodStringBuilders.isEmpty()) {
            sectionJoiner.add(getSectionString("methods", nonStaticMethodStringBuilders));
        }

        final String classString = (visibility.equals(Visibility.PACKAGE_PRIVATE) ? "" : visibility.value + " ")
                + (isAbstract ? "abstract " : "")
                + (isFinal ? "final " : "")
                + "class "
                + name
                + " {"
                + System.lineSeparator()
                + sectionJoiner
                + System.lineSeparator()
                + "}";

        final StringJoiner packageJoiner = new StringJoiner(System.lineSeparator() + System.lineSeparator());

        if (!packageName.isBlank()) {
            packageJoiner.add(String.format("package %s;", packageName));
        }

        packageJoiner.add(classString);

        return packageJoiner.toString();
    }

    private String getSectionString(String sectionComment, List<? extends JavaStringable> javaStringableList) {
        StringJoiner sectionJoiner = new StringJoiner(System.lineSeparator());
        sectionJoiner.add("// " + sectionComment);
        for (JavaStringable javaStringable : javaStringableList) {
            sectionJoiner.add(javaStringable.toJavaString());
        }
        return sectionJoiner.toString();
    }

    private List<AttributeStringBuilder> getNonStaticAttributeStringBuilders() {
        return attributeStringBuilders.stream()
                .filter(attributeStringBuilder -> !attributeStringBuilder.isStatic())
                .toList();
    }

    private List<AttributeStringBuilder> getStaticAttributeStringBuilders() {
        return attributeStringBuilders.stream()
                .filter(JavaElement::isStatic)
                .toList();
    }

    private List<MethodStringBuilder> getNonStaticMethodStringBuilders() {
        return methodStringBuilders.stream()
                .filter(methodStringBuilder -> !methodStringBuilder.isStatic())
                .toList();
    }

    private List<MethodStringBuilder> getStaticMethodStringBuilders() {
        return methodStringBuilders.stream()
                .filter(JavaElement::isStatic)
                .toList();
    }
}
