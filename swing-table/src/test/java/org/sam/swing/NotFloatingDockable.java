package org.sam.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import org.sam.swing.resource.ResourceLoader;

import com.javadocking.DockingManager;
import com.javadocking.dock.Position;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.dockable.DockingMode;
import com.javadocking.dockable.DraggableContent;
import com.javadocking.drag.DragListener;
import com.javadocking.model.FloatDockModel;

public class NotFloatingDockable extends JPanel {

	// Static fields.

		public static final int FRAME_WIDTH = 600;
		public static final int FRAME_HEIGHT = 450;

		// Constructor.

		public NotFloatingDockable(JFrame frame)
		{
			super(new BorderLayout());

			// Create the dock model for the docks.
			FloatDockModel dockModel = new FloatDockModel();
			dockModel.addOwner("frame0", frame);

			// Give the dock model to the docking manager.
			DockingManager.setDockModel(dockModel);

			// Create the content components.
			TextPanel textPanel1 = new TextPanel("I am window 1.");
			TextPanel textPanel2 = new TextPanel("I am window 2.");
			TextPanel textPanel3 = new TextPanel("I am window 3.");
			TextPanel textPanel4 = new TextPanel("I am window 4.");

			// We don't want the dockables to float.
			int dockingModes = DockingMode.ALL - DockingMode.FLOAT;
			
			// Create the dockables around the content components.
			Icon icon = new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_OPEN_LAYER));
			Dockable dockable1 = new DefaultDockable("Window1", textPanel1, "Window 1", icon, dockingModes);
			Dockable dockable2 = new DefaultDockable("Window2", textPanel2, "Window 2", icon, dockingModes);
			Dockable dockable3 = new DefaultDockable("Window3", textPanel3, "Window 3", icon, dockingModes);
			Dockable dockable4 = new DefaultDockable("Window4", textPanel4, "Window 4", icon, dockingModes);

			// Create the tab docks.
			TabDock leftTabDock = new TabDock();
			TabDock rightTabDock = new TabDock();

			// Add the dockables to these tab docks.
			leftTabDock.addDockable(dockable1, new Position(0));
			leftTabDock.addDockable(dockable2, new Position(1));
			rightTabDock.addDockable(dockable3, new Position(0));
			rightTabDock.addDockable(dockable4, new Position(1));
			
			// Add the 2 root docks to the dock model.
			dockModel.addRootDock("leftTabDock", leftTabDock, frame);
			dockModel.addRootDock("rightTabDock", rightTabDock, frame);
				
			// Create the split panes.
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			splitPane.setDividerLocation(300);

			// Add the root docks to the split pane.
			splitPane.setLeftComponent(leftTabDock);
			splitPane.setRightComponent(rightTabDock);
			
			// Add the split pane to the panel.
			add(splitPane, BorderLayout.CENTER);
			
		}
		
		/**
		 * This is the class for the content.
		 */
		private class TextPanel extends JPanel implements DraggableContent
		{
			
			private JLabel label; 
			
			public TextPanel(String text)
			{
				super(new FlowLayout());
				
				// The panel.
				setMinimumSize(new Dimension(80,80));
				setPreferredSize(new Dimension(150,150));
				setBackground(Color.white);
				setBorder(BorderFactory.createLineBorder(Color.lightGray));
				
				// The label.
				label = new JLabel(text);
				label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				add(label);
			}
			
			// Implementations of DraggableContent.

			public void addDragListener(DragListener dragListener)
			{
				addMouseListener(dragListener);
				addMouseMotionListener(dragListener);
				label.addMouseListener(dragListener);
				label.addMouseMotionListener(dragListener);
			}
		}

		// Main method.
		
		public static void createAndShowGUI()
		{
			
			// Create the frame.
			JFrame frame = new JFrame("Not Floating Dockables");

			// Create the panel and add it to the frame.
			NotFloatingDockable panel = new NotFloatingDockable(frame);
			frame.getContentPane().add(panel);
			
			// Set the frame properties and show it.
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - FRAME_WIDTH) / 2, (screenSize.height - FRAME_HEIGHT) / 2);
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setVisible(true);
			
		}

		public static void main(String args[]) 
		{
	        Runnable doCreateAndShowGUI = new Runnable() 
	        {
	            public void run() 
	            {
	                createAndShowGUI();
	            }
	        };
	        SwingUtilities.invokeLater(doCreateAndShowGUI);
	    }
		
}
