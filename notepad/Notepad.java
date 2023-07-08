package notepad;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener {

    JTextArea area;
    String text;

    Notepad() {
        getContentPane().setBackground(Color.WHITE);

        setTitle("Notepad");
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image i = icon.getImage();
        setIconImage(i);

//        here is menu bar 
        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(new Color(255, 250, 250));
        JMenu file = new JMenu("File");
        menubar.add(file);
        file.setFont(new Font("AERIAL", Font.PLAIN, 13));
        setJMenuBar(menubar);

        JMenu edit = new JMenu("Edit");
        menubar.add(edit);
        edit.setFont(new Font("AERIAL", Font.PLAIN, 13));
        setJMenuBar(menubar);

        JMenu help = new JMenu("Help");
        menubar.add(help);
        help.setFont(new Font("AERIAL", Font.PLAIN, 13));
        setJMenuBar(menubar);

//        menubar items in file frame
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);
        file.add(newdoc);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        file.add(open);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        file.add(save);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        file.add(print);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exit.addActionListener(this);
        file.add(exit);

//        menubar items in edit frame 
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        edit.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        edit.add(paste);
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        edit.add(cut);
        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);
        edit.add(selectall);

//        help 
        JMenuItem help1 = new JMenuItem("About");
        help1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        help1.addActionListener(this);
        help.add(help1);

//    text area 
        area = new JTextArea();
        area.setFont(new Font("Tahoma", Font.PLAIN, 16));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

//        scrollbar 
//we need to remove the add.area bcz area is on the pane
        JScrollPane pane = new JScrollPane(area);

        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setSize(1200, 620);
//        setLocation(80, 75);
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            area.setText("");
        } else if (e.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filename = new FileNameExtensionFilter("only .txt file", "txt");
            chooser.addChoosableFileFilter(filename);
            int action = chooser.showOpenDialog(this);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception ea) {
                System.err.println(ea);
            }
        } else if (e.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);

            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outfile = null;
            try {
                outfile = new BufferedWriter(new FileWriter(filename));
                area.write(outfile);
            } catch (Exception ea) {
                System.err.println(ea);
            }

        } else if (e.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception ea) {
                System.err.println(ea);
            }
        } else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        } else if (e.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } else if (e.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (e.getActionCommand().equals("Select All")) {
            area.selectAll();
        } else if (e.getActionCommand().equals("About")) {
            new About();
        }

    }

}
