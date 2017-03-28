/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.view;

import at.mjst.finbase.desktop.model.entity.Entity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 * ToDo: Short class description
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-03-17
 */
public class ConfigurableButtonCell<S extends Entity, T> extends TableCell<S, T>
{
    private Button cellButton;

    public ConfigurableButtonCell()
    {
        cellButton = new Button();
        cellButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                // do something when button clicked
                //                    S record = getItem();
                // do something with record....
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(T record, boolean empty)
    {
        super.updateItem(record, empty);
        if (!empty) {
            cellButton.setText("Something with ");
            setGraphic(cellButton);
        } else {
            setGraphic(null);
        }
    }
}
