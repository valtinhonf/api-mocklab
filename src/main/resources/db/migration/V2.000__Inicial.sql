CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE SEQUENCE public.mocks_idmock_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.mocks (
	idmock int8 DEFAULT nextval('mocks_idmock_sequence'::regclass),
	idmockpublic uuid NOT NULL,
	idproject uuid NULL,
	"path" varchar(255) NOT NULL,
	name varchar(75) NOT NULL,
	description varchar(500) NULL,
	observation varchar(4000) NULL,
	"method" varchar(8) NOT NULL,
	status varchar(8) NOT NULL,
	status_code_response varchar(3) DEFAULT '"200"' NOT NULL,
	response_body text NULL,
	createdat timestamptz NOT NULL,
	updatedat timestamp with time zone NOT NULL,
	expiresin timestamp with time zone NULL,
	iduser uuid NOT NULL,
	CONSTRAINT mocks_pk PRIMARY KEY (idmock),
	CONSTRAINT mocks_unique UNIQUE (idmockpublic),
	CONSTRAINT mocks_unique_mock UNIQUE (idproject,"path","method")
);

-- Column comments

COMMENT ON COLUMN public.mocks.idmockpublic IS 'IdPublico do mock';
COMMENT ON COLUMN public.mocks."path" IS 'Path do mock';
COMMENT ON COLUMN public.mocks.iduser IS 'Owner of this mock';


CREATE OR REPLACE FUNCTION find_mock(request_url TEXT, request_method text, pidusuario_pdIdProject text)
RETURNS INT8 AS $$
DECLARE
    route RECORD;
    route_regex TEXT;
BEGIN
    FOR route IN
        SELECT idmock, path FROM public.mocks WHERE method = request_method AND (iduser = pidusuario_pdIdProject::uuid or idproject = pidusuario_pdIdProject::uuid)
    LOOP
        route_regex := '^' || REGEXP_REPLACE(route.path, '{\w+}', '\w+', 'g') || '$';

        IF request_url ~ route_regex THEN
            RETURN route.idmock;
        END IF;
    END LOOP;

    RETURN -1;
END;
$$ LANGUAGE plpgsql;

COMMENT ON FUNCTION public.find_mock(text, text, text) IS 'Função que implementa consulta por regex para pesquisar o mock';