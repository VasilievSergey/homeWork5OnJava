package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BackupUtility {

    public static void main(String[] args) {
        File sourceDirectory = new File("./src/main/java/org/example");
        File backupDirectory = new File("./backup2");

        createBackup(sourceDirectory, backupDirectory);
    }

    public static void createBackup(File sourceDir, File backupDir) {
        if (!sourceDir.isDirectory()) {
            System.out.println("Указанный путь не является директорией.");
            return;
        }

        if (!backupDir.exists()) {
            backupDir.mkdirs();
        }

        File[] files = sourceDir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                try {
                    Files.copy(file.toPath(), new File(backupDir, file.getName()).toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Не удалось создать резервную копию файла: " + file.getName());
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Резервная копия успешно создана в директории: " + backupDir.getPath());
    }

}