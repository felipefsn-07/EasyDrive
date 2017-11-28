-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 28-Nov-2017 às 08:32
-- Versão do servidor: 5.5.54
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_autoescola`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `aula`
--

CREATE TABLE `aula` (
  `codAula` int(11) NOT NULL,
  `dataAula` varchar(20) NOT NULL,
  `horaInicio` varchar(20) NOT NULL,
  `horaFim` varchar(20) NOT NULL,
  `codVeiculo` int(11) DEFAULT NULL,
  `codInstrutor` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `aula`
--

INSERT INTO `aula` (`codAula`, `dataAula`, `horaInicio`, `horaFim`, `codVeiculo`, `codInstrutor`) VALUES
(3, '30/11/2017', '11:00', '12:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `automovel`
--

CREATE TABLE `automovel` (
  `placa` varchar(15) NOT NULL,
  `ano` varchar(10) NOT NULL,
  `modelo` varchar(25) NOT NULL,
  `capacidade` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `codVeiculo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `automovel`
--

INSERT INTO `automovel` (`placa`, `ano`, `modelo`, `capacidade`, `status`, `codVeiculo`) VALUES
('111-1111', '1111', '1111', 1, 1, 1),
('222-2222', '2222', '2', 1, 1, 2),
('333-3333', '3333', '3333', 333331, 1, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `codCliente` int(11) NOT NULL,
  `codEndereco` int(11) DEFAULT NULL,
  `nome` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `cel` varchar(50) NOT NULL,
  `dataNasc` varchar(40) NOT NULL,
  `rg` varchar(50) NOT NULL,
  `cpf` varchar(50) NOT NULL,
  `numLadv` varchar(25) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `categoria` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`codCliente`, `codEndereco`, `nome`, `tel`, `cel`, `dataNasc`, `rg`, `cpf`, `numLadv`, `status`, `categoria`) VALUES
(2, 11, '00000000', '(00) 0000-0000', '(00) 00000-0000', '00/00/0000', '00.000.000-0', '000.000.000-00', '00000000000', 1, 'A'),
(3, 12, 'Lucca', '(22) 2222-2222', '(22) 22222-2222', '22/22/2222', '22.222.222-2', '222.222.222-22', '22222222222', 0, 'A'),
(4, 13, 'FELIPE', '(33) 3333-3333', '(33) 33333-3333', '33/33/3333', '33.333.333-3', '333.333.333-33', '33333333333', 1, 'A'),
(5, NULL, '55555555', '(55) 5555-5555', '(55) 55555-5555', '55/55/5555', '55.555.555-5', '555.555.555-55', '55555555555', 1, 'A'),
(6, NULL, '666666', '(66) 6666-6666', '(66) 66666-6666', '66/66/6666', '66.666.666-6', '666.666.666-66', '66666666666', 1, 'A'),
(7, 15, '7777777', '(77) 7777-7777', '(77) 77777-7777', '77/77/7777', '77.777.777-7', '777.777.777-77', '77777777777', 1, 'A');

-- --------------------------------------------------------

--
-- Estrutura da tabela `clienteaula`
--

CREATE TABLE `clienteaula` (
  `codAula` int(11) NOT NULL,
  `codCliente` int(11) NOT NULL,
  `presenca` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clienteaula`
--

INSERT INTO `clienteaula` (`codAula`, `codCliente`, `presenca`) VALUES
(3, 4, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `clienteexame`
--

CREATE TABLE `clienteexame` (
  `codExame` int(11) NOT NULL,
  `codCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `codEndereco` int(11) NOT NULL,
  `num` varchar(10) NOT NULL,
  `cidade` varchar(20) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `logradouro` varchar(30) NOT NULL,
  `bairro` varchar(20) NOT NULL,
  `cep` varchar(20) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `endereco`
--

INSERT INTO `endereco` (`codEndereco`, `num`, `cidade`, `estado`, `logradouro`, `bairro`, `cep`, `status`) VALUES
(6, '0000000', '00000000', '000000', '0000000', '0000000000', '00000-000', 1),
(7, '0000', '0000', '0000', '000000', '000000', '00000-000', 1),
(8, '000', '0000', '0000', '0000', '000', '00000-000', 1),
(9, '000', '000000', '000', '000', '0000000', '00000-000', 1),
(10, '000000', '000000', '000000', '00000000', '0000000', '00000-000', 1),
(11, '1111111111', '111111', '11111111', '11111111111111111111111', '11111111', '11111-111', 1),
(12, '2222222', '222222222222', '22222222', '222222222', '222222222', '22222-222', 1),
(13, '3333333', '333333333', '33333333', '33333333', '33333333', '33333-333', 1),
(14, '44', '444444444', '4444', 'felipe', '44444', '44444-444', 1),
(15, '777', '77777', '7777', '777777', '7777', '77777-777', 1),
(16, '88', '8888', '888', '8888', '888', '88888-888', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `exame`
--

CREATE TABLE `exame` (
  `codExame` int(11) NOT NULL,
  `dataExame` varchar(20) NOT NULL,
  `horaInicio` varchar(20) NOT NULL,
  `horaFim` varchar(20) NOT NULL,
  `codVeiculo` int(11) NOT NULL,
  `codInstrutor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `codFunc` int(11) NOT NULL,
  `codEndereco` int(11) DEFAULT NULL,
  `rg` varchar(12) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `dataNasc` varchar(20) NOT NULL,
  `tel` varchar(14) NOT NULL,
  `cel` varchar(30) NOT NULL,
  `horaEntra` varchar(15) NOT NULL,
  `horaSai` varchar(15) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `tipo` varchar(10) NOT NULL,
  `carteira` varchar(20) DEFAULT NULL,
  `categoria` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionario`
--

INSERT INTO `funcionario` (`codFunc`, `codEndereco`, `rg`, `nome`, `cpf`, `dataNasc`, `tel`, `cel`, `horaEntra`, `horaSai`, `status`, `tipo`, `carteira`, `categoria`) VALUES
(10, 9, '00.000.000-0', '00000', '000.000.000-00', '30/11/0002', '(00) 0000-0000', '(00) 00000-0000', '00:00', '01:00', 0, 'Gerente', 'null', 'null'),
(11, NULL, '11.111.111-1', 'Lucca', '111.111.111-11', '11/11/1111', '(11) 1111-1111', '(11) 11111-1111', '11:11', '12:12', 1, 'Instrutor', '12312313   ', 'A'),
(12, 14, '44.444.444-4', 'Lucca', '444.444.444-44', '13/09/44447', '(44) 4444-4444', '(44) 44444-4444', '20:44', '21:00', 1, 'Gerente', 'null', 'null'),
(13, 16, '88.888.888-8', 'Instrutor', '888.888.888-88', '08/04/8895', '(88) 8888-8888', '(88) 88888-8888', '17:28', '18:00', 1, 'Instrutor', '546778     ', 'A');

-- --------------------------------------------------------

--
-- Estrutura da tabela `gerente`
--

CREATE TABLE `gerente` (
  `codFunc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `instrutor`
--

CREATE TABLE `instrutor` (
  `codFunc` int(11) NOT NULL,
  `numCarteira` varchar(12) NOT NULL,
  `categoria` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `instrutorexame`
--

CREATE TABLE `instrutorexame` (
  `numCarteira` varchar(12) NOT NULL,
  `codExame` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `login`
--

CREATE TABLE `login` (
  `codLogin` int(11) NOT NULL,
  `login` varchar(25) NOT NULL,
  `senha` varchar(25) NOT NULL,
  `status` int(11) NOT NULL,
  `codFunc` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `login`
--

INSERT INTO `login` (`codLogin`, `login`, `senha`, `status`, `codFunc`) VALUES
(4, 'admin', 'admin', 1, NULL),
(12, '000000000', '0tk3eCcg', 0, 10),
(13, '111111111', '7CEdM33D', 0, 11),
(14, '444444444', '0uFggRyL', 0, 12),
(15, '888888888', 'kVvUJQti', 0, 13);

-- --------------------------------------------------------

--
-- Estrutura da tabela `recepcionista`
--

CREATE TABLE `recepcionista` (
  `codFunc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aula`
--
ALTER TABLE `aula`
  ADD PRIMARY KEY (`codAula`),
  ADD KEY `Fk_codVeiculo` (`codVeiculo`),
  ADD KEY `Fk_codInstrutor` (`codInstrutor`);

--
-- Indexes for table `automovel`
--
ALTER TABLE `automovel`
  ADD PRIMARY KEY (`codVeiculo`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codCliente`),
  ADD KEY `Fk_endereco2` (`codEndereco`);

--
-- Indexes for table `clienteaula`
--
ALTER TABLE `clienteaula`
  ADD PRIMARY KEY (`codAula`,`codCliente`),
  ADD KEY `Fk_codCliente` (`codCliente`);

--
-- Indexes for table `clienteexame`
--
ALTER TABLE `clienteexame`
  ADD PRIMARY KEY (`codExame`,`codCliente`),
  ADD KEY `Fk_codCliente2` (`codCliente`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`codEndereco`);

--
-- Indexes for table `exame`
--
ALTER TABLE `exame`
  ADD PRIMARY KEY (`codExame`),
  ADD KEY `Fk_codVeiculo2` (`codVeiculo`),
  ADD KEY `Fk_codInstrutor2` (`codInstrutor`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`codFunc`),
  ADD KEY `Fk_endereco` (`codEndereco`);

--
-- Indexes for table `gerente`
--
ALTER TABLE `gerente`
  ADD KEY `FK_codFunc` (`codFunc`);

--
-- Indexes for table `instrutor`
--
ALTER TABLE `instrutor`
  ADD PRIMARY KEY (`numCarteira`),
  ADD KEY `FK_codFunc2` (`codFunc`);

--
-- Indexes for table `instrutorexame`
--
ALTER TABLE `instrutorexame`
  ADD PRIMARY KEY (`numCarteira`,`codExame`),
  ADD KEY `Fk_codExame2` (`codExame`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`codLogin`),
  ADD KEY `Fk_codFuncionario` (`codFunc`);

--
-- Indexes for table `recepcionista`
--
ALTER TABLE `recepcionista`
  ADD KEY `FK_codFunc3` (`codFunc`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aula`
--
ALTER TABLE `aula`
  MODIFY `codAula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `automovel`
--
ALTER TABLE `automovel`
  MODIFY `codVeiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `codEndereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `exame`
--
ALTER TABLE `exame`
  MODIFY `codExame` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `codFunc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `codLogin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `aula`
--
ALTER TABLE `aula`
  ADD CONSTRAINT `Fk_codInstrutor` FOREIGN KEY (`codInstrutor`) REFERENCES `funcionario` (`codFunc`),
  ADD CONSTRAINT `Fk_codVeiculo` FOREIGN KEY (`codVeiculo`) REFERENCES `automovel` (`codVeiculo`);

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `Fk_endereco2` FOREIGN KEY (`codEndereco`) REFERENCES `endereco` (`codEndereco`);

--
-- Limitadores para a tabela `clienteaula`
--
ALTER TABLE `clienteaula`
  ADD CONSTRAINT `Fk_codAula` FOREIGN KEY (`codAula`) REFERENCES `aula` (`codAula`),
  ADD CONSTRAINT `Fk_codCliente` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`);

--
-- Limitadores para a tabela `clienteexame`
--
ALTER TABLE `clienteexame`
  ADD CONSTRAINT `Fk_codCliente2` FOREIGN KEY (`codCliente`) REFERENCES `cliente` (`codCliente`),
  ADD CONSTRAINT `FK_codExame` FOREIGN KEY (`codExame`) REFERENCES `exame` (`codExame`);

--
-- Limitadores para a tabela `exame`
--
ALTER TABLE `exame`
  ADD CONSTRAINT `Fk_codInstrutor2` FOREIGN KEY (`codInstrutor`) REFERENCES `funcionario` (`codFunc`),
  ADD CONSTRAINT `Fk_codVeiculo2` FOREIGN KEY (`codVeiculo`) REFERENCES `automovel` (`codVeiculo`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `Fk_endereco` FOREIGN KEY (`codEndereco`) REFERENCES `endereco` (`codEndereco`);

--
-- Limitadores para a tabela `gerente`
--
ALTER TABLE `gerente`
  ADD CONSTRAINT `FK_codFunc` FOREIGN KEY (`codFunc`) REFERENCES `funcionario` (`codFunc`);

--
-- Limitadores para a tabela `instrutor`
--
ALTER TABLE `instrutor`
  ADD CONSTRAINT `FK_codFunc2` FOREIGN KEY (`codFunc`) REFERENCES `funcionario` (`codFunc`);

--
-- Limitadores para a tabela `instrutorexame`
--
ALTER TABLE `instrutorexame`
  ADD CONSTRAINT `Fk_codExame2` FOREIGN KEY (`codExame`) REFERENCES `exame` (`codExame`),
  ADD CONSTRAINT `Fk_numCarteira2` FOREIGN KEY (`numCarteira`) REFERENCES `instrutor` (`numCarteira`);

--
-- Limitadores para a tabela `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `Fk_codFuncionario` FOREIGN KEY (`codFunc`) REFERENCES `funcionario` (`codFunc`);

--
-- Limitadores para a tabela `recepcionista`
--
ALTER TABLE `recepcionista`
  ADD CONSTRAINT `FK_codFunc3` FOREIGN KEY (`codFunc`) REFERENCES `funcionario` (`codFunc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
