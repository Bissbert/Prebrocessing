package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import ch.bissbert.prebrocessing.util.NameUtil;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.Set;

/**
 * A class that contains the basic information for a constructor as well as the producing the java code for it.
 *
 * @author Bissbert
 * @version 1.0
 * @see MethodStringBuilder
 * @see JavaElement
 * @see JavaStringable
 * @since 1.0
 */
@Getter
@Setter
public class ConstructorStringBuilder extends MethodStringBuilder {

    private static final String CONSTRUCTOR_NAME = "\\Constructor\\";

    public ConstructorStringBuilder(String className, String methodContent, Set<Modifier> modifiers, List<JavaMethodParamStringBuilder> paramBuilderList) {
        super(CONSTRUCTOR_NAME, new SimpleNameTypeMirror(className), methodContent, modifiers, paramBuilderList);
    }

    public ConstructorStringBuilder(String className, String methodContent, Set<Modifier> modifiers, JavaMethodParamStringBuilder... paramBuilderList) {
        super(CONSTRUCTOR_NAME, new SimpleNameTypeMirror(className), methodContent, modifiers, paramBuilderList);
    }

    public ConstructorStringBuilder(TypeMirror classType, String methodContent, Set<Modifier> modifiers, List<JavaMethodParamStringBuilder> paramBuilderList) {
        super(CONSTRUCTOR_NAME, classType, methodContent, modifiers, paramBuilderList);
    }

    public ConstructorStringBuilder(TypeMirror classType, String methodContent, Set<Modifier> modifiers, JavaMethodParamStringBuilder... paramBuilderList) {
        super(CONSTRUCTOR_NAME, classType, methodContent, modifiers, paramBuilderList);
    }


    /**
     * Creates the java code string for a constructor.
     *
     * @return the java code string for a constructor
     */
    @Override
    public String toJavaString() {
        return super.toJavaString().replace(CONSTRUCTOR_NAME, "").replace(type.toString() + " ", NameUtil.removeClassPackage(type.toString()));
    }
}
