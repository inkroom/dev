-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.15 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 flag 的数据库结构
CREATE DATABASE IF NOT EXISTS `flag` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `flag`;

-- 导出  表 flag.board 结构
CREATE TABLE IF NOT EXISTS `board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `white` int(11) NOT NULL COMMENT '白方',
  `black` int(11) DEFAULT NULL COMMENT '黑方',
  `chesses` json DEFAULT NULL COMMENT 'json数组，已下的棋子',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态，0等待，1下棋中，2白方胜，3黑方胜',
  `now` int(11) DEFAULT NULL COMMENT '应该落子的用户Id',
  PRIMARY KEY (`id`),
  KEY `FK_board_users` (`white`),
  KEY `FK_board_users_2` (`black`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='棋局，包括执子双方，落子。先加入的玩家执白子';

-- 正在导出表  flag.board 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` (`id`, `white`, `black`, `chesses`, `state`, `now`) VALUES
	(1, 1, 2, '["{\\"x\\":6,\\"y\\":8,\\"id\\":1}", "{\\"x\\":7,\\"y\\":7,\\"id\\":2}", "{\\"x\\":7,\\"y\\":8,\\"id\\":1}", "{\\"x\\":8,\\"y\\":7,\\"id\\":2}", "{\\"x\\":8,\\"y\\":8,\\"id\\":1}", "{\\"x\\":9,\\"y\\":7,\\"id\\":2}", "{\\"x\\":9,\\"y\\":8,\\"id\\":1}", "{\\"x\\":10,\\"y\\":7,\\"id\\":2}", "{\\"x\\":10,\\"y\\":8,\\"id\\":1}"]', 1, 2),
	(2, 3, NULL, '[]', 0, NULL),
	(3, 5, NULL, '[]', 0, NULL),
	(10, 1, 2, '["{\\"x\\":8,\\"y\\":1,\\"id\\":2}", "{\\"x\\":4,\\"y\\":3,\\"id\\":1}", "{\\"x\\":9,\\"y\\":7,\\"id\\":2}", "{\\"x\\":6,\\"y\\":4,\\"id\\":1}"]', 1, 1);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 导出  表 flag.users 结构
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态，0休闲，1白子，2黑子',
  `boardId` int(11) NOT NULL DEFAULT '-1' COMMENT '棋局id',
  PRIMARY KEY (`id`),
  KEY `FK_users_board` (`boardId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- 正在导出表  flag.users 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `account`, `password`, `state`, `boardId`) VALUES
	(1, 'inkbox1', 'inkbox', 1, 10),
	(2, 'inkbox2', 'inkbox', 2, 10),
	(3, 'inkbox3', 'inkbox', 0, -1),
	(4, 'inkbox4', 'inkbox', 0, -1),
	(5, 'inkbox5', 'inkbox', 0, -1),
	(6, 'inkbox6', 'inkbox', 0, -1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- 导出  过程 flag.insert_board 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_board`(
	IN `userId` INT












)
    DETERMINISTIC
    COMMENT '创建棋局'
BEGIN
	declare error int(11);
	set error = 0;
	start transaction;
	-- 创建棋局
		insert into board (board.white,board.chesses,board.now) values(userId,'[]',userId);
	
	set error = row_count()+error;
	-- 更新创建者状态
	update users set users.state = 1,users.boardId = last_insert_id() where users.id = userId and users.boardId=-1 limit 1;
	
	set error = row_count() + error;
--	select error;
	if error =2 then commit;

	select last_insert_id() as boardId;
	else rollback;
	select null as boardId;
	end if;
--	select error as result;
END//
DELIMITER ;

-- 导出  过程 flag.insert_chess 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_chess`(
	IN `array` VARCHAR(50),
	IN `boardId` INT



,
	IN `otherUserId` INT


)
    COMMENT '落子'
BEGIN
	update board set board.chesses = JSON_ARRAY_INSERT(board.chesses,'$[0]',array),board.now=otherUserId
	 where board.id= boardId and board.now != otherUserId limit 1;
	if row_count() = 1 then
		select board.chesses,board.white,board.black from board where board.id = boardId;
	else 
		select null;
	end if;
END//
DELIMITER ;

-- 导出  过程 flag.join_board 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `join_board`(
	IN `boardId` INT,
	IN `userId` INT












)
    COMMENT '加入棋局'
BEGIN
	declare error int(1);
	set error = 0;
	start transaction;
	update board set board.state = 1 ,board.black =  userId ,board.now=board.white where board.id = boardId and board.white!=userId limit 1;
	set error= row_count()+error;
--	select error as one;
	update users set users.state = 2 ,users.boardId=boardId where users.id = userId and users.boardId=-1 limit 1;
	set error= row_count()+error;
--	select error as se;
	if error = 2 then commit;
		select * from board where board.id = boardId;
	else rollback;
		select null;
	end if;
	
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
