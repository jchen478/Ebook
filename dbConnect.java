import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {
	
	static final String databasePrefix ="ebook";
    static final String netID ="postgres"; // Please enter your netId
    static final String hostName ="localhost:5432";
    static final String databaseURL ="jdbc:postgresql://"+hostName+"/"+databasePrefix;
    static final String password="password"; // please enter your own password
    private java.sql.Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
	
	//Method for calling database connection
	public void Connection(){
	      
        try {
              Class.forName("org.postgresql.Driver");
              System.out.println("databaseURL"+ databaseURL);
              connection = DriverManager.getConnection(databaseURL, netID, password);
              System.out.println("Successfully connected to the database");
           }
          catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
          catch (SQLException e) {
              e.printStackTrace();
          }
      } // end of Connection
	
	//Method querying database
    public ResultSet simpleQuery(String sqlQuery) {
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery(sqlQuery);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return resultSet;
    } // end of simpleQuery method
    
    //Method for updating database
    public void exQuery(String sqlQuery) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate(sqlQuery);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    } // end of execution method
}