/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 *
 * @author Flor
 */
public class AgregarFuente {

    private static Font ttfBase = null;
    private static Font telegraficoFont = null;
    private static InputStream myStream = null;
    private static final String FONT_PATH_TELEGRAFICO = "src/Fuentes/Boogaloo-Regular.ttf";

    public Font createFont() {


            try {
                myStream = new BufferedInputStream(
                        new FileInputStream(FONT_PATH_TELEGRAFICO));
                ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
                telegraficoFont = ttfBase.deriveFont(Font.PLAIN, 24);               
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Font not loaded.");
            }
            return telegraficoFont;
    }
}