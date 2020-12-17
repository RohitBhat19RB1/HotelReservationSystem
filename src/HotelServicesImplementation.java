import java.util.*;
import java.time.LocalDate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import static java.util.Collections.sort;

public class HotelServicesImplementation implements HotelServicesList {
    ArrayList<Hotel> hotelList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    @Override
    public void addExistingHotels() {
        hotelList.add(new Hotel("Lakewood",3,110,90,80,80));
        hotelList.add(new Hotel("Bridgewood",4,160,60,110,50));
        hotelList.add(new Hotel("Ridgewood",5,220,150,100,40));
        System.out.println("Existing Hotels are Added Successfully");
    }

    @Override
    public void addHotel() {
        String hotelName;
        System.out.println("Enter Name of Hotel: ");
        do {
            hotelName = scan.nextLine();
        } while (hotelName.isEmpty());
        System.out.println("Enter Rating of Hotel: ");
        int hotelRating = scan.nextInt();
        System.out.println("Enter Rate on Weekdays for Regular Customer: ");
        int rate_weekday_regularC = scan.nextInt();
        System.out.println("Enter Rate on Weekends for Regular Customer: ");
        int rate_weekend_regularC = scan.nextInt();
        System.out.println("Enter Rate on Weekdays for Reward Customer: ");
        int rate_weekday_rewardC = scan.nextInt();
        System.out.println("Enter Rate on Weekends for Reward Customer: ");
        int rate_weekend_rewardC = scan.nextInt();
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        hotel.setRating(hotelRating);
        hotel.setWeekdayRateforRegularCustomer(rate_weekday_regularC);
        hotel.setWeekendRateforRegularCustomer(rate_weekend_regularC);
        hotel.setWeekdayRateforRewardCustomer(rate_weekday_rewardC);
        hotel.setWeekendRateforRewardCustomer(rate_weekend_rewardC);
        hotelList.add(hotel);
        System.out.println("Hotel Added Successfully!!!");
    }

    @Override
    public boolean checkForRewardCustomer() {
        boolean rewardCustomer = true;
        boolean entry = true;
        while (entry) {
            System.out.println("Select Customer Type");
            System.out.println("1. Reward Customer");
            System.out.println("2. Regular Customer");
            String user_option = scan.nextLine();
            if (user_option.equalsIgnoreCase("1")) {
                System.out.println("Customer is Reward Customer");
                entry = false;
                break;
            } else if(user_option.equalsIgnoreCase("2")) {
                System.out.println("Customer is Regular Customer");
                rewardCustomer = false;
                entry = false;
                break;
            } else {
                System.out.println("Enter Valid Option!!");
            }
        }
        return rewardCustomer;
    }

    @Override
    public String getCheckInDate() {
        String checkInDate;
        System.out.println("\nEnter Check-In Date");
        System.out.println("\nEnter Year in pattern like 'yyyy' for Example:- '2020'");
        System.out.println("Enter Year: ");
        String checkInDateYear;
        do {
            checkInDateYear = scan.nextLine();
        } while (checkInDateYear.isEmpty());

        System.out.println("\nEnter Month in pattern like 'mm' for Example:- '09'");
        System.out.println("Enter Month: ");
        String checkInDateMonth = scan.nextLine();

        System.out.println("\nEnter Day in pattern like 'dd' for Example:- '17'");
        System.out.println("Enter Day: ");
        String checkInDateDay = scan.nextLine();

        //Given Check-In Date by User
        checkInDate = checkInDateYear+"-"+checkInDateMonth+"-"+checkInDateDay;
        return checkInDate;
    }

    @Override
    public String getCheckInDay(String Date) throws ParseException {
        String checkInDay;
        //Create Date Object of Given Dates
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkIn = DateFormat.parse(Date);

        //Find Day using Date Objects
        DateFormat DayFormat = new SimpleDateFormat("EEEE");
        checkInDay = DayFormat.format(checkIn);
        return checkInDay;
    }

    @Override
    public String getCheckOutDate() {
        String checkOutDate;
        System.out.println("\nEnter Check-Out Date");
        System.out.println("\nEnter Year in pattern like 'yyyy' for Example:- '2020'");
        System.out.println("Enter Year: ");
        String checkOutDateYear;
        do {
            checkOutDateYear = scan.nextLine();
        } while (checkOutDateYear.isEmpty());

        System.out.println("\nEnter Month in pattern like 'mm' for Example:- '09'");
        System.out.println("Enter Month: ");
        String checkOutDateMonth = scan.nextLine();

        System.out.println("\nEnter Day in pattern like 'dd' for Example:- '17'");
        System.out.println("Enter Day: ");
        String checkOutDateDay = scan.nextLine();

        //Given Check-Out Date by User
        checkOutDate = checkOutDateYear+"-"+checkOutDateMonth+"-"+checkOutDateDay;
        return checkOutDate;
    }

    @Override
    public String getCheckOutDay(String Date) throws ParseException {
        String checkOutDay;
        //Create Date Object of Given Dates
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkOut = DateFormat.parse(Date);

        //Find Day using Date Objects
        DateFormat DayFormat = new SimpleDateFormat("EEEE");
        checkOutDay = DayFormat.format(checkOut);
        return checkOutDay;
    }

    @Override
    public int getStayDays(String checkInDate, String checkOutDate) {
        long noOfDaysBetween;
        //Creating LocalDate Object of Given Dates
        LocalDate dateBefore = LocalDate.parse(checkInDate);
        LocalDate dateAfter = LocalDate.parse(checkOutDate);

        //Calculating number of Days between Given Dates
        noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
        return (int) noOfDaysBetween;
    }

    @Override
    public ArrayList<String> getListofDays(String checkInDate, String checkOutDate) throws ParseException {
        LocalDate start = LocalDate.parse(checkInDate);
        LocalDate end = LocalDate.parse(checkOutDate);
        List<LocalDate> totalDates = new ArrayList<>();
        List<String> listOfDates = new ArrayList<>();
        ArrayList<String> listOfDays = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }

        for (LocalDate date: totalDates) {
            listOfDates.add(date.toString());
        }

        for (String date: listOfDates) {
            String day = getCheckOutDay(date);
            listOfDays.add(day);
        }
        return listOfDays;
    }

    @Override
    public void findCheapestHotel() throws ParseException {
        boolean rewardCustomer = checkForRewardCustomer();
        String checkInDate = getCheckInDate();
        String checkOutDate = getCheckOutDate();
        HashMap<String,Integer> mapHotelCost = new HashMap<>();
        ArrayList<String> listOfDays = getListofDays(checkInDate,checkOutDate);
        ArrayList<Integer> listOfTotalCost = new ArrayList<>();
        for (Hotel hotel: hotelList) {
            int totalCost = 0;
            String hotelName = hotel.getName();
            int rateForWeekday;
            int rateForWeekend;
            if (rewardCustomer) {
                rateForWeekday = hotel.getWeekdayRateforRewardCustomer();
                rateForWeekend = hotel.getWeekendRateforRewardCustomer();
            } else {
                rateForWeekday = hotel.getWeekdayRateforRegularCustomer();
                rateForWeekend = hotel.getWeekendRateforRegularCustomer();
            }
            for (String day : listOfDays) {
                if(day.equalsIgnoreCase("Saturday") | day.equalsIgnoreCase("Sunday")) {
                    totalCost += rateForWeekend;
                } else {
                    totalCost += rateForWeekday;
                }
            }
            listOfTotalCost.add(totalCost);
            mapHotelCost.put(hotelName,totalCost);
        }
        sort(listOfTotalCost);
        mapHotelCost.forEach((k,v) -> {
            System.out.println("Hotel "+k+" Total Amount "+v+"$");
        });
        System.out.println();
        for(Map.Entry<String,Integer> entry : mapHotelCost.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if(value == listOfTotalCost.get(0)) {
                System.out.println("Best Option for you is Hotel "+key+" having Total Amount "+value+"$");
            }
        }
    }

    @Override
    public void findCheapestBestRatedHotel() throws ParseException {
        boolean rewardCustomer = checkForRewardCustomer();
        String checkInDate = getCheckInDate();
        String checkOutDate = getCheckOutDate();
        HashMap<String,Integer> mapHotelCost = new HashMap<>();
        ArrayList<String> listOfDays = getListofDays(checkInDate,checkOutDate);
        ArrayList<Integer> listOfTotalCost = new ArrayList<>();
        for (Hotel hotel: hotelList) {
            if (hotel.getRating() >= 4) {
                int totalCost = 0;
                String hotelName = hotel.getName();
                int rateForWeekday;
                int rateForWeekend;
                if (rewardCustomer) {
                    rateForWeekday = hotel.getWeekdayRateforRewardCustomer();
                    rateForWeekend = hotel.getWeekendRateforRewardCustomer();
                } else {
                    rateForWeekday = hotel.getWeekdayRateforRegularCustomer();
                    rateForWeekend = hotel.getWeekendRateforRegularCustomer();
                }
                for (String day : listOfDays) {
                    if (day.equalsIgnoreCase("Saturday") | day.equalsIgnoreCase("Sunday")) {
                        totalCost += rateForWeekend;
                    } else {
                        totalCost += rateForWeekday;
                    }
                }
                listOfTotalCost.add(totalCost);
                mapHotelCost.put(hotelName, totalCost);
            }
        }
        sort(listOfTotalCost);
        mapHotelCost.forEach((k,v) -> {
            System.out.println("Hotel "+k+" Total Amount "+v+"$");
        });
        System.out.println();
        for(Map.Entry<String,Integer> entry : mapHotelCost.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if(value == listOfTotalCost.get(0)) {
                System.out.println("Best Option for you is Hotel "+key+" having Total Amount "+value+"$");
            }
        }
    }

    @Override
    public void findBestRatedHotel() throws ParseException {
        ArrayList<Hotel> bestRatedHotels = new ArrayList<>();
        ArrayList<Integer> hotelRatings = new ArrayList<>();
        for (Hotel hotel: hotelList) {
            int rating = hotel.getRating();
            hotelRatings.add(rating);
        }
        sort(hotelRatings);
        int highestRating = hotelRatings.get(hotelRatings.size()-1);
        for (Hotel hotel: hotelList) {
            if (hotel.getRating() == highestRating) {
                bestRatedHotels.add(hotel);
            }
        }
        String checkInDate = getCheckInDate();
        String checkOutDate = getCheckOutDate();
        HashMap<String,Integer> mapHotelCost = new HashMap<>();
        ArrayList<String> listOfDays = getListofDays(checkInDate,checkOutDate);
        ArrayList<Integer> listOfTotalCost = new ArrayList<>();
        for (Hotel hotel: bestRatedHotels) {
            int totalCost = 0;
            String hotelName = hotel.getName();
            int rateForWeekday = hotel.getWeekdayRateforRegularCustomer();
            int rateForWeekend = hotel.getWeekendRateforRegularCustomer();
            for (String day : listOfDays) {
                if(day.equalsIgnoreCase("Saturday") | day.equalsIgnoreCase("Sunday")) {
                    totalCost += rateForWeekend;
                } else {
                    totalCost += rateForWeekday;
                }
            }
            listOfTotalCost.add(totalCost);
            mapHotelCost.put(hotelName,totalCost);
        }
        sort(listOfTotalCost);
        mapHotelCost.forEach((k,v) -> {
            System.out.println("Hotel "+k+" Total Amount "+v+"$");
        });
        System.out.println();
        for(Map.Entry<String,Integer> entry : mapHotelCost.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if(value == listOfTotalCost.get(0)) {
                System.out.println("Best Option for you is Hotel "+key+" having Total Amount "+value+"$");
            }
        }
    }

    @Override
    public void displayAllHotels() {
        for (Hotel hotel : hotelList) {
            System.out.println();
            System.out.println("Hotel Name: "+hotel.getName());
            System.out.println("Hotel Rating: "+hotel.getRating());
            System.out.println("Following Rates are of Per Day:");
            System.out.println("***Weekday Rates***");
            System.out.println("\t For Regular Customers: "+hotel.getWeekdayRateforRegularCustomer()+"$");
            System.out.println("\t For Reward Customers: "+hotel.getWeekdayRateforRewardCustomer()+"$");
            System.out.println("***Weekend Rates***");
            System.out.println("\t For Regular Customers: "+hotel.getWeekendRateforRegularCustomer()+"$");
            System.out.println("\t For Reward Customers: "+hotel.getWeekendRateforRewardCustomer()+"$");
        }
    }
}