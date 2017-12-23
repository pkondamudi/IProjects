-- phpMyAdmin SQL Dump
-- version 3.4.11.1
-- http://www.phpmyadmin.net
--
-- Host: 198.38.82.101
-- Generation Time: Jul 15, 2016 at 01:02 PM
-- Server version: 5.5.36
-- PHP Version: 5.4.31

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pavankon_marrker`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `insert_post`$$
CREATE PROCEDURE `insert_post`(
IN ContextBoardNameParam VARCHAR(1024),
IN contextboarddescriptionParam VARCHAR(1024),
IN PostParam VARCHAR(1024), 
IN WebURLParam VARCHAR(2048), 
IN UserIdParam VARCHAR(1024)
)
BEGIN
	
    DECLARE ContextBoardIdVar VARCHAR(1024);
    
    SET ContextBoardIdVar = UUID();
    
    IF NOT EXISTS(SELECT * FROM `pavankon_marrker`.`contextBoards` 
					WHERE ContextBoardName = ContextBoardNameParam) THEN
		
             
        INSERT INTO `pavankon_marrker`.`contextboards`
			(`contextboardid`,`contextboardname`,`contextboarddescription`,
			`boardtypeid`,`inheritedBoardid`,`userId`,`isdefault`)
			VALUES(ContextBoardIdVar,ContextBoardNameParam,contextboarddescriptionParam,'fe47ed1b-107b-11e6-8690-f0def15edb3c',NULL,UserIdParam,0);
        
        else
        
        SET ContextBoardIdVar = (SELECT ContextBoardId FROM `pavankon_marrker`.`contextBoards` 
					WHERE ContextBoardName = ContextBoardNameParam);
        
    END IF;
    
    SELECT ContextBoardIdVar;
    
    IF NOT EXISTS(SELECT * FROM `pavankon_marrker`.`posts` WHERE WebURL = WebURLParam) THEN
		
			INSERT INTO `pavankon_marrker`.`posts`
					(`postid`,`posttypeid`,`post`,`vlocation`, `ilocation` ,`contextboardid`,`userid`, `WebURL`)
					VALUES(UUID(), '8d5b33a0-107b-11e6-8690-f0def15edb3c', PostParam, NULL, NULL, ContextBoardIdVar, UserIdParam, WebURLParam);
                    
			INSERT INTO `pavankon_marrker`.`posts`
					(`postid`,`posttypeid`,`post`,`vlocation`, `ilocation` ,`contextboardid`,`userid`, `WebURL`)
					VALUES(UUID(), '8d5b33a0-107b-11e6-8690-f0def15edb3c', PostParam, NULL, NULL, '7874959e-c38d-4bc9-adb5-d532901ba475', UserIdParam, WebURLParam);
					
		END IF;
    
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `contextboards`
--

DROP TABLE IF EXISTS `contextboards`;
CREATE TABLE IF NOT EXISTS `contextboards` (
  `contextboardid` varchar(215) NOT NULL,
  `contextboardname` mediumtext,
  `contextboarddescription` longtext,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `boardtypeid` varchar(1024) DEFAULT NULL,
  `inheritedBoardid` varchar(1024) DEFAULT NULL,
  `userId` varchar(1024) DEFAULT NULL,
  `isdefault` int(11) DEFAULT '0',
  `coverimagelocation` varchar(1021) DEFAULT NULL,
  PRIMARY KEY (`contextboardid`),
  KEY `USERID_CONTEXTBOARDS_idx` (`userId`(333)),
  KEY `BOARDTYPE_CTXBOARDS` (`boardtypeid`(333)),
  FULLTEXT KEY `FULL_TEXT` (`contextboardname`,`contextboarddescription`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contextboards`
--

INSERT INTO `contextboards` (`contextboardid`, `contextboardname`, `contextboarddescription`, `timestamp`, `boardtypeid`, `inheritedBoardid`, `userId`, `isdefault`, `coverimagelocation`) VALUES
('d2e7913f-42ab-4a78-afec-879e99499601', 'Pavan Ravikanth Kondamudi', 'Pavan Ravikanth''s personal board.', '2016-05-21 07:48:37', 'f7202e07-107b-11e6-8690-f0def15edb3c', NULL, 'c0be21cf-6b9b-499e-aafa-ff9327f0dab1', 1, NULL),
('a777fe55-c762-410b-82a0-1b63bb600244', 'J K', 'J''s personal board.', '2016-05-21 14:51:13', 'f7202e07-107b-11e6-8690-f0def15edb3c', NULL, 'd9c60f11-29b8-4ee0-9e93-1675f80b8361', 1, NULL),
('7874959e-c38d-4bc9-adb5-d532901ba475', 'Marrker WebBot', 'Marrker''s personal board.', '2016-07-01 09:30:12', 'f7202e07-107b-11e6-8690-f0def15edb3c', NULL, 'b854ae2f-fa4b-4af8-af29-c96a0b9058c8', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `contextboardtypes`
--

DROP TABLE IF EXISTS `contextboardtypes`;
CREATE TABLE IF NOT EXISTS `contextboardtypes` (
  `boardtypeid` varchar(215) NOT NULL,
  `boardtype` mediumtext,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`boardtypeid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contextboardtypes`
--

INSERT INTO `contextboardtypes` (`boardtypeid`, `boardtype`, `timestamp`) VALUES
('f7202e07-107b-11e6-8690-f0def15edb3c', 'Private', '2016-05-02 15:42:12'),
('f9d6d5a7-107b-11e6-8690-f0def15edb3c', 'Public', '2016-05-02 15:42:17'),
('fe47ed1b-107b-11e6-8690-f0def15edb3c', 'Protected', '2016-05-02 15:42:25');

-- --------------------------------------------------------

--
-- Table structure for table `followtypes`
--

DROP TABLE IF EXISTS `followtypes`;
CREATE TABLE IF NOT EXISTS `followtypes` (
  `followtypeId` varchar(215) NOT NULL,
  `followtype` mediumtext,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`followtypeId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `followtypes`
--

INSERT INTO `followtypes` (`followtypeId`, `followtype`, `timestamp`) VALUES
('1d2707eb-107c-11e6-8690-f0def15edb3c', 'Always_Inherit', '2016-05-02 17:18:06'),
('20c9c7fe-107c-11e6-8690-f0def15edb3c', 'Never_Inherit', '2016-05-02 17:18:06'),
('2694419c-107c-11e6-8690-f0def15edb3c', 'Give_me_an_alert', '2016-05-02 17:18:06');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE IF NOT EXISTS `posts` (
  `postid` varchar(215) NOT NULL,
  `posttypeid` varchar(1024) DEFAULT NULL,
  `post` longtext,
  `contextboardid` varchar(1024) DEFAULT NULL,
  `userid` varchar(1024) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ilocation` varchar(1024) DEFAULT NULL,
  `vlocation` varchar(1024) DEFAULT NULL,
  `WebURL` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`postid`),
  KEY `posts_user_idx` (`userid`(333)),
  KEY `posts_posttype_idx` (`posttypeid`(333)),
  KEY `posts_contextboards_idx` (`contextboardid`(333))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `posttype`
--

DROP TABLE IF EXISTS `posttype`;
CREATE TABLE IF NOT EXISTS `posttype` (
  `posttypeid` varchar(215) NOT NULL,
  `posttype` mediumtext,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`posttypeid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `posttype`
--

INSERT INTO `posttype` (`posttypeid`, `posttype`, `timestamp`) VALUES
('8d5b33a0-107b-11e6-8690-f0def15edb3c', 'Open', '2016-05-02 15:39:15'),
('93799616-107b-11e6-8690-f0def15edb3c', 'Closed', '2016-05-02 15:39:25');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userid` varchar(215) NOT NULL,
  `username` mediumtext,
  `password` mediumtext,
  `firstname` mediumtext,
  `lastname` mediumtext,
  `DOB` mediumtext,
  `email` varchar(1024) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `disables` int(11) DEFAULT '0',
  `JoinDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `loc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `EMAIL` (`email`(333))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `username`, `password`, `firstname`, `lastname`, `DOB`, `email`, `timestamp`, `disables`, `JoinDate`, `loc`) VALUES
('b854ae2f-fa4b-4af8-af29-c96a0b9058c8', 'MarrkerBot', 'd652ff4630971b1301c050364226e24a', 'Marrker', 'Bot', NULL, 'marrkerwebbot@marrker.com', '2016-07-05 05:54:45', 0, '2016-07-01 09:30:12', NULL);

--
-- Triggers `user`
--
DROP TRIGGER IF EXISTS `setUserJoinDate`;
DELIMITER //
CREATE TRIGGER `setUserJoinDate` BEFORE INSERT ON `user`
 FOR EACH ROW SET NEW.JoinDate = NOW()
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `user_board_xref`
--

DROP TABLE IF EXISTS `user_board_xref`;
CREATE TABLE IF NOT EXISTS `user_board_xref` (
  `UserBoardXrefid` varchar(215) NOT NULL,
  `UserId` varchar(1024) DEFAULT NULL,
  `contextboardid` varchar(1024) DEFAULT NULL,
  `followtypeid` varchar(1024) DEFAULT NULL,
  `lastvisit` timestamp NULL DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserBoardXrefid`),
  KEY `users_idx` (`UserId`(333))
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
