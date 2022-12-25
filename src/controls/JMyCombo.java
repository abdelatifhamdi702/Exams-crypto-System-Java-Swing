/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComboBox;

/**
 *
 * @author Abdelatif Hamdi
 */
public class JMyCombo extends  JComboBox{
    public JMyCombo(){
    setOpaque(false); // supprimer le vide apres la modification
    }
    
    @Override
    protected void paintComponent(Graphics g){
        //interieur
    g.setColor(getBackground());
    g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
    super.paintComponent(g);};
    @Override
    protected void paintBorder(Graphics g)    {
        //bordure
    g.setColor(Color.BLUE);
    g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);    
    };
    
    
}
