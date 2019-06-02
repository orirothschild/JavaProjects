package il.co.ilrd.enumtest;

public enum WeekDays {
	SUNDAY(0),
	MONDAY(1),
	THUSDAY(2),
	WENDSDAY(3),
	thursday(4),
	Friday(5),
	Saturday(6);
	
	private int value;
	
	WeekDays(int day){
		value = day;
	}

	public int getDay() {
		return ordinal() + 1;
	}
	
	public String getStringWeekDay() {
	    return toString();
	}
	
	public WeekDays fromValue(int i) {
	    return values()[i];
	}
	public void PrintDays() {
	for(WeekDays day: values()) {
		System.out.println(day.value);
	}
		
	}
	
	public boolean enumCompare(WeekDays i) {
	    return this.equals(i);
	}
	@Override
	
	public String toString() {
		String orginalString = super.toString();
		return (orginalString.substring(0,1) + orginalString.substring(1).toLowerCase());
	}

}
