import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    private static int lengthSourceDirectory;

    public static void copyFolder(String sourceDirectory, String destinationDirectory) {
        // TODO: write code copy content of sourceDirectory to destinationDirectory
        try {
            File copied = new File(sourceDirectory);

            lengthSourceDirectory = sourceDirectory.length();
            String str = sourceDirectory.substring(lengthSourceDirectory);

            System.out.println(str);
            System.out.println(sourceDirectory + "has length: " + sourceDirectory.length());
            System.out.println(copied.getName() + "has length: " + copied.getName().length());

            String destinationDirectoryPath = destinationDirectory + "\\" + copied.getName();
            new File(destinationDirectoryPath).mkdirs();

            Files.walkFileTree(copied.toPath(), visitor(destinationDirectory));

            System.out.println(destinationDirectory + "\\" + copied.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static FileVisitor<? super Path> visitor(String destinationDirectory){
        return new FileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                String directory = dir.toString();
                String finalPath = destinationDirectory + directory.substring(lengthSourceDirectory);
                new File(finalPath).mkdirs();

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String file1 = file.toString();
                String finalPath = destinationDirectory + file1.substring(lengthSourceDirectory);
                Files.copy(file, new File(finalPath).toPath());

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.SKIP_SIBLINGS;

            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        };
    }
}
