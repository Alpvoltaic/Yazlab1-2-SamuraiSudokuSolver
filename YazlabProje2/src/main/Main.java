package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;

public class Main {

    static JFrame pencere;
    static Arayüz arayüz;
    public static int[][] harita;
    public static int satır, sütun;
    public static int[][] S0;
    public static int[][] S1;
    public static int[][] S2;
    public static int[][] S3;
    public static int[][] S4;
    public static int[][] S1yedek;
    public static int[][] S2yedek;
    public static int[][] S3yedek;
    public static int[][] S4yedek;
    public static int[][] S0yedek;
    public static int sayaç;
    public Thread t1;
    public Thread t2;
    public Thread t3;
    public Thread t4;
    public Thread t5;

    public static void main(String[] args) {
        harita = new int[21][21];
        S0 = new int[9][9];
        S1 = new int[9][9];
        S2 = new int[9][9];
        S3 = new int[9][9];
        S4 = new int[9][9];
        S1yedek = new int[9][9];
        S2yedek = new int[9][9];
        S3yedek = new int[9][9];
        S4yedek = new int[9][9];
        S0yedek = new int[9][9];
        satır = 0;
        sütun = 0;
        sayaç = 0;

        arayüz = new Arayüz();
        arayüz.setFocusable(true);
        dosyayıOku();
        parçala(harita);

    }

    static class sudokuThread implements Runnable {

        private int[][] samurai;
        private int N;
        private int parametre;

        public sudokuThread(int[][] samurai, int N, int parametre) {
            this.samurai = samurai;
            this.N = N;
            this.parametre = parametre;
        }

        @Override
        public void run() {

            giriş:

            for (;;) {

                if (parametre == 1) {

                    samurai = S1yedek;
                    S2 = S2yedek;
                    S3 = S3yedek;
                }
                if (parametre == 2) {
                    samurai = S2yedek;
                    S1 = S1yedek;
                    S4 = S4yedek;
                }
                if (parametre == 3) {
                    samurai = S3yedek;
                    S1 = S1yedek;
                    S4 = S4yedek;
                }
                if (parametre == 4) {
                    samurai = S4yedek;
                    S2 = S2yedek;
                    S3 = S3yedek;
                }
                int kontrol = 0;

                if (SudokuÇöz(samurai, N, parametre)) {
                    if (parametre == 1) {
                        //System.out.println("Sol üst çözülmeye başladı.");
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                S0[i][j] = samurai[i + 6][j + 6];

                            }

                        }
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                harita[i][j] = samurai[i][j]; //sol üst

//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                                arayüz.repaint();

                            }
                        }
                        sayaç++;

                        //   System.out.println("Sol üst çözüldü." + sayaç);
                        //                 break;
                    } else if (parametre == 2) {
                        //         System.out.println("Sol alt çözülmeye başladı.");
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                S0[i + 6][j] = samurai[i][j + 6];

                            }

                        }
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                harita[i + 12][j] = samurai[i][j]; //sol alt
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                                arayüz.repaint();

                            }
                        }
                        sayaç++;
                        //        System.out.println("Sol alt çözüldü." + sayaç);
                        //                   break;

                    } else if (parametre == 3) {
                        //         System.out.println("Sağ üst çözülmeye başladı.");
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                S0[i][j + 6] = samurai[i + 6][j];

                            }

                        }
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                harita[i][j + 12] = samurai[i][j]; //sağ üst
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                                arayüz.repaint();

                            }
                        }
                        sayaç++;
                        //           System.out.println("Sağ üst çözüldü." + sayaç);
                        //                   break;

                    } else if (parametre == 4) {
                        //           System.out.println("Sağ alt çözülmeye başladı.");
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                S0[i + 6][j + 6] = samurai[i][j];

                            }

                        }
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                harita[i + 12][j + 12] = samurai[i][j]; //sağ alt
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                                arayüz.repaint();
                            }
                        }
                        sayaç++;
                        //           System.out.println("Sağ alt çözüldü." + sayaç);
                        //                       break;

                    } else if (parametre == 5) {

                        //          System.out.println("Orta çözülmeye başladı.");
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                harita[i + 6][j + 6] = samurai[i][j]; //orta
//                                try {
//                                    Thread.sleep(100);
//                                } catch (InterruptedException ex) {
//                                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//                                }
                                arayüz.repaint();
                            }
                        }
                        //         System.out.println("Orta çözüldü.");
                        break;
                    }
//                arayüz.repaint();
//                break;

                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (S1[i][j] != -1 && S2[i][j] != -1 && S3[i][j] != -1 && S4[i][j] != -1) {
                                kontrol++;

                            }

                        }

                    }

                    if (kontrol == 81) {

                        System.out.println("Köşeler çözüldü.");
                        Thread t5 = new Thread(new sudokuThread(S0, N, 5));
                        t5.start();

                        break;
                    }

                } else {

                    for (;;) {
                        continue giriş;
                    }

                }
            }

            // System.out.println("Thread bitti.");
        }
    }

    static void çöz() {
        int N = 9;
        Thread t1 = new Thread(new sudokuThread(S1, N, 1));
        Thread t2 = new Thread(new sudokuThread(S2, N, 2));
        Thread t3 = new Thread(new sudokuThread(S3, N, 3));
        Thread t4 = new Thread(new sudokuThread(S4, N, 4));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    static void dosyayıOku() {
        try {
            File dosya = new File("sudoku.txt");
            BufferedReader oku = new BufferedReader(new InputStreamReader(
                    new FileInputStream(dosya), "UTF-8"));

            String dosyasatır;

            while ((dosyasatır = oku.readLine()) != null) {
                haritaOluştur(dosyasatır);
            }

        } catch (Exception e) {
        }
    }

    static void haritaOluştur(String dosyasatır) {
        //satır uzunluğuna göre boşlukları ekle
        sütun = 0;

        if (dosyasatır.length() == 9) {
            for (int i = 0; i < dosyasatır.length() + 12; i++) {
                if (i < 6 || i > 14) {
                    harita[satır][sütun] = 0;
                    sütun++;
                } else {
                    if (dosyasatır.charAt(i - 6) == '*') {
                        harita[satır][sütun] = -1;
                        sütun++;
                    } else {

                        harita[satır][sütun] = Integer.parseInt(String.valueOf(dosyasatır.charAt(i - 6)));
                        sütun++;
                    }

                }
            }

        }
        if (dosyasatır.length() == 18) {

            for (int i = 0; i < dosyasatır.length() + 3; i++) {
                if (i < 9) {
                    if (dosyasatır.charAt(i) == '*') {
                        harita[satır][sütun] = -1;
                        sütun++;
                    } else {

                        harita[satır][sütun] = Integer.parseInt(String.valueOf(dosyasatır.charAt(i)));
                        sütun++;
                    }
                } else if (i == 9 || i == 10 || i == 11) {
                    harita[satır][sütun] = 0;
                    sütun++;
                } else {
                    if (dosyasatır.charAt(i - 3) == '*') {
                        harita[satır][sütun] = -1;
                        sütun++;
                    } else {
                        harita[satır][sütun] = Integer.parseInt(String.valueOf(dosyasatır.charAt(i - 3)));
                        sütun++;
                    }
                }
            }

        }
        if (dosyasatır.length() == 21) {
            for (int i = 0; i < dosyasatır.length(); i++) {
                if (dosyasatır.charAt(i) == '*') {
                    harita[satır][sütun] = -1;
                    sütun++;
                } else {
                    harita[satır][sütun] = Integer.parseInt(String.valueOf(dosyasatır.charAt(i)));
                    sütun++;
                }

            }
        }
        satır++;

    }

    static void parçala(int[][] samurai) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                S0[i][j] = samurai[i + 6][j + 6]; //orta
                S0yedek[i][j] = samurai[i + 6][j + 6];
                S1[i][j] = samurai[i][j]; //sol üst
                S1yedek[i][j] = samurai[i][j];
                S2[i][j] = samurai[i + 12][j];//sol alt
                S2yedek[i][j] = samurai[i + 12][j];
                S3[i][j] = samurai[i][j + 12];//sağ üst
                S3yedek[i][j] = samurai[i][j + 12];
                S4[i][j] = samurai[i + 12][j + 12];//sağ alt
                S4yedek[i][j] = samurai[i + 12][j + 12];
            }
        }

    }

    public static boolean Yerleştir(int[][] tahta,
            int tahtasatır, int tahtasütun,
            int sayı, int parametre) {

        //parametreye göre köşeleri kontrol
        if (parametre == 1) {//sol üst
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if ((S1[6 + i][6 + j] == S2[k][6 + j] && S1[6 + i][6 + j] != -1) || (S1[6 + i][6 + j] == S3[6 + i][k] && S1[6 + i][6 + j] != -1)) {

                            return false;
                        }

                    }

                }

            }

        } else if (parametre == 2) {//sol alt
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if ((S2[i][6 + j] == S1[6 + k][6 + j] && S2[i][6 + j] != -1) || (S2[i][6 + j] == S4[i][k] && S2[i][6 + j] != -1)) {
                            return false;
                        }

                    }

                }

            }

        } else if (parametre == 3) {//sağ üst
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if ((S3[6 + i][j] == S1[6 + i][6 + k] && S3[6 + i][j] != -1) || (S3[6 + i][j] == S4[k][j] && S3[6 + i][j] != -1)) {
                            return false;
                        }

                    }

                }

            }

        } else if (parametre == 4) {//sağ alt
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if ((S4[i][j] == S2[i][6 + k] && S4[i][j] != -1) || (S4[i][j] == S3[6 + k][j] && S4[i][j] != -1)) {
                            return false;
                        }

                    }

                }

            }

        }

        for (int x = 0; x < tahta.length; x++) {

            if (tahta[tahtasatır][x] == sayı) {
                return false;
            }
        }

        for (int y = 0; y < tahta.length; y++) {

            if (tahta[y][tahtasütun] == sayı) {
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(tahta.length);
        int kutuSatırBaş = tahtasatır - tahtasatır % sqrt;
        int kutuSütunBaş = tahtasütun - tahtasütun % sqrt;

        for (int y = kutuSatırBaş;
                y < kutuSatırBaş + sqrt; y++) {
            for (int x = kutuSütunBaş;
                    x < kutuSütunBaş + sqrt; x++) {
                if (tahta[y][x] == sayı) {
                    return false;
                }
            }
        }

        
        return true;
    }

    public static boolean SudokuÇöz(
            int[][] tahta, int n, int parametre) {
        int tahtasatır = -1;
        int tahtasütun = -1;
        boolean isFull = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tahta[i][j] == -1) {
                    tahtasatır = i;
                    tahtasütun = j;

                    isFull = false;
                    break;
                }
            }
            if (!isFull) {
                break;
            }
        }

        if (isFull) {
            return true;
        }

        for (int sayı = 1; sayı <= n; sayı++) {
            if (Yerleştir(tahta, tahtasatır, tahtasütun, sayı, parametre)) {
                tahta[tahtasatır][tahtasütun] = sayı;
                if (SudokuÇöz(tahta, n, parametre)) {

                    return true;
                } else {

                    tahta[tahtasatır][tahtasütun] = -1;
                }
            }
        }
        return false;
    }

}
