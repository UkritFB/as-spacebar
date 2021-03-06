package com.tibco.as.spacebar.ui.editor.snapshot;

import com.tibco.as.space.Tuple;
import com.tibco.as.spacebar.ui.editor.StringPropertyAccessor;

public class EditablePropertyAccessor extends StringPropertyAccessor<Tuple> {

	private SnapshotBrowser editor;

	public EditablePropertyAccessor(String[] fieldNames, SnapshotBrowser editor) {
		super(fieldNames);
		this.editor = editor;
	}

	@Override
	public void setDataValue(Tuple rowObject, int columnIndex, Object newValue) {
		String fieldName = getColumnProperty(columnIndex);
		Object oldValue = rowObject.get(fieldName);
		if (!isSame(newValue, oldValue)) {
			rowObject.put(fieldName, newValue);
			editor.addChanged(rowObject);
		}
	}

	private boolean isSame(Object newValue, Object oldValue) {
		if (newValue == null) {
			return oldValue == null;
		}
		return newValue.equals(oldValue);
	}

}
