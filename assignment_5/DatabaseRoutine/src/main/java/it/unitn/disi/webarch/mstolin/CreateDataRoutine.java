package it.unitn.disi.webarch.mstolin;

import it.unitn.disi.webarch.mstolin.data.Store;
import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.entities.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.entities.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.entities.reservation.HotelReservationEntity;
import it.unitn.disi.webarch.mstolin.entities.reservation.ReservationEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateDataRoutine {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    private CreateDataRoutine(Set<Map<String, Object>> apartments, Set<Map<String, Object>> hotels) {
        EntityTransaction transaction = this.entityManager.getTransaction();
        try {
            transaction.begin();
            this.writeApartments(apartments);
            this.writeHotels(hotels);
            transaction.commit();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    public static void main(String[] args) {
        new CreateDataRoutine(Store.generateApartments(), Store.generateHotels());
    }

    private void writeApartments(Set<Map<String, Object>> apartments) throws ParseException {
        for (Map<String, Object> apartment : apartments) {
            ApartmentEntity apartmentEntity = new ApartmentEntity(
                    (String) apartment.get("name"),
                    (int) apartment.get("price"),
                    (int) apartment.get("finalCleaningFee"),
                    (int) apartment.get("maxPersons")
            );
            Set<ReservationEntity> reservations = this.generateApartmentReservations(apartmentEntity);
            apartmentEntity.setReservations(reservations);
            this.entityManager.persist(apartmentEntity);
        }
    }

    private void writeHotels(Set<Map<String, Object>> hotels) throws ParseException {
        for (Map<String, Object> hotel : hotels) {
            HotelEntity hotelEntity = new HotelEntity(
                    (String) hotel.get("name"),
                    (int) hotel.get("price"),
                    (int) hotel.get("extraHalfBoard"),
                    (int) hotel.get("stars"),
                    (int) hotel.get("places")
            );
            Set<ReservationEntity> reservations = this.generateHotelReservations(hotelEntity);
            hotelEntity.setReservations(reservations);
            this.entityManager.persist(hotelEntity);
        }
    }

    private Set<ReservationEntity> generateApartmentReservations(AccommodationEntity accommodation) throws ParseException {
        Set<ReservationEntity> reservations = new HashSet<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");
        Set<Integer> randomDays = this.getRandomNumberSet(4, 1, 28);
        for (Integer randomDay : randomDays) {
            Date dateOfReservation = formatter.parse("2022-2-" + randomDay);
            ReservationEntity reservation = new ReservationEntity(
                    "Mock Guest",
                    accommodation,
                    dateOfReservation,
                    dateOfReservation
            );
            reservations.add(reservation);
        }
        return reservations;
    }

    private Set<ReservationEntity> generateHotelReservations(AccommodationEntity accommodation) throws java.text.ParseException {
        Set<ReservationEntity> reservations = new HashSet<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");
        for (int i = 1; i <= 28; i++) {
            Date dateOfReservation = formatter.parse("2022-2-" + i);
            int randomOccupancy = (int) Math.round(
                    this.getRandomNumber(0.9, 1) * ((HotelEntity) accommodation).getPlaces()
            );
            HotelReservationEntity reservation = new HotelReservationEntity(
                    "Mock Guest",
                    accommodation,
                    dateOfReservation,
                    dateOfReservation,
                    randomOccupancy
            );
            reservations.add(reservation);
        }
        return reservations;
    }

    private Set<Integer> getRandomNumberSet(int length, int min, int max) {
        // Create all days
        ArrayList<Integer> allNums = new ArrayList<>();
        for (int i = min; i < max; i++) {
            allNums.add(i);
        }
        // Shuffle all days
        Collections.shuffle(allNums);
        // Select n random days
        Set<Integer> randomNums = new HashSet<>();
        for (int i = 0; i < length; i++) {
            randomNums.add(allNums.get(i));
        }
        return randomNums;
    }

    double getRandomNumber(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

}
