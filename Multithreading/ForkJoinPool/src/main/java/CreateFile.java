import java.util.Set;

public class CreateFile {
    protected final Set<String> paths;
    protected final String pathToFile;

    public CreateFile(Set<String> paths, String pathToFile) {
        this.paths = paths;
        this.pathToFile = pathToFile;
        creating();
    }

    private void creating(){
        paths.forEach(p -> System.out.println("name: " + p));
    }
}
