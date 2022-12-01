import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Serveur
 */
public class Serveur {
    static ArrayList<MyFile> myfiles= new ArrayList<>();
    public static void main(String[] args) {
        int fileId=0;
        JFrame jFrame=new JFrame("serveur");
        jFrame.setSize(400,400);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        
        JScrollPane jScrollPane=new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(jScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel jlTitle=new JLabel("File Receiver");
        jlTitle.setFont(new Font("Arial",Font.BOLD,25));
        jlTitle.setBorder(new EmptyBorder(20,0,10,0));
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        jFrame.add(jlTitle);
        jFrame.add(jScrollPane);
        jFrame.setVisible(true);

        try {
        ServerSocket serverSocket=new ServerSocket(1234);
        while (true) {
                Socket socket=serverSocket.accept();
                DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
                int filelength=dataInputStream.readInt();
                if (filelength>0) {
                    byte[] fileNamebytes=new byte[filelength];
                    dataInputStream.readFully(fileNamebytes,0,fileNamebytes.length);
                    String filename=new String(fileNamebytes);

                    int fileContentlength=dataInputStream.readInt();
                    if (fileContentlength>0) {
                        byte[]fileContentbytes=new byte[fileContentlength];
                        dataInputStream.readFully(fileContentbytes,0,fileContentlength);

                        JPanel jpFileRow=new JPanel();
                        jpFileRow.setLayout(new BoxLayout(jpFileRow, BoxLayout.X_AXIS));
                         
                        JLabel jlFileName=new JLabel(filename);
                        jlFileName.setFont(new Font("Arial",Font.BOLD,20));
                        jlFileName.setBorder(new EmptyBorder(20,0,10,0));
                        jlFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

                        if (getFileExtension(filename).equalsIgnoreCase("txt")) {
                            jpFileRow.setName(String.valueOf(fileId));

                            jpFileRow.addMouseListener(new MousseListener(jpFileRow, myfiles));

                            jpFileRow.add(jlFileName);
                            jFrame.validate();
                        }else{
                            jpFileRow.setName(String.valueOf(fileId));
                            jpFileRow.addMouseListener(new MousseListener(jpFileRow, myfiles));

                            jpFileRow.add(jlFileName);
                            jPanel.add(jpFileRow);
                            jFrame.validate();
                        }
                        myfiles.add(new MyFile(fileId, filename,fileContentbytes,getFileExtension(filename)));
                    }
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
  
    public static JFrame createFrame(String fileName, byte[] fileData, String FileExtension) {
        JFrame jFrame=new JFrame();
        jFrame.setSize(400, 400);

        JPanel jpanel=new JPanel();
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));

        JLabel jlTitle=new JLabel("`file DOwnloader ");
        jlTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jlPrompt=new JLabel("Are you want you to  download "+fileName);
        jlPrompt.setFont(new Font("Arial",Font.BOLD,20));
        jlPrompt.setBorder(new EmptyBorder(20,0,10,0));
        jlPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbYES=new JButton("YES");
        jbYES.setPreferredSize(new Dimension(150,75));
        jbYES.setFont(new Font("Arial",Font.BOLD,20));

        JButton jbNO=new JButton("NO");
        jbNO.setPreferredSize(new Dimension(150,75));
        jbNO.setFont(new Font("Arial",Font.BOLD,20));

        JLabel jlFileContent=new JLabel();
        jlFileContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButton=new JPanel();
        jpButton.setBorder(new EmptyBorder(20,0,10,0));
        jpButton.add(jbYES);
        jpButton.add(jbNO);
        if (FileExtension.equalsIgnoreCase("txt")) {
            jlFileContent.setText("<html"+new String(fileData)+ "<html");
        }else{
            jlFileContent.setIcon(new ImageIcon(fileData));
        }

        jbYES.addActionListener(new BoutListener(jbYES, fileName, fileData, jFrame));
        jbNO.addActionListener(new BoutListener(jbNO, fileName, fileData, jFrame));

        jpanel.add(jlTitle);
        jpanel.add(jlPrompt);
        jpanel.add(jlFileContent);
        jpanel.add(jpButton);

        jFrame.add(jpanel);

        return jFrame;

    }

    private static String getFileExtension(String filename) {
        int i=filename.lastIndexOf(".");
        if (1>0) {
            return filename.substring(i);
        }else{
            return "tss extension";
        }
    }
}