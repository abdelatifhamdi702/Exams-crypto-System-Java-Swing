/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author Abdelatif Hamdi
 */
public class JMyBottom extends  JButton{
    Color BorderColor = Color.BLUE;
    Color foregroundColor = new Color(70,130,180);
    public JMyBottom(Color BorderColor,Color foregroundColor){ this.BorderColor=BorderColor;
    this.foregroundColor = foregroundColor;
    setOpaque(false); // supprimer le vide apres la modification
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
    }

    public JMyBottom() {
        setOpaque(false); // supprimer le vide apres la modification
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    protected void paintComponent(Graphics g){
        //interieur
    g.setColor(foregroundColor);
    g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
    super.paintComponent(g);};
    @Override
    protected void paintBorder(Graphics g)    {
        //bordure
    g.setColor(BorderColor);
    g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);    
    };
    
    
}
