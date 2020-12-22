# spring-batch

=============================================================================
 -> Objetivo do JOB:

 	#O Job deve pegar a quantidade de [user_odds] a serem gerados na tabela [auditor_logs]
 	
 	#Ele deve gerar esta quantidade de registro na tabela [user_odds]
 	
 	#Ele deve setar o status do processamento na tabela [auditor_logs]
 	
 	#Ele deve setar a data do processamento na tabela [auditor_logs]
 	
 	#Para garantir o processamento correto ele não pode deixar um registro em [auditor_logs] 
 		com status processando e com dtprocessamento preenchido caso o JOB seja interrompido.
 	
=============================================================================
 -> Documentação Spring Batch
	
	https://docs.spring.io/spring-batch/docs/current/reference/html/index.html

=============================================================================

 -> Referência na documentação para abordagem adotada no protóripo:
	
	https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#exampleTaskletImplementation

=============================================================================

-> Criar um container Docker para a base de dados de teste

	docker run --name docker-postgres-spring-batch --network=postgres-network -e "POSTGRES_PASSWORD=root"  -p 5432:5432  -v "/home/cinq/projetos/elo/docker_volume_spring_batch:/var/lib/postgresql/data" -d postgres

=============================================================================

-> Criar tabelas

	CREATE TABLE auditor_logs (
		id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
		qtdRaspadinhas numeric(8,2) NOT NULL,
		idTransacao int4 NOT NULL,
		idCampanha int4 NOT NULL,
		idUsuario int4 NOT NULL,
		dtProcessado timestamp NULL,
		status varchar(50) NULL
	);

	CREATE TABLE user_odds (
		id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
		idUsuario int4 NOT NULL,
		idCampanha int4 NOT NULL,
		idTransacao int4 NOT NULL,
		idAuditorLogs int4 NOT NULL
	);

=============================================================================

 -> Inserir registros

	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(2,1,1,1);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(3,2,1,2);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(5,3,1,3);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(2,4,1,4);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(1,5,1,5);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(1,6,1,6);

	-----
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(50,7,1,1);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(30,8,1,2);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(200,9,1,3);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(1000,10,1,4);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(10000,11,1,5);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(100000,12,1,6);

	-----

	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(100000,13,1,1);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(100000,14,1,2);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(100000,15,1,3);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(100000,16,1,4);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(100000,17,1,5);
	insert into auditor_logs (qtdraspadinhas, idtransacao, idcampanha, idusuario) values(100000,18,1,6);

=============================================================================

 -> Testes

	Ainda não implementado o teste do JOB.
	Mas com os dados informados é esperado a geração de 14 registros em user_odds

	---- Total de raspadinhas: 711.294

	select count(*) from user_odds uo ;

	select count(*) from user_odds uo where idauditorlogs =1;
	select count(*) from user_odds uo where idauditorlogs =2;
	select count(*) from user_odds uo where idauditorlogs =3;
	select count(*) from user_odds uo where idauditorlogs =4;
	select count(*) from user_odds uo where idauditorlogs =5;
	select count(*) from user_odds uo where idauditorlogs =6;


=============================================================================

 -> Limpar base para refazer o teste:

	UPDATE public.auditor_logs
	SET dtprocessado=NULL, status=NULL
	WHERE id in (1,2,3,4,5,6,7);

drop table auditor_logs;
drop table user_odds;