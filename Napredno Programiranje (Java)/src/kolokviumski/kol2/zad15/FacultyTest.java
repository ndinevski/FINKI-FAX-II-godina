package kolokviumski.kol2.zad15;


import java.util.*;


class OperationNotAllowedException extends Exception{
    public OperationNotAllowedException(String mess) {
        super(mess);
    }
}


class Student{
    String id;
    int yearsOfStudies;
    Map<Integer, Map<String,Integer>> gradesInSemestersBySubjects;
    List<Integer> allGrades;
    Set<String> allCourses;

    public Student(String id, int yearsOfStudies) {
        this.id = id;
        this.yearsOfStudies = yearsOfStudies;

        int k;
        if(yearsOfStudies==3) {
            k=6;
        }else{
            k=8;
        }

        gradesInSemestersBySubjects = new TreeMap<>();
        for(int i=0;i<k;i++){
            gradesInSemestersBySubjects.put(i,new HashMap<>());
        }

        allGrades = new ArrayList<>();
        allCourses = new TreeSet<>();
    }

    public String getId() {
        return id;
    }

    public int getYearsOfStudies() {
        return yearsOfStudies;
    }

    public int getNumberOfCourses(){
        return allGrades.size();
    }

    public void addGrade(int term, String courseName, int grade) throws OperationNotAllowedException {
        if((yearsOfStudies==3 && term>6) || (yearsOfStudies==4 && term>8)){
            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, id));
        }
        if(gradesInSemestersBySubjects.get(term-1).size()>=3){
            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d",id, term));
        }

        gradesInSemestersBySubjects.get(term-1).putIfAbsent(courseName,grade);

        allGrades.add(grade);
        allCourses.add(courseName);
    }

    public boolean isGraduated() {
        if((yearsOfStudies==3 && allGrades.size()==18) || (yearsOfStudies==4 && allGrades.size()==24)){
            return true;
        }
        return false;
    }

    public double getAverage() {
        return allGrades.stream()
                .mapToInt(i->i)
                .average()
                .orElse(5.0);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(id).append("\n");

        for(int i=0;i<gradesInSemestersBySubjects.size();i++){
            sb.append(String.format("Term %d\n", i+1));
            sb.append(String.format("Courses: %d\n", gradesInSemestersBySubjects.get(i).size()));
            sb.append(String.format("Average grade for term: %.2f\n", gradesInSemestersBySubjects.get(i).values()
                    .stream()
                    .mapToDouble(p->p)
                    .average()
                    .orElse(5.0)));
        }

        sb.append(String.format("Average grade: %.2f\n", getAverage()));

        sb.append(String.format("Courses attended: %s", String.join(",",allCourses)));
        return sb.toString();
    }

    public String shortString() {
        return String.format("Student: %s Courses passed: %d Average grade: %.2f", id, getNumberOfCourses(), getAverage());
    }
}

class Course{
    String name;
    List<Integer> grades;
    int studentsOnCourse;


    public Course(String name) {
        this.name = name;
        grades = new ArrayList<>();
        studentsOnCourse =0;
    }

    public String getName() {
        return name;
    }

    public int getStudentsOnCourse() {
        return studentsOnCourse;
    }

    public void addGrade(int grade){
        grades.add(grade);
    }

    public double getAverage(){
        return grades.stream().mapToInt(i->i).average().orElse(0);
    }

    public void addStudentOnCourse(){
        studentsOnCourse++;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f", name, studentsOnCourse, getAverage());
    }


}

class Faculty {

    Map<String, Student> studentsById;
    Map<String, Course> allCourses;
    List<String> logs;

    public Faculty() {
        studentsById = new HashMap<>();
        logs = new ArrayList<>();
        allCourses = new TreeMap<>(Comparator.reverseOrder());
    }

    void addStudent(String id, int yearsOfStudies) {
        Student newStudent = new Student(id, yearsOfStudies);

        studentsById.putIfAbsent(id, newStudent);
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {

        allCourses.putIfAbsent(courseName, new Course(courseName));
        allCourses.get(courseName).addGrade(grade);
        allCourses.get(courseName).addStudentOnCourse();

        Student student = studentsById.get(studentId);

        student.addGrade(term, courseName, grade);

        if(student.isGraduated()){
            logs.add(String.format("Student with ID %s graduated with average grade %.2f in %d years.", student.getId(), student.getAverage(), student.getYearsOfStudies()));
            studentsById.remove(studentId);
        }
    }

    String getFacultyLogs() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i=0;i<logs.size()-1;i++) {
            sb.append(logs.get(i)).append("\n");
        }
        sb.append(logs.get(i));
        return sb.toString();
    }

    String getDetailedReportForStudent(String id) {
        return studentsById.get(id).toString();
    }

    void printFirstNStudents(int n) {
        studentsById.values().stream()
                        .sorted(Comparator.comparing(Student::getNumberOfCourses)
                                .thenComparing(Student::getAverage)
                                .thenComparing(Student::getId).reversed())
                        .limit(n).forEach(p-> System.out.println(p.shortString()));
    }

    void printCourses() {
        allCourses.values()
                .stream()
                .sorted(Comparator.comparing(Course::getStudentsOnCourse).thenComparing(Course::getAverage)
                        .thenComparing(Course::getName))
                .forEach(System.out::println);
    }
}

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}

