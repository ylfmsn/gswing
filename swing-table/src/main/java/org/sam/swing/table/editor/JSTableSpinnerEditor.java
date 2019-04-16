package org.sam.swing.table.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

/**
 * 微调控件editor
 * @author sam
 *
 */
public class JSTableSpinnerEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845493748266516628L;

	/**
	 * 当前系统的控件
	 */
	protected JComponent editorComponent;
	
    /**
     * The delegate class which handles all methods sent from the
     * <code>CellEditor</code>.
     */
    protected EditorDelegate delegate;
    
    /**
     * 激活次数
     */
    protected int clickCountToStart = 1;


    /**
     * Constructs a <code>DefaultCellEditor</code> that uses a text field.
     *
     * @param textField  a <code>JTextField</code> object
     */
    public JSTableSpinnerEditor(final JSpinner textField) {
        editorComponent = textField;
        this.clickCountToStart = 2;
        delegate = new EditorDelegate() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -577821741970648323L;

			@Override
			public void setValue(Object value) {
                textField.setValue(value);
            }

			@Override
            public Object getCellEditorValue() {
                return textField.getValue();
            }
        };
  
    }
    
    /**
     * 系统默认的微调控件
     */
    public JSTableSpinnerEditor() {
        this(new JSpinner());
    }

    /**
     * Returns a reference to the editor component.
     *
     * @return the editor <code>Component</code>
     */
    public Component getComponent() {
        return editorComponent;
    }

    /**
     * Specifies the number of clicks needed to start editing.
     *
     * @param count  an int specifying the number of clicks needed to start editing
     * @see #getClickCountToStart
     */
    public void setClickCountToStart(int count) {
        clickCountToStart = count;
    }

    /**
     * Returns the number of clicks needed to start editing.
     * @return the number of clicks needed to start editing
     */
    public int getClickCountToStart() {
        return clickCountToStart;
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#getCellEditorValue
     */
    @Override
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#isCellEditable(EventObject)
     */
    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return delegate.isCellEditable(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#shouldSelectCell(EventObject)
     */
    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return delegate.shouldSelectCell(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#stopCellEditing
     */
    @Override
    public boolean stopCellEditing() {
        return delegate.stopCellEditing();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#cancelCellEditing
     */
    @Override
    public void cancelCellEditing() {
        delegate.cancelCellEditing();
    }

    /** Implements the <code>TreeCellEditor</code> interface. */
    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value,
                                                boolean isSelected,
                                                boolean expanded,
                                                boolean leaf, int row) {
        String         stringValue = tree.convertValueToText(value, isSelected,
                                            expanded, leaf, row, false);

        delegate.setValue(stringValue);
        return editorComponent;
    }

    /** Implements the <code>TableCellEditor</code> interface. */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected,
                                                 int row, int column) {
        delegate.setValue(value);
        return editorComponent;
    }

    /**
     * The protected <code>EditorDelegate</code> class.
     */
    protected class EditorDelegate extends JSEditorDelegateAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3567056544985065197L;

		@Override
		public boolean stopCellEditing() {
			fireEditingStopped();
            return true;
		}

		@Override
		public void cancelCellEditing() {
			fireEditingCanceled();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JSTableSpinnerEditor.this.stopCellEditing();
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			JSTableSpinnerEditor.this.stopCellEditing();
		}

       
    }
}
