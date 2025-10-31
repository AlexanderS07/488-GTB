import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Inserisci la lunghezza del percorso (in metri): ");
        int lunghezza = input.nextInt();
        input.nextLine(); // consuma il newline

        System.out.print("Quanti cavalli partecipano alla gara? ");
        int numeroCavalli = input.nextInt();
        input.nextLine();

        ArrayList<Cavallo> cavalli = new ArrayList<>();

        for (int i = 0; i < numeroCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nome = input.nextLine();
            cavalli.add(new Cavallo(nome));
        }

        boolean garaFinita = false;
        Cavallo vincitore = null;

        try (FileWriter writer = new FileWriter("risultato_gara.txt")) {

            writer.write("=== GARA DEI CAVALLI ===\n");
            writer.write("Lunghezza del percorso: " + lunghezza + " metri\n\n");

            // Ciclo di gara
            while (!garaFinita) {

                for (Cavallo c : cavalli) {
                    c.avanza();

                    String stato = c.getNome() + " è a " + c.getPosizione() + " metri.";
                    if (c.isAzzoppato()) {
                        stato += "Si è azzoppato!";
                    }

                    System.out.println(stato);
                    writer.write(stato + "\n");

                    if (c.haFinito(lunghezza)) {
                        garaFinita = true;
                        vincitore = c;
                        break;
                    }
                }

                writer.write("------------------------------\n");
                System.out.println("------------------------------");
            }

            writer.write("\nIl vincitore è " + vincitore.getNome() + "!\n");
            System.out.println("Il vincitore è " + vincitore.getNome() + "!");

            System.out.println("\nRisultato salvato nel file 'risultato_gara.txt'");

        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
        }

        input.close();
    }
}
