package il.co.ilrd.enumtest;

public class testWeekDays {

	public static void main(String[] args) {
		WeekDays day = WeekDays.MONDAY;
		System.out.println(day.getStringWeekDay() + "\n");
		System.out.println(day.fromValue(3) + "\n");
		System.out.println(day.fromValue(3) + "\n");
		System.out.println(day.toString());
	}

}
