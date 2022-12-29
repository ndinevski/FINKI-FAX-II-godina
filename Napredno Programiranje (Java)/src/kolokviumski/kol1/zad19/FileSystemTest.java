package kolokviumski.kol1.zad19;

import java.util.*;
import java.util.stream.Collectors;

class FileNameExistsException extends Exception{
    public FileNameExistsException(String mess) {
        super(mess);
    }
}

public class FileSystemTest {

    public static Folder readFolder (Scanner sc)  {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i=0;i<totalFiles;i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String [] parts = fileInfo.split("\\s+");
                try {
                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    folder.addFile(readFolder(sc));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return folder;
    }

    public static void main(String[] args)  {

        //file reading from input

        Scanner sc = new Scanner(System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.addFile(readFolder(sc));
        } catch (FileNameExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());




    }
}


interface IFile{
    String getFileName();
    long getFileSize();
    String getFileInfo(String tabs);
    void sortBySize();
    long findLargestFile ();
    String getFileType();
}


class File implements  IFile{
    private String name;
    private long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return size;
    }


    @Override
    public void sortBySize() {}

    @Override
    public long findLargestFile() {
        return size;
    }

    @Override
    public String getFileType() {
        return "File";
    }

    @Override
    public String getFileInfo(String tabs) {
        return tabs + String.format("File name: %10s File size: %10d\n", name, size);
    }
}

class Folder implements IFile{
    private String name;
    private long size;
    List<IFile> files;

    public Folder(String name) {
        this.name = name;
        files = new ArrayList<>();
        size = 0;
    }

    public void addFile(IFile file) throws FileNameExistsException {

        if(files.stream().map(IFile::getFileName).anyMatch(p->p.equals(file.getFileName()))){
            throw new FileNameExistsException("There is already a file named " + file.getFileName() + " in the folder " + name);
        }else {
            files.add(file);
        }
    }



    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        size = files.stream().mapToLong(IFile::getFileSize).sum();
        return size;
    }

    @Override
    public String getFileInfo(String tabs) {
        String result = tabs + String.format("Folder name: %10s Folder size: %10d\n", name, getFileSize());
        for(IFile file : files) {
            result +=  file.getFileInfo(tabs + "\t");
        }
        return result;
    }

    @Override
    public void sortBySize() {
        files = files.stream().sorted(Comparator.comparing(IFile::getFileSize)).collect(Collectors.toList());
        files.stream().filter(p->p.getFileType().equals("Folder")).forEach(IFile::sortBySize);
    }

    @Override
    public long findLargestFile() {
        List<Long> sizes = new ArrayList<>();
        sizes.add(files.stream().filter(file -> file.getFileType().equals("File"))
                .mapToLong(IFile::getFileSize)
                .max().orElse(0));
        for(IFile file : files) {
            sizes.add(file.findLargestFile());
        }
        return sizes.stream().mapToLong(Long::longValue).max().orElse(0);
    }

    @Override
    public String getFileType() {
        return "Folder";
    }

}

class FileSystem{
    Folder root = new Folder("root");

    public FileSystem() {
    }
    void addFile(IFile file) throws FileNameExistsException {
        root.addFile(file);
    }

    long findLargestFile(){
        return root.files.stream().map(IFile::findLargestFile).max(Comparator.naturalOrder()).get();
    }

    void sortBySize(){
        root.sortBySize();
    }


    @Override
    public String toString() {
        return root.getFileInfo("");
    }
}