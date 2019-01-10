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


-- 导出 image 的数据库结构
CREATE DATABASE IF NOT EXISTS `image` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `image`;

-- 导出  表 image.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) COLLATE utf8_bin NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `password` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `salt` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `size` bigint(20) NOT NULL DEFAULT '0' COMMENT '相册已用大小',
  `capacity` bigint(20) NOT NULL DEFAULT '10240000' COMMENT '相册容量',
  `role` varchar(5) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='账号表';

-- 正在导出表  image.account 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `nickname`, `username`, `password`, `salt`, `create_time`, `size`, `capacity`, `role`) VALUES
	(1, 'GOSICK', 'inkbox', '79190a6239e63cb82953d3583cbcf703', 'jk9blnkc', '2017-03-01 12:31:31', -241691, 10240000, '0'),
	(4, '你猜一猜', 'test1', '5f7ca59c7a1b9b66cb608b7efd6d04b8', '2hl7c03o', '2017-09-11 11:34:20', 0, 10240000, '1'),
	(5, '测试注册', '注册', '366315892fc9bc210df4917b9d9500ab', 'feuxa9hn', '2017-09-11 11:38:17', 0, 10240000, '1');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 导出  表 image.album 结构
CREATE TABLE IF NOT EXISTS `album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '相册名',
  `authority` bigint(20) NOT NULL DEFAULT '0' COMMENT '权限 0：所有人可见，1：仅自己可见，大于等于2：回答问题可见，数字即问题id',
  `owner` bigint(20) NOT NULL DEFAULT '0' COMMENT '拥有者',
  `cover` bigint(20) NOT NULL DEFAULT '0' COMMENT '封面——图片路径',
  `number` int(11) NOT NULL DEFAULT '0' COMMENT '图片数量',
  `size` bigint(20) NOT NULL DEFAULT '0' COMMENT '已用空间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `change_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间——上传图片',
  `content` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '相册说明',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '分类',
  PRIMARY KEY (`id`),
  KEY `FK_album_account` (`owner`),
  CONSTRAINT `FK_album_account` FOREIGN KEY (`owner`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='相册';

-- 正在导出表  image.album 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` (`id`, `name`, `authority`, `owner`, `cover`, `number`, `size`, `create_time`, `change_time`, `content`, `type`) VALUES
	(3, 'K', 1, 1, 34, 9, -45087, '2017-08-15 23:41:29', '2017-08-15 23:41:29', '这个相册叫k', 0),
	(4, '灰色', 0, 1, 15, 3, 168, '2017-08-15 23:41:29', '2017-08-15 23:41:29', '来自灰色三部曲', 0),
	(20, '随便创建的', 12, 1, 44, 43, 0, '2017-09-12 11:22:35', '2017-09-12 11:22:35', '真的是随便来的', 0);
/*!40000 ALTER TABLE `album` ENABLE KEYS */;

-- 导出  表 image.config 结构
CREATE TABLE IF NOT EXISTS `config` (
  `type` int(11) NOT NULL,
  `path` varchar(100) COLLATE utf8_bin NOT NULL,
  `content` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='配置表';

-- 正在导出表  image.config 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` (`type`, `path`, `content`) VALUES
	(1, '1000', '清空数据库存储的ip 的时间间隔，单位秒'),
	(2, '200', '单位时间内允许的最高访问次数'),
	(3, '60000', '限制访问的单位时间，单位毫秒'),
	(4, '60000', 'redis存储的清空ip等数据的时长，即配置更新的最长时间'),
	(5, 'E:\\娱乐\\图片\\test\\', '默认图片存储路径'),
	(6, '14608800', '图片最大大小'),
	(403, 'E:\\403.jpg', '图片403（没权限）时默认返回路径'),
	(404, 'E:\\test.jpg', '图片404时默认返回路径'),
	(406, 'E:\\test.jpg', '图片406（参数错误）时默认返回路径');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;

-- 导出  表 image.ip 结构
CREATE TABLE IF NOT EXISTS `ip` (
  `ip_address` varchar(15) COLLATE utf8_bin NOT NULL,
  `count` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `last_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用来做访问频率限制';

-- 正在导出表  image.ip 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `ip` DISABLE KEYS */;
INSERT INTO `ip` (`ip_address`, `count`, `start_time`, `last_time`) VALUES
	('127.0.0.1', 8, '2017-10-27 11:19:10', '2017-10-27 11:19:42'),
	('10.14.2.160', 16, '2017-10-27 11:20:21', '2017-10-27 11:22:49');
/*!40000 ALTER TABLE `ip` ENABLE KEYS */;

-- 导出  表 image.question 结构
CREATE TABLE IF NOT EXISTS `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '相册题干',
  `answer` varchar(50) COLLATE utf8_bin NOT NULL,
  `owner` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_question_album` (`owner`),
  CONSTRAINT `FK_question_album` FOREIGN KEY (`owner`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='回答问题存放的答案';

-- 正在导出表  image.question 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` (`id`, `content`, `answer`, `owner`) VALUES
	(12, '多少来个问题吧', '就一个问题', 20);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;

-- 导出  表 image.upload 结构
CREATE TABLE IF NOT EXISTS `upload` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `path` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `owner` bigint(20) NOT NULL COMMENT '属于哪个相册',
  PRIMARY KEY (`id`),
  KEY `FK_upload_album` (`owner`),
  CONSTRAINT `FK_upload_album` FOREIGN KEY (`owner`) REFERENCES `album` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='上传文件';

-- 正在导出表  image.upload 的数据：~57 rows (大约)
/*!40000 ALTER TABLE `upload` DISABLE KEYS */;
INSERT INTO `upload` (`id`, `path`, `create_time`, `status`, `owner`) VALUES
	(10, 'E:\\娱乐\\图片\\k\\k014.jpg', '2017-08-15 23:43:13', NULL, 3),
	(11, 'E:\\娱乐\\图片\\k\\k013.jpg', '2017-08-15 23:43:13', NULL, 3),
	(12, 'E:\\娱乐\\图片\\k\\k017.jpg', '2017-08-15 23:43:13', NULL, 3),
	(14, 'E:\\娱乐\\图片\\灰色\\灰色1.jpg', '2017-08-15 23:43:13', NULL, 4),
	(15, 'E:\\娱乐\\图片\\灰色\\灰色2.jpg', '2017-08-15 23:43:13', NULL, 4),
	(16, 'E:\\娱乐\\图片\\灰色\\灰色3.jpg', '2017-08-15 23:43:13', NULL, 4),
	(17, 'E:\\娱乐\\图片\\k\\k012.jpg', '2017-08-15 23:43:13', NULL, 3),
	(18, 'E:\\娱乐\\图片\\k\\k073.jpg', '2017-08-15 23:43:13', NULL, 3),
	(32, 'E:\\娱乐\\图片\\k\\k074.jpg', '2017-08-15 23:43:13', NULL, 3),
	(33, 'E:\\娱乐\\图片\\k\\k064.jpg', '2017-08-15 23:43:13', NULL, 3),
	(34, 'E:\\娱乐\\图片\\k\\k034.jpg', '2017-08-15 23:43:13', NULL, 3),
	(35, 'E:\\娱乐\\图片\\k\\k103.jpg', '2017-08-15 23:43:13', NULL, 3),
	(36, 'E:\\娱乐\\图片\\k\\k101.jpg', '2017-08-15 23:43:13', NULL, 3),
	(37, 'E:\\娱乐\\图片\\k\\k100.jpg', '2017-08-15 23:43:13', NULL, 3),
	(38, 'E:\\娱乐\\图片\\test\\1505186954824黑执事1.jpg', '2017-09-12 11:29:14', NULL, 20),
	(39, 'E:\\娱乐\\图片\\test\\1505186954899黑执事2.jpg', '2017-09-12 11:29:14', NULL, 20),
	(40, 'E:\\娱乐\\图片\\test\\1505186955133黑执事3.jpg', '2017-09-12 11:29:15', NULL, 20),
	(41, 'E:\\娱乐\\图片\\test\\1505186955319黑执事4.jpg', '2017-09-12 11:29:15', NULL, 20),
	(42, 'E:\\娱乐\\图片\\test\\1505187186299动物007.jpg', '2017-09-12 11:33:06', NULL, 20),
	(43, 'E:\\娱乐\\图片\\test\\1505187186432动物008.jpg', '2017-09-12 11:33:06', NULL, 20),
	(44, 'E:\\娱乐\\图片\\test\\1505187186633动物009.jpg', '2017-09-12 11:33:06', NULL, 20),
	(45, 'E:\\娱乐\\图片\\test\\1505187186983动物010.jpg', '2017-09-12 11:33:07', NULL, 20),
	(46, 'E:\\娱乐\\图片\\test\\1505187187161动物012.jpg', '2017-09-12 11:33:07', NULL, 20),
	(47, 'E:\\娱乐\\图片\\test\\1505187186994动物011.jpg', '2017-09-12 11:33:07', NULL, 20),
	(48, 'E:\\娱乐\\图片\\test\\1505187187403动物013.jpg', '2017-09-12 11:33:07', NULL, 20),
	(49, 'E:\\娱乐\\图片\\test\\1505187187506动物014.jpg', '2017-09-12 11:33:07', NULL, 20),
	(50, 'E:\\娱乐\\图片\\test\\1505187201528动物151.jpg', '2017-09-12 11:33:21', NULL, 20),
	(51, 'E:\\娱乐\\图片\\test\\1505187201733动物152.jpg', '2017-09-12 11:33:21', NULL, 20),
	(52, 'E:\\娱乐\\图片\\test\\1505187201940动物153.jpg', '2017-09-12 11:33:21', NULL, 20),
	(53, 'E:\\娱乐\\图片\\test\\1505187202041动物154.jpg', '2017-09-12 11:33:22', NULL, 20),
	(54, 'E:\\娱乐\\图片\\test\\1505187202229动物155.jpg', '2017-09-12 11:33:22', NULL, 20),
	(55, 'E:\\娱乐\\图片\\test\\1505187202395动物156.jpg', '2017-09-12 11:33:22', NULL, 20),
	(56, 'E:\\娱乐\\图片\\test\\1505187202596动物157.jpg', '2017-09-12 11:33:22', NULL, 20),
	(57, 'E:\\娱乐\\图片\\test\\1505187202733动物158.jpg', '2017-09-12 11:33:22', NULL, 20),
	(58, 'E:\\娱乐\\图片\\test\\1505187202933动物159.jpg', '2017-09-12 11:33:22', NULL, 20),
	(59, 'E:\\娱乐\\图片\\test\\1505187203097动物160.jpg', '2017-09-12 11:33:23', NULL, 20),
	(60, 'E:\\娱乐\\图片\\test\\1505187421090神的记事本01.jpg', '2017-09-12 11:37:01', NULL, 20),
	(61, 'E:\\娱乐\\图片\\test\\1505187421263神的记事本02.jpg', '2017-09-12 11:37:01', NULL, 20),
	(62, 'E:\\娱乐\\图片\\test\\1505187421491神的记事本03.jpg', '2017-09-12 11:37:01', NULL, 20),
	(63, 'E:\\娱乐\\图片\\test\\1505187421654神的记事本04.jpg', '2017-09-12 11:37:01', NULL, 20),
	(64, 'E:\\娱乐\\图片\\test\\1505187421789神的记事本05.jpg', '2017-09-12 11:37:01', NULL, 20),
	(65, 'E:\\娱乐\\图片\\test\\1505187421947神的记事本06.jpg', '2017-09-12 11:37:02', NULL, 20),
	(66, 'E:\\娱乐\\图片\\test\\1505187422236神的记事本07.jpg', '2017-09-12 11:37:02', NULL, 20),
	(67, 'E:\\娱乐\\图片\\test\\1505187440101魁拔02.jpg', '2017-09-12 11:37:20', NULL, 20),
	(68, 'E:\\娱乐\\图片\\test\\1505187440271魁拔03.jpg', '2017-09-12 11:37:20', NULL, 20),
	(69, 'E:\\娱乐\\图片\\test\\1505187440486魁拔04.jpg', '2017-09-12 11:37:20', NULL, 20),
	(70, 'E:\\娱乐\\图片\\test\\1505187440672魁拔05.jpg', '2017-09-12 11:37:20', NULL, 20),
	(71, 'E:\\娱乐\\图片\\test\\1505187440721魁拔06.jpg', '2017-09-12 11:37:20', NULL, 20),
	(72, 'E:\\娱乐\\图片\\test\\1505187441018魁拔07.jpg', '2017-09-12 11:37:21', NULL, 20),
	(73, 'E:\\娱乐\\图片\\test\\1505187441120魁拔08.jpg', '2017-09-12 11:37:21', NULL, 20),
	(74, 'E:\\娱乐\\图片\\test\\1505187441259魁拔09.jpg', '2017-09-12 11:37:21', NULL, 20),
	(75, 'E:\\娱乐\\图片\\test\\1505196123257刀剑神域001.jpg', '2017-09-12 14:02:03', NULL, 20),
	(76, 'E:\\娱乐\\图片\\test\\1505196123392刀剑神域002.jpg', '2017-09-12 14:02:03', NULL, 20),
	(77, 'E:\\娱乐\\图片\\test\\1505196123534刀剑神域003.jpg', '2017-09-12 14:02:03', NULL, 20),
	(78, 'E:\\娱乐\\图片\\test\\1505196123889刀剑神域006.jpg', '2017-09-12 14:02:03', NULL, 20),
	(79, 'E:\\娱乐\\图片\\test\\1505196124093刀剑神域007.jpg', '2017-09-12 14:02:04', NULL, 20),
	(80, 'E:\\娱乐\\图片\\test\\1505196124344刀剑神域008.jpg', '2017-09-12 14:02:04', NULL, 20);
/*!40000 ALTER TABLE `upload` ENABLE KEYS */;

-- 导出  过程 image.delete_image 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_image`(
	IN `owner_id` BIGINT,
	IN `album_id` BIGINT,
	IN `image_id` BIGINT,
	IN `size` BIGINT
)
    COMMENT '删除图片'
BEGIN
	update account,album set account.size=account.size-size,album.size=album.size-size
	  where account.id=owner_id and album.id=album_id
	 and album.owner = account.id;
	 
	 delete from upload where upload.id = image_id;
END//
DELIMITER ;

-- 导出  过程 image.update_ip 结构
DELIMITER //
CREATE DEFINER=`image`@`%` PROCEDURE `update_ip`(
	IN `ip_a` VARCHAR(20)


)
BEGIN
	declare error int(1);
	set error = 0;
	update ip set ip.count=ip.count+1,ip.last_time=now() where ip.ip_address=ip_a;
	set error=error+row_count();
	
	if error = 0 then
		insert into ip values(ip_a,1,now(),now());
	end if;
	select ip_address as ip,count,start_time as startTime,last_time as lastTime from ip where ip.ip_address = ip_a limit 1;
	
END//
DELIMITER ;

-- 导出  触发器 image.album_after_insert 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `album_after_insert` AFTER INSERT ON `album` FOR EACH ROW BEGIN
	if new.authority>=2 then
	 update question set question.owner = new.id where question.id = new.authority  limit 1;
	 end if;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 image.album_after_update 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `album_after_update` AFTER UPDATE ON `album` FOR EACH ROW BEGIN
	if new.authority!= old.authority and old.authority>=2 then
	delete from  question where question.id = old.authority limit 1;
	update question set owner = new.id where question.id = new.authority;
	end if;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
