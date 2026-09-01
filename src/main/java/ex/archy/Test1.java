package ex.archy;

/*
Existing accepted appointments:

- A: [10:00, 11:30) → **accepted** (no overlap)
- B: [12:30, 13:00) → **accepted** (no overlap)

Requests:

- C: [11:00, 11:45) → **accepted** (overlaps A; double booking allowed)
- D: [09:00, 10:30) → **accepted** (overlaps A; double booking allowed)
- E: [09:15, 10:15) → **rejected** (would create triple booking during [10:00, 10:15))* */

import java.time.LocalTime;
import java.util.ArrayList;

public class Test1 {

    static class  Appointment {
        LocalTime start;
        LocalTime end;
        boolean confirmed;


        public Appointment(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
        }

        public void confirm () {
            confirmed = true;
        }
    }

    static class Register {
//        should be thread safe
        ArrayList<Appointment> appointments = new ArrayList<>();

        public void registerAppointment (Appointment appointment) {
            if (!doubleBooking(appointment)) {
                appointment.confirm();
            }
            appointments.add(appointment);
        }

        private boolean overlap(Appointment a, Appointment b) {
            if (a.start.equals(b.start) && a.end.equals(b.end)) return true;
            if (a.start.isAfter(b.start) && a.end.isBefore(b.end)) return true;
            if (a.start.isAfter(b.start) && a.end.isAfter(b.end) && a.start.isBefore(b.end)) return true;
            if (a.start.isBefore(b.start) && a.end.isBefore(b.end) && a.end.isAfter(b.start)) return true;
            return a.start.isBefore(b.start) && a.end.isAfter(b.end);
        }

        private boolean doubleBooking(Appointment app) {
            int overlapCount = 0;
            for (Appointment a : appointments) {
                if (overlap(app, a)) {
                    overlapCount++;
                }
            }
            return overlapCount >= 2;
        }
    }

    public static void main(String[] args) {
        Register register = new Register();
        register.registerAppointment(new Appointment(LocalTime.parse("10:00"), LocalTime.parse("11:30")));
        register.registerAppointment(new Appointment(LocalTime.parse("12:30"), LocalTime.parse("13:30")));
        register.registerAppointment(new Appointment(LocalTime.parse("11:00"), LocalTime.parse("11:45")));
        register.registerAppointment(new Appointment(LocalTime.parse("09:00"), LocalTime.parse("10:30")));
        register.registerAppointment(new Appointment(LocalTime.parse("09:15"), LocalTime.parse("10:15")));

        register.appointments
                .forEach(a -> System.out.println("Appointment: " + a.start + " - " + a.end + " | Confirmed: " + a.confirmed));
    }
}
