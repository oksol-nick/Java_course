package results.athletics.competition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ApplicationTests {

	ResultProcessor resultProcessor = new ResultProcessor();
	GetAndParseResults getAndParseResults = new GetAndParseResults();

	String fileName = "src/main/resources/results.csv";
	String gender = "M";
	int distance = 10;
	int numOfPeople = 3;

    @Test
	void resultProcessor()
	{
		List<Result> testList = new ArrayList<>();
		testList.add(new Result("Борис Шипов", "M", 10, 1397));
		testList.add(new Result("Денис Кораблёв","M", 10,1533));
		testList.add(new Result("Фёдор Курочкин","M", 10,1995));

		List<Result> results = resultProcessor.resultProcessor(fileName, gender, distance, numOfPeople);

		for(int i = 0; i < testList.size(); i++) {
			Assertions.assertEquals(testList.get(i).getName()  , results.get(i).getName());
			Assertions.assertEquals(testList.get(i).getGender()  , results.get(i).getGender());
			Assertions.assertEquals(testList.get(i).getDistance()  , results.get(i).getDistance());
			Assertions.assertEquals(testList.get(i).getTime()  , results.get(i).getTime());
		}
	}

	@Test
	void getAndParseResults() {

		List<Result> testList = new ArrayList<>();

		testList.add(new Result("Мария Швецова", "F", 10, 1750));
		testList.add(new Result("Денис Кораблёв", "M", 10, 1533));
		testList.add(new Result("Фёдор Курочкин", "M", 10, 1995));
		testList.add(new Result("Пётр Анисимович", "M", 10, 2548));
		testList.add(new Result("Ольга Цветкова", "F", 10, 1577));
		testList.add(new Result("Виктор Ковин", "M", 10, 2265));
		testList.add(new Result("Людмила Колонкова", "F", 10, 1664));
		testList.add(new Result("Борис Шипов", "M", 10, 1397));
		testList.add(new Result("Владимир Винокуров", "M", 5, 772));
		testList.add(new Result("Леонид Панов", "M", 5, 1341));
		testList.add(new Result("Евгений Жиров", "M", 5, 941));
		testList.add(new Result("Олег Платонов", "M", 5, 503));
		testList.add(new Result("Наталья Белова", "F", 5, 767));
		testList.add(new Result("Альбина Крутицкая", "F", 5, 994));
		testList.add(new Result("Евгения Анатольевна", "F", 5, 867));
		testList.add(new Result("Зоя Павловна", "F", 5, 1192));

		List<Result> results = getAndParseResults.getAndParseResults(new File(fileName));

		for(int i = 0; i < testList.size(); i++) {
			Assertions.assertEquals(testList.get(i).getName() , results.get(i).getName());
			Assertions.assertEquals(testList.get(i).getGender(), results.get(i).getGender());
			Assertions.assertEquals(testList.get(i).getDistance(), results.get(i).getDistance());
			Assertions.assertEquals(testList.get(i).getTime(), results.get(i).getTime());
		}
	}
}

