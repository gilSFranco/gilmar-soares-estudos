package application;

import model.entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Date checkIn, checkOut;
        int roomNumber;
        Date now;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Room number: ");
        roomNumber = sc.nextInt();

        System.out.print("Check-in date (dd/MM/yyyy): ");
        checkIn = sdf.parse(sc.next());

        System.out.print("Check-out date (dd/MM/yyyy): ");
        checkOut = sdf.parse(sc.next());

        if(!checkOut.after(checkIn)) {
            System.out.println("\nError in reservation: Check-out date must be after check-in date.");
        } else{
            Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
            System.out.println("\nReservation: " + reservation);

            System.out.println();

            System.out.println("Enter the data to update the reservation: ");

            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());

            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            now = new Date();

            if(checkIn.before(now) || checkOut.before(now)) {
                System.out.println("\nError in reservation: Reservation dates for update must be future dates.");
            }
            else if(!checkOut.after(checkIn)) {
                System.out.println("\nError in reservation: Check-out date must be after check-in date.");
            }
            else {
                reservation.updatedates(checkIn, checkOut);
                System.out.println("\nReservation: " + reservation);
            }
        }




        sc.close();
    }
}