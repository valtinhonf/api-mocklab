CREATE OR REPLACE FUNCTION match_route(request_url TEXT, request_method text, pidusuario numeric)
RETURNS TEXT AS $$
DECLARE
    route RECORD;
    route_regex TEXT;
BEGIN
    FOR route IN
        SELECT id, path_template FROM route_templates WHERE http_method = request_method AND idusuario = pidusuario
    LOOP
        -- Converte a rota com placeholders em uma expressão regular
        route_regex := '^' || REGEXP_REPLACE(route.path_template, '{\w+}', '\w+', 'g') || '$';

        -- Verifica se a URL da requisição corresponde ao template (usando regex)
        IF request_url ~ route_regex THEN
            -- Retorna o template correspondente ou qualquer outra informação necessária
            RETURN 'Template encontrado: ' || route.path_template;
        END IF;
    END LOOP;

    -- Caso não encontre nenhum template correspondente
    RETURN 'Nenhum template encontrado para a rota: ' || request_url;
END;
$$ LANGUAGE plpgsql;


select match_route('/product/avc/simple', 'GET', 100)