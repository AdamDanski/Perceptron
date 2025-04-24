import java.util.*;

public class Perceptron {
    private double[] wagi;
    private double prog;
    private double stalaUczenia;

    public Perceptron(int liczbaAtrybut, double stalaUczenia) {
        this.wagi = new double[liczbaAtrybut];
        this.prog = 0.0;
        this.stalaUczenia = stalaUczenia;
        losujWagi();
    }
    private void losujWagi() {
        Random rand = new Random();
        for (int i = 0; i < wagi.length; i++) {
            wagi[i] = rand.nextDouble() * 2 - 1;
        }
        prog = rand.nextDouble()*2-1;
    }
    private int klasyfikuj(double[] wejscia) {
        double sum = prog;
        for (int i = 0; i < wejscia.length; i++) {
            sum+=wejscia[i]*wagi[i];
        }
        return (sum >=0 ) ? 1 : -1;
    }
    private void nauka(double[] wejscia, int oczekiwaneWyjscie) {
        int przewidzianeWyjscie = klasyfikuj(wejscia);
        int blad = oczekiwaneWyjscie - przewidzianeWyjscie;

        if (blad != 0) {
            for (int i = 0; i < wagi.length; i++) {
                wagi[i] += stalaUczenia * wejscia[i]*blad;
            }
            prog += stalaUczenia * blad;
        }
    }
    public void trening(double[][] treningoweDane, int[] oczekiwaneWyjscie) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < treningoweDane.length; j++) {
                nauka(treningoweDane[j], oczekiwaneWyjscie[j]);
            }
        }
    }
    public void test(double[][] treningoweDane, int[] oczekiwaneWyjscie) {
        int poprwaneWyjscie = 0;
        for (int i = 0; i < treningoweDane.length; i++) {
            int przewidzianeWyjscie = klasyfikuj(treningoweDane[i]);
            if (przewidzianeWyjscie == oczekiwaneWyjscie[i]){
                poprwaneWyjscie++;
            }
        }
        double skutecznosc = (double) poprwaneWyjscie / treningoweDane.length * 100;
        System.out.println("skutecznosc: " + skutecznosc+"%");
    }
    public void klient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj wektor, wartosci oddziel spacja lub wpisz 'koniec' zeby skonczyc prace: ");
        while (true){
            System.out.println(" ");
            String wpisz = sc.nextLine();
            if (wpisz.equals("koniec")){
                System.out.println("Koniec pracy");
                break;
            }

            String[] liczby = wpisz.split(" ");
            double[] wektor = new double[wagi.length];

            if( liczby.length != wagi.length){
                System.out.println("nie poprawna ilosc danych");
                continue;
            }
            try{
                for (int i = 0; i < liczby.length; i++) {
                    wektor[i] = Double.parseDouble(liczby[i]);
                }
            }catch (NumberFormatException e){
                System.out.println("bledne foramt wartosci");
                continue;
            }
            int wynik = klasyfikuj(wektor);
            System.out.println("Klasyfikacja: " + (wynik==1 ? "Iris-setosa" : "Iris-versicolor"));
        }
    }
}
