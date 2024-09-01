import jdk.jshell.spi.SPIResolutionException;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class StudentCommandHandler {

    private StudentStorage studentStorage = new StudentStorage();

    public void processCommand(Command command) {

        Action action = command.getAction();
        switch (action) {
            case CREATE -> {
                processCreateCommand(command);
                break;
            }
            case UPDATE -> {
                processUpdateCommand(command);
                break;
            }
            case DELETE -> {
                processDeleteCommand(command);
                break;
            }
            case STAT_BY_COURSE -> {
                processStatsByCourseCommand(command);
                break;
            }
            case STAT_BY_CITY -> { //ДЕЕСТВИЕ ДОБАВЛЕНО В РАМКАХ ДОМАШНЕГО ЗАДАНИЯ
                processStatsByCityCommand(command);
            }
            case SEARCH -> {
                processSearchCommand(command);
                break;
            }
            default -> {
                System.out.println("Действие " + action + " не поддерживается");
            }

        }
        System.out.println("Обработка команды. Действие: "
                + command.getAction().name()
                + ", данные " + command.getData());
    }



    /** ФУНКЦИЯ ИЗМЕНЕНА В РАМКАХ ДОМАШНЕГО ЗАДАНИЯ */
    private void processSearchCommand(Command command) {

        String surname = command.getData();

        if (surname.matches("^[А-Я]{1}[а-яё]{1,}\\-{0,1}[А-Я]{0,1}[а-яё]{0,}$")) {
            studentStorage.searchExactBySurname(surname);
        } else if((surname.matches("^[А-Я]{1}[а-яё]{1,}\\-{0,1}[А-Я]{0,1}[а-яё]{0,}\\,[А-Я]{1}[а-яё]{1,}\\-{0,1}[А-Я]{0,1}[а-яё]{0,}$"))) {
            String[] dataArray = surname.split(",");
            Arrays.sort(dataArray);
            String surnameFirst = dataArray[0];
            String surnameSecond = dataArray[1];
            studentStorage.searchByTwoSurnames(surnameFirst, surnameSecond);

        }  else if(surname == "") {
            studentStorage.getAllStudents();

        } else{
            System.out.println("\nПроверьте введенную фамилию.\nФамилия вводится кириллическими символами, должна начинаться с заглавной буквы.\n");
        }
    }



    /** ФУНКЦИЯ ИЗМЕНЕНА В РАМКАХ ДОМАШНЕГО ЗАДАНИЯ */
    private void processCreateCommand(Command command) {
        String data = command.getData();

        if(data.matches("^[А-Я]{1}[а-яё]{1,}\\-{0,1}[А-Я]{0,1}[а-яё]{0,}\\,[А-Я]{1}[а-яё]{1,}\\,[A-ZА-Я]{1}[a-zа-яё]{1,}[A-ZА-Яa-zа-яё \\-]{0,}\\,[А-Я]{1}[а-яё]{1,}\\-{0,}[А-Яа-я\\-]{0,}\\,[0-9]{2}$")) {

            String[] dataArray = data.split(",");
                Student student = new Student();

                student.setSurname(dataArray[0]);
                student.setName(dataArray[1]);
                student.setCourse(dataArray[2]);
                student.setCity(dataArray[3]);
                student.setAge(Integer.valueOf(dataArray[4]));

                studentStorage.createStudent(student);
                studentStorage.printAll();

        } else {
            System.out.println("\nПроверьте введенные данные. Формат ввода данных:\nФамилия,Имя,Курс,Город,Возраст(числовое значение)\n");
        }

    }



    /** ФУНКЦИЯ ИЗМЕНЕНА В РАМКАХ ДОМАШНЕГО ЗАДАНИЯ */
    public void processUpdateCommand(Command command) {
        String data = command.getData();

        if (data.matches("^[0-9]{1,}\\,[А-Я]{1}[а-яё]{1,}\\-{0,1}[А-Я]{0,1}[а-яё]{0,}\\,[А-Я]{1}[а-яё]{1,}\\,[A-ZА-Я]{1}[a-zа-яё]{1,}[A-ZА-Яa-zа-яё \\-]{0,}\\,[А-Я]{1}[а-яё]{1,}\\-{0,}[А-Яа-я\\-]{0,}\\,[0-9]{2}$")) {
            String[] dataArray = data.split(",");

            Student student = new Student();

            Long id = Long.valueOf(dataArray[0]);

            student.setSurname(dataArray[1]);
            student.setName(dataArray[2]);
            student.setCourse(dataArray[3]);
            student.setCity(dataArray[4]);
            student.setAge(Integer.valueOf(dataArray[5]));

            studentStorage.updateStudent(id, student);
            studentStorage.printAll();
        } else {
            System.out.println("\nПроверьте введенные данные. Формат ввода данных:\nИдентификатор(числовое значение),Фамилия,Имя,Курс,Город,Возраст(числовое значение)\n");
        }
    }



    /** ФУНКЦИЯ ДОБАВЛЕНА В РАМКАХ ДОМАШНЕГО ЗАДАНИЯ */
    private void processStatsByCityCommand(Command command) {
        Map<String, Long> data = studentStorage.getCountByCity();
        studentStorage.printMap(data);
    }




    private void processStatsByCourseCommand(Command command) {
        Map<String, Long> data = studentStorage.getCountByCourse();
        studentStorage.printMap(data);
    }

    public void processDeleteCommand(Command command) {
        String data = command.getData();
        Long id = Long.valueOf(data);

        studentStorage.deleteStudent(id);
        studentStorage.printAll();
    }
}

