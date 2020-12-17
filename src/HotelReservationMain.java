import java.text.ParseException;
import java.util.Scanner;

public class HotelReservationMain extends HotelServicesImplementation {

    public static void main(String[] args) throws ParseException {
        HotelServicesList myHotel = new HotelServicesImplementation();
        Scanner scan = new Scanner(System.in);
        boolean mainEntry = true;
        while (mainEntry) {
            System.out.println("\n<<<<<<<<<<<<<<<Welcome to Hotel Reservation>>>>>>>>>>>>>>>");
            System.out.println("----------------------------------------------------------");
            System.out.println();
            System.out.println("""
                    1. Add Existing Hotel
                    2. Add New Hotel
                    3. Find Cheapest Hotel
                    4. Find Cheapest Best Rated Hotel
                    5. Find Best Rated Hotel
                    6. Display All Hotels
                    7. Quit Application""");
            String usr_choice = scan.nextLine();
            switch (usr_choice) {
                case "1":
                    myHotel.addExistingHotels();
                    break;
                case "2":
                    myHotel.addHotel();
                    break;
                case "3":
                    myHotel.findCheapestHotel();
                    break;
                case "4":
                    myHotel.findCheapestBestRatedHotel();
                    break;
                case "5":
                    myHotel.findBestRatedHotel();
                    break;
                case "6":
                    myHotel.displayAllHotels();
                    break;
                case "7":
                    mainEntry = false;
                    break;
                default:
                    System.out.println("Enter Valid Option!!!");
            }
        }
    }
}
