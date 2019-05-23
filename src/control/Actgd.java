package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Actgd implements ActionListener {
	tool ts;

	public Actgd(tool p) {
		ts = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ts.rdbtnmntmFix) {
			dofix();
		} else if (e.getSource() == ts.rdbtnmntmResize) {
			doResize();
		}else if(e.getSource()==ts.rdbtnmntmNimbus)
		{
			doNimbus();
		}
	}

	private void doNimbus() {

	}

	private void doResize() {
		// TODO Auto-generated method stub
		ts.fix = false;
		ts.rdbtnmntmFix.setSelected(false);
		int d = ts.his.size() - 1;
		int lx = ts.mx, ly = ts.my;
		BufferedImage a = ts.his.get(d);
		if (a.getWidth() + 40 < lx) {
			lx = a.getWidth() + 40;
		}
		if (a.getHeight() + 70 < ly) {
			ly = a.getHeight() + 70;
		}
		ts.bar1.setValue(0);
		ts.bar2.setValue(0);
		ts.x = 0;
		ts.y = 0;
		ts.setSize(lx, ly);
		ts.repaint();
	}

	private void dofix() {
		// TODO Auto-generated method stub
		ts.fix = true;
		ts.setSize(600, 600);
		ts.rdbtnmntmResize.setSelected(false);
		ts.repaint();
	}

}
