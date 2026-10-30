import java.util.InputMismatchException;
import java.util.Scanner; 

public class ATMMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        AkunBank[] daftarAkun = new AkunBank[2];
        daftarAkun[0] = new AkunBank("101", "H (Hapid)", "123456", 500000);
        daftarAkun[1] = new AkunBank("102", "ujang ronda", "000000", 150000);        
        
        boolean atmMenyala = true;
        
        System.out.println("=========================================");
        System.out.println("|         SELAMAT DATANG DI ATM         |");
        System.out.println("|              WONG SUCCES              |");
        System.out.println("=========================================");

        while (atmMenyala) {
            AkunBank akunAktif = null; 
            boolean loginBerhasil = false;
            int percobaan = 0;
            
            System.out.println("\n--- SILAKAN LOGIN ---");
            while (percobaan < 3 && !loginBerhasil) {
                System.out.print("Masukkan No Rekening : ");
                String inputRek = input.next();
                System.out.print("Masukkan PIN         : ");
                String inputPin = input.next();
                
                for (int i = 0; i < daftarAkun.length; i++) {
                    if (daftarAkun[i].cekLogin(inputRek, inputPin)) {
                        akunAktif = daftarAkun[i]; 
                        loginBerhasil = true;
                        break; 
                    }
                }
                
                if (!loginBerhasil) {
                    percobaan++;
                    System.out.println(">> REKENING ATAU PIN SALAH! Sisa percobaan: " + (3 - percobaan) + " <<\n");
                }
            }

            if (loginBerhasil && akunAktif != null) {
                System.out.println("\nLogin Berhasil! Halo, " + akunAktif.getNama());
                int pilihan = 0;
                
                do {
                    System.out.println("\n=========================================");
                    System.out.println("|              MENU UTAMA               |");
                    System.out.println("=========================================");
                    System.out.println("| 1. Informasi Saldo                    |");
                    System.out.println("| 2. Tarik Tunai                        |");
                    System.out.println("| 3. Setor Tunai                        |");
                    System.out.println("| 4. Transfer                           |");
                    System.out.println("| 5. Mutasi / Riwayat Transaksi         |");
                    System.out.println("| 6. Logout (Ganti Akun)                |"); 
                    System.out.println("| 7. Matikan Mesin ATM                  |"); 
                    System.out.println("=========================================");
                    System.out.print("Pilih menu (1-7): ");
                    
                
                    try {
                        pilihan = input.nextInt(); 
                        
                        switch (pilihan) {
                            case 1:
                                akunAktif.tampilkanSaldo();
                                break;
                            case 2:
                                System.out.print("Masukkan nominal penarikan: Rp ");
                                double tarik = input.nextDouble();
                                akunAktif.tarikTunai(tarik);
                                break;
                            case 3:
                                System.out.print("Masukkan nominal setoran: Rp ");
                                double setor = input.nextDouble();
                                akunAktif.setorTunai(setor);
                                break;
                            case 4:
                                System.out.print("Masukkan No Rekening Tujuan: ");
                                String rekTujuan = input.next();
                                
                                AkunBank akunTujuan = null;
                                for (int i = 0; i < daftarAkun.length; i++) {
                                    if (daftarAkun[i].getNoRekening().equals(rekTujuan)) {
                                        akunTujuan = daftarAkun[i];
                                        break;
                                    }
                                }
                                
                                if (akunTujuan != null) {
                                    if (akunTujuan == akunAktif) {
                                        System.out.println(">> Tidak bisa transfer ke rekening sendiri! <<");
                                    } else {
                                        System.out.print("Masukkan nominal transfer: Rp ");
                                        double nominalTransfer = input.nextDouble();
                                        akunAktif.transfer(akunTujuan, nominalTransfer);
                                    }
                                } else {
                                    System.out.println(">> Rekening tujuan tidak ditemukan! <<");
                                }
                                break;
                            case 5:
                                akunAktif.cetakRiwayat();
                                break;
                            case 6:
                                System.out.println(">> Logout berhasil. Mengembalikan ke layar awal... <<");
                                break;
                            case 7:
                                System.out.println("=========================================");
                                System.out.println("|   TERIMA KASIH TELAH MENGGUNAKAN ATM  |");
                                System.out.println("|              WONG SUCCES              |");
                                System.out.println("=========================================");
                                atmMenyala = false; 
                                break;
                            default:
                                System.out.println(">> Pilihan tidak valid! Silakan coba lagi. <<");
                        }
                    } catch (InputMismatchException e) {
                       
                        System.out.println(">> ERROR: Input harus berupa ANGKA! Jangan masukkan huruf. <<");
                        input.nextLine();  
                        pilihan = 0; 
                    }
                    
                } while (pilihan != 6 && pilihan != 7); 
                
            } else {
                System.out.println("=========================================");
                System.out.println("| ATM DIBLOKIR! TERLALU BANYAK KESALAHAN|");
                System.out.println("=========================================");
                atmMenyala = false; 
            }
        }
        input.close();
    }
}