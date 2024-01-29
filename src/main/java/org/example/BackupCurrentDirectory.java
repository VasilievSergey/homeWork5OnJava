package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class BackupCurrentDirectory {
    private final String source;
    public BackupCurrentDirectory(String source) {
        this.source = source;
    }

    public static void main(String[] args) throws IOException {
        new BackupCurrentDirectory(".").copy();
    }

    public void copy() throws IOException {
        File folder = new File(this.source);
        String backup = "./backup";
        File backupFolder = new File(backup);
        if(!backupFolder.exists())
        {
            Path path = Files.createDirectories(Paths.get(backup));
            System.out.printf("Директория %s создана\n", path.toString());
        }
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {
                Files.copy(Paths.get(file.getName()),
                        Paths.get(backupFolder + "/" + file.getName()),
                        REPLACE_EXISTING);
                System.out.printf("Файл %s скопирован.\n", file.getName());
            }
        }
    }
}
