import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.PrinterName;
import javax.swing.*;

public class BatchPrinter extends JFrame implements ActionListener {

	// Declare all of the relevant variables, objects and constants to be used.
	boolean encryptMode = true, affectDigits = false;

	final int LIST_WIDTH = 200, LIST_HEIGHT = 150;
	final String ADD_FILE_COMMAND = "Add File", REMOVE_FILE_COMMAND = "Remove File", PRINT_COMMAND = "Print",
			SELECT_PRINTER_COMMAND = "Select Printer", PROGRAM_TITLE = "Batch Printer", FILES_COUNT_LABEL = "Files to be printed: ";

	ArrayList<File> filesList = new ArrayList<File>();

	int filesCount = 0;

	DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel);

	JLabel filesCountLabel = new JLabel(FILES_COUNT_LABEL);
	
	String destination = new String();

	public static void main(String[] args) {

		new BatchPrinter();
		
	}

	public BatchPrinter() {

		Container contentPane = this.getContentPane();

		// List
		// Initialize and create scroll pane for the list.
		// -------------------------------------------------------
		list.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		list.getActionMap().put("up", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent event) {
				list.setSelectedIndex(list.getSelectedIndex() - 1);
			}
		});
		list.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
		list.getActionMap().put("down", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent event) {
				list.setSelectedIndex(list.getSelectedIndex() + 1);
			}
		});
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);

		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setPreferredSize(new Dimension(LIST_WIDTH, LIST_HEIGHT));
		// -------------------------------------------------------

		// Labels
		// Set the label sizes, the alignments of text within and the fonts.
		// -------------------------------------------------------
		Font titleLabelFont = new Font("Times New Roman", Font.PLAIN, 24);
		Font regularLabelFont = new Font("Times New Roman", Font.PLAIN, 16);
		Font smallLabelFont = new Font("Times New Roman", Font.PLAIN, 14);

		// Program Title Label
		JLabel programLabel = new JLabel(PROGRAM_TITLE);
		programLabel.setHorizontalAlignment(SwingConstants.CENTER);
		programLabel.setVerticalAlignment(SwingConstants.CENTER);
		programLabel.setFont(titleLabelFont);
		
		// Destination Label
		JLabel destionationLabel = new JLabel("Destination");
		destionationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		destionationLabel.setVerticalAlignment(SwingConstants.CENTER);
		destionationLabel.setFont(regularLabelFont);

		// Files Count Label (global variable)
		filesCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		filesCountLabel.setVerticalAlignment(SwingConstants.CENTER);
		filesCountLabel.setFont(smallLabelFont);
		filesCountLabel.setText(FILES_COUNT_LABEL + filesCount);
		// -------------------------------------------------------

		// Buttons
		// Set up the buttons' hotkeys, specific action commands, tooltips,
		// listeners and background colors.
		// -------------------------------------------------------
		
		// Add File Button
		JButton addFileButton = new JButton(ADD_FILE_COMMAND);
		addFileButton.setMnemonic(KeyEvent.VK_A);
		addFileButton.setActionCommand(ADD_FILE_COMMAND);
		addFileButton.setToolTipText("Add files to be printed.");
		addFileButton.addActionListener(this);
		addFileButton.setBackground(Color.LIGHT_GRAY);

		// Remove File Button
		JButton removeFileButton = new JButton(REMOVE_FILE_COMMAND);
		removeFileButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DELETE"),
				"remove file");
		removeFileButton.getActionMap().put("remove file", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent event) {
				removeFileButton();
			}
		});
		removeFileButton.setMnemonic(KeyEvent.VK_R);
		removeFileButton.setActionCommand(REMOVE_FILE_COMMAND);
		removeFileButton.setToolTipText("Removes selected files on the list.");
		removeFileButton.addActionListener(this);
		removeFileButton.setBackground(Color.LIGHT_GRAY);

		// Print Button
		JButton printButton = new JButton(PRINT_COMMAND);
		printButton.setMnemonic(KeyEvent.VK_P);
		printButton.setActionCommand(PRINT_COMMAND);
		printButton.setToolTipText("Print files on the list.");
		printButton.addActionListener(this);
		printButton.setBackground(Color.LIGHT_GRAY);
		// -------------------------------------------------------
		
		// Combo Box
		// Set up the printer selector combo box and populate it with available printers
		// -------------------------------------------------------
        PrintService[] printerServices = PrinterJob.lookupPrintServices();
        
        String[] printers = new String[printerServices.length];

        for (int i = 0; i < printerServices.length; i++) {
            printers[i] = printerServices[i].getName();
        }
        
		JComboBox<String> printerList = new JComboBox<String>(printers);
		printerList.setActionCommand(SELECT_PRINTER_COMMAND);
		printerList.addActionListener(this);
		printerList.setSelectedIndex(0);

		// Layout
		// -------------------------------------------------------
		GroupLayout layout = new GroupLayout(contentPane);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(programLabel)
						.addGroup(layout.createSequentialGroup()
								.addComponent(addFileButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addComponent(removeFileButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(destionationLabel)
								.addComponent(printerList, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addComponent(printButton, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(listScrollPane)
						.addComponent(filesCountLabel)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(programLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(addFileButton)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(destionationLabel)
									.addComponent(printerList)))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(destionationLabel)
								.addComponent(printerList))
						.addComponent(removeFileButton)
						.addComponent(listScrollPane))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(printButton)
						.addComponent(filesCountLabel)));

		// Set up the content pane.
		this.setLayout(layout);
		this.setResizable(false);
		this.setSize(600, 600);
		this.setTitle(PROGRAM_TITLE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void updateFilesCount() {
		filesCount = filesList.size();
		filesCountLabel.setText(FILES_COUNT_LABEL + filesCount);
	}

	private void addFileButton() {

		// Display file chooser
		final JFileChooser fileChooser = new JFileChooser();
		DocumentsFilter documentsFilter = new DocumentsFilter();
		fileChooser.setFileFilter(documentsFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);

		int returnValue = fileChooser.showDialog(getContentPane(), "Add");

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			File[] files = fileChooser.getSelectedFiles();

			for (int i = 0; i < files.length; i++) {

				if (!filesList.contains(files[i])) {
					filesList.add(files[i]);
					listModel.addElement(files[i].getName());
					// JOptionPane.showMessageDialog(getContentPane(), "File
					// " + files[i].getName() + " added.");
				} else {
					// JOptionPane.showMessageDialog(getContentPane(), "File
					// " + files[i].getName() + " is already on the list.");
				}
			}
			updateFilesCount();
		}
	}

	private void removeFileButton() {

		int indices[] = list.getSelectedIndices();

		if (indices.length == 0) {
			JOptionPane.showMessageDialog(getContentPane(), "No file selected", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			for (int i = indices.length - 1; i >= 0; i--) {
				String fileName = listModel.getElementAt(indices[i]);
				for (int j = 0; j < filesList.size(); j++) {
					if (filesList.get(j).getName().equals(fileName)) {
						filesList.remove(j);
					}
				}
				listModel.remove(indices[i]);
				if (indices[i] < listModel.size() - 1) {
					list.setSelectedIndex(indices[i]);
				} else {
					list.setSelectedIndex(listModel.size() - 1);
				}
			}

			updateFilesCount();
		}

	}

	private void printButton() {
		if (filesCount == 0) {
			JOptionPane.showMessageDialog(getContentPane(), "No files to be printed", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {

			String printJobsString = (filesCount == 1 ? " print job " : " print jobs ");

			//String printerName = printService.getAttribute(PrinterName.class).getValue();

			int userChoice = JOptionPane.showConfirmDialog(getContentPane(),
					"Send " + filesCount + printJobsString + "to " + destination + "?", "Confirm Print",
					JOptionPane.YES_NO_OPTION);

			if (userChoice == JOptionPane.YES_OPTION) {

				for (int i = 0; i < filesList.size(); i++) {
					try {
						FileInputStream fileInputStream = new FileInputStream(filesList.get(i));
						Doc document = new SimpleDoc(fileInputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
						JobName jobName = new JobName(filesList.get(i).getName(), Locale.CANADA);
						PrintRequestAttributeSet requestAttributeSet = new HashPrintRequestAttributeSet();
						requestAttributeSet.add(jobName);
						
						PrintServiceAttributeSet serviceAttributeSet = new HashPrintServiceAttributeSet();
						PrinterName printerName = new PrinterName(destination, Locale.CANADA);
						serviceAttributeSet.add(printerName);
						
						PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, serviceAttributeSet);
						
						printService[0].createPrintJob().print(document, requestAttributeSet);
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (PrintException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				}

				JOptionPane.showMessageDialog(getContentPane(),
						filesCount + printJobsString + "sent to " + destination);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		// Check which action was performed and act accordingly
		if (event.getActionCommand() == ADD_FILE_COMMAND) {

			addFileButton();

		} else if (event.getActionCommand() == REMOVE_FILE_COMMAND) {

			removeFileButton();

		} else if (event.getActionCommand() == PRINT_COMMAND) {

			printButton();

		} else if (event.getActionCommand() == SELECT_PRINTER_COMMAND) {

			JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
			destination = (String) comboBox.getSelectedItem();

		}
	}

}