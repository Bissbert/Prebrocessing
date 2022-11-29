package ch.bissbert.prebrocessing.file.builder.method;


import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import ch.bissbert.prebrocessing.file.Visibility;

import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;
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
sealed public class MethodStringBuilder extends JavaElement permits ConstructorStringBuilder, GetterMethodStringBuilder, SetterMethodStringBuilder {

    private final List<JavaMethodParamStringBuilder> paramBuilderList;
    private final String methodContent;

    public MethodStringBuilder(boolean isStatic, String name, TypeMirror type, Visibility visibility, String methodContent, List<JavaMethodParamStringBuilder> paramBuilderList) {
        super(isStatic, name, type, visibility);
        //check that name is not empty or null
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name must not be null or empty");
        }
        this.paramBuilderList = paramBuilderList;
        this.methodContent = methodContent;
    }

    public MethodStringBuilder(boolean isStatic, String name, TypeMirror type, Visibility visibility, String methodContent, JavaMethodParamStringBuilder... paramBuilderList) {
        this(isStatic, name, type, visibility, methodContent, new ArrayList<>(List.of(paramBuilderList)));
    }

    public MethodStringBuilder(boolean isStatic, String name, TypeMirror type, Visibility visibility, String methodContent) {
        this(isStatic, name, type, visibility, methodContent, new ArrayList<>());
    }

    public MethodStringBuilder(String name, TypeMirror type, Visibility visibility, String methodContent, List<JavaMethodParamStringBuilder> paramBuilderList) {
        this(false, name, type, visibility, methodContent, paramBuilderList);
    }

    public MethodStringBuilder(String name, TypeMirror type, Visibility visibility, String methodContent, JavaMethodParamStringBuilder... paramBuilderList) {
        this(false, name, type, visibility, methodContent, paramBuilderList);
    }

    public MethodStringBuilder(String name, TypeMirror type, Visibility visibility, String methodContent) {
        this(false, name, type, visibility, methodContent);
    }

    public List<JavaMethodParamStringBuilder> getParamBuilderList() {
        return paramBuilderList;
    }

    public String getMethodContent() {
        return methodContent;
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
