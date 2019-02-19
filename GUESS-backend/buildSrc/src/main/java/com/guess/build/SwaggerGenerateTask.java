package com.guess.build;

import io.swagger.codegen.DefaultGenerator;
import io.swagger.codegen.config.CodegenConfigurator;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

public class SwaggerGenerateTask extends DefaultTask {

    @Input
    @InputFile
    public String inputSpecPath;

    @Input
    @OutputDirectory
    public String outputDirPath;

    @Input
    public String apiPackage;

    @Input
    public String modelPackage;

    @TaskAction
    public void generate() {

        assert inputSpecPath != null;
        assert outputDirPath != null;
        assert apiPackage != null;
        assert modelPackage != null;

        CodegenConfigurator config = new CodegenConfigurator();
        config.setInputSpec(inputSpecPath);
        config.setOutputDir(outputDirPath);
        config.setLang(CustomSpringCodegen.class.getName());
        config.addAdditionalProperty("apiPackage", apiPackage);
        config.addAdditionalProperty("hideGenerationTimestamp", true);
        config.addAdditionalProperty("interfaceOnly", true);
        config.addAdditionalProperty("modelPackage", modelPackage);
        config.addAdditionalProperty("useTags", true);
        config.addSystemProperty("apis", "");
        config.addSystemProperty("models", "");
        new DefaultGenerator().opts(config.toClientOptInput()).generate();
    }
}
