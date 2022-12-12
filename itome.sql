/*
SQLyog Ultimate v11.11 (32 bit)
MySQL - 5.5.5-10.4.22-MariaDB : Database - itome
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`itome` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `itome`;

/*Table structure for table `compras` */

DROP TABLE IF EXISTS `compras`;

CREATE TABLE `compras` (
  `idcompras` int(11) NOT NULL AUTO_INCREMENT,
  `valor` int(11) NOT NULL,
  `fecha_Compra` date NOT NULL DEFAULT current_timestamp(),
  `proveedor_id` int(11) NOT NULL,
  `formpago_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idcompras`),
  UNIQUE KEY `UK_ac5t77lftf1x5s4g0e1jtbbt3` (`cantidad`),
  KEY `fk_compras_proveedor1_idx` (`proveedor_id`),
  KEY `fk_compras_formpago1_idx` (`formpago_id`),
  CONSTRAINT `fk_compras_formpago1` FOREIGN KEY (`formpago_id`) REFERENCES `formpago` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_compras_proveedor1` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `compras` */

LOCK TABLES `compras` WRITE;

insert  into `compras`(`idcompras`,`valor`,`fecha_Compra`,`proveedor_id`,`formpago_id`,`cantidad`) values (1,5215001,'2022-02-06',3,1,853),(2,521500121,'2022-02-06',4,1,8541000);

UNLOCK TABLES;

/*Table structure for table `formpago` */

DROP TABLE IF EXISTS `formpago`;

CREATE TABLE `formpago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `formpago` */

LOCK TABLES `formpago` WRITE;

insert  into `formpago`(`id`,`nombre`) values (1,'Efectivo'),(2,'Tarje de credito');

UNLOCK TABLES;

/*Table structure for table `marca` */

DROP TABLE IF EXISTS `marca`;

CREATE TABLE `marca` (
  `idmarca` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idmarca`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `marca` */

LOCK TABLES `marca` WRITE;

insert  into `marca`(`idmarca`,`nombre`) values (3,'addidas'),(4,'Jimmy Choo');

UNLOCK TABLES;

/*Table structure for table `pedido` */

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `idpedidos` int(11) NOT NULL AUTO_INCREMENT,
  `total` varchar(45) NOT NULL,
  `formpago_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idpedidos`),
  UNIQUE KEY `UK_git832bh6ubwxlyuvvy2puack` (`total`),
  UNIQUE KEY `UK_sgodwrjfa4la0v78eicvdssga` (`cantidad`),
  KEY `fk_pedido_formpago1_idx` (`formpago_id`),
  KEY `fk_pedido_usuario1_idx` (`usuario_id`),
  CONSTRAINT `fk_pedido_formpago1` FOREIGN KEY (`formpago_id`) REFERENCES `formpago` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `pedido` */

LOCK TABLES `pedido` WRITE;

insert  into `pedido`(`idpedidos`,`total`,`formpago_id`,`usuario_id`,`cantidad`) values (4,'52101',1,24,8454),(6,'23',1,25,84),(7,'126500',1,26,25);

UNLOCK TABLES;

/*Table structure for table `pedido_has_producto` */

DROP TABLE IF EXISTS `pedido_has_producto`;

CREATE TABLE `pedido_has_producto` (
  `pedido_idpedidos` int(11) NOT NULL,
  `producto_idproducto` int(11) NOT NULL,
  PRIMARY KEY (`pedido_idpedidos`,`producto_idproducto`),
  KEY `fk_pedido_has_producto_producto1_idx` (`producto_idproducto`),
  KEY `fk_pedido_has_producto_pedido1_idx` (`pedido_idpedidos`),
  CONSTRAINT `fk_pedido_has_producto_pedido1` FOREIGN KEY (`pedido_idpedidos`) REFERENCES `pedido` (`idpedidos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_has_producto_producto1` FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `pedido_has_producto` */

LOCK TABLES `pedido_has_producto` WRITE;

insert  into `pedido_has_producto`(`pedido_idpedidos`,`producto_idproducto`) values (4,2),(4,3),(6,3),(7,4);

UNLOCK TABLES;

/*Table structure for table `personas` */

DROP TABLE IF EXISTS `personas`;

CREATE TABLE `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `telefono` int(11) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `create_at` date NOT NULL DEFAULT current_timestamp(),
  `update_at` date NOT NULL,
  `Tipo_doc` varchar(45) NOT NULL,
  `num_doc` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

/*Data for the table `personas` */

LOCK TABLES `personas` WRITE;

insert  into `personas`(`id`,`nombres`,`apellidos`,`telefono`,`correo`,`direccion`,`create_at`,`update_at`,`Tipo_doc`,`num_doc`) values (28,'jhon','ruiz',300680,'admi','calle 75a sur #18','2022-08-07','2022-08-06','C.C',8763122),(29,'jhon','ruiz',2147483647,'jhonfrc156@gmail.com','calle 75a sur #18','2022-09-01','2022-09-01','cc',454335),(30,'David','herrera',3006803,'jhonfrc156@gmail.com','calle 75a sur #18','2022-09-01','2022-09-01','cc',8763122),(31,'carlos','andres',31324,'carl@gmail.com','calle 75a sur #18','2022-08-06','2022-08-06','C.C',12004552),(32,'carlos','andres',31324,'carl@gmail.com','calle 75a sur #18','2022-08-06','2022-08-06','C.C',12004552),(33,'San','ramirez',45112354,'santiagoR@gmail.com','calle 7','2022-08-07','2022-08-06','C.E',78512);

UNLOCK TABLES;

/*Table structure for table `producto` */

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `codigo` varchar(45) NOT NULL,
  `precio` int(11) NOT NULL,
  `sock` int(11) NOT NULL,
  `marca_idmarca` int(11) NOT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `fk_producto_marca1_idx` (`marca_idmarca`),
  CONSTRAINT `fk_producto_marca1` FOREIGN KEY (`marca_idmarca`) REFERENCES `marca` (`idmarca`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `producto` */

LOCK TABLES `producto` WRITE;

insert  into `producto`(`idproducto`,`nombre`,`codigo`,`precio`,`sock`,`marca_idmarca`) values (2,'chanclas','sdf12',524201,4564,3),(3,'Tenis','sdf12',15230,121,3),(4,'Zapatillas','sdf12',5412,1,4);

UNLOCK TABLES;

/*Table structure for table `producto_has_compras` */

DROP TABLE IF EXISTS `producto_has_compras`;

CREATE TABLE `producto_has_compras` (
  `producto_idproducto` int(11) NOT NULL,
  `compras_idcompras` int(11) NOT NULL,
  PRIMARY KEY (`producto_idproducto`,`compras_idcompras`),
  KEY `fk_producto_has_compras_compras1_idx` (`compras_idcompras`),
  KEY `fk_producto_has_compras_producto1_idx` (`producto_idproducto`),
  CONSTRAINT `fk_producto_has_compras_compras1` FOREIGN KEY (`compras_idcompras`) REFERENCES `compras` (`idcompras`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_compras_producto1` FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `producto_has_compras` */

LOCK TABLES `producto_has_compras` WRITE;

insert  into `producto_has_compras`(`producto_idproducto`,`compras_idcompras`) values (2,1),(3,2);

UNLOCK TABLES;

/*Table structure for table `proveedor` */

DROP TABLE IF EXISTS `proveedor`;

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personas_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_proveedor_personas_idx` (`personas_id`),
  CONSTRAINT `fk_proveedor_personas` FOREIGN KEY (`personas_id`) REFERENCES `personas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `proveedor` */

LOCK TABLES `proveedor` WRITE;

insert  into `proveedor`(`id`,`personas_id`) values (3,28),(4,31),(5,33);

UNLOCK TABLES;

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `nomre` varchar(45) NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `rol` */

LOCK TABLES `rol` WRITE;

insert  into `rol`(`idrol`,`nomre`) values (10,'Empleado'),(11,'Administrador');

UNLOCK TABLES;

/*Table structure for table `rol_has_usuario` */

DROP TABLE IF EXISTS `rol_has_usuario`;

CREATE TABLE `rol_has_usuario` (
  `rol_idrol` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`rol_idrol`,`usuario_id`),
  KEY `fk_rol_has_usuario_usuario1_idx` (`usuario_id`),
  KEY `fk_rol_has_usuario_rol1_idx` (`rol_idrol`),
  CONSTRAINT `fk_rol_has_usuario_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rol_has_usuario_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `rol_has_usuario` */

LOCK TABLES `rol_has_usuario` WRITE;

insert  into `rol_has_usuario`(`rol_idrol`,`usuario_id`) values (10,24),(10,25),(10,27),(11,26),(11,27);

UNLOCK TABLES;

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombreusuario` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `personas_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pxmmy0wtdljr5w1i7ncrjjby2` (`password`),
  UNIQUE KEY `UK_puhr3k3l7bj71hb7hk7ktpxn0` (`nombreusuario`),
  KEY `FK2n5fafv1octx5wgcynl75t3kx` (`personas_id`),
  CONSTRAINT `FK2n5fafv1octx5wgcynl75t3kx` FOREIGN KEY (`personas_id`) REFERENCES `personas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

/*Data for the table `usuario` */

LOCK TABLES `usuario` WRITE;

insert  into `usuario`(`id`,`nombreusuario`,`password`,`personas_id`) values (24,'santiago12','123456789',33),(25,'david45','452',30),(26,'andres07','45234',31),(27,'jhonfrc12','jhon117',28);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
