import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentStorage {
    private Map<Long, Student> studentStorageMap = new HashMap<>();

    private StudentSurnameStorage studentSurnameStorage = new StudentSurnameStorage();

    private Long currentId = 0L;

    public Long createStudent(Student student) {
        Long nextId = getNextId();
        studentStorageMap.put(nextId, student);
        studentSurnameStorage.studentCreated(nextId, student.getSurname());
        return nextId;
    }

    public boolean updateStudent(Long id, Student student) {
        if(!studentStorageMap.containsKey(id)) {
            return false;
        } else {
            String newSurname = student.getSurname();
            String oldSurname = studentStorageMap.get(id).getSurname();
            studentSurnameStorage.studentUpdated(id, oldSurname, newSurname);
            studentStorageMap.put(id, student);
            return true;
        }

    }

    public boolean deleteStudent(Long id) {
        Student remove = studentStorageMap.remove(id);
        if(remove != null) {
            String surname = remove.getSurname();
            studentSurnameStorage.studentDeleted(id, surname);
        }
        return remove != null;
    }

    public Long getNextId() {
        currentId = currentId +1;
        return currentId;
    }

    public void printAll() {
        System.out.println(studentStorageMap);
    }

    public void printMap(Map<String, Long> data){
        data.entrySet().stream().forEach(e-> {
            System.out.println(e.getKey() + " - " + e.getValue());
        });
    }

    public Map<String, Long> getCountByCourse () {

        return studentStorageMap.values().stream().collect(Collectors.toMap(
                student-> student.getCourse(),
                student -> 1L,
                (count1, count2) -> count1 + count2
                ));

//        Map<String, Long> res = new HashMap<>();
//        for (Student student : studentStorageMap.values()) {
//            String key = student.getCourse();
//            Long count = res.getOrDefault(key, 0L);
//            count++;
//            res.put(key, count);
//        }
//        return res;
    }

    /**
     ДОМАШНЕЕ ЗАДАНИЕ+++++++++++++++++++++++++++++++++++++++++++++++++++++++
     +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */

    public void searchByTwoSurnames (String surnameFirst, String surnameSecond) {

        Set<Long> students = studentSurnameStorage.getStudentBetweenSurnames(surnameFirst, surnameSecond);
        for(Long studentsId : students) {
            Student student = studentStorageMap.get(studentsId);
            System.out.println(student);
        }
    }

    public void searchExactBySurname(String surname) {
        Set<Long> students = studentSurnameStorage.getStudentBySurname(surname);
        for(Long studentsId : students) {
            Student student = studentStorageMap.get(studentsId);
            System.out.println(student);
        }
    }

    public void getAllStudents() {
        Set<Long> students = studentStorageMap.keySet();
        for(Long studentsId : students) {
            Student student = studentStorageMap.get(studentsId);
            System.out.println(student);
        }
    }

    public Map<String, Long> getCountByCity () {

        return studentStorageMap.values().stream().collect(Collectors.toMap(
                student-> student.getCity(),
                student -> 1L,
                (count1, count2) -> count1 + count2
        )); }
}
