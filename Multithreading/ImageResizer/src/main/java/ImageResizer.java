import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.imgscalr.Scalr.resize;

public class ImageResizer implements Runnable{
    private final File[] files;
    private final String dstFolder;
    private final int newWidth;
    private final long start;

    public ImageResizer(File[] files, String dstFolder, int newWidth, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.newWidth = newWidth;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                int newHeight = (int) Math.round( image.getHeight() / (image.getWidth() / (double) newWidth) );
                BufferedImage newImage = resize(image, newWidth, newHeight);

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished after start: " + (System.currentTimeMillis() - start));
    }
}
