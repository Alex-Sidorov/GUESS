package com.guess.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerController {

    private final String uiConfiguration;
    private final String securityConfiguration;
    private final String configuration;
    private final String swaggerYaml;
    private final String swaggerJson;

    @SneakyThrows
    public SwaggerController(ResourceLoader resourceLoader,
                             ObjectMapper objectMapper,
                             Yaml yaml) {

        JsonNode configurationUiJson = loadJson(objectMapper, resourceLoader, "classpath:/swagger/configuration-ui.json");
        JsonNode configurationSecurityJson = loadJson(objectMapper, resourceLoader, "classpath:/swagger/configuration-security.json");
        JsonNode configurationJson = loadJson(objectMapper, resourceLoader, "classpath:/swagger/configuration.json");
        Map<?, ?> swaggerYaml = loadYaml(yaml, resourceLoader, "classpath:/swagger/swagger.yaml");

        this.uiConfiguration = objectMapper.writeValueAsString(configurationUiJson);
        this.securityConfiguration = objectMapper.writeValueAsString(configurationSecurityJson);
        this.configuration = objectMapper.writeValueAsString(configurationJson);
        this.swaggerYaml = yaml.dump(swaggerYaml);
        this.swaggerJson = objectMapper.writeValueAsString(swaggerYaml);
    }

    @GetMapping("/")
    public String swaggerUiRedirect() {
        return "redirect:swagger-ui.html";
    }

    @ResponseBody
    @GetMapping("/swagger-resources/configuration/ui")
    public String uiConfiguration() {
        return uiConfiguration;
    }

    @ResponseBody
    @GetMapping("/swagger-resources/configuration/security")
    public String securityConfiguration() {
        return securityConfiguration;
    }

    @ResponseBody
    @GetMapping("/swagger-resources")
    public String configuration() {
        return configuration;
    }

    @ResponseBody
    @GetMapping(value = {"/swagger.yaml", "/swagger.yml"}, produces = "text/yaml")
    public String swaggerYaml() {
        return swaggerYaml;
    }

    @ResponseBody
    @GetMapping("/swagger.json")
    public String swaggerJson() {
        return swaggerJson;
    }

    private Map<?, ?> loadYaml(Yaml yaml, ResourceLoader resourceLoader, String location) throws IOException {
        return yaml.load(resourceLoader.getResource(location).getInputStream());
    }

    private JsonNode loadJson(ObjectMapper objectMapper, ResourceLoader resourceLoader, String location) throws IOException {
        return objectMapper.readTree(resourceLoader.getResource(location).getInputStream());
    }
}
