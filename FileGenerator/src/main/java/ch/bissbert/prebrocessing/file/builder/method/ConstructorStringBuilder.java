package ch.bissbert.prebrocessing.file.builder.method;

import ch.bissbert.prebrocessing.file.JavaElement;
import ch.bissbert.prebrocessing.file.JavaStringable;
import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import ch.bissbert.prebrocessing.file.Visibility;
import ch.bissbert.prebrocessing.util.NameUtil;

import javax.lang.model.type.TypeMirror;
import java.util.List;

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
final public class ConstructorStringBuilder extends MethodStringBuilder {

    private final static String CONSTRUCTOR_NAME = "\\Constructor\\";

    public ConstructorStringBuilder(String className, Visibility visibility, String methodContent, List<JavaMethodParamStringBuilder> paramBuilderList) {
        super(false, CONSTRUCTOR_NAME, new SimpleNameTypeMirror(className), visibility, methodContent, paramBuilderList);
    }

    public ConstructorStringBuilder(String className, Visibility visibility, String methodContent, JavaMethodParamStringBuilder... paramBuilderList) {
        super(false, CONSTRUCTOR_NAME, new SimpleNameTypeMirror(className), visibility, methodContent, paramBuilderList);
    }

    public ConstructorStringBuilder(TypeMirror classType, Visibility visibility, String methodContent, List<JavaMethodParamStringBuilder> paramBuilderList) {
        super(false, CONSTRUCTOR_NAME, classType, visibility, methodContent, paramBuilderList);
    }

    public ConstructorStringBuilder(TypeMirror classType, Visibility visibility, String methodContent, JavaMethodParamStringBuilder... paramBuilderList) {
        super(false, CONSTRUCTOR_NAME, classType, visibility, methodContent, paramBuilderList);
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
