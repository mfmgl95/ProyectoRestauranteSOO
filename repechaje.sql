-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: repechaje
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carta`
--

DROP TABLE IF EXISTS `carta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carta` (
  `idCarta` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCarta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta`
--

LOCK TABLES `carta` WRITE;
/*!40000 ALTER TABLE `carta` DISABLE KEYS */;
INSERT INTO `carta` VALUES (1,'2017-08-28');
/*!40000 ALTER TABLE `carta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `razonSocial` varchar(45) DEFAULT NULL,
  `DNI` varchar(45) DEFAULT NULL,
  `RUC` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprobante`
--

DROP TABLE IF EXISTS `comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comprobante` (
  `idComprobante` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `monto` varchar(45) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `Pedido` int(11) NOT NULL,
  PRIMARY KEY (`idComprobante`),
  KEY `fk_Comprobante_Pedido1_idx` (`Pedido`),
  CONSTRAINT `fk_Comprobante_Pedido1` FOREIGN KEY (`Pedido`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprobante`
--

LOCK TABLES `comprobante` WRITE;
/*!40000 ALTER TABLE `comprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle`
--

DROP TABLE IF EXISTS `detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle` (
  `Item_idItem` int(11) NOT NULL AUTO_INCREMENT,
  `Pedido_idPedido` int(11) NOT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Item_idItem`,`Pedido_idPedido`),
  KEY `fk_Item_has_Pedido_Pedido1_idx` (`Pedido_idPedido`),
  KEY `fk_Item_has_Pedido_Item1_idx` (`Item_idItem`),
  CONSTRAINT `fk_Item_has_Pedido_Item1` FOREIGN KEY (`Item_idItem`) REFERENCES `item` (`idItem`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_Pedido_Pedido1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle`
--

LOCK TABLES `detalle` WRITE;
/*!40000 ALTER TABLE `detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallecarta`
--

DROP TABLE IF EXISTS `detallecarta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallecarta` (
  `idItem` int(11) NOT NULL AUTO_INCREMENT,
  `idCarta` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idItem`,`idCarta`),
  KEY `fk_Item_has_Carta_Carta1_idx` (`idCarta`),
  KEY `fk_Item_has_Carta_Item1_idx` (`idItem`),
  CONSTRAINT `fk_Item_has_Carta_Carta1` FOREIGN KEY (`idCarta`) REFERENCES `carta` (`idCarta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_Carta_Item1` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallecarta`
--

LOCK TABLES `detallecarta` WRITE;
/*!40000 ALTER TABLE `detallecarta` DISABLE KEYS */;
INSERT INTO `detallecarta` VALUES (100,1,25),(102,1,25),(103,1,25),(104,1,25),(105,1,25),(107,1,25),(108,1,20),(109,1,20),(110,1,50),(111,1,30),(112,1,30),(113,1,15),(114,1,15),(115,1,15);
/*!40000 ALTER TABLE `detallecarta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `idItem` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `tipo` varchar(2) DEFAULT NULL COMMENT '1. Entrada\n2. Menu\n3. Bebida\n4. Extra\n5. Adicional',
  `precio` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`idItem`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (100,'Cau cau','Md',6.00),(101,'Cabrito','Md',6.00),(102,'Lomo saltado','Md',6.00),(103,'Pepian de pava','Md',6.00),(104,'Papa a la huancaina','En',0.00),(105,'Sopa de fideos','En',0.00),(106,'Ensalada rusa','En',0.00),(107,'Ensalada normal','En',0.00),(108,'Ceviche normal','Ex',10.00),(109,'Ceviche mixto','Ex',12.00),(110,'Te','Be',2.00),(111,'Gaseosa personal','Be',1.00),(112,'Agua mineral','Be',2.00),(113,'Arroz','Ad',3.00),(114,'Porcion de Ensalada','Ad',3.00),(115,'Huevo frito','Ad',1.50);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesa` (
  `idMesa` int(11) NOT NULL AUTO_INCREMENT,
  `estado` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0. disponible\n1. ocupada\n2. reservada',
  PRIMARY KEY (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
INSERT INTO `mesa` VALUES (1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0),(8,0),(9,0),(10,0),(11,0),(12,0);
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idPedido` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `estado` tinyint(4) NOT NULL DEFAULT '0',
  `idCliente` int(11) DEFAULT NULL,
  `idMesa` int(11) NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `fk_Pedido_Cliente_idx` (`idCliente`),
  KEY `fk_Pedido_Mesa_idx` (`idMesa`),
  CONSTRAINT `fk_Pedido_Cliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Mesa` FOREIGN KEY (`idMesa`) REFERENCES `mesa` (`idMesa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta`
--

DROP TABLE IF EXISTS `receta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receta` (
  `idReceta` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` mediumtext,
  `ingredientes` mediumtext,
  `preparacion` mediumtext,
  `idItem` int(11) NOT NULL,
  PRIMARY KEY (`idReceta`),
  KEY `fk_Receta_Item1_idx` (`idItem`),
  CONSTRAINT `fk_Receta_Item1` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` VALUES (1,'Cau cau','3/4 de kg de mondongo;\r\n2 cucharadas de margarina Sello de Oro;\r\n2 cucharadas de leche;\r\n2 ramas de hierbabuena;\r\nAceite (cantidad necesaria);\r\n1 cebolla picada;\r\n1 cucharadita de ajos;\r\n3 cucharadas de ají amarillo molido;\r\n1/4 de cdta. de comino;\r\n1 cdta. de palillo;\r\n1 kg de papa blanca cocida en cuadraditos;\r\nSal y pimienta al gusto;\r\nJugo de 1 limón;','Cocina el mondongo con agua que lo cubra y las ramas de hierbabuena hasta que esté suave. Córtalo en cuadritos. \nAparte, calienta el aceite con la margarina Sello de Oro y sofríe los ajos, la cebolla, el ají amarillo molido y el palillo. Cuando esté bien cocido, añade el mondongo, las papas y un poco del líquido en el que se cocinó el mondongo. \nDeja que dé un hervor y espolvorea la hierbabuena picada. Si se deseas, rocía jugo de limón. Sírvelo con arroz graneadito.',100),(2,'Cabrito','2 kg cabrito en trozos;\r\nchicha de jora;\r\nají molido amarillo;\r\n4 ají en tiras amarillo;\r\n1 cda comino molido;\r\n1/2 cda pimienta;\r\n40 gr ajos picados sal;\r\n1 taza aceite;\r\n1 atado culantro picado;\r\nzapallo loche rallado;\r\nsalsa criolla;\r\n2 cebolla;\r\njugo de un limón;\r\najinomoto culantro picado;\r\nsal y pimienta;\r\nPara servir;\r\nyuca cocida;\r\narroz blanco desgranado;\r\nfrijoles;','Poner a macerar el cabrito en la víspera, en la chicha de jora (de no más de dos semanas de maceración), ajos picados, el ají molido, comino, salpimentar.\r\nA la mañana siguiente se pica el ají en tiras, retirar las venas y semillas, reservar. Rehogar en una sartén los ajos, el ají molido y el ají picado, dorar muy poco los trozos de cabrito y verter el liquido de la maceración, dejar que se haga hasta que este suave; incorporar el culantro piadito, loche rallado y dejar en el fuego unos minutos más.\r\nServir con yuca hervida, frijoles, arroz blanco la salsa criolla: cebolla picada, limón, ajinomoto, culantro picado y salpimentar.',101),(3,'Lomo saltado','600 gr. de lomo de res;\r\n1 cebolla roja cortada en gajos;\r\n2 tomates cortados en gajos;\r\n1 cucharada pequeña de pimienta negra molida;\r\n5 tallos de cebolla china (cebollín) en corte sesgado;\r\n1 ají amarillo cortado en tiras sin venas;\r\nAceite vegetal;\r\n800 gr. de arroz blanco cocinado;\r\n400 gr. de papas (patatas) fritas en bastones, de preferencia papas amarillas;\r\n200 ml. de caldo de carne;\r\n300 ml. de sillao (salsa de soja);\r\nUn poco de hojas de culantro (cilantro) picado;\r\n50 ml. de vinagre;\r\n1 cucharada de ajo molido en pasta;\r\n1/4 de cucharada de orégano seco molido;\r\n1/4 de cucharada de comino;\r\nSal al gusto;','Corta el lomo en trozos (bastones) más o menos gruesos (3cm x 1cm) y condiméntalos con ajo molido, comino, orégano y una cuchara de vinagre. Déjalo reposar unos 10 minutos para que coja sabor. Calienta la sartén a fuego alto con un poco de aceite\r\nCuando veas que empieza a salir un poquito de humo, entonces agrega la carne y fríela por unos segundos, el tiempo dependerá de que tan caliente este la sartén. A continuación resérvala en un bol y tápala para que conserve el calor\r\nAhora en la misma sartén agrega la cebolla picada, el tomate, el ají amarillo y un poquito de aceite. Fríe todo por unos segundos y agrega un poco de caldo de carne, salsa de soja (sillao) y el vinagre. Agrega la carne que habías reservado, mezcla todo con movimientos de sartén (sin utensilio para no maltratar el tomate) y después de unos 30 segundos más o menos agrega la cebolla china y finalmente un poco de culantro picado (cilantro)\r\nEl Lomo Saltado tiene que estar jugoso, por eso, si es necesario corrige agregando un poco de caldo de carne\r\nSirve en un plato casi la totalidad del lomo y deja un poco para mezclar con las papas fritas. Agrega las papas fritas al resto de lomo saltado en la sartén, mézclalo y vuelve a servir todo encima.\r\nEn el Perú nos encanta el arroz, así que este plato también va acompañado de arroz blanco',102),(4,'Pepian de pava','1 pavita;\r\n1 poro, cortado en trozos, para cocinar la pava;\r\n1 cebolla roja, pelada, entera, para cocinar la pava;\r\n1 zanahoria, pelada y cortada en trozos;\r\nGranos de pimienta, para cocinar la pava;\r\n1 k (2.4 lb) de arroz;\r\n5 tazas de caldo por cada taza de arroz;\r\n3/4 de taza de ají colorado molido, panca;\r\n3/4 de taza de ají amarillo molido;\r\n3 cebollas rojas, finamente picadas;\r\n2 cucharaditas de ajos molidos;\r\n1/2 taza de aceite, aproximadamente;\r\n1/2 cucharadita de ajo, finamente picado (para el aderezo de ají);\r\n2 cucharadas de ají colorado panca (para el aderezo de ají);\r\nSal;\r\nPimienta y comino;','Colocar la pava en una olla con suficiente cantidad de agua, el poro, el apio, la cebolla, zanahoria y los granos de pimienta. Cocinar hasta que esté tierna. Una vez cocida, retirar del caldo y dejar enfriar. Desmenuzar en trozos grandes. Colar el caldo y reservar.\r\n\r\nLavar el arroz y dejarlo secar, revolviendo de vez en cuando. Moler el arroz con un rodillo. Debe quedar partido, no molido. Aparte, en una olla grande calentar el aceite y agregar la cebolla picada, ajo, ajíes, sal, pimienta y comino. Dejar que el aderezo se cocine bien, a fuego lento.\r\n\r\nAgregar el caldo que se reservó de la pava y llevarlo a hervir. Una vez que comienza a hervir agregar el arroz y dejar cocinar a fuego lento. Esto último se debe hacer 15 a 20 minutos antes de servir. Asimiemo, el arroz no debe tener una consistencia espesa. Debe ser como un aguadito. También, se debe tener cuidado que no se seque demasiado.\r\n\r\nAparte hacer un aderezo de ají con aceite, el ajo y 2 cucharadas de ají panca y sal. Para servir, colocar los trozos de pavo en el plato, cubrir con el arroz y colocar una cucharadita de aderezo de ají encima.\r\n\r\nSe puede servir en fuente, colocando los trozos de pavo en una fuente y el arroz en otra. También se puede acompañar con salsa de cebolla.',103),(5,'Papa a la huancaina','6 papas (patatas) blancas medianas o si tienes suerte y puedes conseguir 12 papas amarillas peruanas;\r\n300 gramos de queso fresco (salado);\r\n1/2 taza de leche evaporada;\r\n3 ajíes amarillos;\r\n2 cucharadas de aceite de girasol u otro similar pero no de oliva;\r\n3 huevos duros cortados en mitades;\r\n6 aceitunas negras;\r\n6 hojas de lechuga;\r\n2 paquetes (12 galletas) de galletas de soda saladas o similar;\r\n3 cucharadas de perejil picado, sal y pimienta al gusto;','Pon en una olla la cantidad de agua suficiente para cubrir las papas, echa un poco de sal y hierve las papas hasta que se cuezan sin pasarse. Puedes ir verificando de rato en rato con un palito o tenedor, pero cuidado para no destruirlas. El tiempo dependerá del tipo de papa que utilices\r\nLuego de hervir las papas, retíralas y pélalas cuando todavía estén calientes pero con mucho cuidado para no quemarte\r\nMientras se enfrían las papas, prepara la crema. Pon en una licuadora (de preferencia) las galletas, el queso, los ajíes, la leche, un poco de sal, aceite y pimienta y licúa.\r\nPara servir, decora un plato con una base de lechuga, a continuación una papa cortada en rodajas, cúbrelas con la crema y decora con el huevo, el perejil picado y la aceituna negra',104),(6,'Sopa de fideos','170-190 gr fideos (puedes elegir entre los fideos tradicionales, los fideos orientales, la pasta fresca al huevo o incluso los fideos de arroz);\r\n2 1/2 litros agua;\r\n3 zahanorias;\r\n1 cebolla;\r\n2 patatas medianas;\r\n1 puerro;\r\n1 ramita de apio;\r\n1 nabo;\r\nEspecias: sal, pimienta, perejil verde\r\n1 cubito caldo de verdura;\r\n1-2 cucharas aceite de oliva;','Pelamos las zanahorias, la cebolla, las patatas y el nabo y los cortamos en trozos grandes. Los ponemos a hervir junto a los demás ingredientes –menos los fideos y las especias– a fuego medio durante unos 20 minutos.\r\nEscurrimos el caldo en otra cazuela y añadimos los fideos. Sazonamos a gusto con sal, pimienta, perejil y el cubito de caldo de verdura.\r\nLo dejamos a hervir entre 5 y 10 minutos más –dependiendo del tipo de fideos que hemos empleado–.\r\nPara una presentación más apetecible cortaremos unas rodajas de zanahoria hervida y las añadiremos a nuestra sopa. También dejaremos caer unas gotas de aceite de oliva para darle un aspecto muy bonito.',105),(7,'Ensalada rusa','2 beterragas (remolachas);\r\n2 papas (patatas) grandes;\r\n2 zanahorias grandes;\r\n1 taza de arvejas (guisantes);\r\n3 huevos duros;\r\n200 gr de vainitas (judías verdes);\r\nMayonesa;\r\nHojas de lechuga para decorar;\r\nPerejil;\r\nSal y pimienta;','Primero hierve las papas (patatas), las betarragas y las zanahorias, todas con sin pelar. Las zanahorias normalmente necesitan más tiempo\r\nCuando estén hervidas retira las verduras, déjalas enfriar y las pélalas\r\nMientras tanto pon en otra olla las vainitas y los guisantes y hiérvelos sin pasarte de hervor\r\nEn otra olla hierve los huevos\r\nCuando tengas todo hervido, pica las papas, las beterragas y las zanahorias en dados\r\nAgrega las vainitas y los guisantes\r\nAñade mayonesa, sal y pimienta al gusto. Remuévelo todo\r\nPara servir pon una hoja de lechuga y en el centro un poco de ensalada',106),(8,'Ensalada normal','Tomates para ensalada;\r\nLechuga rizadita murciana o lechuga valenciana;\r\nCebolla tierna;\r\n1 buen aceite de oliva virgen extra;\r\nLimón o vinagres;\r\nsal;\r\npimienta (opcional);\r\n1 lata atún pequeña (opcional);\r\n1 poco de maíz opcional);','Lavar bien todo. Y lávelo a conciencia. Muchas veces en las hortalizas se mezclan sabores químicos que nos confunden los sabores originales. Seque la lechuga y todo lo demás.\r\nTrocear la lechuga, el tomate, la cebolla. Cada uno tiene sus manías. Pero el secreto está en que los trozos que combina de tomate y cebolla, o la cebolla, la lechuga y el tomate sean proporcionados y consigan una mezcla equilibrada para su paladar de tal forma que pueda deleitarle. Trocear bien es un arte. Haga esto antes de servir a la mesa. No prepare nunca con antelación una ensalada. Ni tan siquiera media hora.\r\nAliñar la ensalada. Los franceses dicen que hay que ser \"un avaro para el vinagre, un generoso para el aceite de oliva, un moderado para la sal y un loco para darle vueltas\". El orden: la sal, luego el vinagre y, por último el aceite.',107),(9,'Ceviche normal','1 kilo de pescado fresco y de preferencia que no sea grasoso;\r\n1 cebolla roja cortada en juliana;\r\n1 rocoto;\r\n10 limones;\r\n1 camote grande o 2 medianos hervidos (batata/moniato);\r\n1 lechuga;\r\n1 atado de Culantro picado (cilantro);\r\n1 choclo hervido (maíz cocido);\r\nSal y pimienta al gusto;','pescado, de su textura y de lo grasoso que sea. Es preferible como ya he comentado, elegir un pescado fresco de carne blanca y con cuerpo (fuerte).\r\nEn un bol de cristal o de metal frota el ají limón, esto es para que le de el aroma propio de este ají. Luego puedes agregar pequeños trozos de ají limo al gusto, pero con cuidado porque es muy picante.\r\nAgrega el pescado cortado al bol y exprime los limones sobre el pescado directamente. El jugo (zumo) debe casi cubrir el pescado. Añade sal, pimienta al gusto, media cucharita de ajo molido, cebolla y un poco de culantro picado.\r\nAgrega uno o dos cubos de hielo para enfriar la preparación por 3 minutos.\r\nDeja reposar unos minutos, esto depende del gusto de cada persona. También hay pescados que absorben más rápido el limón y no necesitan demasiado tiempo. Repito, todo depende del gusto de cada persona.\r\nPara servir, decoramos el plato con una hoja de lechuga, unos trozos de camote y colocamos el Ceviche en el centro acompañado de choclo.',108),(10,'Ceviche mixto','1 taza de pulpo cocido, en cuadritos;\r\n1 taza de camarón chico, limpios y sin cáscara;\r\n1 taza de filete de sierra cortado en cubitos;\r\n1/2 pieza de cebolla morada picada finamente;\r\n3 piezas de chile guajillo picado finamente;\r\n1 taza de grano de elote cocido;\r\n1 taza de jugo de limón;\r\n1/2 taza de aceite de oliva;\r\n1 cucharadita de sal;\r\n1 cucharadita de pimienta;\r\n1 pieza de camote cocido y en cuadritos;\r\n1 cucharada de cilantro fresco picado finamente;\r\n1 pieza de limón cortado en rodajas, para decorar;','En un bowl grande, mezcla el pulpo, los camarones, el filete de pescado, la cebolla morada, el chile guajillo y el elote.\r\nAgrega el jugo de limón, el aceite de oliva, la sal y la pimienta y mezcla muy bien.\r\nTapa y deja marinar en el refrigerador 45 minutos.\r\nAgrega el camote y el cilantro.\r\nSirve y decora con rodajas de limón.',109);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'repechaje'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-28 21:34:00
