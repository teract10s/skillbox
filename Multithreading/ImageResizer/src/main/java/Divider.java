import java.io.File;

public class Divider {
    private final File[] files;
    private final String dstFolder;
    private final int newWidth;
    private final long start;

    public Divider(File[] files, String dstFolder, int newWidth, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.newWidth = newWidth;
        this.start = start;
    }

    public int separation(int countOfProcessorCores){
        int smallestCount = files.length / countOfProcessorCores;
        int remainder = files.length - (smallestCount * countOfProcessorCores);
        int currentItem = 0;

        for (int i = 0; i < countOfProcessorCores; i++){
            int count = i + 1 <= remainder ? smallestCount + 1 : smallestCount;
            File[] files1 = new File[count];
            System.arraycopy(files, currentItem, files1, 0, files1.length);
            ImageResizer resizer = new ImageResizer(files1, dstFolder, newWidth, start);
            new Thread(resizer).start();
            currentItem += files1.length;
            System.out.println(files1.length);
        }
        return currentItem;
    }
}
