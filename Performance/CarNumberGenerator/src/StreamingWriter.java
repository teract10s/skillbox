import java.io.FileOutputStream;

public class StreamingWriter extends Thread{
    private final char[] letters;
    private final FileOutputStream writer;

    public StreamingWriter(char[] letters, FileOutputStream writer) {
        this.letters = letters;
        this.writer = writer;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
         try{
            for (int number = 0; number < 1000; number++) {
                StringBuilder buffer = new StringBuilder();
                int regionCode = 199;

                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            buffer.append(firstLetter).append(padNumber(number, 3))
                                    .append(secondLetter).append(thirdLetter)
                                    .append(padNumber(regionCode, 2)).append('\n');
                        }
                    }
                }
                writer.write(buffer.toString().getBytes());
            }

            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        return "0".repeat(Math.max(0, padSize)) + numberStr;
    }
}
