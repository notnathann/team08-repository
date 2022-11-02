PRAGMA foreign_keys = OFF;
drop table if exists RUN_DETAILS;
drop table if exists PART_INFORMATION;
PRAGMA foreign_keys = ON;

CREATE TABLE RUN_DETAILS (
	FileName           TEXT NOT NULL,
	FilePath           TEXT NOT NULL,
	LoadNumber         TEXT NOT NULL,
	Equipment          TEXT NOT NULL,
	RunRecipe          TEXT NOT NULL,
	RunStart           TEXT NOT NULL,
	RunEnd             TEXT NOT NULL,
	RunDuration        TEXT NOT NULL,
	FileLength            TEXT NOT NULL,
	OperatorName            TEXT NOT NULL,
	ExportControl              TEXT NOT NULL,
    PRIMARY KEY (FileName)
);

CREATE TABLE PART_INFORMATION (
    FileName           TEXT NOT NULL,
    IndexNumber              INT NOT NULL,
    WorkOrder          INT NOT NULL,
    PartNumber         INT NOT NULL,
    PartDescription    TEXT,
    ToolLocation       TEXT,
    Comment1           TEXT,
    Comment2           TEXT,
    Comment3           TEXT,
    PartTCs            TEXT,
    PartProbes         TEXT,
    OtherSensors       TEXT,
    PRIMARY KEY (Number)       
    FOREIGN KEY (FileName) REFERENCES RUN_DETAILS(FileName)
);

