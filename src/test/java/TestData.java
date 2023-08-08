import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestData
{
    @JsonProperty("flightDetails")
    private FlightDetails flightDetails;

    @JsonProperty("passengerCounts")
    private PassengerCount passengerCount;

    @JsonProperty("passengers")
    private List<Passenger> passengerList;

    public FlightDetails getFlightDetails() {
        return flightDetails;
    }

    public PassengerCount getPassengerCount() {
        return passengerCount;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }
    static class FlightDetails
    {
        @JsonProperty("tripType")
        private String tripType;

        @JsonProperty("origin")
        private String origin;

        @JsonProperty("destination")
        private String destination;

        @JsonProperty("departureDate")
        private String departureDate;

        @JsonProperty("returnDate")
        private String returnDate;

        @JsonProperty("fareType")
        private String fareType;

        public String getTripType() {
            return tripType;
        }

        public String getOrigin() {
            return origin;
        }

        public String getDestination() {
            return destination;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public String getFareType() {
            return fareType;
        }


    }

    static class PassengerCount
    {
        @JsonProperty("adults")
        private int adults;

        @JsonProperty("teens")
        private int teens;

        @JsonProperty("children")
        private int children;

        public int getAdults() {
            return adults;
        }

        public int getTeens() {
            return teens;
        }

        public int getChildren() {
            return children;
        }
     }
    static class Passenger {

        @JsonProperty("firstName")
        private String firstName;

        @JsonProperty("lastName")
        private String lastName;

        @JsonProperty("title")
        private String title;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getTitle() {
            return title;
        }
    }
    public static TestData get(String filename) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), TestData.class);
    }
}
