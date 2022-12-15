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
              
                    FileOutputStream fileOutputStream=new FileOutputStream(new File(fileName));
                    fileOutputStream.write(fileData);
                    fileOutputStream.close();
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
