import java.io.FileOutputStream;

public class StreamingWriter {
    private final char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private final FileOutputStream writer;

    public StreamingWriter( FileOutputStream writer) {
        this.writer = writer;
        generate();
    }

    private void generate() {
        long start = System.currentTimeMillis();
         try{
            for (int number = 0; number < 1000; number++) {
                StringBuilder buffer = new StringBuilder();
                int regionCode = 199;

                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {

                            String strNumber = String.valueOf(number);
                            if (number <= 9){
                                strNumber = "00" + strNumber;
                            }else if (number <= 99){
                                strNumber = "0" + strNumber;
                            }

                            buffer.append(firstLetter).append(strNumber)
                                    .append(secondLetter).append(thirdLetter)
                                    .append(regionCode).append('\n');
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
}
