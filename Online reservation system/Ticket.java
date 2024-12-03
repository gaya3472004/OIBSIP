
import java.util.*;

class Ticket {
    int pnr;
    String passengerName, trainName, travelClass, source, destination, journeyDate;
    int trainNumber;

    public Ticket(int pnr, String passengerName, int trainNumber, String trainName, String travelClass, String source, String destination, String journeyDate) {
        this.pnr = pnr;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.travelClass = travelClass;
        this.source = source;
        this.destination = destination;
        this.journeyDate = journeyDate;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + ", Passenger: " + passengerName + ", Train: " + trainNumber + " - " + trainName +
                ", Class: " + travelClass + ", From: " + source + " To: " + destination + ", Date: " + journeyDate;
    }
}

