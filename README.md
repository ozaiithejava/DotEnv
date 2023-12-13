# DotEnv
.env manager in java


## Usage:
```Java

        // Çevresel değişkenleri oku
        Map<String, String> envVariables = envManager.readEnvVariables();
        System.out.println("Çevresel Değişkenler:");
        envVariables.forEach((key, value) -> System.out.println(key + ": " + value));

        // Belirli bir çevresel değişkeni getir
        String dbUrl = envManager.getEnvVariable("DB_URL");
        System.out.println("DB_URL Değeri: " + dbUrl);

        // Belirli bir çevresel değişkeni güncelle
        envManager.setEnvVariable("DB_URL", "new_value");

        // Yeni çevresel değişken ekle
        envManager.addEnvVariable("NEW_VARIABLE", "new_value");

        // Belirli bir çevresel değişkenin varlığını kontrol et
        boolean dbUrlExists = envManager.checkEnvVariableExists("DB_URL");
        System.out.println("DB_URL Var mı? " + dbUrlExists);

        // Belirli bir çevresel değişkeni sil
        envManager.deleteEnvVariable("NEW_VARIABLE");

        // .env dosyasını sil
        envManager.deleteEnvFile();
    }
```
