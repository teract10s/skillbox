import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    private static long sizeOfDirectory;

    public static long calculateFolderSize(String path) {
        try {
            File folder = new File(path);

            FileVisitor<? super Path> visitor = new FileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    sizeOfDirectory += file.toFile().length();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            };

            Files.walkFileTree(folder.toPath(),  visitor);

            return sizeOfDirectory;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

}
