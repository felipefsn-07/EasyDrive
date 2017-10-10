-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 10-Out-2017 às 15:20
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
  `dataAula` date NOT NULL,
  `horarioAula` time NOT NULL,
  `codVeiculo` int(11) NOT NULL,
  `numCarteira` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `automovel`
--

CREATE TABLE `automovel` (
  `placa` varchar(8) NOT NULL,
  `ano` year(4) NOT NULL,
  `modelo` varchar(25) NOT NULL,
  `capacidade` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  `codVeiculo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `codCliente` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `tel` varchar(14) NOT NULL,
  `cel` varchar(14) NOT NULL,
  `dataNasc` date NOT NULL,
  `rg` varchar(12) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `numLadv` varchar(25) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `categoria` char(1) NOT NULL,
  `codEndereco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `clienteaula`
--

CREATE TABLE `clienteaula` (
  `codAula` int(11) NOT NULL,
  `codCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `num` char(6) NOT NULL,
  `cidade` varchar(20) NOT NULL,
  `estado` char(2) NOT NULL,
  `logradouro` varchar(30) NOT NULL,
  `bairro` varchar(20) NOT NULL,
  `cep` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `exame`
--

CREATE TABLE `exame` (
  `codExame` int(11) NOT NULL,
  `dataExame` date NOT NULL,
  `horaExame` time NOT NULL,
  `codVeiculo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `codFunc` int(11) NOT NULL,
  `codLogin` int(11) NOT NULL,
  `codEndereco` int(11) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `dataNasc` date NOT NULL,
  `tel` varchar(14) NOT NULL,
  `cel` varchar(14) NOT NULL,
  `horaEntra` datetime NOT NULL,
  `horaSai` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `senha` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD KEY `Fk_numCarteira` (`numCarteira`),
  ADD KEY `Fk_codVeiculo` (`codVeiculo`);

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
  ADD KEY `Fk_codEndereco2` (`codEndereco`);

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
  ADD KEY `Fk_codVeiculo2` (`codVeiculo`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`codFunc`),
  ADD KEY `FK_codLogin` (`codLogin`),
  ADD KEY `Fk_codEndereco` (`codEndereco`);

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
  ADD PRIMARY KEY (`codLogin`);

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
  MODIFY `codAula` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `automovel`
--
ALTER TABLE `automovel`
  MODIFY `codVeiculo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `codCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `codEndereco` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `exame`
--
ALTER TABLE `exame`
  MODIFY `codExame` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `funcionario`
--
ALTER TABLE `funcionario`
  MODIFY `codFunc` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `codLogin` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `aula`
--
ALTER TABLE `aula`
  ADD CONSTRAINT `Fk_codVeiculo` FOREIGN KEY (`codVeiculo`) REFERENCES `automovel` (`codVeiculo`),
  ADD CONSTRAINT `Fk_numCarteira` FOREIGN KEY (`numCarteira`) REFERENCES `instrutor` (`numCarteira`);

--
-- Limitadores para a tabela `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `Fk_codEndereco2` FOREIGN KEY (`codEndereco`) REFERENCES `endereco` (`codEndereco`);

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
  ADD CONSTRAINT `Fk_codVeiculo2` FOREIGN KEY (`codVeiculo`) REFERENCES `automovel` (`codVeiculo`);

--
-- Limitadores para a tabela `funcionario`
--
ALTER TABLE `funcionario`
  ADD CONSTRAINT `Fk_codEndereco` FOREIGN KEY (`codEndereco`) REFERENCES `endereco` (`codEndereco`),
  ADD CONSTRAINT `FK_codLogin` FOREIGN KEY (`codLogin`) REFERENCES `login` (`codLogin`);

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
-- Limitadores para a tabela `recepcionista`
--
ALTER TABLE `recepcionista`
  ADD CONSTRAINT `FK_codFunc3` FOREIGN KEY (`codFunc`) REFERENCES `funcionario` (`codFunc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
