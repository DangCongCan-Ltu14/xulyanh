import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import file.sjpg;
import file.spng;
import segment.imgs;
import xla.buff;
import xla.loc;

public class tool extends JFrame implements ActionListener, WindowListener {
	/**
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

		Close = new JMenuItem("close");
		File.add(Close);

		JMenu mnEdit = new JMenu("edit");
		menuBar.add(mnEdit);

		JMenu Loc = new JMenu("loc");
		mnEdit.add(Loc);

		LocGauss = new JMenuItem("loc gauss");
		Loc.add(LocGauss);

		LocTrungBinh = new JMenuItem("loc trung binh");
		Loc.add(LocTrungBinh);

		LocLaplace = new JMenuItem("loc  laplace");
		Loc.add(LocLaplace);

		segment = new JMenuItem("segment");
		mnEdit.add(segment);

		JMenuItem Potrace = new JMenuItem("potrace");
		mnEdit.add(Potrace);

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

		segment.addActionListener(this);
		Open.addActionListener(this);
		LocGauss.addActionListener(this);
		LocTrungBinh.addActionListener(this);
		LocGauss.addActionListener(this);
		LocLaplace.addActionListener(this);
		LocGauss.addActionListener(this);
		Save.addActionListener(this);
		SaveAs.addActionListener(this);
		gray.addActionListener(this);
		Invert.addActionListener(this);
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
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			chooser.setFileFilter(new sjpg());
			chooser.setFileFilter(new spng());
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				System.out.println("sss");
			repaint();
		} else if (e.getSource() == SaveAs) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("choosertitle");
			chooser.setFileFilter(new sjpg());
			chooser.setFileFilter(new spng());
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				System.out.println("sss");
			his.removeAll(his);
			repaint();
		} else if (e.getSource() == LocGauss) {
			dogauss();
		} else if (e.getSource() == LocLaplace) {
			loLaplace();
		} else if (e.getSource() == Invert) {
			int d = his.size();
			if (d > 0) {
				BufferedImage in = loc.imgiv(his.get(d - 1));
				addlist(in);
			}
			repaint();
		} else if (e.getSource() == gray) {
			dogray();
		} else if (e.getSource() == segment) {
			dosegment();
		}

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
			int p = Integer.parseInt(s);
			if (d > 0) {
				BufferedImage in = new imgs(his.get(d - 1), p).segment();
				addlist(in);
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
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");

		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
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

}
