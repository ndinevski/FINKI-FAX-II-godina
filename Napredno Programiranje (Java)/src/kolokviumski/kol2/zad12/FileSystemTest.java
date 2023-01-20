package kolokviumski.kol2.zad12;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class File{
    private String name;
    private int size;
    private LocalDateTime time;

    public File(String name, int size, LocalDateTime time) {
        this.name = name;
        this.size = size;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isHiddenFile(){
        if(name.charAt(0)=='.'){
            return true;
        }
        return false;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%-10s %5dB %s", name, size, time);
    }
}

class FileSystem{

    Map<Character, Set<File>> filesByFolder;
    Map<Integer, Set<File>> filesByYear;
    Map<String, Integer> sizesByMonthAndDay;

    public FileSystem() {
        filesByFolder = new HashMap<>();
        filesByYear = new HashMap<>();
        sizesByMonthAndDay = new TreeMap<>();
    }

    public void addFile(char folder, String name, int size, LocalDateTime createdAt){
        File file = new File(name, size, createdAt);

        filesByFolder.putIfAbsent(folder, new TreeSet<>(Comparator.comparing(File::getTime)
                                                        .thenComparing(File::getName)
                                                        .thenComparing(File::getSize)));
        filesByFolder.get(folder).add(file);


        filesByYear.putIfAbsent(createdAt.getYear(), new TreeSet<>(Comparator.comparing(File::getTime)
                                                                .thenComparing(File::getName)
                                                                .thenComparing(File::getSize)));
        filesByYear.get(createdAt.getYear()).add(file);

        String monthAndDay = createdAt.getMonth()+"-"+createdAt.getDayOfMonth();
        sizesByMonthAndDay.putIfAbsent(monthAndDay, 0);
        sizesByMonthAndDay.replace(monthAndDay, sizesByMonthAndDay.get(monthAndDay)+file.getSize());
    }

    public List<File> findAllHiddenFilesWithSizeLessThen(int size){

        List<File> files = new ArrayList<>();

        for (Character character : filesByFolder.keySet()) {
            List<File> filesInFolder = filesByFolder.get(character).stream().collect(Collectors.toList());
            files.addAll(filesInFolder);
        }


        return files.stream()
                .filter(file->file.isHiddenFile() && file.getSize()<size)
                .collect(Collectors.toList());
    }

    public int totalSizeOfFilesFromFolders(List<Character> folders){
        int totalSize = 0;
        for(int i=0;i<folders.size();i++){
            char folder = folders.get(i);
            totalSize += filesByFolder.get(folder).stream()
                    .mapToInt(File::getSize)
                    .sum();
        }
        return totalSize;
    }

    public Map<Integer, Set<File>> byYear(){
        return filesByYear;
    }

    public Map<String, Integer> sizeByMonthAndDay(){
        return sizesByMonthAndDay;
    }


}



public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Integer> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}


