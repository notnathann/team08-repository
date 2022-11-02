PRAGMA foreign_keys = OFF;
drop table if exists RUN_DETAILS;
drop table if exists PART_INFORMATION;
PRAGMA foreign_keys = ON;

CREATE TABLE RUN_DETAILS (
	FileName           TEXT NOT NULL,
	FilePath           TEXT,
	LoadNumber         TEXT,
	Equipment          TEXT,
	RunRecipe          TEXT,
	RunStart           TEXT  ,
	RunEnd             TEXT  ,
	RunDuration        TEXT  ,
	FileLength            TEXT  ,
	OperatorName            TEXT  ,
	ExportControl              TEXT  ,
    IP                 TEXT  ,
    PRIMARY KEY (FileName)
);

CREATE TABLE PART_INFORMATION (
    RunFileName           TEXT NOT NULL,
    IndexNumber              INT  NOT NULL,
    WorkOrder          INT  ,
    PartNumber         TEXT  ,
    PartDescription    TEXT,
    ToolLocation       TEXT,
    Comment1           TEXT,
    Comment2           TEXT,
    Comment3           TEXT,
    PartTCs            TEXT,
    PartProbes         TEXT,
    OtherSensors       TEXT,
    PRIMARY KEY (IndexNumber)       
    FOREIGN KEY (RunFileName) REFERENCES RUN_DETAILS(FileName)
);

