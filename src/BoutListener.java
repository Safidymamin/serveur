import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
public class BoutListener implements ActionListener{
    JButton button;
    String fileName;
    byte[] fileData;
    JFrame jFrame;
    
    public BoutListener(JButton button, String fileName, byte[] fileData, JFrame jFrame) {
        this.button = button;
        this.fileName = fileName;
        this.fileData = fileData;
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (button.getText().equals("YES")) {
            try {
                int taille=fileData.length/2;
                byte[] temp1=new byte[taille];
                byte[] temp2=new byte[taille];
                byte[][] bb=new byte[2][];
                for (int i = 0; i < taille; i++) {
                    temp1[i]=fileData[i];
                }
                for (int j = taille; j <fileData.length ; j++) {
                    for (int i = 0; i < taille; i++) {
                        temp2[i]=fileData[j];
                    }
                }
                bb[0]=temp1;
                bb[1]=temp2;
                for (int i = 0; i < 2; i++) {
                    File fileToDownload=new File(i+" "+fileName);
                    FileOutputStream fileOutputStream=new FileOutputStream(fileToDownload);
                    fileOutputStream.write(bb[i]);
                    fileOutputStream.close();
                }
                jFrame.dispose();
            } catch (Exception ey) {
                // TODO: handle exception
                ey.printStackTrace();
            }
        }
        if (button.getText().equals("NO")) {
            jFrame.dispose();
        }
    }
    
}
