package com.worstcoder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.lang.*;

public class Notepad extends JFrame implements ActionListener{

    JTextArea area;
    JScrollPane pane;
    String text;


    Notepad(){
        super("DocPad");
//        initComponents();
        ImageIcon img = new ImageIcon("com/worstcoder/Note.png");
        super.setIconImage(img.getImage());

        setBounds(0,0,1950,1050);
        //creating Menu bar
        JMenuBar menubar = new JMenuBar();
        //create first menu file
        JMenu file = new JMenu("File");

        JMenuItem nw = new JMenuItem("New");
        nw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        nw.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        file.add(nw);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        //creating second menu Edit
        JMenu edit = new JMenu("Edit");

        JMenuItem cpy = new JMenuItem("Copy");
        cpy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        cpy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);

        edit.add(cpy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);

        //help i    n menu bar
        JMenu help = new JMenu("Help");

        JMenuItem about = new JMenuItem("About Notepad");
        about.addActionListener(this);

        //add the about option in help menu
        help.add(about);

        //adding menu items in menu bar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        //setting Menu bar
        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("ARIAL", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("New")){
            area.setText("");
        }else if(e.getActionCommand().equals("Open")){
            JFileChooser opn = new JFileChooser();
            opn.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter allow  = new FileNameExtensionFilter("Only .txt files", "txt");
            opn.addChoosableFileFilter(allow);

            int act = opn.showOpenDialog(this);
            if(act != JFileChooser.APPROVE_OPTION){
                return;
            }

            File file = opn.getSelectedFile();

            try{
                BufferedReader bfr = new BufferedReader(new FileReader(file));
                area.read(bfr, null);
            }catch(Exception E){
            E.printStackTrace();
            }

        }else if(e.getActionCommand().equals("Save")){
                JFileChooser saveas = new JFileChooser();
                saveas.setApproveButtonText("Save");
                int act = saveas.showOpenDialog(this);
                if(act != JFileChooser.APPROVE_OPTION){
                    return;
                }
                File filename = new File(saveas.getSelectedFile()+".txt");
                BufferedWriter outfile;
                try{
                    outfile = new BufferedWriter(new FileWriter(filename));
                    area.write(outfile);
                }catch(Exception E){
                E.printStackTrace();
                }

        }else if(e.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch(Exception E){
                E.printStackTrace();
            }

        }else if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(e.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }
        else if(e.getActionCommand().equals("Paste")){
            area.insert(text, area.getCaretPosition());
        }
        else if(e.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(e.getActionCommand().equals("Select All")){
            area.selectAll();
        }
        else if(e.getActionCommand().equals("About Notepad")){
            new About().setVisible(true);
        }
    }

    public static void main(String[] args) {
	// write your code here
        new Notepad().setVisible(true);
    }
}
