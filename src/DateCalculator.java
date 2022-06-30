public class DateCalculator {
    public static Date addToDate(Date date, int num) {
        // TODO: Add your code here...
        int yearLength = yearLength(date.getYear());
        if (num > yearLength)
            return addToDate(naiveAddToDate(date, 0, 0, 1), num - yearLength);
        if (num > yearLength - dayNumber(date))
            return addToDate(new Date(0, 1, date.getYear() + 1), num - (yearLength - dayNumber(date)));
        int monthLength = monthLength(date.getMonth(), date.getYear());
        if (num > monthLength)
            return addToDate(naiveAddToDate(date, 0, 1, 0), num - monthLength);
        if (num > monthLength - date.getDay())
            return addToDate(new Date(0, date.getMonth() + 1, date.getYear()), num - (monthLength -date.getDay()));
        return new Date(date.getDay() + num, date.getMonth(), date.getYear());
    }

    public static boolean isLeapYear(int year){
        return year%4 == 0 && (year%100 != 0 || year%400 == 0);
    }

    public static int yearLength(int year){
        return isLeapYear(year)? 366: 365;
    }

    //get number of days for each month
    public static int monthLength(int month, int year){
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> 30;
        };
    }

    public static int dayNumber(Date date){
        int dayNumber = 0;
        int i = 1;
        while (date.getMonth() > i){
            dayNumber += monthLength(i, date.getYear());
            i++;
        }
        return dayNumber + date.getDay();
    }
    public static Date naiveAddToDate(Date date, int extraDays, int extraMonths, int extraYears){
        return new Date(date.getDay() + extraDays, date.getMonth() + extraMonths, date.getYear() + extraYears);
    }
}


