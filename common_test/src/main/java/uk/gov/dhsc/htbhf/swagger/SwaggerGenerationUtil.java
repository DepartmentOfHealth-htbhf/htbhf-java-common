package uk.gov.dhsc.htbhf.swagger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.io.Files;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Can be used to check that the Swagger spec is available to the running application
 * on the given port and then saves it in the root project directory as swagger.yml.
 */
public class SwaggerGenerationUtil {

    public static void assertSwaggerDocumentationRetrieved(TestRestTemplate testRestTemplate, int applicationPort) throws IOException {
        String response = testRestTemplate.getForObject(buildUrl(applicationPort), String.class);

        String jsonAsYaml = convertJsonToYaml(response);

        assertThat(jsonAsYaml).isNotBlank();
        assertThat(jsonAsYaml).containsSequence("swagger: \"2.0\"");

        File swaggerFile = new File("../swagger.yml");
        Files.asCharSink(swaggerFile, Charset.defaultCharset()).write(jsonAsYaml);
    }

    private static String convertJsonToYaml(String response) throws IOException {
        JsonNode jsonNodeTree = new ObjectMapper().readTree(response);
        return new YAMLMapper()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .writeValueAsString(jsonNodeTree);
    }

    private static String buildUrl(int applicationPort) {
        return "http://localhost:" + applicationPort + "/v2/api-docs";
    }
}

