package Presentation.receiptui.tablemodels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class MyHeaderButtonRenderer extends JButton implements
		TableCellRenderer, MouseListener {
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;
	public static final int NONE = 0;
	public static final int DOWN = 1;
	public static final int UP = 2;
	private int headerHeight = 18;
	private int pushedColumn = 0;
	private Hashtable<Integer, Integer> state = null;
	private JButton noneButton = null;
	private JButton downButton = null;
	private JButton upButton = null;
	private JTableHeader header = null;
	private MyHeaderButtonRenderer renderer = null;

	public MyHeaderButtonRenderer() {
		this(null, null);
	}

	public MyHeaderButtonRenderer(JTableHeader header,
			MyHeaderButtonRenderer renderer) {
		pushedColumn = -1;
		state = new Hashtable<Integer, Integer>();
		setMargin(new Insets(0, 0, 0, 0));
		setHorizontalTextPosition(LEFT);
		this.header = header;
		this.renderer = renderer;
		init();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		JButton button = this;
		Object obj = state.get(new Integer(column));
		if (obj != null) {
			if (((Integer) obj).intValue() == DOWN) {
				button = downButton;
			} else if (((Integer) obj).intValue() == UP) {
				button = upButton;
			} else {
				button = noneButton;
			}
		}
		JTableHeader header = table.getTableHeader();
		if (header != null) {
			Color fgColor = null;
			Color bgColor = null;
			if (hasFocus) {
				fgColor = UIManager.getColor("TableHeader.focusCellForeground");
				bgColor = UIManager.getColor("TableHeader.focusCellBackground");
			}
			if (fgColor == null) {
				fgColor = header.getForeground();
			}
			if (bgColor == null) {
				bgColor = header.getBackground();
			}
			setForeground(fgColor);
//			setBackground(bgColor);
			setBackground(new Color(230,230,250));

			setFont(header.getFont());
		}
		// set height.
		if (headerHeight != 0) {
			setPreferredSize(new Dimension(getWidth(), headerHeight));
		} else {
			setPreferredSize(new Dimension(getWidth(), getHeight()));
		}

		Border border = null;
		if (hasFocus) {
			border = UIManager.getBorder("TableHeader.focusCellBorder");
		}
		if (border == null) {
			border = UIManager.getBorder("TableHeader.cellBorder");
		}
		setBorder(border);

		button.setText((value == null) ? "" : value.toString());
		if (header != null && renderer != null) {
			boolean isPressed = (column == pushedColumn);
			button.getModel().setPressed(isPressed);
			button.getModel().setArmed(isPressed);
		}
		return button;
	}

	/**
	 * init.
	 */
	private void init() {
		noneButton = new JButton();
		noneButton.setMargin(new Insets(0, 0, 0, 0));
		noneButton.setHorizontalTextPosition(LEFT);

		downButton = new JButton();
		downButton.setMargin(new Insets(0, 0, 0, 0));
		downButton.setHorizontalTextPosition(LEFT);

		upButton = new JButton();
		upButton.setMargin(new Insets(0, 0, 0, 0));
		upButton.setHorizontalTextPosition(LEFT);
	}

	public void setPressedColumn(int col) {
		pushedColumn = col;
	}

	public void setSelectedColumn(int col) {
		if (col < 0) {
			return;
		}
		Integer value = null;
		Object obj = state.get(new Integer(col));
		if (obj == null) {
			value = new Integer(DOWN);
		} else {
			if (((Integer) obj).intValue() == DOWN) {
				value = new Integer(UP);
			} else if (((Integer) obj).intValue() == UP) {
				value = new Integer(NONE);
			} else {
				value = new Integer(DOWN);
			}
		}
		state.clear();
		if (value > 0) {
			state.put(new Integer(col), value);
		}

	}

	public int getState(int col) {
		int retValue;
		Object obj = state.get(new Integer(col));
		if (obj == null) {
			retValue = NONE;
		} else {
			if (((Integer) obj).intValue() == DOWN) {
				retValue = DOWN;
			} else if (((Integer) obj).intValue() == UP) {
				retValue = UP;
			} else {
				retValue = NONE;
			}
		}
		return retValue;
	}

	public void setHeight(int headerheight) {
		headerHeight = headerheight;
	}

	public void mouseClicked(MouseEvent e) {
		if (header != null && renderer != null) {
			int col = header.columnAtPoint(e.getPoint());
			renderer.setPressedColumn(col);
			renderer.setSelectedColumn(col);
			header.repaint();

			if (header.getTable().isEditing()) {
				header.getTable().getCellEditor().stopCellEditing();
			}
			if (!(DOWN == renderer.getState(col))
					&& !(UP == renderer.getState(col))) {
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		if (header != null && renderer != null) {
			header.columnAtPoint(e.getPoint());
			renderer.setPressedColumn(-1);
			header.repaint();
		}
	}
}
