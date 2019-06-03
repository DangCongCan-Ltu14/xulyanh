package control;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JRadioButtonMenuItem;

public class tool extends JFrame implements KeyListener, AdjustmentListener {
	/**
	 * @author amneiht
	 *
	 */

	static final long serialVersionUID = -2667525690602204291L;
	protected int mx = 1700, my = 800;
	protected JMenuBar menuBar;
	protected JMenu File;
	protected JMenu mnHelp;
	protected JMenuItem mntmInfo;
	protected JMenuItem Open;
	protected JMenuItem Close;
	protected JScrollBar bar2;
	protected JScrollBar bar1;
	protected JMenuItem gray;
	protected JMenuItem Invert;

	protected JMenuItem segment;
	protected JMenuItem LocGauss;
	protected JMenuItem LocTrungBinh;
	protected JMenuItem LocLaplace;
	protected JMenuItem SaveAs;
	protected List<BufferedImage> his = new LinkedList<BufferedImage>();
	protected List<BufferedImage> pre = new LinkedList<BufferedImage>();
	protected boolean fix = true;
	protected int dx = 0, dy = 0;
	protected int x = 0, y = 0;
	protected JMenu Potrace;
	protected JMenuItem Vector;
	protected JMenuItem KhoiPhuc;
	protected JMenuItem Redo;
	protected JMenuItem Binary;
	protected JMenuItem Svg;
	protected JMenuItem gauss2;
	protected JMenuItem mntmTach;
	protected JMenuItem mntmltv;
	protected JMenu mnSegment;
	protected JMenuItem mntmSegment;
	// protected Act action;
	protected JMenu mnGiaoDien;
	protected JMenu mnSetsize;
	protected JRadioButtonMenuItem rdbtnmntmNimbus;
	protected JRadioButtonMenuItem rdbtnmntmFix;
	protected JRadioButtonMenuItem rdbtnmntmResize;
	protected JRadioButtonMenuItem rdbtnmntmGtk;
	protected JRadioButtonMenuItem rdbtnmntmPr;

	public tool() {
		// this.setResizable(false);
		// action = new Act(this);
		addmenu();
		addac();
		setbar();
		addgd();
	}

	private void addgd() {
		Actgd ac = new Actgd(this);
		rdbtnmntmFix.addActionListener(ac);
		rdbtnmntmResize.addActionListener(ac);
		rdbtnmntmGtk.addActionListener(ac);
		rdbtnmntmNimbus.addActionListener(ac);
	}

	private void addmenu() {
		bar2 = new JScrollBar();

		bar1 = new JScrollBar();
		bar1.setOrientation(JScrollBar.HORIZONTAL);
		setResizable(true);
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(getContentPane(), popupMenu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Invert = new JMenuItem("invert");
		popupMenu.add(Invert);

		gray = new JMenuItem("gray");
		popupMenu.add(gray);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(bar1, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(bar2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(bar2, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(235, Short.MAX_VALUE).addComponent(bar1,
						GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)));
		getContentPane().setLayout(groupLayout);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		File = new JMenu("File");
		menuBar.add(File);

		Open = new JMenuItem("open");
		File.add(Open);

		JMenu mnSetting = new JMenu("setting");
		File.add(mnSetting);

		mnGiaoDien = new JMenu("giao dien");
		mnSetting.add(mnGiaoDien);
		rdbtnmntmNimbus = new JRadioButtonMenuItem("nimbus");
		mnGiaoDien.add(rdbtnmntmNimbus);

		rdbtnmntmGtk = new JRadioButtonMenuItem("gtk");
		mnGiaoDien.add(rdbtnmntmGtk);

		mnSetsize = new JMenu("setsize");
		mnSetting.add(mnSetsize);

		rdbtnmntmFix = new JRadioButtonMenuItem("fix");
		rdbtnmntmFix.setEnabled(true);
		mnSetsize.add(rdbtnmntmFix);

		rdbtnmntmResize = new JRadioButtonMenuItem("resize");
		mnSetsize.add(rdbtnmntmResize);

		rdbtnmntmPr = new JRadioButtonMenuItem("prievew");
		rdbtnmntmPr.setActionCommand("privew");
		mnSetting.add(rdbtnmntmPr);

		SaveAs = new JMenuItem("save as");
		File.add(SaveAs);

		Svg = new JMenuItem("svg");
		File.add(Svg);

		Close = new JMenuItem("close");
		File.add(Close);

		JMenu mnEdit = new JMenu("edit");
		menuBar.add(mnEdit);

		JMenu Loc = new JMenu("loc");
		mnEdit.add(Loc);

		LocGauss = new JMenuItem("loc gauss");
		Loc.add(LocGauss);

		gauss2 = new JMenuItem("loc gauss 2");
		Loc.add(gauss2);

		LocTrungBinh = new JMenuItem("loc trung binh");
		Loc.add(LocTrungBinh);

		mntmltv = new JMenuItem("loc trung vi");
		Loc.add(mntmltv);

		LocLaplace = new JMenuItem("loc  laplace");
		Loc.add(LocLaplace);

		mntmTach = new JMenuItem("tach");
		mnEdit.add(mntmTach);

		Potrace = new JMenu("potrace");
		mnEdit.add(Potrace);

		Vector = new JMenuItem("vector");
		Potrace.add(Vector);

		KhoiPhuc = new JMenuItem("khoi phuc");
		Potrace.add(KhoiPhuc);

		Redo = new JMenuItem("redo");
		mnEdit.add(Redo);

		mnSegment = new JMenu("segment");
		menuBar.add(mnSegment);

		segment = new JMenuItem("segment-1");
		mnSegment.add(segment);

		mntmSegment = new JMenuItem("segment-2");
		mnSegment.add(mntmSegment);

		Binary = new JMenuItem("binary");
		mnSegment.add(Binary);

		mnHelp = new JMenu("help");
		menuBar.add(mnHelp);

		mntmInfo = new JMenuItem("info");
		mnHelp.add(mntmInfo);

	}

	private void setbar() {
		bar1.addAdjustmentListener(this);
		bar2.addAdjustmentListener(this);
		bar1.setMaximum(100);
		bar2.setMaximum(100);

	}

	public void paint(Graphics g) {
		super.paint(g);
		int d = his.size();
		if (d > 0) {

			int sx = 10, sy = 50;

			BufferedImage in = his.get(d - 1);

			int dx = getWidth() - sx - 20;
			int dy = getHeight() - sy - 20;
			int px = in.getWidth() - x;
			int py = in.getHeight() - y;
			if (px > dx) {
				px = dx;
			}
			if (py > dy) {
				py = dy;
			}
			g.drawImage(in, sx, sy, px + sx, py + sy, x, y, x + px, y + py, null);
			g.dispose();
		}
	}

	protected void addac() {
		Act action = new Act(this);
		addKeyListener(this);
		// addMouseMotionListener(this);
		rdbtnmntmPr.addActionListener(action);
		segment.addActionListener(action);
		Open.addActionListener(action);
		LocGauss.addActionListener(action);
		LocTrungBinh.addActionListener(action);
		LocLaplace.addActionListener(action);
		SaveAs.addActionListener(action);
		gray.addActionListener(action);
		Invert.addActionListener(action);
		Vector.addActionListener(action);
		KhoiPhuc.addActionListener(action);
		Redo.addActionListener(action);
		Binary.addActionListener(action);
		Svg.addActionListener(action);
		gauss2.addActionListener(action);
		mntmTach.addActionListener(action);
		mntmltv.addActionListener(action);
		mntmSegment.addActionListener(action);

	}

	/**
	 * 
	 */

	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("GTK+".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
				// System.out.println(info.getName());
			}
			// UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
			e.printStackTrace();

		}
		tool p = new tool();
		p.setTitle("Phần mềm vectơ hóa ảnh");
		p.setSize(600, 600);
		p.setVisible(true);
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		if (e.getSource() == bar1) {

			int d = bar1.getValue();

			int z = his.size();

			if (z > 0) {
				BufferedImage in = his.get(z - 1);
				// System.out.println(in.getWidth() - getWidth());
				if (in.getWidth() > this.getWidth()) {
					x = (((in.getWidth() - this.getWidth()) * d) / 86);

					repaint();
				}
			}
		} else if (e.getSource() == bar2) {

			int d = bar2.getValue();

			int z = his.size();

			if (z > 0) {
				BufferedImage in = his.get(z - 1);
				// System.out.println(in.getHeight() - getHeight());
				if (in.getHeight() > this.getHeight()) {
					y = (((in.getHeight() - this.getHeight()) * d) / 86);

					repaint();
				}
			}
		}
	}

	protected void addlist(BufferedImage bufferedImage) {
		his.add(bufferedImage);
		pre.removeAll(pre);
		if (his.size() > 20)
			his.remove(0);
	}

	public void windowOpened(WindowEvent e) {

	}

	protected static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyChar() == 'z')
			doctrlz();
		else if (e.getKeyChar() == 'r')
			doctrlr();
	}

	protected void doctrlz() {

		int d = his.size();
		if (d > 1) {
			pre.add(0, his.remove(d - 1));
		}
		repaint();
	}

	protected void doctrlr() {

		int d = pre.size();
		if (d > 0) {
			System.out.println("ss");
			his.add(pre.remove(0));
			if (his.size() > 20)
				his.remove(0);

		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
