package com.guess.build;

import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.languages.SpringCodegen;
import io.swagger.models.properties.Property;

public class CustomSpringCodegen extends SpringCodegen {

    @Override
    public void processOpts() {
        super.processOpts();
        useJava8DateLibrary();
    }

    @Override
    public CodegenProperty fromProperty(String name, Property p) {
        // TODO: implement custom CodegenProperty which would support @Email/@Digits/@URL validations
        return super.fromProperty(name, p);
    }

    private void useJava8DateLibrary() {
        additionalProperties.put("jsr310", "true");
        typeMapping.put("date", "LocalDate");
        importMapping.put("LocalDate", "java.time.LocalDate");
        typeMapping.put("DateTime", "OffsetDateTime");
        importMapping.put("OffsetDateTime", "java.time.OffsetDateTime");
    }
}
