package ch.bissbert.prebrocessing.file.builder.method;


import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * A class that contains the basic information for a method as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see JavaElement
 * @see JavaStringable
 * @since 1.0
 */
@Getter
@Setter
public class MethodStringBuilder extends JavaElement {

    private List<JavaMethodParamStringBuilder> paramBuilderList;
    private String methodContent;

    public MethodStringBuilder(String name, TypeMirror type, String methodContent, Set<Modifier> modifiers, List<JavaMethodParamStringBuilder> paramBuilderList) {
        super(name, type, modifiers);
        //check that name is not empty or null
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name must not be null or empty");
        }
        this.paramBuilderList = paramBuilderList;
        this.methodContent = methodContent;
    }

    public MethodStringBuilder(String name, TypeMirror type, String methodContent, Set<Modifier> modifiers, JavaMethodParamStringBuilder... paramBuilderList) {
        this(name, type, methodContent, modifiers, new ArrayList<>(List.of(paramBuilderList)));
    }

    public MethodStringBuilder(String name, TypeMirror type, String methodContent, Set<Modifier> modifiers) {
        this(name, type, methodContent, modifiers, new ArrayList<>());
    }

    @Override
    public String toJavaString() {
        return super.toJavaString() + "(" + generateParams() + "){" + methodContent + "}";
    }

    private String generateParams() {
        StringJoiner paramJoiner = new StringJoiner(", ");
        for (JavaMethodParamStringBuilder paramStringBuilder : paramBuilderList) {
            paramJoiner.add(paramStringBuilder.toJavaString());
        }
        return paramJoiner.toString();
    }
}
