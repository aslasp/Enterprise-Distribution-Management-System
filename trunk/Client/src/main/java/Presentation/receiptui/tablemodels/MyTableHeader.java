package Presentation.receiptui.tablemodels;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MyTableHeader extends JTableHeader implements CellEditorListener {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;
	protected Vector<ColumnGroup> columnGroups = null;
	transient protected int editingColumn = 0;
	transient protected TableCellEditor cellEditor = null;
	transient protected Component editorComp = null;

	public MyTableHeader() {
		this(null);
	}

	public MyTableHeader(TableColumnModel model) {
		super(model);
		setReorderingAllowed(false);
		cellEditor = null;
		recreateTableColumn(columnModel);
	}

	public void setReorderingAllowed(boolean b) {
		reorderingAllowed = b;
	}

	public void addColumnGroup(ColumnGroup g) {
		if (columnGroups == null) {
			columnGroups = new Vector<ColumnGroup>();
		}
		columnGroups.addElement(g);
	}

	public Enumeration<?> getColumnGroups(TableColumn col) {
		if (columnGroups == null)
			return null;
		Enumeration<ColumnGroup> enumTemp = columnGroups.elements();
		while (enumTemp.hasMoreElements()) {
			ColumnGroup cGroup = (ColumnGroup) enumTemp.nextElement();
			Vector<?> v_ret = (Vector<?>) cGroup.getColumnGroups(col,
					new Vector<ColumnGroup>());
			if (v_ret != null) {
				return v_ret.elements();
			}
		}
		return null;
	}

	public void setColumnMargin() {
		if (columnGroups == null) {
			return;
		}
		int columnMargin = getColumnModel().getColumnMargin();
		Enumeration<ColumnGroup> enumTemp = columnGroups.elements();
		while (enumTemp.hasMoreElements()) {
			ColumnGroup cGroup = (ColumnGroup) enumTemp.nextElement();
			cGroup.setColumnMargin(columnMargin);
		}
	}

	public void updateUI() {
		resizeAndRepaint();
		invalidate();
	}

	protected void recreateTableColumn(TableColumnModel columnModel) {
		int n = columnModel.getColumnCount();
		MyTableColumn[] newCols = new MyTableColumn[n];
		TableColumn[] oldCols = new TableColumn[n];
		for (int i = 0; i < n; i++) {
			oldCols[i] = columnModel.getColumn(i);
			newCols[i] = new MyTableColumn();
			newCols[i].copyValues(oldCols[i]);
		}
		for (int i = 0; i < n; i++) {
			columnModel.removeColumn(oldCols[i]);
		}
		for (int i = 0; i < n; i++) {
			columnModel.addColumn(newCols[i]);
		}
	}

	public boolean editCellAt(int index) {
		return false;
//		return editCellAt(index);
	}

	public boolean editCellAt(int index, EventObject e) {
		if (cellEditor != null && !cellEditor.stopCellEditing()) {
			return false;
		}
		if (!isCellEditable(index)) {
			return false;
		}
		TableCellEditor editor = getCellEditor(index);

		if (editor != null && editor.isCellEditable(e)) {
			editorComp = prepareEditor(editor, index);
			editorComp.setBounds(getHeaderRect(index));
			add(editorComp);
			editorComp.validate();
			setCellEditor(editor);
			setEditingColumn(index);
			editor.addCellEditorListener(this);
            return false;
	//		return true;
		}
		return false;
	}

	public boolean isCellEditable(int index) {
		if (getReorderingAllowed()) {
			return false;
		}
	//	int columnIndex = columnModel.getColumn(index).getModelIndex();
	//	MyTableColumn col = (MyTableColumn) columnModel.getColumn(columnIndex);
	//	return col.isHeaderEditable();
		return false;
	}

	public TableCellEditor getCellEditor(int index) {
		int columnIndex = columnModel.getColumn(index).getModelIndex();
		MyTableColumn col = (MyTableColumn) columnModel.getColumn(columnIndex);
		return col.getHeaderEditor();
	}

	public void setCellEditor(TableCellEditor newEditor) {
		TableCellEditor oldEditor = cellEditor;
		cellEditor = newEditor;

		if (oldEditor != null && oldEditor instanceof TableCellEditor) {
			((TableCellEditor) oldEditor)
					.removeCellEditorListener((CellEditorListener) this);
		}
		if (newEditor != null && newEditor instanceof TableCellEditor) {
			((TableCellEditor) newEditor)
					.addCellEditorListener((CellEditorListener) this);
		}
	}

	@SuppressWarnings("deprecation")
	public Component prepareEditor(TableCellEditor editor, int index) {
		Object value = columnModel.getColumn(index).getHeaderValue();
		boolean isSelected = true;
		int row = -10;
		JTable table = getTable();
		Component comp = editor.getTableCellEditorComponent(table, value,
				isSelected, row, index);
		if (comp instanceof JComponent) {
			((JComponent) comp).setNextFocusableComponent(this);
		}
		return comp;
	}

	public TableCellEditor getCellEditor() {
		return cellEditor;
	}

	public Component getEditorComponent() {
		return editorComp;
	}

	public void setEditingColumn(int aColumn) {
		editingColumn = aColumn;
	}

	public int getEditingColumn() {
		return editingColumn;
	}

	public void setHeaderEditable(boolean able) {
		for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
			((MyTableColumn) this.getColumnModel().getColumn(i))
					.setHeaderEditable(able);
		}
	}

	public void removeEditor() {
		TableCellEditor editor = getCellEditor();
		if (editor != null) {
			editor.removeCellEditorListener(this);

			requestFocus();
			remove(editorComp);

			int index = getEditingColumn();
			Rectangle cellRect = getHeaderRect(index);

			setCellEditor(null);
			setEditingColumn(-1);
			editorComp = null;

			repaint(cellRect);
		}
	}

	public boolean isEditing() {
	//	return (cellEditor == null) ? false : true;
		return false;
	}

	public void editingStopped(ChangeEvent e) {
		TableCellEditor editor = getCellEditor();
		if (editor != null) {
			Object value = editor.getCellEditorValue();
			int index = getEditingColumn();
			columnModel.getColumn(index).setHeaderValue(value);
			removeEditor();
		}
	}

	public void editingCanceled(ChangeEvent e) {
		removeEditor();
	}
}