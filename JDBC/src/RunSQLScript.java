import java.sql.*;
import java.util.Scanner;

public class RunSQLScript{

    // Set JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ott_db";
    static final String USER = "root";
    static final String PASSWORD = "2795";

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try{
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            conn.setAutoCommit(false); // Disable auto-commit mode
            stmt = conn.createStatement();

            while (true){
                System.out.println("\nChoose an action:");
                System.out.println("1. Search programmes by attribute");
                System.out.println("2. Search cast members by name");
                System.out.println("3. Search episodes by attributes");
                System.out.println("4. Manage programmes");
                System.out.println("5. Add episodes to a programme");
                System.out.println("6. Exit");
                System.out.print("\nEnter your choice: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 1:  // Search programmes by attribute
                        searchProgrammesByAttribute(stmt);
                        break;
                    case 2:  // Search cast members by name
                        searchCastByName(stmt);
                        break;
                    case 3:  // Search episodes by attributes
                        searchEpisodesByAttributes(stmt);
                        break;
                    case 4:  // Manage programmes
                        manageProgrammes(stmt);
                        break;
                    case 5:  // Add episodes to a programme
                        addEpisodesToProgramme(stmt);
                        break;
                    case 6:  // Exit
                        System.out.println("\nExiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (Exception e){
            System.out.println("General Error.");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
            scanner.close();
        }
        System.out.println("\nEnd of Program");
    }

    // Search Programmes by Attribute (Case 1)
    private static void searchProgrammesByAttribute(Statement stmt) {
        try {
            // Query 1: Searching for programmes having genre 'Horror'
            String query1 = "SELECT * FROM Programme, Genres WHERE Genres.genre_id = Programme.genre_id AND Genres.genre_name = 'Horror';";
            System.out.println("\nRunning SQL: " + query1);
            executeSelectQuery(stmt, query1);

            // Query 2: Searching for programmes released after 2022-01-01
            String query2 = "SELECT * FROM Programme WHERE release_date > '2022-01-01';";
            System.out.println("\nRunning SQL: " + query2);
            executeSelectQuery(stmt, query2);

            // Query 3: Searching for programmes with a duration of less than 90 minutes
            String query3 = "SELECT * FROM Programme WHERE duration < 90;";
            System.out.println("\nRunning SQL: " + query3);
            executeSelectQuery(stmt, query3);

        } catch (SQLException e) {
            System.out.println("\nError executing search for programmes by attribute.");
        }
    }

    // Search Cast by Name (Case 2)
    private static void searchCastByName(Statement stmt) {
        try {
            // Query 1: Searching for cast members with the name 'Aarav Kapoor'
            String query1 = "SELECT * FROM Cast_Details WHERE cast_name = 'Aarav Kapoor';";
            System.out.println("\nRunning SQL: " + query1);
            executeSelectQuery(stmt, query1);

            // Query 2: Searching for cast members who have worked in multiple programmes
            String query2 = "SELECT Cast_Details.cast_name, COUNT(Programme_Cast.programme_id) AS programme_count "
                    + "FROM Cast_Details "
                    + "JOIN Programme_Cast ON Cast_Details.cast_id = Programme_Cast.cast_id "
                    + "JOIN Programme ON Programme_Cast.programme_id = Programme.programme_id "
                    + "GROUP BY Cast_Details.cast_id "
                    + "HAVING COUNT(Programme_Cast.programme_id) > 1;";
            System.out.println("\nRunning SQL: " + query2);
            executeSelectQuery(stmt, query2);

            // Query 3: Searching for cast members born after 1990-01-01
            String query3 = "SELECT * FROM Cast_Details WHERE date_of_birth > '1990-01-01';";
            System.out.println("\nRunning SQL: " + query3);
            executeSelectQuery(stmt, query3);

            // Query 4: Searching for cast members of a specific programme ('Galactic Wars')
            String query4 = "SELECT Cast_Details.cast_name "
                    + "FROM Cast_Details "
                    + "JOIN Programme_Cast ON Cast_Details.cast_id = Programme_Cast.cast_id "
                    + "JOIN Programme ON Programme_Cast.programme_id = Programme.programme_id "
                    + "WHERE Programme.programme_name = 'Galactic Wars';";
            System.out.println("\nRunning SQL: " + query4);
            executeSelectQuery(stmt, query4);

        } catch (SQLException e){
            System.out.println("\nError executing search for cast members.");
        }
    }

    // Search Episodes by Attributes (Case 3)
    private static void searchEpisodesByAttributes(Statement stmt){
        try{
            // Query 1: Searching for episodes of the programme 'Galactic Wars'
            String query1 = "SELECT e.season_number, e.episode_number, e.episode_name, e.release_date, e.duration "
                    + "FROM Episodes as e "
                    + "JOIN Programme as p ON e.programme_id = p.programme_id "
                    + "WHERE p.programme_name = 'Galactic Wars';";
            System.out.println("\nRunning SQL: " + query1);
            executeSelectQuery(stmt, query1);

            // Query 2: Searching for all episodes in season 1 of 'Galactic Wars'
            String query2 = "SELECT e.episode_number, e.episode_name, e.release_date, e.duration "
                    + "FROM Episodes as e "
                    + "JOIN Programme as p ON e.programme_id = p.programme_id "
                    + "WHERE e.season_number = 1 AND p.programme_name = 'Galactic Wars';";
            System.out.println("\nRunning SQL: " + query2);
            executeSelectQuery(stmt, query2);

        } catch (SQLException e){
            System.out.println("\nError executing search for episodes.");
        }
    }

    // Manage Programmes (Case 4)
    private static void manageProgrammes(Statement stmt){
        try{
            stmt.getConnection().setAutoCommit(false);  // Start transaction

            // Query 1: Adding a new programme
            String insertQuery = "INSERT INTO Programme (programme_name, programme_type, genre_id, release_date, duration) "
                    + "VALUES ('The Lost Treasure', 'Movie', 9, '2023-12-01', 120);";
            System.out.println("\nRunning SQL: " + insertQuery);
            stmt.execute(insertQuery);
            System.out.println("\nProgramme added successfully.");
            System.out.println("Transaction committed.");

            stmt.getConnection().commit();  // Commit all changes
        } catch (SQLException e){
            System.out.println("\nError adding programme.");
            try {
                stmt.getConnection().rollback();  // Roll back if any error occurs
                System.out.println("Transaction rolled back.");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback.");
            }
        }

        try{
            stmt.getConnection().setAutoCommit(false);  // Start transaction

            // Query 2: Deleting a programme by ID
            String deleteQuery = "DELETE FROM Programme WHERE programme_id = 10;";
            System.out.println("\nRunning SQL: " + deleteQuery);
            stmt.execute(deleteQuery);
            System.out.println("\nProgramme deleted successfully.");
            System.out.println("Transaction committed.");

            stmt.getConnection().commit();  // Commit all changes
        } catch (SQLException e){
            System.out.println("\nError deleting programme.");
            try {
                stmt.getConnection().rollback();  // Roll back if any error occurs
                System.out.println("Transaction rolled back.");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback.");
            }
        }

        try{
            stmt.getConnection().setAutoCommit(false);  // Start transaction

            // Query 3: Updating a programme's genre
            String updateGenreQuery = "UPDATE Programme SET genre_id = 3 WHERE programme_id = 1;";
            System.out.println("\nRunning SQL: " + updateGenreQuery);
            stmt.execute(updateGenreQuery);
            System.out.println("\nProgramme genre updated successfully.");
            System.out.println("Transaction committed.");

            stmt.getConnection().commit();  // Commit all changes
        } catch (SQLException e){
            System.out.println("\nError updating programme genre.");
            try {
                stmt.getConnection().rollback();  // Roll back if any error occurs
                System.out.println("Transaction rolled back.");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback.");
            }

        }

        try{
            stmt.getConnection().setAutoCommit(false);  // Start transaction

            // Query 4: Updating a programme's release date
            String updateReleaseDateQuery = "UPDATE Programme SET release_date = '2023-11-15' WHERE programme_id = 2;";
            System.out.println("\nRunning SQL: " + updateReleaseDateQuery);
            stmt.execute(updateReleaseDateQuery);
            System.out.println("\nProgramme release date updated successfully.");
            System.out.println("Transaction committed.");

            stmt.getConnection().commit();  // Commit all changes
        } catch (SQLException e){
            System.out.println("\nError executing programme management queries.");
            try {
                stmt.getConnection().rollback();  // Roll back if any error occurs
                System.out.println("Transaction rolled back.");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback.");
            }
        }
    }

    // Add Episodes to a Programme (Case 5)
    private static void addEpisodesToProgramme(Statement stmt){
        try{
            stmt.getConnection().setAutoCommit(false);  // Start transaction

            // Query 1: Adding a new episode and updating the programme duration
            String query1 = "INSERT INTO Episodes (programme_id, episode_name, season_number, episode_number, release_date, duration) "
                    + "VALUES (1, 'The New Dawn', 2, 2, '2024-11-01', 44);";
            System.out.println("Running SQL: " + query1);

            stmt.executeUpdate(query1);  // This will fail if programme_id 9999 does not exist
            System.out.println("\nEpisode added successfully.");

            // Query 2: Updating the Programme's duration
            String query2 = "UPDATE Programme SET duration = duration + 44 WHERE programme_id = 1;";
            System.out.println("\nRunning SQL: " + query2);

            stmt.executeUpdate(query2);  // This will fail if programme_id 9999 does not exist
            System.out.println("\nEpisode added successfully.");
            System.out.println("Transaction committed.");

            stmt.getConnection().commit();  // Commit all valid changes
        } catch (SQLException e){
            System.out.println("\nError executing add episodes to programme queries.");
            try {
                stmt.getConnection().rollback();  // Roll back if outer block fails
                System.out.println("Transaction rolled back.");
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback.");
            }
        }

        try{
            // Query 3: Attempting to insert an invalid episode (for error handling)
            String query3 = "INSERT INTO Episodes (programme_id, episode_name, season_number, episode_number, release_date, duration) "
                    + "VALUES (9999, 'Phantom Episode', 3, 1, '2024-12-01', 50);";
            System.out.println("\nRunning SQL: " + query3);

            stmt.executeUpdate(query3);  // This will fail if programme_id 9999 does not exist
            System.out.println("\nEpisode added successfully.");

            stmt.getConnection().commit();  // Commit all valid changes
        } catch (SQLException e){
            System.out.println("\nError executing add episodes to programme queries.");
            try{
                stmt.getConnection().rollback();  // Roll back if outer block fails
                System.out.println("Transaction rolled back.");
            } catch (SQLException rollbackEx){
                System.out.println("Error during rollback.");
            }
        }
    }

    // Helper function to execute SELECT queries
    private static void executeSelectQuery(Statement stmt, String query) throws SQLException{
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        System.out.println();

        // Print column names
        for (int i = 1; i <= columnCount; i++){
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();

        // Print the rows of the result set
        int count = 1;
        while (rs.next()) {
            System.out.print("[" + count + "] ");
            for (int i = 1; i <= columnCount; i++){
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
            count++;
        }

        if (count == 1){
            System.out.println("No data found for this query.");
        }

        rs.close();
    }
}