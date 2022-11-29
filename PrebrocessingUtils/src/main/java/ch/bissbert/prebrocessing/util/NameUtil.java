package ch.bissbert.prebrocessing.util;

public class NameUtil {
    private NameUtil() {
    }

    public static String getBuilderClassName(String className) {
        return className + "Builder";
    }

    public static String getSetterNameFromAttribute(String propertyName) {
        return "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    public static String getAttributeNameFromSetter(String setterName) {
        return setterName.substring(3, 4).toLowerCase() + setterName.substring(4);
    }

    public static String getGetterNameFromAttribute(String propertyName) {
        return "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    public static String getAttributeNameFromGetter(String getterName) {
        return getterName.substring(3, 4).toLowerCase() + getterName.substring(4);
    }

    public static String removeClassPackage(String className) {
        return className.substring(className.lastIndexOf(".") + 1);
    }
}
