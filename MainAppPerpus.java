/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP1AplikasiPerpustakaan;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author ASUS
 */
public class MainAppPerpus {
    public static void main(String[] args) {
        Map<String, Integer> jenisBuku = new HashMap<>();
        jenisBuku.put("pelajaran", 2000);
        jenisBuku.put("novel", 5000);
        jenisBuku.put("skripsi", 10000);

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("======================================================");
        System.out.println("Selamat Datang di Perpustakaan Binus Online");
        System.out.println("======================================================");
        System.out.println("\n*Melayani peminjaman buku");
        System.out.println("selama tidak melewati tenggat");
        System.out.println("*Batas waktu peminjaman adalah selama 10 hari");
        System.out.println("*Lebih dari batas waktu akan dikenai denda");
        System.out.println("======================================================");
        System.out.println("");

        System.out.print("Masukkan jenis buku (pelajaran/novel/skripsi): ");
        String jenis = scanner.nextLine();

        System.out.print("\nMasukkan tanggal pinjam (yyyy-mm-dd): ");
        String tglPinjamStr = scanner.nextLine();
        LocalDate tglPinjam = LocalDate.parse(tglPinjamStr);

        //digunakan untuk memberi tenggat 10 hari pinjam
        LocalDate tglJatuhTempo = tglPinjam.plusDays(10);
        LocalDate tglKembali;

        do {
            System.out.print("Masukkan tanggal kembali (yyyy-mm-dd): ");
            String tglKembaliStr = scanner.nextLine();
            tglKembali = LocalDate.parse(tglKembaliStr);

            if (tglKembali.isBefore(tglJatuhTempo)) {
                System.out.println("Tanggal kembali harus setelah atau pada tanggal jatuh tempo (max 10 hari) (" + tglJatuhTempo + ")");
            }
        } while (tglKembali.isBefore(tglJatuhTempo));

        long jmlhTerlambat = ChronoUnit.DAYS.between(tglJatuhTempo, tglKembali);
        jmlhTerlambat = Math.max(jmlhTerlambat, 0); 

        int tarifDendaPerHari = jenisBuku.getOrDefault(jenis, 0);
        int denda = tarifDendaPerHari * (int) jmlhTerlambat;

        System.out.println("\nJumlah hari melebihi batas tanggal kembali: " + jmlhTerlambat);
        System.out.println("Denda yang harus dibayarkan: Rp " + denda);
    }
}
