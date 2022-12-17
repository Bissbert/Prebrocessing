package ch.bissbert.prebrocessing.file.builder;


import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import ch.bissbert.prebrocessing.file.Visibility;
import ch.bissbert.prebrocessing.file.builder.method.ConstructorStringBuilder;
import ch.bissbert.prebrocessing.file.builder.method.MethodStringBuilder;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Modifier;
import java.util.*;

/**
 * A class that contains the basic information for a class as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see JavaStringable
 * @since 1.0
 */
@Getter
@Setter
public class ClassStringBuilder implements JavaStringable {
    private List<ConstructorStringBuilder> constructorStringBuilders;
    private List<AttributeStringBuilder> attributeStringBuilders;
    private List<MethodStringBuilder> methodStringBuilders;
    private String name;
    private String packageName;

    private Set<Modifier> modifiers;

    public ClassStringBuilder(
            String name,
            String packageName,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders,
            Set<Modifier> modifiers
    ) {
        List<String> errors = new ArrayList<>();

        if (modifiers.contains(Modifier.FINAL) && modifiers.contains(Modifier.ABSTRACT)) {
            errors.add("A class can't be final and abstract at the same time.");
        }


        if (modifiers.contains(Modifier.PRIVATE)) {
            errors.add("A class can't be private.");
        }

        if (modifiers.contains(Modifier.PROTECTED)) {
            errors.add("A class can't be protected.");
        }

        if (Visibility.getVisibility(modifiers) == Visibility.PACKAGE_PRIVATE && (packageName == null || packageName.isBlank())) {
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
        this.modifiers = modifiers;
        this.name = name;
        this.packageName = packageName;
    }

    public ClassStringBuilder(
            String name,
            List<ConstructorStringBuilder> constructorStringBuilders,
            List<AttributeStringBuilder> attributeStringBuilders,
            List<MethodStringBuilder> methodStringBuilders,
            Set<Modifier> modifiers
    ) {
        this(name, null, constructorStringBuilders, attributeStringBuilders, methodStringBuilders, modifiers);
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

        final String classString = (Visibility.getVisibility(modifiers).equals(Visibility.PACKAGE_PRIVATE) ? "" : Visibility.getVisibility(modifiers).value + " ")
                + (isAbstract() ? "abstract " : "")
                + (isFinal() ? "final " : "")
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

    private boolean isFinal() {
        return modifiers.contains(Modifier.FINAL);
    }

    private boolean isAbstract() {
        return modifiers.contains(Modifier.ABSTRACT);
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
