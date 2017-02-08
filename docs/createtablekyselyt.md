## Alue

```sql

CREATE TABLE Alue 
(
    id integer PRIMARY KEY,
    otsikko varchar(300) NOT NULL UNIQUE,
    kuvaus varchar(600) NOT NULL UNIQUE
);

```

## Keskustelu

```sql

CREATE TABLE Keskustelu 
(
    id integer PRIMARY KEY,
    alue integer NOT NULL,
    otsikko varchar(300) NOT NULL,
    luontiAika timestamp NOT NULL,
    FOREIGN KEY(alue) REFERENCES Alue(id)
);

```

## Viesti

```sql

CREATE TABLE Viesti 
(
    id integer PRIMARY KEY,
    alue integer NOT NULL,
    keskustelu integer NOT NULL,
    nimimerkki varchar(100) NOT NULL,
    viesti varchar(4000) NOT NULL,
    lahetysAika timestamp NOT NULL,
    FOREIGN KEY(alue) REFERENCES Alue(id),
    FOREIGN KEY(keskustelu) REFERENCES Keskutelu(id)
);

```