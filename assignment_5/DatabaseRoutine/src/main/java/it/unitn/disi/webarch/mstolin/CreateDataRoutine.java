package it.unitn.disi.webarch.mstolin;

import it.unitn.disi.webarch.mstolin.data.Store;
import it.unitn.disi.webarch.mstolin.entities.accommodation.AccommodationEntity;
import it.unitn.disi.webarch.mstolin.entities.accommodation.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.entities.accommodation.HotelEntity;
import it.unitn.disi.webarch.mstolin.entities.occupancy.AccommodationOccupancy;
import it.unitn.disi.webarch.mstolin.entities.occupancy.ApartmentOccupancy;
import it.unitn.disi.webarch.mstolin.entities.occupancy.HotelOccupancy;

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
            Set<AccommodationOccupancy> occupancies = this.generateApartmentOccupancies(apartmentEntity);
            apartmentEntity.setOccupancies(occupancies);
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
            Set<AccommodationOccupancy> occupancies = this.generateHotelOccupancies(hotelEntity);
            hotelEntity.setOccupancies(occupancies);
            this.entityManager.persist(hotelEntity);
        }
    }

    private Set<AccommodationOccupancy> generateApartmentOccupancies(AccommodationEntity accommodation) throws ParseException {
        Set<AccommodationOccupancy> occupancies = new HashSet<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");
        Set<Integer> randomDays = this.getRandomNumberSet(4, 1, 28);
        for (Integer i = 1; i <= 28; i++) {
            Date dateOfReservation = formatter.parse("2022-2-" + i);
            boolean isAvailable = !randomDays.contains(i);
            ApartmentOccupancy apartmentOccupancy = new ApartmentOccupancy(
                    accommodation,
                    dateOfReservation,
                    isAvailable
            );
            occupancies.add(apartmentOccupancy);
        }
        return occupancies;
    }

    private Set<AccommodationOccupancy> generateHotelOccupancies(AccommodationEntity accommodation) throws java.text.ParseException {
        Set<AccommodationOccupancy> occupancies = new HashSet<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");
        for (int i = 1; i <= 28; i++) {
            Date dateOfReservation = formatter.parse("2022-2-" + i);
            int randomOccupancy = (int) Math.round(
                    this.getRandomNumber(0.9, 1) * ((HotelEntity) accommodation).getPlaces()
            );
            HotelOccupancy hotelOccupancy = new HotelOccupancy(accommodation, dateOfReservation, randomOccupancy);
            occupancies.add(hotelOccupancy);
        }
        return occupancies;
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
