-- Client
 CREATE TABLE [dbo].[Client] (
    [ClientID][nvarchar](15) NOT NULL,
    [FirstName][nvarchar](35) NULL,
    [LastName][nvarchar](35) NULL,
    [RiskPercentage][int] NULL,
 
    CONSTRAINT [PK_CLIENT] PRIMARY KEY ([ClientID] ASC)
)

-- Users
 CREATE TABLE [dbo].[Users] (
    [Username][nvarchar](15) NOT NULL,
	[Password][nvarchar](35) NULL,
    [FirstName][nvarchar](35) NULL,
    [LastName][nvarchar](35) NULL,
    [Role][nvarchar] (20) NULL,
 
    CONSTRAINT [PK_USERS] PRIMARY KEY ([Username] ASC)
)

-- Case
 CREATE TABLE [dbo].[Case] (
    [CaseID] [int] IDENTITY(1,1) NOT NULL,
	[ClientID][nvarchar](15) NOT NULL,
    [Description][nvarchar](500) NULL,
 
    CONSTRAINT [PK_CASE] PRIMARY KEY ([CaseID] ASC)
)

-- Consultation
 CREATE TABLE [dbo].[Consultation] (
    [ConsultationID] [int] IDENTITY(1,1) NOT NULL,
	[CaseID] [int] NOT NULL,
	[AppointmentDate][datetime] NULL,
    [Discussion][nvarchar](500) NULL,
	[Attendance][int] DEFAULT 0,
	[Comments][nvarchar](500) NULL,
	[ModifyDate][datetime] NULL,

    CONSTRAINT [PK_CONSULTATION] PRIMARY KEY ([ConsultationID] ASC)
)

ALTER TABLE [Consultation] 
  ADD CONSTRAINT CONSTRAINT_DATE_CONSULTATION
    DEFAULT GETDATE() FOR [AppointmentDate]

ALTER TABLE [Consultation] 
  ADD CONSTRAINT CONSTRAINT_DATE_CONSULTATION_MODIFIED
    DEFAULT GETDATE() FOR [ModifyDate]

-- Foreign Keys
ALTER TABLE [dbo].[Case] ADD CONSTRAINT [FK_Client] FOREIGN KEY([ClientID]) REFERENCES [dbo].[Client] (ClientID)
ALTER TABLE [dbo].[Consultation] ADD CONSTRAINT [FK_Case] FOREIGN KEY([CaseID]) REFERENCES [dbo].[Case] (CaseID)