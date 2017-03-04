/*
 * Copyright (c) 2017, Ing. Michael J. Stallinger and/or his affiliates. All rights reserved.
 * This source code is subject to license terms, see the LICENSE file for details.
 */
package at.mjst.finbase.desktop.view;

import com.sun.javafx.scene.control.skin.TableViewSkin;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.input.ScrollEvent;

/**
 * This is a custom implementation of {@link TableView}, that handles the scroll-events so, that the current
 * selection follows the visible area.
 * Special thanks to <a href="http://stackoverflow.com/a/20434361/700165">this stackOverflow article</a>!
 *
 * @author Ing. Michael J. Stallinger (projects@mjst.at)
 * @since 2017-02-22
 */
public class CustomTableView<S> extends TableView<S>
{
    private VirtualFlow<?> flow = null;

    /**
     * Constructor
     */
    public CustomTableView()
    {
        super();
        // You need this to prevent NPE's when trying to use the skin
        skinProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                TableViewSkin<?> skin = (TableViewSkin<?>) newValue;
                for (Node n : skin.getChildren()) {
                    if (n instanceof VirtualFlow) {
                        flow = (VirtualFlow<?>) n;
                    }
                }
            }
        });
        // This handles the scrolling to ensure that a selection is visible
        getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || flow == null || newValue.intValue() < 0 || (flow.getFirstVisibleCell() == null)) {
                return;
            }
            int selected = newValue.intValue();
            if (selected <= flow.getFirstVisibleCell().getIndex() || selected >= flow.getLastVisibleCell().getIndex()) {
                flow.show(selected);
            }
        });
        // Overrides the ScrollEvent handler to force selection rather than scrolling
        addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() < 0) {
                CustomTableView.this.getSelectionModel().selectNext();
            } else {
                CustomTableView.this.getSelectionModel().selectPrevious();
            }
            event.consume();
        });
    }
}
