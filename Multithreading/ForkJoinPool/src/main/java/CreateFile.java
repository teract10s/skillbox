import lombok.Getter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Getter
public class CreateFile {
    private final Set<String> paths;
    private final FileWriter fileWriter;

    public CreateFile(Set<String> paths, String pathToFile) throws IOException {
        this.paths = paths;
        fileWriter = new FileWriter(pathToFile);
        creating();
    }

    private void creating(){
        List<String> sortPaths = sort(paths);
        sortPaths.forEach(s -> {
            try {
                int count = getSlashAtUrl(s) - 3;
                s = toNewStr(s, count);
                fileWriter.write(s + '\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static String toNewStr(String url, int count){
        StringBuilder result = new StringBuilder(url);
        for (int i = 0; i < count; i++){
            result.insert(0, '\t');
        }
        return result.toString();
    }

    private static int getSlashAtUrl(String url){
        int count = 0;
        for (int i = 0; i < url.length(); i++){
            if (url.charAt(i) == '/'){
                count++;
            }
        }
        return count;
    }

    private static List<String> sort(Set<String> paths){
        List<String> sortingList = new ArrayList<>(paths);
        Collections.sort(sortingList);
        return sortingList;
    }
}
