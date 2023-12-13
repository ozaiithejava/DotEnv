import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class EnvVariableManager {
    private Path envFilePath;

    public EnvVariableManager(String filePath) {
        this.envFilePath = Path.of(filePath);
        createEnvFileIfNotExists();
    }

    private void createEnvFileIfNotExists() {
        if (!Files.exists(envFilePath)) {
            try {
                Files.createFile(envFilePath);
                System.out.println(".env dosyası oluşturuldu.");
            } catch (IOException e) {
                System.err.println("Dosya oluşturma sırasında bir hata oluştu: " + e.getMessage());
            }
        }
    }

    public void deleteEnvFile() {
        try {
            Files.deleteIfExists(envFilePath);
            System.out.println(".env dosyası silindi.");
        } catch (IOException e) {
            System.err.println("Dosya silme sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    public Map<String, String> readEnvVariables() {
        Map<String, String> envVariables = new HashMap<>();

        try {
            if (!Files.exists(envFilePath)) {
                createEnvFileIfNotExists();
            }

            String envContent = Files.readString(envFilePath);
            String[] lines = envContent.split("\n");

            for (String line : lines) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    envVariables.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Dosya okuma sırasında bir hata oluştu: " + e.getMessage());
        }

        return envVariables;
    }

    public String getEnvVariable(String variableName) {
        Map<String, String> envVariables = readEnvVariables();
        return envVariables.get(variableName);
    }

    public void setEnvVariable(String variableName, String newValue) {
        try {
            Map<String, String> envVariables = readEnvVariables();
            envVariables.put(variableName, newValue);

            updateEnvFile(envVariables);

            System.out.println("Çevresel değişken güncellendi: " + variableName);
        } catch (IOException e) {
            System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    public void addEnvVariable(String variableName, String value) {
        try {
            Map<String, String> envVariables = readEnvVariables();
            envVariables.put(variableName, value);

            updateEnvFile(envVariables);

            System.out.println("Çevresel değişken eklendi: " + variableName);
        } catch (IOException e) {
            System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    public boolean checkEnvVariableExists(String variableName) {
        Map<String, String> envVariables = readEnvVariables();
        return envVariables.containsKey(variableName);
    }

    public void deleteEnvVariable(String variableName) {
        try {
            Map<String, String> envVariables = readEnvVariables();
            envVariables.remove(variableName);

            updateEnvFile(envVariables);

            System.out.println("Çevresel değişken silindi: " + variableName);
        } catch (IOException e) {
            System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    private void updateEnvFile(Map<String, String> envVariables) throws IOException {
        StringBuilder updatedEnvContent = new StringBuilder();
        envVariables.forEach((key, value) -> updatedEnvContent.append(key).append("=").append(value).append("\n"));

        Files.writeString(envFilePath, updatedEnvContent.toString(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
