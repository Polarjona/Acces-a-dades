# Acces-a-dades-RA1-PT2

## Requeriments

1. **Llegir un fitxer de text** (entrada.txt) amb **BufferedReader**.

2. Per cada línia llegida:
   - **Invertir la línia** (exemple: "Hola món" → "nóm aloH").

   - **Aplicar xifrat Cèsar:** cada caràcter es desplaça N posicions en Unicode.

3. Escriure el resultat a un fitxer de sortida (**xifrat.txt**) utilitzant **BufferedWriter**.

4. Crear un mètode per **desxifrar el fitxer**:
   - Llegir el fitxer xifrat.

   - Aplicar **desplaçament invers** de la clau.

   - Tornar a invertir cada línia per recuperar el missatge original.

   - Escriure el resultat a **desxifrat.txt**.

5. Opcional: permetre que l’usuari introdueixi la **clau de xifrat/desxifrat** per consola.

6. Mostrar per consola missatges de **progrés i errors**.
