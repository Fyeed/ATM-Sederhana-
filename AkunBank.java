public class AkunBank {
    private String noRekening;
    private String nama;
    private String pin;
    private double saldo;
    private String[] riwayatTransaksi; 
    private int jumlahTransaksi;

    public AkunBank(String noRekening, String nama, String pin, double saldoAwal) {
        this.noRekening = noRekening;
        this.nama = nama;
        this.pin = pin;
        this.saldo = saldoAwal;
        this.riwayatTransaksi = new String[5]; 
        this.jumlahTransaksi = 0;
    }

    public String getNoRekening() { return this.noRekening; }
    public String getNama() { return this.nama; }

    public boolean cekLogin(String inputRek, String inputPin) {
        return this.noRekening.equals(inputRek) && this.pin.equals(inputPin);
    }

    public void tampilkanSaldo() {
        System.out.println("\n+---------------------------------------+");
        System.out.printf("| SALDO ANDA SAAT INI: Rp %,13.2f |\n", saldo);
        System.out.println("+---------------------------------------+");
    }

    public void setorTunai(double jumlah) {
        if (jumlah > 0) {
            this.saldo += jumlah; 
            tambahRiwayat("Setor Tunai  : + Rp " + jumlah);
            System.out.println(">> Setor tunai berhasil dilakukan! <<");
        } else {
            System.out.println(">> Nominal tidak valid! <<");
        }
    }

    public void tarikTunai(double jumlah) {
        if (jumlah > 0 && this.saldo >= jumlah) {
            this.saldo -= jumlah; 
            tambahRiwayat("Tarik Tunai  : - Rp " + jumlah);
            System.out.println(">> Penarikan berhasil! Silakan ambil uang Anda. <<");
        } else if (jumlah > this.saldo) {
            System.out.println(">> Maaf, saldo Anda tidak mencukupi! <<");
        } else {
            System.out.println(">> Nominal tidak valid! <<");
        }
    }

    public void terimaTransfer(double jumlah, String namaPengirim) {
        this.saldo += jumlah;
        this.tambahRiwayat("Trf masuk dari " + namaPengirim + " : + Rp " + jumlah);
    }

    public void transfer(AkunBank tujuan, double jumlah) {
        if (jumlah > 0 && this.saldo >= jumlah) {
            this.saldo -= jumlah;        
         
            tujuan.terimaTransfer(jumlah, this.nama);      
            
            this.tambahRiwayat("Transfer ke " + tujuan.getNama() + " : - Rp " + jumlah);
            System.out.println(">> Transfer ke " + tujuan.getNama() + " BERHASIL! <<");
        } else if (jumlah > this.saldo) {
            System.out.println(">> Maaf, saldo Anda tidak mencukupi! <<");
        } else {
            System.out.println(">> Nominal tidak valid! <<");
        }
    }

    private void tambahRiwayat(String aktivitas) {
        if (this.jumlahTransaksi < 5) {
            this.riwayatTransaksi[this.jumlahTransaksi] = aktivitas;
            this.jumlahTransaksi++;
        } else {
            for (int i = 0; i < 4; i++) {
                this.riwayatTransaksi[i] = this.riwayatTransaksi[i + 1];
            }
            this.riwayatTransaksi[4] = aktivitas;
        }
    }

    public void cetakRiwayat() {
        System.out.println("\n+-------------------------------------------------+");
        System.out.println("| NO |             RIWAYAT TRANSAKSI              |");
        System.out.println("+-------------------------------------------------+");
        
        if (this.jumlahTransaksi == 0) {
            System.out.println("|             Belum ada transaksi                 |");
        } else {
            for (int i = 0; i < this.jumlahTransaksi; i++) {
              
                System.out.printf("| %-2d | %-42.42s |\n", (i + 1), this.riwayatTransaksi[i]);
            }
        }
        System.out.println("+-------------------------------------------------+");
    }
}