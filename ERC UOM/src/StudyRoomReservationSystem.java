import java.util.ArrayList;
import java.util.List;

public class StudyRoomReservationSystem {


    private List<StudyRoom> studyRooms;

    public StudyRoomReservationSystem() {
        studyRooms = new ArrayList<>();
    }

    public void addStudyRoom(StudyRoom room) {
        studyRooms.add(room);
    }

    public void reserveStudyRoom(int roomNumber) throws StudyRoomUnavailableException {
        StudyRoom studyRoom = getStudyRoomByNumber(roomNumber);

        if (studyRoom == null) {
            throw new IllegalArgumentException("Invalid room number.");
        }

        synchronized (studyRoom) {
            if (studyRoom.isAvailability()) {
                studyRoom.setAvailability(false);
                System.out.println("Study room " + roomNumber + " reserved successfully.");
            } else {
                throw new StudyRoomUnavailableException("Study room " + roomNumber + " is already occupied.");
            }
        }
    }

    public void releaseStudyRoom(int roomNumber) throws StudyRoomUnavailableException {
        StudyRoom studyRoom = getStudyRoomByNumber(roomNumber);

        if (studyRoom == null) {
            throw new IllegalArgumentException("Invalid room number.");
        }

        synchronized (studyRoom) {
            if (!studyRoom.isAvailability()) {
                studyRoom.setAvailability(true);
                System.out.println("Study room " + roomNumber + " released successfully.");
            } else {
                throw new StudyRoomUnavailableException("Study room " + roomNumber + " is already available.");
            }
        }
    }

    public void displayStudyRoomStatus() {
        for (StudyRoom studyRoom : studyRooms) {
            if (studyRoom.isAvailability() == true)
                System.out.println("Room Number: " + studyRoom.getRoomNumber() + ", Capacity: " + studyRoom.getCapacity() + ", Availability: Available");
            else
                System.out.println("Room Number: " + studyRoom.getRoomNumber() + ", Capacity: " + studyRoom.getCapacity() + ", Availability: Occupied");
        }
    }

    private StudyRoom getStudyRoomByNumber(int roomNumber) {
        for (StudyRoom studyRoom : studyRooms) {
            if (studyRoom.getRoomNumber() == roomNumber) {
                return studyRoom;
            }
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        StudyRoomReservationSystem reservationSystem = new StudyRoomReservationSystem();

        // Create multiple study room objects with different room numbers and capacities
        StudyRoom room1 = new StudyRoom(1, 4);
        StudyRoom room2 = new StudyRoom(2, 6);
        StudyRoom room3 = new StudyRoom(3, 8);

        // Add study rooms to the reservation system
        reservationSystem.addStudyRoom(room1);
        reservationSystem.addStudyRoom(room2);
        reservationSystem.addStudyRoom(room3);


        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    // Reserve room 101
                    reservationSystem.reserveStudyRoom(1);
                    Thread.sleep(1000);
                    reservationSystem.releaseStudyRoom(1);
                } catch (StudyRoomUnavailableException e) {
                    System.out.println("Exception: " + e.getMessage());
                } catch (IllegalArgumentException x){
                    System.out.println("Exception: " + x.getMessage());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    // Reserve room 101
                    reservationSystem.reserveStudyRoom(2);
                    Thread.sleep(1000);
                    reservationSystem.releaseStudyRoom(2);
                } catch (StudyRoomUnavailableException e) {
                    System.out.println("Exception: " + e.getMessage());
                } catch (IllegalArgumentException x){
                    System.out.println("Exception: " + x.getMessage());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                try {
                    // Reserve room 101
                    reservationSystem.reserveStudyRoom(3);
                    Thread.sleep(1000);
                    reservationSystem.releaseStudyRoom(3);
                } catch (StudyRoomUnavailableException e) {
                    System.out.println("Exception: " + e.getMessage());
                } catch (IllegalArgumentException x){
                    System.out.println("Exception: " + x.getMessage());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Study Room Status:");
        reservationSystem.displayStudyRoomStatus();

    }
}

class StudyRoom {
    private int roomNumber;
    private int capacity;
    private boolean availability;

    public StudyRoom(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.availability = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

class StudyRoomUnavailableException extends Exception {
    public StudyRoomUnavailableException(String message) {
        super(message);
    }
}