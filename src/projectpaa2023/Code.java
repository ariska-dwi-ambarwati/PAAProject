/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpaa2023;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author lenovo
 */
public class Code extends JPanel implements ActionListener {
    private final int Lebar_Panel = 805;
    private final int Tinggi_Panel = 610;
    private final int Ukuran_Cell = 30;
    private final int No_Baris = 20;
    private final int No_Kolom = 20;
    private JButton TombolMulai;
    private JButton TombolBerhenti;
    private JButton TombolAcakPeta;
    private JButton AcakDroidMerah;
    private JButton AcakDroidHijau;
    private Timer timer;
    private Random random;
    private boolean TampilanDroidMerah;
    private boolean TampilanDroidHijau;
    private boolean[][] grid;
    private int barisDroidMerah;
    private int kolomDroidMerah;
    private int barisDroidHijau;
    private int kolomDroidHijau;
    
public Code() {
    setPreferredSize(new Dimension(Lebar_Panel, Tinggi_Panel));
    random = new Random();
    grid = new boolean[No_Baris][No_Kolom];

    setLayout(new BorderLayout());
    JPanel tombolPanel = new JPanel();
    JPanel PanelTombolDroid = new JPanel();
    PanelTombolDroid.setLayout(new GridLayout(7, 1, 0, 0));
    //membuat tombol-tombol beserta ukuran preferensinya
    TombolMulai = new JButton("Mulai");
    TombolMulai.setPreferredSize(new Dimension(200, 50)); // Mengatur ukuran preferensi tombol
    TombolMulai.addActionListener(this);
    PanelTombolDroid.add(TombolMulai); //menambahkan tombol ke PanelTombolDroid
    
    TombolBerhenti = new JButton("Berhenti");
    TombolBerhenti.setPreferredSize(new Dimension(200, 50)); // Mengatur ukuran preferensi tombol
    TombolBerhenti.addActionListener(this);
    PanelTombolDroid.add(TombolBerhenti);
    
    TombolAcakPeta = new JButton("Acak Peta");
    TombolAcakPeta.setPreferredSize(new Dimension(200, 50)); // Mengatur ukuran preferensi tombol
    TombolAcakPeta.addActionListener(this);
    PanelTombolDroid.add(TombolAcakPeta);
    
    AcakDroidMerah = new JButton("Acak Droid Merah");
    AcakDroidMerah.setPreferredSize(new Dimension(200, 50)); // Mengatur ukuran preferensi tombol
    AcakDroidMerah.addActionListener(this);
    PanelTombolDroid.add(AcakDroidMerah);

    AcakDroidHijau = new JButton("Acak Droid Hijau");
    AcakDroidHijau.setPreferredSize(new Dimension(200, 50)); // Mengatur ukuran preferensi tombol
    AcakDroidHijau.addActionListener(this);
    PanelTombolDroid.add(AcakDroidHijau);
    
    JButton tombolPandanganDroidMerah = new JButton("Pandangan Droid Merah");
    tombolPandanganDroidMerah.setPreferredSize(new Dimension(200, 50)); // Mengatur ukuran preferensi tombol
    tombolPandanganDroidMerah.addActionListener(new ActionListener() {
        @Override //akan mengeksekusi
        public void actionPerformed(ActionEvent e) {
            TampilanDroidMerah = !TampilanDroidMerah; //ubah nilai trus ke false
            repaint();
        }
    });
    PanelTombolDroid.add(tombolPandanganDroidMerah);

    JButton tombolPandanganDroidHijau = new JButton("Pandangan Droid Hijau");
    tombolPandanganDroidHijau.setPreferredSize(new Dimension(200, 50)); // Mengatur ukuran preferensi tombol
    tombolPandanganDroidHijau.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TampilanDroidHijau = !TampilanDroidHijau; 
            repaint(); //ulangi
        }
    });
    PanelTombolDroid.add(tombolPandanganDroidHijau);

    JPanel panelKanan = new JPanel();
    panelKanan.setLayout(new BorderLayout());
    panelKanan.add(tombolPanel, BorderLayout.NORTH);
    panelKanan.add(PanelTombolDroid, BorderLayout.CENTER);

    add(panelKanan, BorderLayout.EAST);
    timer = new Timer(200, this);
    ulangPermainan(); //menginisialisasi keadaan awal game
    }
 
@Override
public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
        if (e.getSource() == TombolMulai) {
            timer.start();
        } else if (e.getSource() == TombolBerhenti) {
            timer.stop();
        } else if (e.getSource() == TombolAcakPeta) {
            ulangPermainan();
            repaint();
        } else if (e.getSource() == AcakDroidMerah) {
            acakPosisi_DroidMerah();
            repaint();
        }else if (e.getSource() == AcakDroidHijau) {
            acakPosisi_DroidHijau();
            repaint();
        }else if (e.getSource() == timer) {
            DroidMerahBergerak();
            DroidHijauBergerak();
            if (DroidMerah_Ditemukan()) {
                timer.stop();
            }
            repaint();
        }
    } 
    //melakukan pengulangan permainan
private void ulangPermainan() {
        // Mengisi seluruh grid dengan dinding
    for (int i = 0; i < No_Baris; i++) {
        for (int j = 0; j < No_Kolom; j++) {
            grid[i][j] = true;
        }
    }
    // Memulai pembangunan labirin dari posisi awal (0, 0)
    BangunLabirin(0, 0);

    // Menempatkan droid merah pada posisi yang valid
    do {
        barisDroidMerah = random.nextInt(No_Baris);
        kolomDroidMerah = random.nextInt(No_Kolom);
    } while (grid[barisDroidMerah][kolomDroidMerah]);

    // Menempatkan droid hijau pada posisi yang valid
    do {
        barisDroidHijau = random.nextInt(No_Baris);
        kolomDroidHijau = random.nextInt(No_Kolom);
    } while (grid[barisDroidHijau][kolomDroidHijau]);
    acakPosisi_DroidHijau();
    }
   
private void BangunLabirin(int barisLabririn, int kolomLabririn) {
    // Mengatur sel saat ini sebagai jalan
    grid[barisLabririn][kolomLabririn] = false;
    // Mendapatkan arah acak
    int[] directions = {0, 1, 2, 3};
    acakArray(directions);
    // Memeriksa setiap arah secara acak
    for (int direction : directions) {
         int nextRow = barisLabririn;
         int nextCol = kolomLabririn;

    if (direction == 0 && barisLabririn > 1) {
            nextRow -= 2;
        } else if (direction == 1 && barisLabririn < No_Baris - 2) {
            nextRow += 2;
        } else if (direction == 2 && kolomLabririn > 1) {
            nextCol -= 2;
        } else if (direction == 3 && kolomLabririn < No_Kolom - 2) {
            nextCol += 2;
        }
        // Memeriksa apakah sel berikutnya masih berada di dalam grid dan belum dikunjungi
        if (nextRow >= 0 && nextRow < No_Baris && nextCol >= 0 && nextCol < No_Kolom && grid[nextRow][nextCol]) {
            // Menghubungkan sel saat ini dengan sel berikutnya
            grid[(barisLabririn + nextRow) / 2][(kolomLabririn + nextCol) / 2] = false;
            BangunLabirin(nextRow, nextCol);
            }
        }
    }
   
private void acakArray(int[] array) {
        int index, temp;
    for (int i = array.length - 1; i > 0; i--) {
        index = random.nextInt(i + 1);
        temp = array[index];
        array[index] = array[i];
        array[i] = temp;
        }
    }
    
private void DroidMerahBergerak() {
    // Cek apakah droid merah sudah bersentuhan dengan droid hijau
    if (barisDroidMerah == barisDroidHijau && kolomDroidMerah == kolomDroidHijau) {
        // Droid merah sudah menemukan droid hijau, berhenti mencari
        timer.stop();
        return;
    }
    // Mendapatkan sel berikutnya yang mendekati droid hijau dengan DFS
    Set<String> visitedCells = new HashSet<>();
    boolean foundNextCell = dfs(barisDroidMerah, kolomDroidMerah, visitedCells);
    // Mengupdate posisi droid merah jika sel berikutnya valid
    if (foundNextCell) {
        visitedCells.add(barisDroidMerah + "," + kolomDroidMerah);
    }
    repaint();
    }
   
private boolean dfs(int row, int col, Set<String> visitedCells) {
        // Cek apakah posisi saat ini adalah droid hijau
    if (row == barisDroidHijau && col == kolomDroidHijau) {
        return true;
    }
    // Cek apakah sel saat ini sudah dikunjungi sebelumnya
    if (visitedCells.contains(row + "," + col)) {
        return false;
    }
    visitedCells.add(row + "," + col);

    // Daftar arah pergerakan droid
    int[] directions = {0, 1, 2, 3};
    acakArray(directions);

    for (int direction : directions) {
        int nextRow = row;
        int nextCol = col;

        switch (direction) {
    case 0:
        if (row > 0 && !grid[row - 1][col]) {
            nextRow = row - 1; // Maju 1 kotak ke atas
        }
        break;
    case 1:
        if (row < No_Baris - 1 && !grid[row + 1][col]) {
            nextRow = row + 1; // Maju 1 kotak ke bawah
        }
        break;
    case 2:
        if (col > 0 && !grid[row][col - 1]) {
            nextCol = col - 1; // Maju 1 kotak ke kiri
        }
        break;
    case 3:
        if (col < No_Kolom - 1 && !grid[row][col + 1]) {
            nextCol = col + 1; // Maju 1 kotak ke kanan
        }
        break;
}
        if (dfs(nextRow, nextCol, visitedCells)) {
            barisDroidMerah = nextRow;
            kolomDroidMerah = nextCol;
            return true;
            }
        }
        return false;
    }
      
private void DroidHijauBergerak() {
        // Mendapatkan sel berikutnya berdasarkan posisi droid merah
    int nextRow = barisDroidHijau;
    int nextCol = kolomDroidHijau;

    // Mendapatkan jarak horizontal dan vertikal antara droid hijau dan merah
    int horizontalDistance = Math.abs(kolomDroidHijau - kolomDroidMerah);
    int verticalDistance = Math.abs(barisDroidHijau - barisDroidMerah);

    // Memilih langkah berdasarkan posisi relatif droid merah
    if (horizontalDistance > verticalDistance) {
        if (kolomDroidHijau < kolomDroidMerah && kolomDroidHijau > 0 && !grid[barisDroidHijau][kolomDroidHijau - 1] && !DroidMerah_Disekitar(barisDroidHijau, kolomDroidHijau - 1)) {
            nextCol--;
        } else if (kolomDroidHijau > kolomDroidMerah && kolomDroidHijau < No_Kolom - 1 && !grid[barisDroidHijau][kolomDroidHijau + 1] && !DroidMerah_Disekitar(barisDroidHijau, kolomDroidHijau + 1)) {
            nextCol++;
        } else if (barisDroidHijau < barisDroidMerah && barisDroidHijau > 0 && !grid[barisDroidHijau - 1][kolomDroidHijau] && !DroidMerah_Disekitar(barisDroidHijau - 1, kolomDroidHijau)) {
            nextRow--;
        } else if (barisDroidHijau > barisDroidMerah && barisDroidHijau < No_Baris - 1 && !grid[barisDroidHijau + 1][kolomDroidHijau] && !DroidMerah_Disekitar(barisDroidHijau + 1, kolomDroidHijau)) {
            nextRow++;
        }
    } else {
        if (barisDroidHijau < barisDroidMerah && barisDroidHijau > 0 && !grid[barisDroidHijau - 1][kolomDroidHijau] && !DroidMerah_Disekitar(barisDroidHijau - 1, kolomDroidHijau)) {
            nextRow--;
        } else if (barisDroidHijau > barisDroidMerah && barisDroidHijau < No_Baris - 1 && !grid[barisDroidHijau + 1][kolomDroidHijau] && !DroidMerah_Disekitar(barisDroidHijau + 1, kolomDroidHijau)) {
            nextRow++;
        } else if (kolomDroidHijau < kolomDroidMerah && kolomDroidHijau > 0 && !grid[barisDroidHijau][kolomDroidHijau - 1] && !DroidMerah_Disekitar(barisDroidHijau, kolomDroidHijau - 1)) {
            nextCol--;
        } else if (kolomDroidHijau > kolomDroidMerah && kolomDroidHijau < No_Kolom - 1 && !grid[barisDroidHijau][kolomDroidHijau + 1] && !DroidMerah_Disekitar(barisDroidHijau, kolomDroidHijau + 1)) {
            nextCol++;
        }
    }
    // Mengupdate posisi droid hijau jika sel berikutnya valid
    if (nextRow >= 0 && nextRow < No_Baris && nextCol >= 0 && nextCol < No_Kolom) {
        barisDroidHijau = nextRow;
        kolomDroidHijau = nextCol;
        }
    }
 
private boolean DroidMerah_Disekitar(int row, int col) {
    // Cek apakah droid merah berada di sekitar posisi (row, col)
    return (Math.abs(row - barisDroidMerah) == 1 && col == kolomDroidMerah) || (row == barisDroidMerah && Math.abs(col - kolomDroidMerah) == 1);
    }

private boolean DroidMerah_Ditemukan() {
        return (barisDroidMerah == barisDroidHijau) && (kolomDroidMerah == kolomDroidHijau);
    }

private void acakPosisi_DroidMerah() {
        do {
            barisDroidMerah = random.nextInt(No_Baris);
            kolomDroidMerah = random.nextInt(No_Kolom);
        } while (grid[barisDroidMerah][kolomDroidMerah] || (barisDroidMerah == barisDroidHijau && kolomDroidMerah == kolomDroidHijau));
    }
  
private void acakPosisi_DroidHijau() {
        do {
            barisDroidHijau = random.nextInt(No_Baris);
            kolomDroidHijau = random.nextInt(No_Kolom);
        } while (grid[barisDroidHijau][kolomDroidHijau] || (barisDroidHijau == barisDroidMerah && kolomDroidHijau == kolomDroidMerah));
    }

//untuk setting warna dan warna komponen 
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
        
// Draw grid
    for (int i = 0; i < No_Baris; i++) {
        for (int j = 0; j < No_Kolom; j++) {
            if (grid[i][j]) {
                g.setColor(Color.BLACK); // Mengganti warna dinding menjadi hitam
                } else {
                g.setColor(Color.WHITE);
            }
    g.fillRect(j * Ukuran_Cell, i * Ukuran_Cell, Ukuran_Cell, Ukuran_Cell);
    g.setColor(Color.BLACK);
    g.drawRect(j * Ukuran_Cell, i * Ukuran_Cell, Ukuran_Cell, Ukuran_Cell);
        }
    }
// Draw droid merah
    g.setColor(Color.RED);
    g.fillOval(kolomDroidMerah * Ukuran_Cell, barisDroidMerah * Ukuran_Cell, Ukuran_Cell, Ukuran_Cell);
// Draw  droid hijau
    g.setColor(Color.GREEN);
    g.fillOval(kolomDroidHijau * Ukuran_Cell, barisDroidHijau * Ukuran_Cell, Ukuran_Cell, Ukuran_Cell);
//sudut pandang droid merah
    if (TampilanDroidMerah) {
        int viewX = kolomDroidMerah * Ukuran_Cell;
        int viewY = barisDroidMerah * Ukuran_Cell;
        g.drawRect(viewX - Ukuran_Cell, viewY - Ukuran_Cell, Ukuran_Cell * 0, Ukuran_Cell * 0);
    if (!TampilanDroidHijau) {
        g.setColor(Color.white);
        int DroidHijau_X = kolomDroidHijau * Ukuran_Cell;
        int DroidHijau_Y = barisDroidHijau * Ukuran_Cell;
        g.fillOval(kolomDroidHijau * Ukuran_Cell, barisDroidHijau * Ukuran_Cell, Ukuran_Cell, Ukuran_Cell);
    }
}
 //sudut pandang droid hijau
    if (TampilanDroidHijau) {
        g.setColor(Color.LIGHT_GRAY);
        int viewX = kolomDroidHijau * Ukuran_Cell;
        int viewY = barisDroidHijau * Ukuran_Cell;
        int startX = Math.max(0, viewX - Ukuran_Cell);
        int startY = Math.max(0, viewY - Ukuran_Cell);
        int endX = Math.min(getWidth(), viewX + Ukuran_Cell * 2);
        int endY = Math.min(getHeight(), viewY + Ukuran_Cell * 2);
            g.fillRect(0, 0, getWidth(), startY);
            g.fillRect(0, endY, getWidth(), getHeight() - endY);
            g.fillRect(0, startY, startX, endY - startY);
            g.fillRect(endX, startY, getWidth() - endX, endY - startY);

    if (!TampilanDroidMerah) {
        g.setColor(Color.RED);
        int greenDroidX = kolomDroidMerah * Ukuran_Cell;
        int greenDroidY = barisDroidMerah * Ukuran_Cell;
            g.fillOval(kolomDroidMerah * Ukuran_Cell, barisDroidMerah * Ukuran_Cell, Ukuran_Cell, Ukuran_Cell);
        }
    }
}
}
