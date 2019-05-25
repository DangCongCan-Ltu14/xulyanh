package control;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;

public class View extends JFrame {
	BufferedImage in = null;
	int x = 0, y = 0;
	Act tl;

	public View(Act p) {
		tl = p;
		setTitle("Preview");
		setSize(320, 350);
		setVisible(true);
		setDefaultLookAndFeelDecorated(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tl.done();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				groupLayout.createSequentialGroup().addGap(150).addComponent(btnOk).addContainerGap(149,
						Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup().addContainerGap(347, Short.MAX_VALUE).addComponent(btnOk)));
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4670228526202482191L;
	private JButton btnOk;

	public void pr(BufferedImage tin) {

		in = tin;

		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);

		int sx = 10, sy = 50;
		if (in != null) {
			g.drawImage(in, sx, sy, null);
		}
		g.dispose();
	}

}
