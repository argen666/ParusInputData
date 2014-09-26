/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parusinputdata;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.sql.*;


/**
 *
 * @author Developer
 */
public class ParusInputData {

    /**
     * @param args the command line arguments
     */
    private static void WriteToDB(/*String action, String timestamp, String filename*/) throws SQLException {
        CallableStatement cstmt = null;
        try{
            String serverIP = "192.168.16.13";
            String portNumber = "1521";
            String instanceName = "pudp";
            String username = "argen666";
            String password = "argen666";
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@" + serverIP + ":" + portNumber + ":" + instanceName;
            Connection connection = DriverManager.getConnection(url, username, password);
            //Connection connection = DriverManager.getConnection ("jdbc:oracle:oci8:@", "argen666", "argen666");

            /*Statement stmt = connection.createStatement (); 
            ResultSet r = stmt.executeQuery ("select * from days"); 
            
            while (r.next()) {
        for (int i = 1; i <= r.getMetaData().getColumnCount(); i++) {
            if (i > 1) System.out.print(",  ");
            String columnValue = r.getString(i);
            System.out.print(columnValue + " ");
            System.getProperty("JDBC_URL"); 
        }}*/
            
            String day="";
            cstmt = connection.prepareCall("{call argen666.TESTPRM(?,?)}");
             cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            cstmt.setString(1, "Monday");        
            cstmt.executeUpdate();
            day=cstmt.getString(2);
            System.out.println(day);
            
            cstmt = connection.prepareCall("{call ? :=parus.get_license()}");
            cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            cstmt.executeUpdate();
            day=cstmt.getString(1);
            System.out.println(day);
            
            //parus.P_INORDERS_SET_STATUS(59945, 109795, 0, TO_DATE('25.09.2014', 'DD.MM.YYYY HH24:MI:SS'), NWARNING, SMSG);
            //java.sql.SQLException: ORA-20103: Работа в Системе невозможна, т.к. приложение "Other" не установлено.
            /*cstmt = connection.prepareCall("{call parus.P_INORDERS_SET_STATUS(59945, 109795, 0, TO_DATE('25.09.2014', 'DD.MM.YYYY HH24:MI:SS'), ?, ?)}");
            cstmt.registerOutParameter(1, java.sql.Types.NUMERIC);
            cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            cstmt.executeUpdate();
            day=cstmt.getString(1);
            System.out.println(day);*/
        }
        catch (SQLException e){
           System.out.println(e);
        }
        finally{
            if (cstmt != null){
                cstmt.close();
            }
        }
    }
    
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here 
          WriteToDB();
          //System.out.println("54654");                           
    }
    
}
