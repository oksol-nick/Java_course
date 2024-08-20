public class Main {
	public static void main(String[ ] args) {
		
		String text = new String ("Домашнее задание 1. В тексте, который вы видите на этом изображении, посчитайте количество букв 'е' в каждом слове" +"\n"+
		"2. Напишите регулярное выражение для проверки телефона в международном формате" +"\n"+
		"3. С помощью регулярного выражения напишите функцию удаления всех букв и пробелов из текста");

		String phoneNumber = new String("+7(918) 258-87-56");

		charCounter(text);
		phoneCheck(phoneNumber);
		deleteSpaceLett(text);

	}

		public static void charCounter (String str) {
			System.out.println("Задание 1.");
			int summ = 0;
			for(int j = 0; j < str.split(" ").length; j++) {
				if(str.split(" ")[j].matches("[^0-9]+")) {
					int counterE = 0;
					for (int i = 0; i < str.split(" ")[j].length(); i++) {
						if (str.split(" ")[j].charAt(i) == 'е') {
							counterE++;
						}
					}
					summ = summ +  counterE;
					System.out.println("В слове " + "==" + str.split(" ")[j] + "==" + " " + counterE + " бук. 'е'" );

					}	
			}
			System.out.println( "\n" + "Всего в тексте " + summ + " бук. 'е'" + "\n" +"\n");			
		}

		public static void phoneCheck (String phoneNum) {
			System.out.println("Задание 2.");
			if (phoneNum.matches("^\\+7\\([0-9]{3}\\) [0-9]{3}\\-[0-9]{2}\\-[0-9]{2}$")) {
				System.out.println( "Номер телефона соответствует международному формату." + "\n" );
			} else System.out.println( "Номер телефона не соответствует формату ввода.\nВведите номер телефона в международном формате +7(000) 000-00-00" + "\n");
		}

		public static void deleteSpaceLett(String strForDel) {
			System.out.println("Задание 3.");
			strForDel = strForDel.replaceAll("[a-zA-Zа-яА-Я\n ]", "");
			System.out.println("Новый текст: " + strForDel);
		}
}
