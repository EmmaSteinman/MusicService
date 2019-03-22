package music;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class MusicService extends MusicServiceSkeleton {

	private final static String databaseHost = "mysql0.cs.stir.ac.uk";
	private final static String databaseName = "CSCU9YW";
	private final static String databasePassword = "ess";
	private final static String databaseUser = "ess";
	private final static String discTable = "music";


//==================================================================================
// getByComposer
// - gets tracks from database with the given Composer
// - Parameters:
// 		- music.ComposerRequest composerRequest: a composerRequest element
// - Return Value:
// 		- music.Result: a result element with the tracks by the given composer
//==================================================================================
	  
	public music.Result getByComposer(music.ComposerRequest composerRequest) throws FaultMessage
	{
		Result res = new Result();
		TrackDetails td = new TrackDetails();
		String composer = composerRequest.getComposerRequest();	//get string from composerRequest element	
		TrackDetail[] tracks = getByField("composer", composer); 
		td.setDetail(tracks);						//set trackDetail sequence of trackDetails element
		res.setResult(td);							//set trackDetails element of result element

		return res;
	}

//==================================================================================
// getByDisc
// - gets tracks from database with the given Disc Number
// - Parameters:
//	 		- music.DiscRequest discReqest: a discRequest element
// - Return Value:
//	 		- music.Result: a result element with the tracks with given disc number
//==================================================================================
	public music.Result getByDisc(music.DiscRequest discRequest) throws FaultMessage
	{
		Result res = new Result();
		TrackDetails td = new TrackDetails();
		int disc = discRequest.getDiscRequest();			//get int from discRequest element
		String discS = Integer.toString(disc);
		TrackDetail[] tracks = getByField("disc", discS);	
		td.setDetail(tracks);							//set trackDetail sequence of trackDetails element
		res.setResult(td);					//set trackDetails element of result element

		return res;
	}
//==================================================================================
// getByField
// - gets tracks from database with the given "field" value
// - Parameters:
//	 	- String field: a string specifying by which field to search
// 		- String value: a string to search in the database
// - Return Value:
//	 	- TrackDetail[]: an array of TrackDetail elements, each pertaining to the
//						specified field and value
//==================================================================================
	private TrackDetail[] getByField(String field, String value) throws FaultMessage 
	{
		try 
		{
			if (value.length() == 0)
				throw (new Exception(field + " is empty"));
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String databaseDesignation = "jdbc:mysql://" + databaseHost + "/" + databaseName + "?user=" + databaseUser
					+ "&password=" + databasePassword;
			Connection connection = DriverManager.getConnection(databaseDesignation);
			Statement statement = connection.createStatement();
			String query = "SELECT disc, track, composer, work, title " + "FROM " + discTable + " " + "WHERE " + field
					+ " LIKE '%" + value + "%'";
			ResultSet result = statement.executeQuery(query);			//execute query to access database
			result.last();
			int resultCount = result.getRow();							//get number of results
			if (resultCount == 0)
				throw (new Exception(field + " '" + value + "' not found"));
			TrackDetail[] trackDetails = new TrackDetail[resultCount];		//create new TrackDetail array with 
			result.beforeFirst();												//appropriate size
			int resultIndex = 0;
			while (result.next()) 
			{
				TrackDetail detail = new TrackDetail();
				detail.setDiscNumber(Integer.parseInt(result.getString(1)));	//parse results from database
				detail.setTrackNumber(Integer.parseInt(result.getString(2)));	//and set to appropriate elements
				detail.setComposerName(result.getString(3));					//in trackDetail complexType
				detail.setWorkName(result.getString(4));
				detail.setTitleName(result.getString(5));
				trackDetails[resultIndex++] = detail;							//insert new trackDetail into array
			}
			connection.close();

			return (trackDetails);
		} 
		catch (Exception exception) //catch exception from accessing database
		{
			String errorMessage = "database access error - " + exception.getMessage();
			throw (new FaultMessage(errorMessage, exception));	//throw faultMessage with modified error from exception
		}
	}

}

