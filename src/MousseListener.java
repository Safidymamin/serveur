import java.awt.event.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class MousseListener implements MouseListener {
    JPanel jPanel;
    ArrayList<MyFile> myfiles;
    
    public MousseListener(JPanel jPanel, ArrayList<MyFile> myfiles) {
        this.jPanel = jPanel;
        this.myfiles = myfiles;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int fileId=Integer.parseInt(jPanel.getName());
        for (MyFile  myFile : myfiles) {
            if (myFile.getId()==fileId) {
                JFrame jfPreview=Serveur.createFrame(myFile.getName(),myFile.getData(),myFile.getFileExtension());
                jfPreview.setVisible(true);
            }
        }    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
