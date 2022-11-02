PRAGMA foreign_keys = OFF;
drop table if exists RUN_DETAILS;
drop table if exists PART_INFORMATION;
PRAGMA foreign_keys = ON;

CREATE TABLE RUN_DETAILS (
	FileName           CHAR (10) NOT NULL,
	FilePath           CHAR (10) NOT NULL,
	LoadNumber         CHAR (10) NOT NULL,
	Equipment            CHAR (10) NOT NULL,
	RunRecipe           CHAR (10) NOT NULL,
	RunStart            CHAR (10) NOT NULL,
	RunEnd            CHAR (10) NOT NULL,
	RunDuration           CHAR (10) NOT NULL,
	FileLength            CHAR (10) NOT NULL,
	OperatorName            CHAR (10) NOT NULL,
	ExportControl              CHAR (10) NOT NULL,
    PRIMARY KEY (FileName)
);

CREATE TABLE PART_INFORMATION (
    FileName           CHAR (10) NOT NULL,
    Number              INTEGER NOT NULL,
    WorkOrder          CHAR (10) NOT NULL,
    PartNumber         CHAR (10) NOT NULL,
    PartDescription    CHAR (10),
    ToolLocation       CHAR (10),
    Comment1           CHAR (10),
    Comment2           CHAR (10),
    Comment3           CHAR (10),
    PartTCs            CHAR (10),
    PartProbes         CHAR (5),
    OtherSensors       CHAR (5),
    PRIMARY KEY (Number)       
    FOREIGN KEY (FileName) REFERENCES RUN_DETAILS(FileName)
);

