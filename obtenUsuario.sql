CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenUsuario`(inId int)
BEGIN
	select * from usuarios where id = inId;
END