package control;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import base.svg.img;
import file.Fvt;
import file.sjpg;
import file.spng;
import pv3.ltv;
import pv3.vector;
import segment.Kmean;
import segment.Kmean2;
import segment.bina;
import xla.buff;
import xla.loc;
import xla.loc2;

public class Act implements ActionListener {
	tool ts;
	protected boolean pre = false;
	int nocolor;
	img is = null;
	View k ;

	int mode = -1;
	int ths = -1;
	ActionEvent ac;

	public Act(tool p) {
		ts = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("xcmn");
		if (e.getSource() == ts.Open) {
			doOpen();
		} else if (e.getSource() == ts.SaveAs) {
			dosaveAs();
		} else if (e.getSource() == ts.Svg) {
			doSvg();
		} else if (e.getSource() == ts.KhoiPhuc) {
			dokhoiphuc();
		} else if (e.getSource() == ts.rdbtnmntmPr) {
			pre = !pre;
			if (pre) {
				k = new View(this);
			} else {
				k.dispose();
			}
		}
		if (pre) {
			preview(e);
		} else {
			dowork(e);
		}
		ts.repaint();
		System.gc();

	}

	private void dowork(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ts.LocLaplace) {
			loLaplace();
		} else if (e.getSource() == ts.Invert) {
			doinvert();
		} else if (e.getSource() == ts.gray) {
			dogray();
		} else if (e.getSource() == ts.segment) {
			dosegment();
		} else if (e.getSource() == ts.Vector) {
			dovecto();
		} else if (e.getSource() == ts.LocTrungBinh) {
			doloctb();
		} else if (e.getSource() == ts.Redo) {
			ts.doctrlz();
		} else if (e.getSource() == ts.Binary) {
			doBina();
		} else if (e.getSource() == ts.gauss2) {
			dolocg2();
		} else if (e.getSource() == ts.LocGauss) {
			dogauss();
		} else if (e.getSource() == ts.mntmTach) {
			dotach();
		} else if (e.getSource() == ts.mntmltv) {
			doltv();
		} else if (e.getSource() == ts.mntmSegment) {
			dosegment2();
		}
	}

	private void preview(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("pre");
		if (e.getSource() == ts.LocLaplace) {
			int d = ts.his.size();
			if (d > 0) {
				System.out.println();
				BufferedImage in = scale(ts.his.get(d - 1));
				in = loc.tc(in);
				k.pr(in);
				ac = e;
			}
		} else if (e.getSource() == ts.Invert) {

			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				in = loc.imgiv(in);
				k.pr(in);
				ac = e;
			}
		} else if (e.getSource() == ts.gray) {

			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				in = loc.imggray(in);
				k.pr(in);
				ac = e;
			}
		} else if (e.getSource() == ts.segment) {
			String s = (String) JOptionPane.showInputDialog(ts, "nhap kich thuoc", "from", JOptionPane.PLAIN_MESSAGE);
			try {
				int d = ts.his.size();
				if (s != null) {
					ths = Integer.parseInt(s);
					if (d > 0) {
						BufferedImage in = scale(ts.his.get(d - 1));
						Kmean sg = new Kmean(in, ths);
						in = sg.segment();
						k.pr(in);
						System.out.println(ths);
						ac = e;
					}
				}
			} catch (Exception se) {
				se.printStackTrace();

			}
		} else if (e.getSource() == ts.Vector) {

			String s = (String) JOptionPane.showInputDialog(ts, "nhap kich thuoc", "from", JOptionPane.PLAIN_MESSAGE);
			try {
				int d = ts.his.size();
				if (s != null) {
					ths = Integer.parseInt(s);
					if (d > 0) {
						BufferedImage in = scale(ts.his.get(d - 1));
						img is = new vector(in, ths).creat();
						k.pr(is.paintimg());
						ac = e;
					}
				}
			} catch (Exception se) {
				se.printStackTrace();

			}
		} else if (e.getSource() == ts.LocTrungBinh) {

			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				doloctb();
				k.pr(in);
				ac = e;
			}
		} else if (e.getSource() == ts.Binary) {

			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				k.pr(bina.segment(in));
				ac = e;
			}
		} else if (e.getSource() == ts.gauss2) {
			String s = (String) JOptionPane.showInputDialog(ts, "nhap kich thuoc", "from", JOptionPane.PLAIN_MESSAGE);
			try {
				int d = ts.his.size();
				if (s != null) {
					ths = Integer.parseInt(s);
					if (d > 0) {
						BufferedImage in = scale(ts.his.get(d - 1));
						in = loc2.tgex(in, ths);
						k.pr(in);
						ac = e;
					}
				}
			} catch (Exception se) {
				se.printStackTrace();

			}
		} else if (e.getSource() == ts.LocGauss) {

			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				dogauss();
				k.pr(in);
				ac = e;
			}
		} else if (e.getSource() == ts.mntmTach) {

			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				dotach();
				k.pr(in);
				ac = e;
			}
		} else if (e.getSource() == ts.mntmltv) {

			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				doltv();
				k.pr(in);
				ac = e;
			}
		} else if (e.getSource() == ts.mntmSegment) {
			int d = ts.his.size();
			if (d > 0) {
				BufferedImage in = scale(ts.his.get(d - 1));
				Kmean2 sg = new Kmean2(in, Math.E);
				in = sg.segment();
				k.pr(in);
				ac = e;
			}
		}

	}

	public void done() {
		if (ac != null) {
			dowork(ac);
			ths = -1;
			ac = null;
		}
		ts.repaint();
	}

	private BufferedImage scale(BufferedImage tin) {
		double h = 300.0 / Math.max(tin.getHeight(), tin.getWidth());
		int y = (int) (tin.getHeight() * h), x = (int) (tin.getWidth() * h);
		BufferedImage in = new BufferedImage(x, y, tin.getType());
		Image tmp = tin.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		Graphics g = in.getGraphics();
		g.drawImage(tmp, 0, 0, null);
		g.dispose();
		return in;
	}

	void doltv() {
		int d = ts.his.size();
		try {

			if (d > 0) {
				BufferedImage in = ltv.tv(ts.his.get(d - 1));
				ts.addlist(in);

			}
		} catch (Exception se) {
			se.printStackTrace();

		}

	}

	void dotach() {
		int d = ts.his.size();
		try {

			if (d > 0) {
				BufferedImage in = loc.tds(ts.his.get(d - 1));
				ts.addlist(in);
			}
		} catch (Exception se) {
			se.printStackTrace();

		}

	}

	void dolocg2() {
		try {
			if (ths < 0) {
				String s = (String) JOptionPane.showInputDialog(ts, "amneiht", "nhap sai so",
						JOptionPane.PLAIN_MESSAGE);
				if (s != null)
					ths = Integer.parseInt(s);
			}

			int d = ts.his.size();
			if (ths > 0) {
				if (d > 0) {
					BufferedImage in = loc2.tgex(ts.his.get(d - 1), ths);
					ts.addlist(in);
				}
			}
		} catch (Exception se) {
			se.printStackTrace();

		}

	}

	void doSvg() {
		if (is != null) {
			int d = ts.his.size();
			if (d > 0) {
				// System.out.println("dosave");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("/amneiht/sdf"));
				chooser.setDialogTitle("choosertitle");
				if (chooser.showSaveDialog(ts) == JFileChooser.APPROVE_OPTION) {
					is.create(chooser.getSelectedFile().getPath());
				}
			}
		}

	}

	void doBina() {

		int d = ts.his.size();
		if (d > 0) {
			ts.addlist(bina.segment(ts.his.get(d - 1)));
		}

	}

	void dosaveAs() {
		try {
			int d = ts.his.size();
			if (d > 0) {
				// System.out.println("dosave");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("/home/amneiht/Desktop/anh"));
				chooser.setDialogTitle("choosertitle");
				chooser.setFileFilter(new sjpg());
				chooser.setFileFilter(new spng());
				chooser.setFileFilter(new Fvt());
				if (chooser.showSaveDialog(ts) == JFileChooser.APPROVE_OPTION) {
					String a = chooser.getSelectedFile().getPath();
					String b = file.list.getex(chooser.getFileFilter().getDescription());
					System.out.println(b);
					if (b.equals("vt")) {
						if (is != null)
							is.save(a);
					} else
						buff.save(ts.his.get(d - 1), a, b);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void doloctb() {
		int d = ts.his.size();
		if (d > 0) {
			BufferedImage in = loc.tb(ts.his.get(d - 1));
			ts.addlist(in);
		}

	}

	void dokhoiphuc() {
		if (is != null) {
			// is.khoiphuc();
			ts.addlist(is.kp(nocolor));

		}

	}

	void dovecto() {
		is = null;
		// String s = (String) JOptionPane.showInputDialog(ts, "amneiht", "nhap sai so",
		// JOptionPane.PLAIN_MESSAGE);
		// try {
		// int d = ts.his.size();
		// int p = Integer.parseInt(s);
		// if (d > 0) {
		// if (p == 0)
		// p = 1;
		// is = new vector(ts.his.get(d - 1), p).creat();
		// ts.addlist(is.paintimg());
		// }
		// } catch (Exception se) {
		// System.out.println("loi nhap so");
		//
		// }
		try {
			if (ths < 0) {
				String s = (String) JOptionPane.showInputDialog(ts, "amneiht", "nhap sai so",
						JOptionPane.PLAIN_MESSAGE);
				if (s != null)
					ths = Integer.parseInt(s);
			}

			int d = ts.his.size();
			if (ths > 0) {
				if (d > 0) {

					is = new vector(ts.his.get(d - 1), ths).creat();
					ts.addlist(is.paintimg());
				}
			}
		} catch (Exception se) {
			se.printStackTrace();

		}

	}

	void doinvert() {
		int d = ts.his.size();
		if (d > 0) {
			BufferedImage in = loc.imgiv(ts.his.get(d - 1));
			ts.addlist(in);
		}

	}

	private void dogray() {
		int d = ts.his.size();
		if (d > 0) {
			BufferedImage in = loc.imggray(ts.his.get(d - 1));
			ts.addlist(in);
		}

	}

	private void dosegment() {
		try {
			if (ths < 0) {
				String s = (String) JOptionPane.showInputDialog(ts, "amneiht", "nhap so mau",
						JOptionPane.PLAIN_MESSAGE);
				if (s != null)
					ths = Integer.parseInt(s);
			}

			int d = ts.his.size();
			System.out.println("com " + ths);
			if (ths > 0) {
				if (d > 0) {
					Kmean sg = new Kmean(ts.his.get(d - 1), ths);
					BufferedImage in = sg.segment();
					ts.addlist(in);
					nocolor = sg.nocolor();

				}
			}
		} catch (Exception se) {
			se.printStackTrace();

		}

	}

	private void dosegment2() {
		try {
			int d = ts.his.size();
			if (d > 0) {

				Kmean2 sg = new Kmean2(ts.his.get(d - 1), Math.E);
				BufferedImage in = sg.segment();
				ts.addlist(in);
				nocolor = sg.nocolor();
			}

		} catch (Exception se) {
			se.printStackTrace();

		}

	}

	void loLaplace() {
		int d = ts.his.size();
		if (d > 0) {
			BufferedImage in = loc.tc(ts.his.get(d - 1));
			ts.addlist(in);
		}

	}

	void doOpen() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("/home/amneiht/Desktop/anh"));
		chooser.setDialogTitle("choosertitle");

		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				//
				String d = chooser.getSelectedFile().getPath();
				if (d.endsWith(".vt")) {
					is = img.read(d);
					ts.his.add(is.paintimg());
				} else
					ts.addlist(buff.get(d));
				// System.out.println(chooser.getSelectedFile().getPath());
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		} else {
			System.out.println("No Selection ");
		}
		if (!ts.fix) {
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
		}

	}

	void dogauss() {
		int d = ts.his.size();
		if (d > 0) {
			// System.out.println("gause");
			BufferedImage in = loc.tg(ts.his.get(d - 1));
			ts.addlist(in);
		}

	}

}
