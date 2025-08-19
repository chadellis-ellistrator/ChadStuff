import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Files {
    List<String> getHiddenFiles() {
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isHidden();
                }
            }
        );

        return Arrays.stream(hiddenFiles).map(File::getName).collect(Collectors.toList());
    }

    List<String> getHiddenFiles8() {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        return Arrays.stream(hiddenFiles).map(File::getName).collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        List<String> fileList = new Files().getHiddenFiles();
        for (String s: fileList) {
            System.out.println(s);
        }

        fileList = new Files().getHiddenFiles8();
        System.out.println("\n8 now\n+++++++++++");
        for (String s: fileList) {
            System.out.println(s);
        }

    }
}
