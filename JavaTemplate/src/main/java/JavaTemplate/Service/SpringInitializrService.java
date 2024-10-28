package JavaTemplate.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SpringInitializrService {

    @Autowired
    private RestTemplate restTemplate;

    public void generateProject(String dependencies, String projectName, String springBootVersion) throws IOException {
        String url = String.format(
                "https://start.spring.io/starter.zip?dependencies=%s&name=%s&bootVersion=%s",
                dependencies, projectName, springBootVersion
        );

        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            byte[] projectZip = response.getBody();
            Path tempFile = Files.createTempFile(projectName, ".zip");
            try (FileOutputStream fos = new FileOutputStream(tempFile.toFile())) {
                fos.write(projectZip);
            }
            System.out.println("Project downloaded to: " + tempFile.toAbsolutePath());
        } else {
            System.err.println("Failed to generate project. HTTP Status: " + response.getStatusCode());
        }
    }
}
