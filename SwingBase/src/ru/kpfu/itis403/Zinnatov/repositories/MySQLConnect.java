package ru.kpfu.itis403.Zinnatov.repositories;
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
/**
 * @desc A singleton database access class for MySQL
 * @author Ramindu
 */
public final class MySQLConnect {
    public Connection conn;
    private static Statement statement;
    public static MySQLConnect db;
    private MySQLConnect() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "Products";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "Ainur";
        String password = "azatay12345";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized MySQLConnect getDbCon() {
        if ( db == null ) {
            db = new MySQLConnect();
        }
        return db;

    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public static ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public static int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;

    }



}