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
import javax.swing.SwingUtilities;

import org.sam.swing.resource.ResourceLoader;

import com.javadocking.DockingManager;
import com.javadocking.dock.SingleDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.DraggableContent;
import com.javadocking.drag.DragListener;
import com.javadocking.model.FloatDockModel;

public class IconDockable extends JPanel {

	// Static fields.

	public static final int FRAME_WIDTH = 500; 
	public static final int FRAME_HEIGHT = 400;

	// Constructor.

	public IconDockable(JFrame frame)
	{
		super(new BorderLayout());

		// Create the dock model for the docks.
		FloatDockModel dockModel = new FloatDockModel();
		dockModel.addOwner("frame0", frame);

		// Give the dock model to the docking manager.
		DockingManager.setDockModel(dockModel);

		// Create the content component.
		TextPanel textPanel = new TextPanel("I am window 1.");

		// Create the dockable around the content component.
		Icon icon = new ImageIcon(ResourceLoader.getResource(ResourceLoader.IMAGE_OPEN_LAYER));
		DefaultDockable dockable = new DefaultDockable("Window1", textPanel, "Window", icon);
		dockable.setDescription("Window with text");
		
		// Create the single dock.
		SingleDock singleDock = new SingleDock();

		// Add the dockables to these tab docks.
		singleDock.addDockable(dockable, SingleDock.SINGLE_POSITION);
		
		// Add the root dock to the dock model.
		dockModel.addRootDock("singledock", singleDock, frame);
		
		// Add the dock to the panel.
		add(singleDock, BorderLayout.CENTER);
		
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
		JFrame frame = new JFrame("Dockable with Icon and Tooltip");

		// Create the panel and add it to the frame.
		IconDockable panel = new IconDockable(frame);
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
