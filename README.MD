# 🏧 Mesin ATM Sederhana - Bank Wong Succes

Aplikasi simulasi mesin ATM berbasis *Command Line Interface (CLI)* yang ditulis dalam bahasa Java. Program ini dirancang untuk mendemonstrasikan pemahaman mengenai 6 konsep dasar Pemrograman Berorientasi Objek (OOP) dan logika fundamental Java.

## 📖 Daftar Isi

* [Fitur Program](#-fitur-program)

* [Konsep Java yang Diterapkan](#-konsep-java-yang-diterapkan)

* [Struktur Program](#-struktur-program)

* [Cara Kerja Kode (Alur & Logika)](#-cara-kerja-kode-alur--logika)

* [Daftar Akun Default (Login)](#-daftar-akun-default-login)

* [Cara Menjalankan Program](#-cara-menjalankan-program)

* [Penanganan Error (Error Handling)](#-penanganan-error-error-handling)

## ✨ Fitur Program

Program ini mensimulasikan fitur-fitur dasar yang ada pada mesin ATM sesungguhnya, meliputi:

1. **Sistem Login Keamanan:** Validasi menggunakan kombinasi Nomor Rekening dan PIN. Mesin akan memblokir pengguna setelah 3 kali gagal *login*.

2. **Cek Saldo:** Menampilkan sisa saldo nasabah dalam format rupiah yang rapi (dengan pemisah ribuan dan desimal).

3. **Tarik & Setor Tunai:** Memungkinkan nasabah untuk menarik atau menyetorkan uang dengan validasi nominal yang logis (tidak bisa menarik uang melebihi saldo atau memasukkan angka negatif).

4. **Transfer Antar Rekening:** Fitur pengiriman uang antar nasabah (objek). Sistem otomatis memotong saldo pengirim dan menambah saldo penerima.

5. **Mutasi Rekening (Riwayat):** Mencatat dan mencetak 5 aktivitas transaksi terakhir nasabah (tarik, setor, dan transfer) dalam bentuk tabel.

6. **Multi-User (Logout & Switch Account):** Pengguna dapat melakukan *logout* dan berganti akun tanpa harus mematikan program, sehingga dapat memverifikasi riwayat transfer antar akun.

## 🛠 Konsep Java yang Diterapkan

Proyek ini dibuat dengan mematuhi 6 materi dasar pemrograman Java:

1. **Class & Object (OOP):** Pemisahan file logika `AkunBank` (sebagai *blueprint/Class*) dan `ATMMain` (sebagai *Main/Object instantiator*).

2. **Percabangan (`if-else` & `switch-case`):** Digunakan untuk navigasi menu utama (1-7), validasi batas penarikan, dan mengecek sisa percobaan PIN.

3. **Perulangan (`while`, `do-while`, `for`):**

   * `while`: Menahan layar *login* maksimal 3 kali percobaan.

   * `do-while`: Mengulang layar menu ATM agar tidak langsung tertutup setelah transaksi selesai.

   * `for`: Mencari data akun dari *Array* dan menggeser log transaksi yang lama saat batas maksimal tercapai.

4. **Scanner:** Mengambil input interaktif dari pengguna (*keyboard*) berupa tipe data teks (`next()`) dan angka (`nextInt()`, `nextDouble()`).

5. **Array (Array & Array of Objects):**

   * Menggunakan Array of Object (`AkunBank[]`) sebagai *database* sementara untuk menyimpan daftar akun nasabah.

   * Menggunakan Array `String[]` dengan panjang statis (5) untuk menyimpan *history* transaksi secara berurutan.

6. **Operator Aritmatika & Logika:**

   * Penjumlahan/pengurangan pada saldo nasabah (`+=` dan `-=`).

   * Operator logika `&&` (AND) dan `!` (NOT) pada proses validasi *login* serta verifikasi kecukupan dana transfer.

## 📂 Struktur Program

Proyek ini terdiri dari dua *file* sumber (*source code*):

### 1. `AkunBank.java`

*File* ini berfungsi sebagai cetakan (*Class*) untuk menyimpan data nasabah. Semua interaksi yang berhubungan dengan perubahan data (mutasi) dilakukan secara aman di sini (*Encapsulation*).

* **Atribut:** Nomor rekening, nama, PIN, saldo, array riwayat transaksi.

* **Metode Utama:** `cekLogin()`, `tarikTunai()`, `setorTunai()`, `transfer()`, `terimaTransfer()`, `cetakRiwayat()`.

### 2. `ATMMain.java`

*File* ini adalah program pengeksekusi utama yang berisi logika tampilan (*User Interface/CLI*) dan navigasi menu.

* Berisi fungsi `main(String[] args)`.

* Menyimpan *database* nasabah dalam bentuk `Array of Objects`.

* Menggunakan blok `try-catch` untuk mengamankan input *user*.

## ⚙️ Cara Kerja Kode (Alur & Logika)

Program ini bekerja dengan mengombinasikan perulangan terstruktur, pencarian data pada *Array of Objects*, dan eksekusi transaksi yang terenkapsulasi di dalam objek akun.

### 1. Alur Eksekusi Program (Flowchart Logika)

```
[Start: ATMMain.main()]
       │
       ▼
[Inisialisasi Array daftarAkun (Rek 101 & 102)]
       │
       ▼
┌───► [Layar Login: Minta No Rekening & PIN]
│      │
│      ▼
│     [Cek Login via Loop 'for' pada daftarAkun]
│      │
│      ├───► (PIN/Rek Salah < 3x) ──► [Tampil Sisa Percobaan] ───┐
│      │                                                      │
│      ├───► (Gagal 3x) ──────────► [Blokir & ATM Mati]        │
│      │                                                      │
│      ▼ (Login Sukses)                                       │
│     [Set akunAktif]                                         │
│      │                                                      │
│      ▼                                                      │
│ ┌──► [Tampilkan Menu Utama 1-7]                             │
│ │    │                                                      │
│ │    ▼                                                      │
│ │   [Input Pilihan (Diisolasi Try-Catch)]                   │
│ │    │                                                      │
│ │    ├── 1. Saldo ──────► [panggil akunAktif.tampilkanSaldo()]
│ │    ├── 2. Tarik ──────► [panggil akunAktif.tarikTunai()]
│ │    ├── 3. Setor ──────► [panggil akunAktif.setorTunai()]
│ │    ├── 4. Transfer ───► [Cari akunTujuan] ──► [panggil akunAktif.transfer()]
│ │    ├── 5. Mutasi ─────► [panggil akunAktif.cetakRiwayat()]
│ │    ├── 6. Logout ─────► [Reset akunAktif] ─────────────────┘
│ └────┴── 7. Matikan ────► [atmMenyala = false] ──► [Exit]

```

### 2. Mekanisme Transaksi & Transfer

* **Setor/Tarik Tunai:** Program memvalidasi nominal menggunakan percabangan `if`. Jika valid, saldo dimodifikasi (`+=` atau `-=`) dan dicatat ke dalam *Array* riwayat.

* **Transfer:**

  1. Program mencari objek tujuan di dalam array `daftarAkun` menggunakan *loop* `for`.

  2. Jika ditemukan dan saldo cukup, saldo `akunAktif` dikurangi.

  3. Program memanggil metode `terimaTransfer()` pada objek tujuan agar saldo penerima bertambah dan riwayat mutasi masuknya tercatat dengan aman.

### 3. Algoritma Rotasi Riwayat (Array FIFO Shift)

Riwayat transaksi disimpan dalam array berkapasitas 5 elemen. Ketika riwayat penuh, sistem menggeser data lama menggunakan algoritma berikut:

```
// Jika array sudah penuh (isi 5), geser data ke kiri
for (int i = 0; i < 4; i++) {
    riwayatTransaksi[i] = riwayatTransaksi[i + 1]; // Index 1 ke 0, 2 ke 1, dst.
}
// Masukkan transaksi terbaru di indeks terakhir (ke-4)
riwayatTransaksi[4] = aktivitas;

```

## 🔑 Daftar Akun Default (Login)

Gunakan kredensial berikut untuk melakukan uji coba (*testing*) pada mesin ATM:

| Nama Nasabah | No. Rekening | PIN | Saldo Awal | 
| ----- | ----- | ----- | ----- | 
| **H (Hafid)** | `101` | `123456` | Rp 500.000,00 | 
| **ujang ronda** | `102` | `000000` | Rp 150.000,00 | 

*Tips Uji Coba: Login dengan rekening 101, lakukan transfer ke 102, kemudian tekan Logout (Menu 6). Login kembali dengan rekening 102 untuk melihat apakah uang dan riwayat transaksi telah masuk (Menu 5).*

## 🚀 Cara Menjalankan Program

Pastikan Anda telah menginstal **Java Development Kit (JDK)** di komputer Anda.

1. Buka *Terminal* (Mac/Linux) atau *Command Prompt / PowerShell* (Windows).

2. Arahkan direktori (`cd`) ke *folder* tempat Anda menyimpan *file* program ini.

3. *Compile* kode dengan menjalankan perintah:

   ```
   javac ATMMain.java AkunBank.java
   
   ```

4. Jalankan program dengan perintah:

   ```
   java ATMMain
   
   ```

## 🛡 Penanganan Error (Error Handling)

* **InputMismatchException:** Dikelola menggunakan blok `try-catch` untuk mencegah program berhenti tak terduga (*crash*) jika pengguna memasukkan karakter non-angka pada menu utama.

* **Format UI (Cetak Tabel):** Teks riwayat dibatasi menggunakan penentu format (`%-42.42s`) saat dicetak. Ini memastikan bentuk kotak tabel ASCII tetap rapi dan tidak jebol meskipun teks mutasi sangat panjang.