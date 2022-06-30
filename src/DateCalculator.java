public class DateCalculator {
    /**
     * Parent function, calculates the date at a certain distance from another
     * @param date The base date, the starting position
     * @param num The number of days to go (can be negative or positive)
     * @return The Date that is num days ahead of date
     */
    public static Date addToDate(Date date, int num) {
        if (num > 0)
            return positiveAddToDate(date, num);
        if (num < 0)
            return negativeAddToDate(date, num);
        return date;
    }

    /**
     * Sub-function of addToDate, special case for when we add a positive number of days
     * @param date The base date, the starting position
     * @param num The number of days to go (only positive)
     * @return The Date that is num days ahead of date
     */
    public static Date positiveAddToDate(Date date, int num){
        int yearLength = yearLength(date.getYear());
        if (num > yearLength - dayNumber(date))
            return positiveAddToDate(new Date(0, 1, date.getYear() + 1), num - (yearLength - dayNumber(date)));

        int monthLength = monthLength(date.getMonth(), date.getYear());
        if (num > monthLength - date.getDay())
            return positiveAddToDate(new Date(0, date.getMonth() + 1, date.getYear()), num - (monthLength -date.getDay()));

        return new Date(date.getDay() + num, date.getMonth(), date.getYear());
    }
    /**
     * Sub-function of addToDate, special case for when we add a negative number of days
     * @param date The base date, the starting position
     * @param num The number of days to go (only negative)
     * @return The Date that is num days before date
     */
    public static Date negativeAddToDate(Date date, int num){
        if (-num >= dayNumber(date))
            return negativeAddToDate(new Date(31, 12, date.getYear() - 1), num + dayNumber(date));

        int prevMonthLength = monthLength(date.getMonth() - 1, date.getYear());
        if (-num >= date.getDay())
            return negativeAddToDate(new Date(prevMonthLength, date.getMonth() - 1, date.getYear()), num + date.getDay());

        return new Date(date.getDay() + num, date.getMonth(), date.getYear());
    }

    /**
     * Checks whether a year is a leap year in the Gregorian calendar
     * @param year The year to check
     * @return Whether year is a leap year
     */
    public static boolean isLeapYear(int year){
        return year%4 == 0 && (year%100 != 0 || year%400 == 0);
    }

    /**
     * Gets the length of a year according to whether is a leap year
     * @param year The year to check
     * @return The number of days in year
     */
    public static int yearLength(int year){
        return isLeapYear(year)? 366: 365;
    }

    /**
     * Gets the number of days for each month
     * @param month The month we want the number of days in
     * @param year The year, for leap year checking purposes
     * @return The number of days in month
     */
    public static int monthLength(int month, int year){
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> 30;
        };
    }

    /**
     * Finds the position of the day in the year, ranging from 1 for January 1st up to 365/6 for December 31st
     * @param date The date to check
     * @return The number of days since the end of the previous year
     */
    public static int dayNumber(Date date){
        int dayNumber = 0;
        int i = 1;
        while (date.getMonth() > i){
            dayNumber += monthLength(i, date.getYear());
            i++;
        }
        return dayNumber + date.getDay();
    }
}



