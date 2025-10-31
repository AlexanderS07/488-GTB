import java.util.Random;

public class Cavallo {
    private String nome;
    private int posizione;
    private boolean azzoppato;
    private Random rnd = new Random();

    public Cavallo(String nome) {
        this.nome = nome;
        this.posizione = 0;
        this.azzoppato = false;
    }

    public void avanza() {
        if (azzoppato) return; // il cavallo azzoppato non si muove

        // 5% di probabilità che si azzoppi
        if (rnd.nextInt(100) < 5) {
            azzoppato = true;
            return;
        }

        // Avanza di un numero casuale di metri (1-10)
        posizione += rnd.nextInt(10) + 1;
    }

    public boolean haFinito(int lunghezza) {
        return posizione >= lunghezza;
    }

    public String getNome() {
        return nome;
    }

    public int getPosizione() {
        return posizione;
    }

    public boolean isAzzoppato() {
        return azzoppato;
    }
}
