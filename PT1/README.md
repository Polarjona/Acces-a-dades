# Acces-a-dades-RA1-PT1

## Exercici — Lectura i anàlisi d’un fitxer de text amb Java

Fes un programa en Java que llegeixi el contingut d’un fitxer de text anomenat **text.txt** i mostri la informació següent:

1. El **nombre total de caràcters**, sense comptar els salts de línia.
   - Els espais i les tabulacions **sí que compten** com a caràcters.

2. El **nombre total de línies** del fitxer.

3. El **nombre total de paraules**.
   - Considerarem una paraula qualsevol seqüència de caràcters separada per un o més espais, tabulacions o salts de línia.

4. El **caràcter que apareix més vegades**.
   - No s'han de tenir en compte els espais, les tabulacions ni els salts de línia.
   - Les majúscules i les minúscules es consideraran caràcters diferents. Per exemple, **A** i **a** són dos caràcters diferents.

5. El programa haurà de gestionar correctament les possibles **excepcions relacionades amb la lectura del fitxer**.

L'objectiu és llegir el fitxer caràcter a **Caràcter utilitzant FileReader**.

## Exemple

Suposem que el fitxer text.txt conté:

Hola món
Java és fàcil

El programa podria mostrar una sortida similar a:

Nombre de caràcters: 20
Nombre de línies: 2
Nombre de paraules: 5
Caràcter més repetit: a

El resultat exacte del caràcter més repetit dependrà del contingut del fitxer.
