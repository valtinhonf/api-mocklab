CREATE TABLE public.projects (
	idproject uuid NOT NULL,
	idorganization uuid NOT NULL,
	idsequence int4 NOT NULL,
	name varchar(50) NOT NULL,
	description varchar(150) NULL,
	observation varchar(150) NULL,
	createdat timestamp with time zone DEFAULT now() NULL,
	updatedat timestamp with time zone DEFAULT now() NULL,
	CONSTRAINT project_pk PRIMARY KEY (idproject),
	CONSTRAINT projects_unique UNIQUE (idorganization,idsequence)
);
