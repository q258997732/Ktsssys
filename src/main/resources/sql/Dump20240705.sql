-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 159.75.172.206    Database: ktssts
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ts_apiuser`
--

DROP TABLE IF EXISTS `ts_apiuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ts_apiuser` (
  `id` varchar(50) NOT NULL COMMENT '唯一标识',
  `user` varchar(45) NOT NULL COMMENT '接口账号',
  `pass` varchar(200) NOT NULL COMMENT '接口密码',
  `token` varchar(200) DEFAULT NULL COMMENT '授权信息',
  `expire` datetime DEFAULT NULL COMMENT '过期时间',
  `role` varchar(45) DEFAULT NULL COMMENT '角色',
  `permission` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_apiuser`
--

LOCK TABLES `ts_apiuser` WRITE;
/*!40000 ALTER TABLE `ts_apiuser` DISABLE KEYS */;
INSERT INTO `ts_apiuser` VALUES ('ce534155377011ef8cc6525400485ee1','admin','admin','','2024-07-01 14:11:50','admin','admin');
/*!40000 ALTER TABLE `ts_apiuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_executer`
--

DROP TABLE IF EXISTS `ts_executer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ts_executer` (
  `id` varchar(32) NOT NULL COMMENT '租户号;唯一标识',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人;更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间;更新时间',
  `exec_name` varchar(100) NOT NULL COMMENT '执行器名称;执行器名称',
  `exec_addr` varchar(100) NOT NULL COMMENT '执行器地址;执行器所在机器地址（通常为IP）',
  `exec_type` varchar(100) NOT NULL COMMENT '执行器类型;执行器类型',
  `exec_online` varchar(1) NOT NULL,
  `exec_available` varchar(1) NOT NULL COMMENT '执行器可用状态;执行器是否可用',
  `exec_monopoly` varchar(1) NOT NULL COMMENT '是否独占;当执行器为独占执行器时不参与任务分配',
  `exec_register` datetime DEFAULT NULL COMMENT '执行器注册时间;执行器注册进模块的时间',
  `exec_version` varchar(255) NOT NULL COMMENT '执行器版本信息;执行器版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ts_executer';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_executer`
--

LOCK TABLES `ts_executer` WRITE;
/*!40000 ALTER TABLE `ts_executer` DISABLE KEYS */;
INSERT INTO `ts_executer` VALUES ('00E89275ECC7461A89E749E52C395479','ktssts','2024-07-05 02:29:35','10_1_20_38','10.1.20.38','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('0AF6FB7A93934E50A673C5344AF53106','ktssts','2024-07-05 02:29:35','10_1_20_9','10.1.20.9','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('0D9F8277954B45FBB42A15C8AE01C6A4','ktssts','2024-07-05 02:29:35','10_1_20_18','10.1.20.18','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('0DD18109933F4D6CA9E7533626E27C80','ktssts','2024-07-05 02:29:35','10_1_20_4','10.1.20.4','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('11C2C00B681F469F899CA977FF308577','ktssts','2024-07-05 02:29:35','10_1_20_32','10.1.20.32','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('223021E083BC44EC8C12EE706251F027','ktssts','2024-07-05 02:29:35','10_1_20_2','10.1.20.2','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('264605E5CD1CEF11A9E3525400C42051','ktssts','2024-07-05 02:29:35','10_1_20_7','10.1.20.7','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('27B38F992508461682DDE34EDE7E4AB0','ktssts','2024-07-05 02:29:35','10_1_20_47','10.1.20.47','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('30EF14EDFF794608A23083DD69C5A0FF','ktssts','2024-07-05 02:29:35','10_1_20_42','10.1.20.42','K-RPA','0','1','0','2024-07-05 02:29:35','3.2024.02.22'),('3A17F85EAF654FC8882ED6C990EB04FD','ktssts','2024-07-05 02:29:35','10_1_20_43','10.1.20.43','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('40787E10A5664ECEBCC40F9FD913F168','ktssts','2024-07-05 02:29:35','10_1_20_34','10.1.20.34','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('4994DDF9D8D247E2A0DCEDF926D0D794','ktssts','2024-07-05 02:29:35','10_1_20_10','10.1.20.10','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('54CAB45FDE20474DBA23AD81B5669029','ktssts','2024-07-05 02:29:35','10_1_20_49','10.1.20.49','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('54DBD867269B41B7977DC4C7955E9346','ktssts','2024-07-05 02:29:35','10_1_20_20','10.1.20.20','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('58D20E90CE1CEF11A245525400C42051','ktssts','2024-07-05 02:29:35','10_1_20_37','10.1.20.37','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('6D64BE2DD6AE47709381A1C81ABDCB9E','ktssts','2024-07-05 02:29:35','10_1_20_39','10.1.20.39','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('7359B847C39846DF8034E168F9F2FCE8','ktssts','2024-07-05 02:29:35','LLH_COMPUTER','192.168.107.242','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('7367B18ED2A74F5D8ECFB54E6CA6F11E','ktssts','2024-07-05 02:29:35','CPCXB-240509-1','10.11.2.134','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('7805E210C31B4D28B2E27E08B8A66B48','ktssts','2024-07-05 02:29:35','10_1_20_12','10.1.20.12','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('78A14258DE03407A983CB72622EAAA9A','ktssts','2024-07-05 02:29:35','10_1_20_16','10.1.20.16','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('78B0124962DA450EAE3918293D6DB968','ktssts','2024-07-05 02:29:35','10_1_20_11','10.1.20.11','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('82DAC76D93734AB18A1AAFDDAFC4672E','ktssts','2024-07-05 02:29:35','10_1_20_17','10.1.20.17','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('876EBF09926F459C81078E6A9AA851D9','ktssts','2024-07-05 02:29:35','10_1_20_22','10.1.20.22','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('8BEA7194E3C949A5B7133DE11DB27B42','ktssts','2024-07-05 02:29:35','10_1_20_5','10.1.20.5','K-RPA','0','1','0','2024-07-05 02:29:35','3.2024.02.22'),('8C5E7FC4B34643B1BE1D083BD9115CAA','ktssts','2024-07-05 02:29:35','10_1_20_3','10.1.20.3','K-RPA','0','1','0','2024-07-05 02:29:35','3.2024.02.22'),('A851304B98D247FE930C9D265AFF9F45','ktssts','2024-07-05 02:29:35','10_1_20_2','10.1.20.8','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('B0E475DCFA9C499289553F418CE0713B','ktssts','2024-07-05 02:29:35','10_1_20_6','10.1.20.6','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('C3DF2C3213AE4AB8ADACE698389E9BE9','ktssts','2024-07-05 02:29:35','10_1_20_46','10.1.20.46','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('C5ABAEF9D82240A8B184B3CB83AB986C','ktssts','2024-07-05 02:29:35','10_1_20_15','10.1.20.15','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('CC5CAB070415445886DF0CCA7B08B6EF','ktssts','2024-07-05 02:29:35','HUANG-MATEBOOK','14.21.57.51','K-RPA','0','1','0','2024-07-05 02:29:35','3.2024.02.22'),('D855A5C3D82540119D37773E2E692708','ktssts','2024-07-05 02:29:35','10_1_20_14','10.1.20.14','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('E08150EF713C4FF1A20E010C2F902596','ktssts','2024-07-05 02:29:35','CPCXB-240509-3','10.11.2.137','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('E0AE205368844D81B3D7F4EBB06F2285','ktssts','2024-07-05 02:29:35','CPCXB-240509-2','10.11.2.136','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('F1747B65E38E4D1DBAB477E54B784EAE','ktssts','2024-07-05 02:29:35','10_1_20_31','10.1.20.31','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22'),('F1ABD57204444251A68780EBE45A1559','ktssts','2024-07-05 02:29:35','10_1_20_13','10.1.20.13','K-RPA','1','1','0','2024-07-05 02:29:35','3.2024.02.22');
/*!40000 ALTER TABLE `ts_executer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_executer_task`
--

DROP TABLE IF EXISTS `ts_executer_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ts_executer_task` (
  `id` varchar(32) NOT NULL COMMENT '租户号;唯一标识',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人;更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间;更新时间',
  `task_id` varchar(255) DEFAULT NULL COMMENT '任务ID;关联任务表ID',
  `exec_id` varchar(255) DEFAULT NULL COMMENT '执行器ID;关联执行器ID',
  `priority` int DEFAULT NULL COMMENT '优先级;数字越大优先级越高',
  `available` varchar(1) DEFAULT NULL COMMENT '可用状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ts_executer_task';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_executer_task`
--

LOCK TABLES `ts_executer_task` WRITE;
/*!40000 ALTER TABLE `ts_executer_task` DISABLE KEYS */;
INSERT INTO `ts_executer_task` VALUES ('01a459f0c8fa4aa7b5c1beab807aca48','ktssts','2024-07-05 02:29:38','05e6cca3cc7a41f682564d756faac06f','00E89275ECC7461A89E749E52C395479',9,'1'),('55053241307f41849a4ac2d6edeaea6e','ktssts','2024-07-05 02:29:38','38e3e3d330364167b214035b8b4bddd9','0AF6FB7A93934E50A673C5344AF53106',9,'1'),('5f9f3fd15c0f40f9a68bc74a5aed5bb8','ktssts','2024-07-05 02:29:38','c00cb6f0d57f4036bfa43770212e1372','223021E083BC44EC8C12EE706251F027',9,'1'),('646e0f1959974903aaa531ada746f9ca','ktssts','2024-07-05 02:29:38','c7c7dc4fbbbe418db7b6c9e1483946b9','264605E5CD1CEF11A9E3525400C42051',9,'1'),('89094bb7c9e34321b93329317b6e6f61','ktssts','2024-07-05 02:29:38','7e0accdbb7db4d9fb921f00eca82cc27','0DD18109933F4D6CA9E7533626E27C80',9,'1'),('8feadb1561b54e03bb7061b886f50ba6','ktssts','2024-07-05 02:29:38','f4b3a22de1454a48a25ea8607e27089a','27B38F992508461682DDE34EDE7E4AB0',9,'1'),('90de9c8a19d34475bab7832e64e8c93c','ktssts','2024-07-05 02:29:38','a29247d591af41868211199e2c300bb6','11C2C00B681F469F899CA977FF308577',9,'1'),('ebdaccccbb144b7498544fcfcac8f1ee','ktssts','2024-07-05 02:29:38','3c3fccc905e14f3eac37bf5f0c890eee','0D9F8277954B45FBB42A15C8AE01C6A4',9,'1');
/*!40000 ALTER TABLE `ts_executer_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ts_task`
--

DROP TABLE IF EXISTS `ts_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ts_task` (
  `id` varchar(32) NOT NULL COMMENT '租户号;唯一标识',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人;更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间;更新时间',
  `add_by` varchar(255) DEFAULT NULL COMMENT '新增人;新增人',
  `add_time` datetime DEFAULT NULL COMMENT '新增时间;新增时间',
  `task_name` varchar(255) NOT NULL COMMENT '任务名称;任务名称',
  `task_desc` varchar(255) NOT NULL COMMENT '任务描述;任务描述',
  `schedule_type` varchar(255) NOT NULL COMMENT '执行周期类型;执行周期类型',
  `schedule_conf` varchar(255) NOT NULL COMMENT '执行周期配置;执行周期配置',
  `exec_param` varchar(255) DEFAULT NULL COMMENT '执行时参数;执行时参数',
  `exec_timeout` int DEFAULT NULL COMMENT '执行超时时间;执行超时时间',
  `exec_retry` int DEFAULT NULL COMMENT '重试次数;重试次数',
  `exec_monopoly` varchar(255) DEFAULT NULL COMMENT '是否独占;任务是否独占一个执行器或执行器组',
  `exec_type` varchar(255) NOT NULL COMMENT '所需执行器类型;所需执行器类型',
  `exec_appoint` varchar(255) DEFAULT NULL COMMENT '指定执行器;任务指定的执行器，为空时随机指定',
  `clone` varchar(1) DEFAULT NULL COMMENT '是否主模板;false为具体任务，true为任务模板',
  `executer_id` varchar(45) DEFAULT NULL,
  `available` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='ts_task';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ts_task`
--

LOCK TABLES `ts_task` WRITE;
/*!40000 ALTER TABLE `ts_task` DISABLE KEYS */;
INSERT INTO `ts_task` VALUES ('05e6cca3cc7a41f682564d756faac06f','ktssts','2024-07-05 02:29:38','ktssts','2024-07-05 02:29:38','K-TSS 自动处理事件','K-TSS 自动处理事件:盛世腾辉3','CYCLE','10','113.140.71.252|6006|盛世腾辉3|RSTH1XCI96716|45b730fe344940e68997559d6881f6dc|46738887e2534e21a6100af6881cc8f6',1800,0,'0','K-RPA','','1',NULL,'1'),('38e3e3d330364167b214035b8b4bddd9','ktssts','2024-07-05 02:29:37','ktssts','2024-07-05 02:29:37','K-TSS 自动处理事件','K-TSS 自动处理事件:古韵旅游','CYCLE','10','113.140.71.252|6006|古韵旅游|84500080@XCI96716|45b730fe344940e68997559d6881f6dc|064357c296af47188475268f211ef9cf',1800,0,'0','K-RPA','','1',NULL,'1'),('3c3fccc905e14f3eac37bf5f0c890eee','ktssts','2024-07-05 02:29:37','ktssts','2024-07-05 02:29:37','K-TSS 自动处理事件','K-TSS 自动处理事件:光大外事','CYCLE','10','113.140.71.252|6006|光大外事|84500080@XCI96716|45b730fe344940e68997559d6881f6dc|c5844a79aa6b4b76b8548a27bfbdc85b',1800,0,'0','K-RPA','','1',NULL,'1'),('7e0accdbb7db4d9fb921f00eca82cc27','ktssts','2024-07-05 02:29:37','ktssts','2024-07-05 02:29:37','K-TSS 自动处理事件','K-TSS 自动处理事件:优博1','CYCLE','10','113.140.71.252|6006|优博1|YB1XCI96746|45b730fe344940e68997559d6881f6dc|098653a20e9d467082230cfd214a0be9',1800,0,'0','K-RPA','','1',NULL,'1'),('a29247d591af41868211199e2c300bb6','ktssts','2024-07-05 02:29:37','ktssts','2024-07-05 02:29:37','K-TSS 自动处理事件','K-TSS 自动处理事件:翔瑞运输1','CYCLE','10','113.140.71.252|6006|翔瑞运输1|XRYS1XCI96716|45b730fe344940e68997559d6881f6dc|f56a30d9878a42caa4f03edda7bac77a',1800,0,'0','K-RPA','','1',NULL,'1'),('c00cb6f0d57f4036bfa43770212e1372','ktssts','2024-07-05 02:29:38','ktssts','2024-07-05 02:29:38','K-TSS 自动处理事件','K-TSS 自动处理事件:通鸿源旅游','CYCLE','10','113.140.71.252|6006|通鸿源旅游|THYXCI967161|45b730fe344940e68997559d6881f6dc|119fc6375ae24cb7b496f0a59315b0f5',1800,0,'0','K-RPA','','1',NULL,'1'),('c7c7dc4fbbbe418db7b6c9e1483946b9','ktssts','2024-07-05 02:29:38','ktssts','2024-07-05 02:29:38','K-TSS 自动处理事件','K-TSS 自动处理事件:阳光假期1','CYCLE','10','113.140.71.252|6006|阳光假期1|YGJQXCI967163|45b730fe344940e68997559d6881f6dc|0fbae8eab3404f8fa029282dfd758ea0',1800,0,'0','K-RPA','','1',NULL,'1'),('f4b3a22de1454a48a25ea8607e27089a','ktssts','2024-07-05 02:29:37','ktssts','2024-07-05 02:29:37','K-TSS 自动处理事件','K-TSS 自动处理事件:宇诺旅游1','CYCLE','10','113.140.71.252|6006|宇诺旅游1|YNLY1XCI96716|45b730fe344940e68997559d6881f6dc|cf7d1a5bde2d40cdb431172e03c0a595',1800,0,'0','K-RPA','','1',NULL,'1');
/*!40000 ALTER TABLE `ts_task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-05 14:41:41
