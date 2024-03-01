package landing;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class baseMethods {

    public String owner;
    public String taskName;

    public String taskDesc;

    public String taskType;

    public LocalDateTime deadline;
     Object idData;
    baseMethods(){

       // createRow(new login().tfusername.getText(), new LimitedTimeTask().localDateTime);

    }

    baseMethods(String owner){

        this.owner=owner;

        System.out.println(this.owner);
    }

    baseMethods(LocalDateTime deadline){

        this.deadline=deadline;

        System.out.println(this.deadline);
    }


    public Connection connect(){

        Connection con= null;

        try{

            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+"TasksDb","postgres", "Nini1234" );

            if(con!=null){

                System.out.println("con success");
            }else{

                System.out.println("con failed");

            }

        }catch(Exception exc){
            System.out.println(exc);

        }

        return con;
    }



    public void createRow(  String taskName,String taskDesc,String taskType, String owner, LocalDateTime deadline) {

        System.out.println(this.owner);
        try (Connection con = connect()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS TasksDataDb (" +
                    "id SERIAL PRIMARY KEY," +
                    "Owner VARCHAR(200)," +
                    "TaskName VARCHAR(200)," +
                    "TaskDescription VARCHAR(200)," +
                    "TaskType VARCHAR(200)," +
                    "Deadline TIMESTAMP," +
                    "UNIQUE (Owner, TaskName, Deadline)" +
                    ");";
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(createTableQuery);
                System.out.println("Table created successfully");
            }

            String insertRowQuery = "INSERT INTO TasksDataDb (Owner, TaskName, TaskDescription, TaskType, Deadline) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(insertRowQuery)) {
                pstmt.setString(1, owner);
                pstmt.setString(2, taskName);
                pstmt.setString(3, taskDesc);
                pstmt.setString(4, taskType);
                pstmt.setObject(5, deadline);
                pstmt.executeUpdate();
                System.out.println("Row inserted successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public String[][] findOne(String taskName) {
        Statement statement;
        ResultSet res = null;
        List<String[]> resultList = new ArrayList<>();

        try {
            String query = String.format("SELECT * FROM TasksDataDb WHERE TaskName='%s'", taskName);
            statement = connect().createStatement();
            res = statement.executeQuery(query);

            while (res.next()) {
                String[] row = new String[]{
                        res.getString("Id"),
                        res.getString("TaskDescription"),
                        res.getString("Deadline")
                };
                resultList.add(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        String[][] resultArray = new String[resultList.size()][];
        resultList.toArray(resultArray);
        return resultArray;
    }



    public void updateName( String oldDesc, String newDesc, String taskName){

        Statement statement;

        try {

            String query = String.format("UPDATE TasksDataDb SET  TaskDescription='%s' WHERE  TaskName='%s';" ,newDesc,taskName);
            statement = connect().createStatement();
            statement.executeUpdate(query);
            System.out.println("data updated");



        } catch (Exception e) {

            System.out.println(e);


        }

    }


    public Boolean remove(String taskName){

        Statement statement;
        ResultSet resultSet;
        boolean taskExists = false; // Flag to track if the task exists

        try {
            String checkQuery = String.format("select * from TasksDataDb where TaskName='%s'", taskName);
            statement = connect().createStatement();
            resultSet = statement.executeQuery(checkQuery);

            if (resultSet.next()) {
                taskExists = true;
            }

            if (taskExists) {
                String deleteQuery = String.format("delete from TasksDataDb where TaskName='%s'", taskName);
                statement.executeUpdate(deleteQuery);
                System.out.println("Task '" + taskName + "' removed");
            } else {
                System.out.println("Task with name '" + taskName + "' does not exist. Deletion aborted.");
            }

        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }


        return taskExists;


    }




        public static void main(String[] args) {


    }


}
