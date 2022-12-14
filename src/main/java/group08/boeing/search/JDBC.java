package group08.boeing.search;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

    private static final String DATABASE = "jdbc:sqlite:database/autoclave.db";
    
    public ArrayList<String> getMatchingOperatorNames(String partial) {
        // Array of lgas to return
        ArrayList<String> operatorNames = new ArrayList<>();

        // JDBC
        Connection connection = null;

        try {
            // Connect to database
            connection = DriverManager.getConnection(DATABASE);

            // Execute a query and get the result
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(100);

            String query = String.format("""
                SELECT RUN_DETAILS.OperatorName FROM RUN_DETAILS
                    WHERE RUN_DETAILS.OperatorName LIKE \"%%%s%%\"
                    ORDER BY
                        CASE
                            WHEN RUN_DETAILS.OperatorName LIKE \"%s%%\" THEN 1
                            WHEN RUN_DETAILS.OperatorName LIKE \"%%%s\" THEN 3
                            ELSE 2
                        END, RUN_DETAILS.OperatorName
                ;""", partial, partial, partial);

            ResultSet results = statement.executeQuery(query);

            // Process results
            while (results.next()) {
                String name = results.getString("OperatorName");

                if (!operatorNames.contains(name)) {
                    operatorNames.add(name);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // Close connection if one exists
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        System.out.println(String.format("Retrieved %d Operator Names", operatorNames.size()));
        return operatorNames;
    }

    public ArrayList<String> getMatchingLoadNumbers(String partial) {
        // Array of lgas to return
        ArrayList<String> loadNumbers = new ArrayList<>();

        // JDBC
        Connection connection = null;

        try {
            // Connect to database
            connection = DriverManager.getConnection(DATABASE);

            // Execute a query and get the result
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(100);

            String query = String.format("""
                SELECT RUN_DETAILS.LoadNumber FROM RUN_DETAILS
                    WHERE RUN_DETAILS.LoadNumber LIKE \"%%%s%%\"
                    ORDER BY
                        CASE
                            WHEN RUN_DETAILS.LoadNumber LIKE \"%s%%\" THEN 1
                            WHEN RUN_DETAILS.LoadNumber LIKE \"%%%s\" THEN 3
                            ELSE 2
                        END, RUN_DETAILS.LoadNumber
                ;""", partial, partial, partial);

            ResultSet results = statement.executeQuery(query);

            // Process results
            while (results.next()) {
                String loadNumber = results.getString("LoadNumber");
                
                if (!loadNumbers.contains(loadNumber)) {
                    loadNumbers.add(loadNumber);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // Close connection if one exists
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        System.out.println(String.format("Retrieved %d Load Numbers", loadNumbers.size()));
        return loadNumbers;
    }

    public ArrayList<String> getMatchingEquipment(String partial) {
        // Array of lgas to return
        ArrayList<String> equipmentList = new ArrayList<>();

        // JDBC
        Connection connection = null;

        try {
            // Connect to database
            connection = DriverManager.getConnection(DATABASE);

            // Execute a query and get the result
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(100);

            String query = String.format("""
                SELECT RUN_DETAILS.Equipment FROM RUN_DETAILS
                    WHERE RUN_DETAILS.Equipment LIKE \"%%%s%%\"
                    ORDER BY
                        CASE
                            WHEN RUN_DETAILS.Equipment LIKE \"%s%%\" THEN 1
                            WHEN RUN_DETAILS.Equipment LIKE \"%%%s\" THEN 3
                            ELSE 2
                        END, RUN_DETAILS.Equipment
                ;""", partial, partial, partial);

            ResultSet results = statement.executeQuery(query);

            // Process results
            while (results.next()) {
                String equipment = results.getString("Equipment");
                
                if (!equipmentList.contains(equipment)) {
                    equipmentList.add(equipment);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // Close connection if one exists
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        System.out.println(String.format("Retrieved %d Equipment", equipmentList.size()));
        return equipmentList;
    }

    public ArrayList<String> getMatchingRunRecipes(String partial) {
        // Array of lgas to return
        ArrayList<String> runRecipes = new ArrayList<>();

        // JDBC
        Connection connection = null;

        try {
            // Connect to database
            connection = DriverManager.getConnection(DATABASE);

            // Execute a query and get the result
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(100);

            String query = String.format("""
                SELECT RUN_DETAILS.RunRecipe FROM RUN_DETAILS
                    WHERE RUN_DETAILS.RunRecipe LIKE \"%%%s%%\"
                    ORDER BY
                        CASE
                            WHEN RUN_DETAILS.RunRecipe LIKE \"%s%%\" THEN 1
                            WHEN RUN_DETAILS.RunRecipe LIKE \"%%%s\" THEN 3
                            ELSE 2
                        END, RUN_DETAILS.RunRecipe
                ;""", partial, partial, partial);

            ResultSet results = statement.executeQuery(query);

            // Process results
            while (results.next()) {
                String runRecipe = results.getString("RunRecipe");
                
                if (!runRecipes.contains(runRecipe)) {
                    runRecipes.add(runRecipe);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // Close connection if one exists
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        System.out.println(String.format("Retrieved %d Run Recipes", runRecipes.size()));
        return runRecipes;
    }

    public ArrayList<RunDetails> getData(String operatorName, String loadNumber, String equipment, String runRecipe) {
        // Array of lgas to return
        ArrayList<RunDetails> data = new ArrayList<>();

        // JDBC
        Connection connection = null;

        try {
            // Connect to database
            connection = DriverManager.getConnection(DATABASE);

            // Execute a query and get the result
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(100);

            String query = String.format("SELECT * FROM RUN_DETAILS");

            String filterQuery = "";

            if (!operatorName.equals("null")) {
                filterQuery += "\n    WHERE RUN_DETAILS.OperatorName = \"" + operatorName + "\"";
            }

            if (!loadNumber.equals("null")) {
                if (filterQuery.isEmpty()) {
                    filterQuery += "\n    WHERE RUN_DETAILS.LoadNumber = \"" + loadNumber + "\"";
                } else {
                    filterQuery += "\n AND RUN_DETAILS.LoadNumber = \"" + loadNumber + "\"";
                }
            }

            if (!equipment.equals("null")) {
                if (filterQuery.isEmpty()) {
                    filterQuery += "\n    WHERE RUN_DETAILS.Equipment = \"" + equipment + "\"";
                } else {
                    filterQuery += "\n AND RUN_DETAILS.Equipment = \"" + equipment + "\"";
                }
            }

            if (!runRecipe.equals("null")) {
                if (filterQuery.isEmpty()) {
                    filterQuery += "\n    WHERE RUN_DETAILS.RunRecipe = \"" + runRecipe + "\"";
                } else {
                    filterQuery += "\n AND RUN_DETAILS.RunRecipe = \"" + runRecipe + "\"";
                }
            }

            query += filterQuery + ";";
            System.out.println(query);

            ResultSet results = statement.executeQuery(query);

            // Process results
            while (results.next()) {
                RunDetails runDetails = new RunDetails();

                runDetails.fileName = results.getString("FileName");
                runDetails.filePath = results.getString("FilePath");
                runDetails.loadNumber = results.getString("LoadNumber");
                runDetails.equipment = results.getString("Equipment");
                runDetails.runRecipe = results.getString("RunRecipe");
                runDetails.runStart = results.getString("RunStart");
                runDetails.runEnd = results.getString("RunEnd");
                runDetails.runDuration = results.getString("RunDuration");
                runDetails.fileLength = results.getString("FileLength");
                runDetails.operatorName = results.getString("OperatorName");
                runDetails.exportControl = results.getString("ExportControl");
                runDetails.ip = results.getString("IP");
                
                data.add(runDetails);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                // Close connection if one exists
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        System.out.println(String.format("Retrieved %d Data Rows", data.size()));
        return data;
    }
}
