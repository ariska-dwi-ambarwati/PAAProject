/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpaa2023;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author lenovo
 */
public class Cell {
    private int X;
    private int Y;
    private boolean ini_Tembok;

    public Cell(int x, int y, boolean iniTembok) {
        this.X = x;
        this.Y = y;
        this.ini_Tembok = iniTembok;
    }

    public void Gambar(Graphics g, int ukuranSel) {
        if (ini_Tembok) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillRect(X * ukuranSel, Y * ukuranSel, ukuranSel, ukuranSel);
    } 
}

