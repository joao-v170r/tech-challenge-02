INSERT INTO tb_tarifa (id, intervalo, preco_intervalo)
VALUES
    (1, '01:00:00', 15),
    (2, '02:00:00', 25);

INSERT INTO tb_parquimetro (id, endereco_completo, latitude, longitude, tolerancia, status_parquimetro)
VALUES
    (1, 'Rua das Flores, 123', -23, -46, '00:15:00', 0);

INSERT INTO tb_parquimetro_tarifa (parquimetro_id, tarifa_id)
VALUES
    (1, 1),
    (1, 2);