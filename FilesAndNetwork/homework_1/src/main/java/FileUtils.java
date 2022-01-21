import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    private static long sizeOfDirectory = 0;

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
                    sizeOfDirectory += attrs.size();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.SKIP_SUBTREE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            };

            Files.walkFileTree(folder.toPath(), visitor);

            return sizeOfDirectory;


//            File folder = new File(path);
//            File[] files = folder.listFiles();
//            long sum = 0;
//
//            for (File file : files) {
//                sum += file.isDirectory() ? FileUtils.calculateFolderSize(file.toString()) : file.length();
//            }
//            return sum;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

//    @Override
//    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
//        return FileVisitResult.CONTINUE;
//    }
//
//    @Override
//    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
//        return FileVisitResult.CONTINUE;
//    }
//
//    @Override
//    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
//        return FileVisitResult.CONTINUE;
//    }
//
//    @Override
//    public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
//        return FileVisitResult.CONTINUE;
//    }
}
