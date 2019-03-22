package music;

import music.MusicServiceStub.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;

import javax.swing.*;

public class Client extends JFrame implements ActionListener {
	private final static int contentInset = 5;
	private final static int trackColumns = 130;
	private final static int trackRows = 20;
	private final static int gridLeft = GridBagConstraints.WEST;
	private final static String programTitle = "Music Album";

	private GridBagConstraints contentConstraints = new GridBagConstraints();
	private GridBagLayout contentLayout = new GridBagLayout();
	private Container contentPane = getContentPane();
	private JButton discButton = new JButton("Check");
	private JLabel discLabel = new JLabel("Disc Number:");
	private JTextField discText = new JTextField(5);
	private JButton nameButton = new JButton("Check");
	private JLabel nameLabel = new JLabel("Composer/Artiste Name:");
	private JTextField nameText = new JTextField(16);
	private Font trackFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
	private Font errorFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
	private JLabel trackLabel = new JLabel("Tracks:");
	private JTextArea trackArea = new JTextArea(trackRows, trackColumns);
	private JScrollPane trackScroller = new JScrollPane(trackArea);
	private Color red = new Color(0.8f, 0.06f, 0.06f);
	private Color textA = new Color(0.66f, 0.09f, 0.2f);
	private Color peach = new Color(0.99f, 0.66f, 0.76f);
	private Color error2 = new Color(0.15f, 0.7f, 0.7f);
	private TrackDetail tracks;

	private MusicServiceStub stub;

//==================================================================================
// Client
// - constructor for client class
// - Parameters:
//	 	- None
// - Return Value:
//	 	-None
//- throws Exception
//==================================================================================
	public Client() throws Exception  {
		contentPane.setLayout(contentLayout);
		contentPane.setBackground(peach);

		addComponent(0, 0, gridLeft, nameLabel);//add components of interface
		addComponent(1, 0, gridLeft, nameText);
		addComponent(2, 0, gridLeft, nameButton);
		addComponent(0, 1, gridLeft, discLabel);
		addComponent(1, 1, gridLeft, discText);
		addComponent(2, 1, gridLeft, discButton);
		addComponent(0, 2, gridLeft, trackLabel);
		addComponent(0, 3, gridLeft, trackScroller);
	
		nameLabel.setForeground(textA);			//format colours, fonts, and action listeners
		nameText.setBackground(Color.WHITE);
		nameButton.addActionListener(this);
		nameButton.setBackground(textA);
		nameButton.setForeground(Color.WHITE);
		discLabel.setForeground(textA);
		discText.setBackground(Color.WHITE);
		discButton.addActionListener(this);
		discButton.setBackground(textA);
		discButton.setForeground(Color.WHITE);
		trackLabel.setForeground(textA);
		trackArea.setFont(trackFont);
		trackArea.setBackground(Color.WHITE);
		trackArea.setEditable(false);
		trackArea.setForeground(textA);
		stub = new MusicServiceStub();			//instantiate client stub 
		
}
//==================================================================================
// main
// - main function for client class
// - Parameters:
//		 - String[] args
// - Return Value:
//		 -None
//- throws Exception
//==================================================================================
	public static void main(String[] args) throws Exception {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		Client window = new Client();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle(programTitle);
		window.pack();
		int windowWidth = window.getWidth();
		int windowHeight = window.getHeight();
		int windowX = (screenWidth - windowWidth) / 2;
		int windowY = (screenHeight - windowHeight) / 2;
		window.setLocation(windowX, windowY);
		window.setVisible(true);
	}
	
//==================================================================================
// addComponent
// - adds a JComponent to the contentPane
// - Parameters:
//		 - int x: x coordinate
//		 - int y: y coordinate
//		 - int position: where to anchor
//		 - JComponent component: component to add
// - Return Value:
//		 -None
//==================================================================================
	private void addComponent(int x, int y, int position, JComponent component) {
		Insets contentInsets = new Insets(contentInset, contentInset, contentInset, contentInset);
		contentConstraints.gridx = x;
		contentConstraints.gridy = y;
		contentConstraints.anchor = position;
		contentConstraints.insets = contentInsets;
		if (component == trackArea || component == trackLabel)
			contentConstraints.gridwidth = GridBagConstraints.REMAINDER;
		contentLayout.setConstraints(component, contentConstraints);
		contentPane.add(component);
	}
//==================================================================================
// actionPerformed
// - main function getting user defined text and searching accordingly
// - Parameters:
//		 - ActionEvent event: user event
// - Return Value:
//		 -None
//==================================================================================
	public void actionPerformed(ActionEvent event) {
		String trackRows = "";
		TrackDetail[] tracks;
		try {
			if (event.getSource() == nameButton)
				tracks = getField("composer", nameText.getText());	//call getField with text from composer box
			else if (event.getSource() == discButton)
				tracks = getField("disc", discText.getText());		//call getField with text from disc box
			else
				return;
			trackArea.setForeground(textA);							//reset colours and font
			trackArea.setBackground(Color.WHITE);
			trackArea.setFont(trackFont);
			trackRows += String.format("%-8s %-8s %-8s %-30s %-30s %-30s %n", "Result", "Disc", "Track", "Composer/Artist", "Work", "Title");			
			for (int i = 0; i < tracks.length; i++)					//for each track in trackDetails
			{
				TrackDetail trackDetail = tracks[i];
				trackRows +=  String.format("%-8s %-8s %-8s %-30s %-30s %-30s %n",
						Integer.toString(i+1),
						Integer.toString(trackDetail.getDiscNumber()),	//extract elements from trackDetail complexType
						Integer.toString(trackDetail.getTrackNumber()),		//and append to string
						trackDetail.getComposerName(),
						trackDetail.getWorkName(),
						trackDetail.getTitleName());
			}
		} 
		catch (FaultMessage fm) 		//catch possible exception from getField
		{
			String error = fm.getMessage();	
			if (error == null) error = fm.toString();
			error = "could not get tracks - " + error; 
			trackArea.setForeground(red);
			trackArea.setBackground(error2);
			trackArea.setFont(errorFont);
			trackRows += error;			//append error to string to be displayed, in green
		}
		trackArea.setText(trackRows);
	}

//==================================================================================
// getField
// - calls client according to user event and text, composer or disc
// - Parameters:
//	 	- String field: field signifying whether to search by composer or disc
//		- String value: which composer or disc number to search for
// - Return Value:
// 	- TrackDetail[]: an array of TrackDetail elements, each pertaining to the
//					specified field and value
//- throws FaultMessage
//==================================================================================
	private TrackDetail[] getField(String field, String value) throws FaultMessage {
		try
		{
			if (field == "composer")
			{
				ComposerRequest req = new ComposerRequest();
				req.setComposerRequest(value);		//set string of composerRequest element		
				MusicServiceStub.Result result = stub.getByComposer(req);	//call service with user text
				TrackDetails details = result.getResult();						//from composer box
				return details.getDetail();
			}
			else //field == disc
			{
				DiscRequest discReq = new DiscRequest();
				discReq.setDiscRequest(Integer.parseInt(value));		//call service with user text
				Result res = stub.getByDisc(discReq);						//from disc box
				TrackDetails details = res.getResult();
				return details.getDetail();
			}
		}
		catch (FaultMessage m) 			//catch error from service
		{
			throw (new FaultMessage(m.getMessage(), m));		//throw fault with same message
		}
		catch (NumberFormatException err) //catch error from entering text to disc box
		{
			throw (new FaultMessage("must enter a valid digit.",err));
		}
		catch (RemoteException e) //catch error from MusicServiceStub
		{ 
			throw (new FaultMessage(e.getMessage(), e));
		}
		

	}

}
