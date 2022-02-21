import java.io.File;

public class Main {
    private static final String dstFolder = "C:/Users/user/Desktop/dst";
    private static final int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "C:/Users/user/Desktop/src";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int countOfProcessorCores = Runtime.getRuntime().availableProcessors();

        Divider divider = new Divider(files, dstFolder, newWidth, start);
        divider.separation(countOfProcessorCores);
    }
}
