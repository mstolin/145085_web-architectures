package it.unitn.disi.webarch.mstolin.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Store {

    public static Set<Map<String, Object>> generateApartments() {
        Set<Map<String, Object>> apartments = new HashSet<>();

        Map<String, Object> pietraBianca = new HashMap<>();
        pietraBianca.put("name", "Pietra Bianca");
        pietraBianca.put("price", 40);
        pietraBianca.put("finalCleaningFee", 15);
        pietraBianca.put("maxPersons", 4);
        apartments.add(pietraBianca);

        Map<String, Object> saporeDiSale = new HashMap<>();
        saporeDiSale.put("name", "Sapore Di Sale");
        saporeDiSale.put("price", 80);
        saporeDiSale.put("finalCleaningFee", 20);
        saporeDiSale.put("maxPersons", 8);
        apartments.add(saporeDiSale);

        Map<String, Object> tenutaDiArtmimino = new HashMap<>();
        tenutaDiArtmimino.put("name", "Tenuta Di Artimino");
        tenutaDiArtmimino.put("price", 60);
        tenutaDiArtmimino.put("finalCleaningFee", 12);
        tenutaDiArtmimino.put("maxPersons", 6);
        apartments.add(tenutaDiArtmimino);

        return apartments;
    }

    public static Set<Map<String, Object>> generateHotels() {
        Set<Map<String, Object>> hotels = new HashSet<>();

        Map<String, Object> artemide = new HashMap<>();
        artemide.put("name", "Artemide");
        artemide.put("price", 100);
        artemide.put("extraHalfBoard", 20);
        artemide.put("stars", 4);
        artemide.put("places", 60);
        hotels.add(artemide);

        Map<String, Object> majestic = new HashMap<>();
        majestic.put("name", "Majestic");
        majestic.put("price", 65);
        majestic.put("extraHalfBoard", 15);
        majestic.put("stars", 4);
        majestic.put("places", 50);
        hotels.add(majestic);

        Map<String, Object> palace = new HashMap<>();
        palace.put("name", "Palace");
        palace.put("price", 200);
        palace.put("extraHalfBoard", 30);
        palace.put("stars", 5);
        palace.put("places", 25);
        hotels.add(palace);

        Map<String, Object> zenith = new HashMap<>();
        zenith.put("name", "Zenith");
        zenith.put("price", 70);
        zenith.put("extraHalfBoard", 18);
        zenith.put("stars", 3);
        zenith.put("places", 40);
        hotels.add(zenith);

        return hotels;
    }

}
