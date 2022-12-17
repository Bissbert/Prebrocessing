package ch.bissbert.prebrocessing.file.builder;

import ch.bissbert.prebrocessing.file.SimpleNameTypeMirror;
import ch.bissbert.prebrocessing.file.Visibility;
import ch.bissbert.prebrocessing.file.builder.method.ConstructorStringBuilder;
import ch.bissbert.prebrocessing.file.builder.method.MethodStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ClassStringBuilderTest {

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                { // public
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "\n" +
                                "}"
                },
                { // package private
                        Visibility.PACKAGE_PRIVATE,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "class Person {\n" +
                                "\n" +
                                "}"
                },
                { // final public
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        true, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public final class Person {\n" +
                                "\n" +
                                "}"
                },
                { // abstract public
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        true, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public abstract class Person {\n" +
                                "\n" +
                                "}"
                },
                { // public with constructor
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>() {
                            {
                                add(new ConstructorStringBuilder(
                                        "Person",
                                        "",
                                        new HashSet<>() {
                                            {
                                                add(Modifier.PUBLIC);
                                            }
                                        }
                                ));
                            }
                        },
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// constructors\n" +
                                "public Person(){" +
                                "}\n" +
                                "}"
                },
                { // public with method
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getName",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        "",
                                        new HashSet<Modifier>() {{
                                            add(Modifier.PUBLIC);
                                        }})
                                );
                            }
                        },
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// methods\n" +
                                "public java.lang.String getName(){" +
                                "}\n" +
                                "}"
                },
                { // public with attribute
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// attributes\n" +
                                "private java.lang.String name;\n" +
                                "}"
                },
                { // public with static method
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getName",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static methods\n" +
                                "public static java.lang.String getName(){" +
                                "}\n" +
                                "}"
                },
                { // public with static attribute
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static attributes\n" +
                                "private static java.lang.String name;\n" +
                                "}"
                },
                { // public with static final attribute
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.FINAL);
                                            add(Modifier.STATIC);
                                            add(Modifier.PRIVATE);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static attributes\n" +
                                "final private static java.lang.String name;\n" +
                                "}"
                },
                { // public with static attribute and non-static attribute
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>(),
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new AttributeStringBuilder(
                                        "age",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static attributes\n" +
                                "private static java.lang.String name;\n" +
                                "\n" +
                                "// attributes\n" +
                                "private java.lang.Integer age;\n" +
                                "}"
                },
                { // public with static method and non-static method
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getName",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new MethodStringBuilder(
                                        "getAge",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>(),
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static methods\n" +
                                "public static java.lang.String getName(){" +
                                "}\n" +
                                "\n" +
                                "// methods\n" +
                                "public java.lang.Integer getAge(){" +
                                "}\n" +
                                "}"
                },
                { // public with static attribute and non-static method
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getAge",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static attributes\n" +
                                "private static java.lang.String name;\n" +
                                "\n" +
                                "// methods\n" +
                                "public java.lang.Integer getAge(){" +
                                "}\n" +
                                "}"
                },
                { // public with static method and non-static attribute
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getName",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "age",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                        }})
                                );
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static methods\n" +
                                "public static java.lang.String getName(){" +
                                "}\n" +
                                "\n" +
                                "// attributes\n" +
                                "private java.lang.Integer age;\n" +
                                "}"
                },
                { // public with static method and non-static attribute and non-static method
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getName",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new MethodStringBuilder(
                                        "getAge",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "age",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static methods\n" +
                                "public static java.lang.String getName(){" +
                                "}\n" +
                                "\n" +
                                "// attributes\n" +
                                "private java.lang.Integer age;\n" +
                                "\n" +
                                "// methods\n" +
                                "public java.lang.Integer getAge(){" +
                                "}\n" +
                                "}"
                },
                { // public with static attribute and non-static attribute and non-static method
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getAge",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new AttributeStringBuilder(
                                        "age",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                        }})
                                );
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static attributes\n" +
                                "private static java.lang.String name;\n" +
                                "\n" +
                                "// attributes\n" +
                                "private java.lang.Integer age;\n" +
                                "\n" +
                                "// methods\n" +
                                "public java.lang.Integer getAge(){" +
                                "}\n" +
                                "}"
                },
                { // public with static attribute and static method and non-static attribute and non-static method
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>(),
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getName",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new MethodStringBuilder(
                                        "getAge",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new AttributeStringBuilder(
                                        "age",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static attributes\n" +
                                "private static java.lang.String name;\n" +
                                "\n" +
                                "// static methods\n" +
                                "public static java.lang.String getName(){" +
                                "}\n" +
                                "\n" +
                                "// attributes\n" +
                                "private java.lang.Integer age;\n" +
                                "\n" +
                                "// methods\n" +
                                "public java.lang.Integer getAge(){" +
                                "}\n" +
                                "}"
                },
                { // public with static attribute and static method and non-static attribute and non-static method and constructor
                        Visibility.PUBLIC,
                        "Person",
                        "ch.bissbert.test",
                        false, // isFinal
                        false, // isAbstract
                        new ArrayList<ConstructorStringBuilder>() {
                            {
                                add(new ConstructorStringBuilder(
                                        "Person",
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<MethodStringBuilder>() {
                            {
                                add(new MethodStringBuilder(
                                        "getName",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new MethodStringBuilder(
                                        "getAge",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        "",
                                        new HashSet<>() {{
                                            add(Modifier.PUBLIC);
                                        }}
                                ));
                            }
                        },
                        new ArrayList<AttributeStringBuilder>() {
                            {
                                add(new AttributeStringBuilder(
                                        "name",
                                        new SimpleNameTypeMirror("java.lang.String"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                            add(Modifier.STATIC);
                                        }}
                                ));
                                add(new AttributeStringBuilder(
                                        "age",
                                        new SimpleNameTypeMirror("java.lang.Integer"),
                                        new HashSet<>() {{
                                            add(Modifier.PRIVATE);
                                        }}
                                ));
                            }
                        },
                        "package ch.bissbert.test;\n" +
                                "\n" +
                                "public class Person {\n" +
                                "// static attributes\n" +
                                "private static java.lang.String name;\n" +
                                "\n" +
                                "// static methods\n" +
                                "public static java.lang.String getName(){" +
                                "}\n" +
                                "\n" +
                                "// attributes\n" +
                                "private java.lang.Integer age;\n" +
                                "\n" +
                                "// constructors\n" +
                                "public Person(){" +
                                "}\n" +
                                "\n" +
                                "// methods\n" +
                                "public java.lang.Integer getAge(){" +
                                "}\n" +
                                "}"
                }
        };
    }

    private final Visibility visibility;
    private final String name;
    private final String packageName;
    private final boolean isFinal;
    private final boolean isAbstract;
    private final ArrayList<ConstructorStringBuilder> constructors;
    private final ArrayList<MethodStringBuilder> methods;
    private final ArrayList<AttributeStringBuilder> attributes;
    private final String expected;

    private ClassStringBuilder classStringBuilder;

    public ClassStringBuilderTest(
            Visibility visibility,
            String name,
            String packageName,
            boolean isFinal,
            boolean isAbstract,
            ArrayList<ConstructorStringBuilder> constructors,
            ArrayList<MethodStringBuilder> methods,
            ArrayList<AttributeStringBuilder> attributes,
            String expected
    ) {
        this.visibility = visibility;
        this.name = name;
        this.packageName = packageName;
        this.isFinal = isFinal;
        this.isAbstract = isAbstract;
        this.constructors = constructors;
        this.methods = methods;
        this.attributes = attributes;
        this.expected = expected;

        System.out.println("------------------------------------------------------------");
        System.out.printf(
                "Running with %nvisibility: %s, %nname: %s, %npackageName: %s, %nisFinal: %s, %nisAbstract: %s, %nconstructors: %s, %nmethods: %s, %nattributes: %s%n",
                visibility,
                name,
                packageName,
                isFinal,
                isAbstract,
                constructors,
                methods,
                attributes
        );
    }

    @Before
    public void setUp() {
        Set<Modifier> modifiers = new HashSet<>();

        if (this.isAbstract) {
            modifiers.add(Modifier.ABSTRACT);
        }
        if (this.isFinal) {
            modifiers.add(Modifier.FINAL);
        }
        if (visibility.toModifier() != null) {
            modifiers.add(visibility.toModifier());
        }

        classStringBuilder = new ClassStringBuilder(
                name,
                packageName,
                constructors,
                attributes,
                methods,
                modifiers
        );
    }

    @Test
    public void toJavaString() {
        System.out.println("Result:\n" + classStringBuilder.toJavaString());
        System.out.println();
        System.out.println("Expected:\n" + expected);
        assertEquals(expected, classStringBuilder.toJavaString());
    }

    @Test
    public void getConstructorStringBuilders() {
        assertEquals(constructors, classStringBuilder.getConstructorStringBuilders());
    }

    @Test
    public void getAttributeStringBuilders() {
        assertEquals(attributes, classStringBuilder.getAttributeStringBuilders());
    }

    @Test
    public void getMethodStringBuilders() {
        assertEquals(methods, classStringBuilder.getMethodStringBuilders());
    }

    @Test
    public void isAbstract() {
        assertEquals(isAbstract, classStringBuilder.getModifiers().contains(Modifier.ABSTRACT));
    }

    @Test
    public void isFinal() {
        assertEquals(isFinal, classStringBuilder.getModifiers().contains(Modifier.FINAL));
    }

    @Test
    public void getVisibility() {
        assertEquals(visibility, Visibility.getVisibility(classStringBuilder.getModifiers()));
    }

    @Test
    public void getName() {
        assertEquals(name, classStringBuilder.getName());
    }

    @Test
    public void getPackageName() {
        assertEquals(packageName, classStringBuilder.getPackageName());
    }
}

