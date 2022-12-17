package ch.bissbert.prebrocessing.file;

import ch.bissbert.prebrocessing.file.builder.AttributeStringBuilder;
import ch.bissbert.prebrocessing.file.builder.ClassStringBuilder;
import ch.bissbert.prebrocessing.file.builder.method.JavaMethodParamStringBuilder;
import ch.bissbert.prebrocessing.file.builder.method.MethodStringBuilder;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class ElementUtils {
    private ElementUtils() {
    }

    /**
     * Returns an {@link TypeElement} as a {@link ClassStringBuilder}.
     *
     * @param element the element to be converted(should be a class)
     * @return the element as a {@link ClassStringBuilder}
     */
    public static ClassStringBuilder classStringBuilderFromElement(TypeElement element) {
        return null;
    }

    /**
     * Returns an {@link ExecutableElement} as a {@link MethodStringBuilder}.
     *
     * @param element the element to be converted(should be a method)
     * @return the element as a {@link MethodStringBuilder}
     */
    public static MethodStringBuilder methodStringBuilderFromElement(ExecutableElement element) {
        return null; // TODO CHANGE THE MODIFIERS OF JAVAX TO BE USED INSTEAD OF BOOL PARAMS
    }

    /**
     * Returns an {@link VariableElement} as a {@link AttributeStringBuilder}.
     *
     * @param element the element to be converted(should be a field)
     * @return the element as a {@link AttributeStringBuilder}
     */
    public static AttributeStringBuilder attributeStringBuilderFromElement(VariableElement element) {
        return null;
    }

    /**
     * Returns an {@link VariableElement} as a {@link JavaMethodParamStringBuilder}.
     *
     * @param element the element to be converted(should be a field)
     * @return the element as a {@link JavaMethodParamStringBuilder}
     */
    public static JavaMethodParamStringBuilder javaMethodParamStringBuilderFromElement(VariableElement element) {
        return null;
    }


}
