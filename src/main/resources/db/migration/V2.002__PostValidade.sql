ALTER TABLE public.mocks ADD post_schema_request text NULL;
COMMENT ON COLUMN public.mocks.post_schema_request IS 'Field that save the json schema to validade in case of a post or put request';
