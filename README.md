# Equipe:
   Felipe Silva do Nascimento - 171204 <br />
   Kaique Chiovetto Siqueira - 177455 <br />
   Lucca Di Bastiani do Amaral - 182787 <br />

# Objetivos:

   Permitir de maneira simples que o usuário possa dispor funcionalidades para 
   gerenciar a auto escola (cadastrar alunos, cadastrar funcionários, marcar aulas, etc), 
   de maneira a tornar a gestão das auto escolas mais práticas e dinâmicas.
  
# Requisitos essenciais:
   * Funcionários e instrutores: o sistema deverá permitir o cadastro, consulta, alteração e 
        exclusão de funcionários e instrutores da auto escola.
       
   * Alunos: o sistema deverá permitir o cadastro, consulta, alteração e exclusão de alunos da
        auto escola.

   * Veículos: o sistema deverá permitir o cadastro, consulta, alteração e exclusão de veículos 
        da auto escola.

   * Aulas: o sistema deverá permitir o agendamento de aulas, bem como o possível cancelamento 
        ou alteração destas.

   * Exames: o sistema deverá permitir o agendamento de exames, bem como o possível cancelamento 
        ou alteração da data do exame.
 
 # Classes 
   * Funcionário
     
     Atributos:
         Nome
         Rg
         CPF
         Data_Nasc
         Telefone
         Celular
         Endereço
         Horário_Entrada
         Horário Saída 
         Código
     
     Métodos:
         Criar
         Ler
         Atualizar
   
   * Aluno
     
     Atributos:
         Nome
         Rg
         CPF
         Data_Nasc
         Telefone
         Celular
         Endereço
         Num_LADV
         Categoria_Pretendida
         Status
      
      Métodos:
         Criar
         Ler
         Atualizar
   
   * Veículos      
      
      Atributos:
          Modelo
          Ano
          Placa
          Capacidade
          Status
       
       Métodos:
         Criar
         Ler
         Atualizar
    
    * Aulas
       
       Atributos:
            Aluno
            Instrutor
            Veículo
            Data
            Horário
        
        Métodos:
            Criar
            Ler
            Cancelar
     
     * Exame
        
        Atributos:
            Aluno
            Instrutor
            Veículo
            Data
            Horário
        
        Métodos:
            Criar
            Ler
            Atualizar

