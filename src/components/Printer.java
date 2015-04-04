package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Printer extends JPanel
{
  private JPanel jPanel = new JPanel();
  private JLabel jLabel = new JLabel();
  private String OUTPUT;
  public String STATUS = "1";
  JScrollPane thePane = new JScrollPane(jPanel);
  public Printer()
  {
    this.OUTPUT = "";

    initComponents();
  }

  public void GetData(String s)
  {
    this.STATUS = "0";
    this.OUTPUT = (this.OUTPUT + String.format("%s", new Object[] { Integer.valueOf(Integer.parseInt(s, 2)) }) + "<br>");
    this.jLabel.setText("<html>" + this.OUTPUT + "</html>");
    this.STATUS = "1";
  }

  public void initComponents()
  {
//    add(this.jPanel);
    add(this.jLabel);
    add(this.thePane);
    this.jPanel.setBorder(BorderFactory.createTitledBorder(null, "Printer", 3, 4, new Font("Verdana", 1, 11), Color.blue));
    this.jPanel.setFont(new Font("Verdana", 1, 8));
    this.jLabel.setFont(new Font("Verdana", 1, 11));

    GroupLayout jPanel4Layout = new GroupLayout(this.jPanel);
    this.jPanel.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 88, 32767).addComponent(this.jLabel));

    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767).addComponent(this.jLabel));

    this.jPanel.setPreferredSize(new Dimension(200, 100));
  }
}