CREATE DEFINER=`root`@`localhost` FUNCTION `borrarUsuario`(inId int) RETURNS int(11)
BEGIN

declare deleteCount int;
	delete from usuarios where id = inId;
    set deleteCount = row_count();
	return deleteCount;
END