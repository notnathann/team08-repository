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
                operatorNames.add(name);
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

    public ArrayList<Integer> getMatchingLoadNumbers(String partial) {
        // Array of lgas to return
        ArrayList<Integer> loadNumbers = new ArrayList<>();

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
                Integer loadNumber = results.getInt("LoadNumber");
                loadNumbers.add(loadNumber);
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
                equipmentList.add(equipment);
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
                runRecipes.add(runRecipe);
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
}
