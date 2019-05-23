package set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Setf extends JFrame {
	private JRadioButton rdbtnCoDinh;
	private JRadioButton rdbtnResize;
	private JRadioButton rdbtnGtk;
	private JRadioButton rdbtnNimb;
	private JRadioButton rdbtnMetal;
	int a, b;

	public Setf() {

		rdbtnGtk = new JRadioButton("GTK+");

		rdbtnNimb = new JRadioButton("Nimb");

		rdbtnMetal = new JRadioButton("Metal");

		JLabel lblGiao = new JLabel("Giao dien");

		JLabel lblSize = new JLabel("size");

		rdbtnCoDinh = new JRadioButton("co dinh");

		rdbtnResize = new JRadioButton("resize");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(40).addComponent(lblGiao))
						.addGroup(groupLayout.createSequentialGroup().addGap(49).addComponent(lblSize))
						.addGroup(groupLayout.createSequentialGroup().addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(rdbtnGtk)
										.addComponent(rdbtnCoDinh))
								.addGap(38)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup().addComponent(rdbtnNimb).addGap(72)
												.addComponent(rdbtnMetal))
										.addComponent(rdbtnResize))))
				.addContainerGap(109, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(46).addComponent(lblGiao).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnGtk)
								.addComponent(rdbtnNimb).addComponent(rdbtnMetal))
						.addGap(18).addComponent(lblSize).addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnCoDinh)
								.addComponent(rdbtnResize))
						.addContainerGap(79, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);
	}

	public Setting pr() {
		
		return null;
	}
}
