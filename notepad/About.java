package notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class About extends JFrame implements ActionListener {

    About() {
        setLayout(null);
        setBounds(400, 100, 600, 500);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/windows.png"));

        Image i2 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(70, 40, 400, 80);
        add(image);
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));

        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(50, 180, 60, 60);
        add(image1);

        JLabel text = new JLabel("<html>Windows Notepad 11.2304.26.0\n<br>"
                + "© 2023 Microsoft. All rights reserved.<hr>"
                + "Thank you for choosing Microsoft!\n<br>"
                + "\n"
                + "Depending on how you obtained the Windows software, this is a license agreement between (i) you and the device manufacturer or software installer "
                + "that distributes the software with your device;  Microsoft is the device manufacturer for devices produced by Microsoft or one of its affiliates, and Microsoft is the"
                + " retailer if you acquired the software directly from Microsoft. Note that if you are a volume license"
                + " customer, use of this software is subject to your volume license agreement rather than this agreement."
                + "<br>clone by YASH © 2023 MAYA</html>");
        text.setBounds(150, 100, 380, 300);
        text.setFont(new Font("AERIAL", Font.PLAIN, 14));
        add(text);

        JButton ok = new JButton("OK");
        ok.setBounds(250, 400, 120, 25);
        ok.addActionListener(this);
        add(ok);

        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new About();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        setVisible(false);
    }
}
