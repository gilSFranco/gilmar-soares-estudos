package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Date checkIn, checkOut;
        int roomNumber;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            roomNumber = sc.nextInt();

            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());

            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("\nReservation: " + reservation);

            System.out.println();

            System.out.println("Enter the data to update the reservation: ");

            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());

            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            reservation.updateDates(checkIn, checkOut);
            System.out.println("\nReservation: " + reservation);
        }
        catch(ParseException e) {
            System.out.println("\nInvalid date format.");
        }
        catch(DomainException e) {
            System.out.println("\nError in reservation: " + e.getMessage());
        }
        catch(RuntimeException e) {
            System.out.println("\nUnexpected error.");
        }

        sc.close();
    }
}