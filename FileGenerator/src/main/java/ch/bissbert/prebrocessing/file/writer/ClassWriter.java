package ch.bissbert.prebrocessing.file.writer;

import ch.bissbert.prebrocessing.file.Visibility;
import ch.bissbert.prebrocessing.file.builder.AttributeStringBuilder;
import ch.bissbert.prebrocessing.file.builder.ClassStringBuilder;
import ch.bissbert.prebrocessing.file.builder.method.ConstructorStringBuilder;
import ch.bissbert.prebrocessing.file.builder.method.MethodStringBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClassWriter implements Closeable {

    private final ClassStringBuilder classStringBuilder;
    private final PrintWriter printWriter;

    public ClassWriter(PrintWriter printWriter, ClassStringBuilder classStringBuilder) {
        this.classStringBuilder = classStringBuilder;
        this.printWriter = printWriter;
    }

    public ClassWriter(PrintWriter printWriter, String name, String packageName, Visibility visibility) {
        this(printWriter, new ClassStringBuilder(name, packageName, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), visibility));
    }

    public List<AttributeStringBuilder> getAttributeStringBuilders() {
        return classStringBuilder.getAttributeStringBuilders();
    }

    public List<MethodStringBuilder> getMethodStringBuilders() {
        return classStringBuilder.getMethodStringBuilders();
    }

    public List<ConstructorStringBuilder> getConstructorStringBuilders() {
        return classStringBuilder.getConstructorStringBuilders();
    }

    @Override
    public void close() throws IOException {
        this.printWriter.println(classStringBuilder.toJavaString());
        this.printWriter.close();
    }
}
