/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.dto.columnselection;

import javafx.scene.control.TableColumn;

/**
 * A set of fields that define properties of {@link TableColumn}.
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-04-06
 */
public class ColumnProperties
{
    private double width;
    private String caption;

    /**
     * @return the desired column width
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * @param width the desired column width
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    public String getCaption()
    {
        return caption;
    }

    public void setCaption(String caption)
    {
        this.caption = caption;
    }
}
