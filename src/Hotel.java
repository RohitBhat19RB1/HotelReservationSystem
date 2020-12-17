public class Hotel {
    private String name;
    private int rating;
    private int weekdayRateforRegularCustomer;
    private int weekendRateforRegularCustomer;
    private int weekdayRateforRewardCustomer;
    private int weekendRateforRewardCustomer;

    public Hotel() {

    }

    public Hotel(String name, int rating, int weekdayRateforRegularCustomer, int weekendRateforRegularCustomer, int weekdayRateforRewardCustomer, int weekendRateforRewardCustomer) {
        this.name = name;
        this.rating = rating;
        this.weekdayRateforRegularCustomer = weekdayRateforRegularCustomer;
        this.weekendRateforRegularCustomer = weekendRateforRegularCustomer;
        this.weekdayRateforRewardCustomer = weekdayRateforRewardCustomer;
        this.weekendRateforRewardCustomer = weekendRateforRewardCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getWeekdayRateforRegularCustomer() {
        return weekdayRateforRegularCustomer;
    }

    public void setWeekdayRateforRegularCustomer(int weekdayRateforRegularCustomer) {
        this.weekdayRateforRegularCustomer = weekdayRateforRegularCustomer;
    }

    public int getWeekdayRateforRewardCustomer() {
        return weekdayRateforRewardCustomer;
    }

    public void setWeekdayRateforRewardCustomer(int weekdayRateforRewardCustomer) {
        this.weekdayRateforRewardCustomer = weekdayRateforRewardCustomer;
    }

    public int getWeekendRateforRegularCustomer() {
        return weekendRateforRegularCustomer;
    }

    public void setWeekendRateforRegularCustomer(int weekendRateforRegularCustomer) {
        this.weekendRateforRegularCustomer = weekendRateforRegularCustomer;
    }

    public int getWeekendRateforRewardCustomer() {
        return weekendRateforRewardCustomer;
    }

    public void setWeekendRateforRewardCustomer(int weekendRateforRewardCustomer) {
        this.weekendRateforRewardCustomer = weekendRateforRewardCustomer;
    }
}
