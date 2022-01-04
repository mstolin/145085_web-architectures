CREATE TABLE ACCOMMODATION(
  ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT ACCOMMODATION_PK PRIMARY KEY,
  DTYPE VARCHAR(255) NOT NULL,
  PRICE INTEGER NOT NULL,
  FINAL_CLEANING_FEE INTEGER, /* APARTMENT */
  MAX_PERSONS INTEGER, /* APARTMENT */
  EXTRA_HALF_BOARD INTEGER, /* HOTEL */
  STARS INTEGER, /* HOTEL */
  PLACES INTEGER /* HOTEL */
);

CREATE TABLE RESERVATION(
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT RESERVATION_PK PRIMARY KEY,
    ACCOMMODATION_ID INTEGER NOT NULL,
    CONSTRAINT ACCOMMODATION_FK FOREIGN KEY (ACCOMMODATION_ID) REFERENCES ACCOMMODATION(ID),
    GUEST_NAME VARCHAR(255) NOT NULL,
    DATE TIMESTAMP NOT NULL,
    OCCUPANCY INTEGER NOT NULL
);