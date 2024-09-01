import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StudentSurnameStorage {

    public TreeMap<String, Set<Long>> sunamesTreeMap = new TreeMap<>();

    public void studentCreated(Long id, String surname) {
        Set<Long> existingIds = sunamesTreeMap.getOrDefault(surname, new HashSet<>());
        existingIds.add(id);
        sunamesTreeMap.put(surname, existingIds);
    }

    public void studentDeleted (Long id, String surname) {
        sunamesTreeMap.get(surname).remove(id);
    }

    public void studentUpdated(Long id, String oldSurname, String newSurname) {
        studentDeleted(id, oldSurname);
        studentCreated(id, newSurname);
    }

    /**
     ДОМАШНЕЕ ЗАДАНИЕ+++++++++++++++++++++++++++++++++++++++++++++++++++++++
     +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */

    public Set<Long> getStudentBetweenSurnames(String surnameFirst, String surnameSecond) {
        Set<Long> res = sunamesTreeMap.subMap(surnameFirst, true, surnameSecond, true).values().stream()
                .flatMap(longs -> longs.stream()).collect(Collectors.toSet());

        return res;
    }

    public Set<Long> getStudentBySurname(String surname) {
        Set<Long> res = sunamesTreeMap.get(surname);

        return res;
    }
}
