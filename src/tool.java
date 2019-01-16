import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import base.svg.img;
import file.sjpg;
import file.spng;
import potrace.vector;
import segment.bina;
import segment.imgs;
import xla.buff;
import xla.loc;
import xla.loc2;

public class tool extends JFrame implements ActionListener, WindowListener,KeyListener {
	/**
	 * @author amneiht
	 *
	 */
	private static final long serialVersionUID = -2667525690602204291L;
	private JMenuBar menuBar;
	private JMenu File;
	private JMenu mnHelp;
	private JMenuItem mntmInfo;
	private JMenuItem Open;
	private JMenuItem Save;
	private JMenuItem Close;
	private JScrollBar bar2;
	private JScrollBar bar1;
	private JMenuItem gray;
	private JMenuItem Invert;
	private JMenuItem segment;
	private JMenuItem LocGauss;
	private JMenuItem LocTrungBinh;
	private JMenuItem LocLaplace;
	private JMenuItem SaveAs;
	List<BufferedImage> his = new LinkedList<BufferedImage>();
	int x = 0, y = 0;
	img is = null;

	private JMenu Potrace;
	private JMenuItem Vector;
	private JMenuItem KhoiPhuc;
	private JMenuItem Redo;
	private JMenuItem Binary;
	private JMenuItem Svg;
	private JMenuItem gauss2;

	public tool() {
		this.addWindowListener(this);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bar2 = new JScrollBar();
		
		bar1 = new JScrollBar();
		bar1.setOrientation(JScrollBar.HORIZONTAL);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(getContentPane(), popupMenu);

		Invert = new JMenuItem("invert");
		popupMenu.add(Invert);

		gray = new JMenuItem("gray");
		popupMenu.add(gray);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(bar1, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(bar2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(bar2, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(443, Short.MAX_VALUE).addComponent(bar1,
						GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)));
		getContentPane().setLayout(groupLayout);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		File = new JMenu("File");
		menuBar.add(File);

		Open = new JMenuItem("open");
		File.add(Open);

		Save = new JMenuItem("save");
		File.add(Save);

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

		LocLaplace = new JMenuItem("loc  laplace");
		Loc.add(LocLaplace);

		segment = new JMenuItem("segment");
		mnEdit.add(segment);

		Binary = new JMenuItem("binary");
		mnEdit.add(Binary);

		Potrace = new JMenu("potrace");
		mnEdit.add(Potrace);

		Vector = new JMenuItem("vector");
		Potrace.add(Vector);

		KhoiPhuc = new JMenuItem("khoi phuc");
		Potrace.add(KhoiPhuc);

		Redo = new JMenuItem("redo");
		mnEdit.add(Redo);

		mnHelp = new JMenu("help");
		menuBar.add(mnHelp);

		mntmInfo = new JMenuItem("info");
		mnHelp.add(mntmInfo);

		addac();
	}

	public void paint(Graphics g) {
		super.paint(g);
		int d = his.size();
		if (d > 0) {
			int sx = 10, sy = 80;
			BufferedImage in = his.get(d - 1);
			int dx = getWidth() - sx - 20;
			int dy = getHeight() - sy - 20;
			int px = in.getWidth() - x;
			int py = in.getHeight() - y;
			if (px > dx)
				px = dx;
			if (py > dy)
				py = dy;
			g.drawImage(in, sx, sy, px + sx, py + sy, x, y, x + px, y + py, null);
		}
	}

	private void addac() {
		addKeyListener(this);
		segment.addActionListener(this);
		Open.addActionListener(this);
		LocGauss.addActionListener(this);
		LocTrungBinh.addActionListener(this);
		LocLaplace.addActionListener(this);
		Save.addActionListener(this);
		SaveAs.addActionListener(this);
		gray.addActionListener(this);
		Invert.addActionListener(this);
		Vector.addActionListener(this);
		KhoiPhuc.addActionListener(this);
		Redo.addActionListener(this);
		Binary.addActionListener(this);
		Svg.addActionListener(this);
		gauss2.addActionListener(this);
	}

	/**
	 * 
	 */

	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		tool p = new tool();
		p.setSize(600, 600);
		p.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// System.out.println("xcmn");
		if (e.getSource() == Open) {
			doOpen();
		} else if (e.getSource() == Save) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("/home/amneiht/Desktop"));
			chooser.setDialogTitle("choosertitle");
			chooser.setFileFilter(new sjpg());
			chooser.setFileFilter(new spng());
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				//System.out.println("sss");
			repaint();
		} else if (e.getSource() == SaveAs) {
			dosaveAs();
		}else if (e.getSource() == LocLaplace) {
			loLaplace();
		} else if (e.getSource() == Invert) {
			doinvert();
		} else if (e.getSource() == gray) {
			dogray();
		} else if (e.getSource() == segment) {
			dosegment();
		} else if (e.getSource() == Vector) {
			dovecto();
		} else if (e.getSource() == KhoiPhuc) {
			dokhoiphuc();
		} else if (e.getSource() == LocTrungBinh) {
			doloctb();
		} else if (e.getSource() == Redo) {
			doctrlz();
		} else if (e.getSource() == Binary) {
			doBina();
		} else if (e.getSource() == Svg) {
			doSvg();
		} else if (e.getSource() == gauss2) {
			dolocg2();
		} else if (e.getSource() == LocGauss) {
			dogauss();
		} 

	}

	private void dolocg2() {
		String s = (String) JOptionPane.showInputDialog(this, "nhap kich thuoc", "from", JOptionPane.PLAIN_MESSAGE);
		try {
			int d = his.size();
			if (s != null) {
				int p = Integer.parseInt(s);
				if (d > 0) {
					BufferedImage in = loc2.tgex(his.get(d - 1), p);
					addlist(in);
				}
			}
		} catch (Exception se) {
			se.printStackTrace();

		}
		repaint();
		
	}

	private void doSvg() {
		if (is != null) {
			int d = his.size();
			if (d > 0) {
				//System.out.println("dosave");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("/amneiht/sdf"));
				chooser.setDialogTitle("choosertitle");
				if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
					is.create(chooser.getSelectedFile().getPath());
				}
				//his.removeAll(his);
				//is = null;
			}
		}
		repaint();

	}

	private void doBina() {

		int d = his.size();
		if (d > 0) {
			addlist(bina.segment(his.get(d - 1)));
		}
		repaint();
	}

	private void dosaveAs() {
		int d = his.size();
		if (d > 0) {
			//System.out.println("dosave");
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			chooser.setFileFilter(new sjpg());
			chooser.setFileFilter(new spng());
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String a = chooser.getSelectedFile().getPath();
				String b = file.list.getex(chooser.getFileFilter().getDescription());
				buff.save(his.get(d - 1), a, b);
			}
			//his.removeAll(his);
			//is = null;
		}
		repaint();

	}

	private void doctrlz() {

		int d = his.size();
		if (d > 1) {
			his.remove(d - 1);
		}
		repaint();
	}

	private void doloctb() {
		int d = his.size();
		if (d > 0) {
			BufferedImage in = loc.tb(his.get(d - 1));
			addlist(in);
		}
		repaint();
	}

	private void dokhoiphuc() {
		if (is != null) {
			is.khoiphuc();
			addlist(is.paintimg());
			repaint();
		}

	}

	private void dovecto() {
		is = null;
		System.gc();
		String s = (String) JOptionPane.showInputDialog(this, "amneiht", "nhap sai so", JOptionPane.PLAIN_MESSAGE);
		try {
			int d = his.size();
			int p = Integer.parseInt(s);
			if (d > 0) {
				is = new vector(his.get(d - 1), p).creat();
				addlist(is.paintimg());
			}
		} catch (Exception se) {
			se.printStackTrace();

		}
		repaint();

	}

	private void doinvert() {
		int d = his.size();
		if (d > 0) {
			BufferedImage in = loc.imgiv(his.get(d - 1));
			addlist(in);
		}
		repaint();

	}

	private void dogray() {
		int d = his.size();
		if (d > 0) {
			BufferedImage in = loc.imggray(his.get(d - 1));
			addlist(in);
		}
		repaint();
	}

	private void dosegment() {
		String s = (String) JOptionPane.showInputDialog(this, "amneiht", "nhap so mau", JOptionPane.PLAIN_MESSAGE);
		try {
			int d = his.size();
			if (s != null) {
				int p = Integer.parseInt(s);
				if (d > 0) {
					BufferedImage in = new imgs(his.get(d - 1), p).segment();
					addlist(in);
				}
			}
		} catch (Exception se) {
			se.printStackTrace();

		}
		repaint();

	}

	private void loLaplace() {
		int d = his.size();
		if (d > 0) {
			BufferedImage in = loc.tc(his.get(d - 1));
			addlist(in);
		}
		repaint();

	}

	private void doOpen() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("/home/amneiht/Desktop"));
		chooser.setDialogTitle("choosertitle");

		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				//
				addlist(buff.get(chooser.getSelectedFile().getPath()));
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		} else {
			System.out.println("No Selection ");
		}
		repaint();

	}

	private void dogauss() {
		int d = his.size();
		if (d > 0) {
		//	System.out.println("gause");
			BufferedImage in = loc.tg(his.get(d - 1));
			addlist(in);
		}
		repaint();
	}

	private void addlist(BufferedImage bufferedImage) {
		his.add(bufferedImage);
		if (his.size() > 10)
			his.remove(0);
	}

	public void windowOpened(WindowEvent e) {

	}

	public void windowClosing(WindowEvent e) {

		System.out.println("cmn");
		System.exit(NORMAL);
	}

	public void windowClosed(WindowEvent e) {

	}

	public void windowIconified(WindowEvent e) {

	}

	public void windowDeiconified(WindowEvent e) {

	}

	public void windowActivated(WindowEvent e) {

	}

	public void windowDeactivated(WindowEvent e) {

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
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

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyChar()=='z')
			doctrlz();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

}
