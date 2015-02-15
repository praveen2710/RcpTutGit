package org.eclipse.jface.snippets.viewers;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Main {
  public static void main(final String args[]) {
    JFrame frame = new JFrame("MenuSample Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar menuBar = new JMenuBar();

    // File Menu, F - Mnemonic
    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic(KeyEvent.VK_F);
    menuBar.add(fileMenu);

    fileMenu.addMenuListener(new MenuListener() {

      public void menuSelected(MenuEvent e) {
        System.out.println("menuSelected");
      }

      public void menuDeselected(MenuEvent e) {
        System.out.println("menuDeselected");

      }

      public void menuCanceled(MenuEvent e) {
        System.out.println("menuCanceled");

      }
    });

    // File->New, N - Mnemonic
    JMenuItem newMenuItem = new JMenuItem("New");
    fileMenu.add(newMenuItem);

    frame.setJMenuBar(menuBar);
    frame.setSize(350, 250);
    frame.setVisible(true);
  }
}
