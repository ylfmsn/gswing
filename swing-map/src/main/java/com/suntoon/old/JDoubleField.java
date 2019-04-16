/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 * 
 *    (C) 2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package com.suntoon.old;

import org.geotools.data.Parameter;

import javax.swing.*;

/**
 * Widget for double values
 * 
 * @author gdavis
 *
 *
 *
 *
 * @source $URL$
 */
public class JDoubleField extends ParamField {

    private JTextField text;

    public JDoubleField(Parameter<?> parameter) {
        super(parameter);
    }

    public JComponent doLayout() {
        text = new JTextField(16);
        return text;
    }

    public Object getValue() {
        String val = text.getText();
        if (val == null || val.equals("")) {
            return new Double(0);
        }
        try {
            return new Double(val);
        } catch (NumberFormatException e) {
            return new Double(0);
        }
    }

    public void addListener(JWizard.Controller controller) {
        text.addKeyListener(controller);
    }

    public void removeListener(JWizard.Controller controller) {
        text.addKeyListener(controller);
    }

    public void setValue(Object value) {
        text.setText(((Double) value).toString());
    }

    public boolean validate() {
        String val = text.getText();
        try {
            Double d = Double.parseDouble(val);
            return d != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
