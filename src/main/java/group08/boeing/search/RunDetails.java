package group08.boeing.search;

public class RunDetails {
    public String fileName;
    public String filePath;
    public String loadNumber;
    public String equipment;
    public String runRecipe;
    public String runStart;
    public String runEnd;
    public String runDuration;
    public String fileLength;
    public String operatorName;
    public String exportControl;
    public String ip;

    public RunDetails(){}

    public RunDetails(
        String fileName, String filePath, String loadNumber, 
        String equipment, String runRecipe, String runStart, 
        String runEnd, String runDuration, String fileLength, 
        String operatorName, String exportControl, String ip){
            this.fileName = fileName;
            this.filePath = filePath;
            this.loadNumber = loadNumber;
            this.equipment = equipment;
            this.runRecipe = runRecipe;
            this.runStart = runStart;
            this.runEnd = runEnd;
            this.runDuration = runDuration;
            this.fileLength = fileLength;
            this.operatorName = operatorName;
            this.exportControl = exportControl;
            this.ip = ip;
    }
}