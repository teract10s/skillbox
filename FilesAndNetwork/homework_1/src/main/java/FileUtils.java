import java.io.File;

public class FileUtils {

    public static long calculateFolderSize(String path) {
        try {
            File folder = new File(path);
            File[] files = folder.listFiles();
            long sum = 0;

            for (File file : files) {
                sum += file.isDirectory() ? FileUtils.calculateFolderSize(file.toString()) : file.length();
            }
            return sum;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
