-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 09/04/2026 às 03:39
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `academyspring`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `aluno`
--

CREATE TABLE `aluno` (
  `id` int(11) NOT NULL,
  `curso` varchar(100) NOT NULL,
  `matricula` varchar(100) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `status` varchar(100) NOT NULL,
  `turno` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `notaEnade` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `aluno`
--

INSERT INTO `aluno` (`id`, `curso`, `matricula`, `nome`, `status`, `turno`, `email`, `notaEnade`) VALUES
(1, 'ADMINISTRACAO', 'ACA2026001', 'Arthur Nobre', 'ATIVO', 'MATUTINO', 'arthur.nobre@email.com', 8.5),
(2, 'BIOMEDICINA', 'ACA2026002', 'Beatriz Silva', 'ATIVO', 'NOTURNO', 'biasilva@email.com', 7.2),
(3, 'DIREITO', 'ACA2026003', 'Carlos Eduardo', 'ATIVO', 'NOTURNO', 'cadu@email.com', 6),
(4, 'ENFERMAGEM', 'ACA2026004', 'Daniela Oliveira', 'ATIVO', 'MATUTINO', 'dani.oliva@email.com', 9.1),
(6, 'ADMINISTRACAO', 'ACA2026006', 'Fernanda Lima', 'ATIVO', 'NOTURNO', 'fer.lima@email.com', 7.5),
(7, 'BIOMEDICINA', 'ACA2026007', 'Gabriel Costa', 'INATIVO', 'MATUTINO', 'gabriel.costa@email.com', 4.2),
(8, 'DIREITO', 'ACA2026008', 'Helena Martins', 'ATIVO', 'NOTURNO', 'helena.m@email.com', 8.9),
(9, 'ENFERMAGEM', 'ACA2026009', 'Igor Ferreira', 'ATIVO', 'MATUTINO', 'igor.f@email.com', 7),
(11, 'ADMINISTRACAO', 'ACA2026011', 'Kevin Santos', 'ATIVO', 'NOTURNO', 'kevin.s@email.com', 6),
(12, 'BIOMEDICINA', 'ACA2026012', 'Larissa Rocha', 'INATIVO', 'MATUTINO', 'larissa.r@email.com', 3.5),
(13, 'DIREITO', 'ACA2026013', 'Murilo Benício', 'ATIVO', 'NOTURNO', 'murilo.b@email.com', 8.2),
(14, 'ENFERMAGEM', 'ACA2026014', 'Natália Guimarães', 'ATIVO', 'NOTURNO', 'naty.gui@email.com', 7.8),
(16, 'BIOMEDICINA', 'ACA2026433', 'Caça Rato', 'ATIVO', 'MATUTINO', 'caca@rato.com', 10),
(17, 'CONTABILIDADE', 'ACA2026978', 'Chico Moedas', 'ATIVO', 'MATUTINO', 'chico@moedas.com', 9);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `user` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `senha`, `user`) VALUES
(4, 'toronto@toronto.com', 'e10adc3949ba59abbe56e057f20f883e', 'Toronto'),
(5, 'teste@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Miro');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
