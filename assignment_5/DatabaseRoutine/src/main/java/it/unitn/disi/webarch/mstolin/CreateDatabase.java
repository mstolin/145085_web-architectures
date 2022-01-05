package it.unitn.disi.webarch.mstolin;

import it.unitn.disi.webarch.mstolin.entities.ApartmentEntity;
import it.unitn.disi.webarch.mstolin.entities.HotelEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CreateDatabase {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            writeApartments(entityManager);
            writeHotels(entityManager);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private static void writeApartments(EntityManager entityManager) {
        ApartmentEntity pietraBianca = new ApartmentEntity("Pietra Bianca", 40, 15, 4);
        entityManager.persist(pietraBianca);
        ApartmentEntity saporeDiSale = new ApartmentEntity("Sapore Di Sale", 80, 20, 8);
        entityManager.persist(saporeDiSale);
        ApartmentEntity tenutaDiArtmimino = new ApartmentEntity("Tenuta Di Artimino", 60, 12, 6);
        entityManager.persist(tenutaDiArtmimino);
    }

    private static void writeHotels(EntityManager entityManager) {
        HotelEntity artemide = new HotelEntity("Artemide", 100, 20, 4, 60);
        entityManager.persist(artemide);
        HotelEntity majestic = new HotelEntity("Majestic", 65, 15, 3, 50);
        entityManager.persist(majestic);
        HotelEntity palace = new HotelEntity("Palace", 200, 30, 5, 25);
        entityManager.persist(palace);
        HotelEntity zenith = new HotelEntity("Zenith", 70, 18, 3, 40);
        entityManager.persist(zenith);
    }

}
