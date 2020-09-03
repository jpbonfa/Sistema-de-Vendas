package br.com.jpbonfa.vendas.util;

/**
 * Classe responsavel por armazenar as mensagens do sistema
 *
 * @author joaop
 * @since 04/08/2000
 */
public class Mensagem {

    //Mensagens correspondente ao tratamento de Login
    public static String informeLogin = "Informe um login válido";
    public static String informeSenha = "Informe uma senha válida";
    public static String erroLogin = "Login e senha invalidos!";
    //Mensagens correspondete ao tratamento 
    public static String erro = "Erro";
    public static String erroListarCidades = "Erro ao listar cidades";
    public static String erroListarEstados = "Erro ao listar estados";

    public static String erroListarContatos = "Erro ao listar contatos";
    public static String erroExcluirContatos = "Erro ao excluir contatos";
    public static String erroSalvarContatos = "Erro ao salvar contatos";
    public static String selecione = "Selecione";
    public static String sucesso = "Sucesso";

    public static String erroListarEndereco = "Erro ao listar endereço";
    public static String erroExcluirEndereco = "Erro ao excluir endereço";
    public static String erroSalvarEndereco = "Erro ao salvar endereço";

    public static String erroListarItemVenda = "Erro ao listar item venda";
    public static String erroExcluirItemVenda = "Erro ao excluir item venda";
    public static String erroSalvarItemVenda = "Erro ao salvar item venda";

    public static String erroListarItemCompra = "Erro ao listar item compra";
    public static String erroExcluirItemCompra = "Erro ao excluir item compra";
    public static String erroSalvarItemCompra = "Erro ao salvar item compra";

    public static String erroListarLogUsuario = "Erro ao listar log usuário";
    public static String erroExcluirLogUsuario = "Erro ao excluir log usuário";
    public static String erroSalvarLogUsuario = "Erro ao salvar log usuário";

    public static String erroListarPf = "Erro ao listar pessoa fisíca";
    public static String erroExcluirPf = "Erro ao excluir pessoa fisíca";
    public static String erroSalvarPf = "Erro ao salvar pessoa fisíca";

    public static String erroListarPj = "Erro ao listar pessoa juridica";
    public static String erroExcluirPj = "Erro ao excluir pessoa juridica";
    public static String erroSalvarPj = "Erro ao salvar pessoa juridica";

    //Mensagens correspondete ao tratamento de Data
    public static String informeData = "Infome uma data,campo obrigatório";
    public static String DataInvalida = "Infome uma data válida!";
    //Mensagens correspondente aos tratamentos da tela de compra á prazo
    public static String informeVencido = "Selecione uma opção para vencido, campo obrigatório";
    public static String informePago = "Selecione uma opção para pago, campo obrigatório";
    //Mensagens correspondente ao tratamento da tela  de Cadastro de Cliente
    public static String informePessoa = "Selecione uma opção para 'Tipo de pessoa', campo obrigatório";
    public static String informeCpf = "Infome um CPF, campo obrigatório";
    public static String informeCpfValido = "Infome um CPF válido, campo obrigatório";
    public static String informeRg = "Infome um RG, campo obrigatório";
    public static String informeNome = "Infome um nome, campo obrigatório";
    public static String informeNomeValido = "Infome um nome válido, campo obrigatório";
    public static String selecioneCliente = "É necessário selecionar um Cliente";
    public static String desejaExcluirCliente = "Deseja mesmo excluir o cliente?";
    public static String clienteExcluido = "Cliente excluído com sucesso";
    public static String clienteSalvo = "Cliente salvo com sucesso";
    public static String clienteAlterado = "Cliente alterado com sucesso";
    public static String clienteErroExcluir = "Erro ao excluir cliente";
    //Mensagens correspondente ao tratamento da tela  de Cadastro de Fornecedor
    public static String informeCnpj = "Infome um CNPJ, campo obrigatório";
    public static String informeIe = "Infome um IE, campo obrigatório";
    public static String informeRazao = "Infome a razão social, campo obrigatório";
    public static String informeDataFundacao = "Infome uma data de fundação, campo obrigatório";
    public static String DataFundacaoInvalida = "Infome uma data de fundação válida!";
    public static String informeLogradouro = "Infome um logradouro, campo obrigatório";
    public static String informeNumero = "Infome um número, campo obrigatório";
    public static String informeBairro = "Infome o bairro, campo obrigatório";
    public static String informeCep = "Infome o CEP, campo obrigatório";
    public static String informeCidade = "Infome a cidade, campo obrigatório";
    public static String informeProduto = "Infome o produto, campo obrigatório";
    public static String informeEstoque = "Infome a quantidade de estoque, campo obrigatório";
    public static String informeQuantidadeMinima = "Infome a quantidade minima, campo obrigatório";
    public static String informeEstado = "Infome o estado, campo obrigatório";
    public static String informeTelefone = "Infome o telefone, campo obrigatório";
    public static String informeCelular = "Infome o celular, campo obrigatório";
    public static String informeEmail = "Infome o e-mail, campo obrigatório";
    public static String informeEmailValido = "Infome o e-mail válido, campo obrigatório";
    public static String informeContato = "Infome o contato, campo obrigatório";
    public static String informeContatoValido = "Infome um contato válido, campo obrigatório";
    public static String selecioneFornecedor = "É necessário selecionar um fornecedor";

    public static String desejaExcluirFornecedor = "Deseja mesmo excluir o fornecedor?";
    public static String fornecedorExcluido = "Fornecedor excluído com sucesso";
    public static String fornecedorSalvo = "Fornecedor salvo com sucesso";
    public static String fornecedorAlterado = "Fornecedor alterado com sucesso";
    public static String fornecedorErroExcluir = "Erro ao excluir fornecedor";
    public static String fornecedorErroSalvar = "Erro ao salvar fornecedor";

    public static String selecioneFuncionario = "É necessário selecionar um funcionário";
    public static String desejaExcluirFuncionario = "Deseja mesmo excluir o funcionário?";
    public static String funcionarioExcluido = "Funcionário excluído com sucesso";
    public static String funcionarioSalvo = "Funcionário salvo com sucesso";
    public static String funcionarioAlterado = "Funcionário alterado com sucesso";
    public static String funcionarioErroExcluir = "Erro ao excluir funcionário";
    //Mensagens correspondente ao tratamento da tela  de Cadastro de Produtos
    public static String informeDescriçao = "Infome a descrição, campo obrigatório";
    public static String informeDesconto = "Infome o desconto, campo obrigatório";
    public static String informeValorCompra = "Infome o valor de compra, campo obrigatório";
    public static String informeValorVenda = "Infome o valor de venda, campo obrigatório";
    public static String informeFornecedor = "Infome o fornecedor, campo obrigatório";
    public static String selecioneProduto = "É necessário selecionar um produto";
    public static String InformeQuantidade = "É necessário informar uma quantidade";
    public static String desejaExcluirProduto = "Deseja mesmo excluir o produto?";
    public static String produtoExcluido = "Produto excluído com sucesso";
    public static String produtoSalvo = "Produto salvo com sucesso";
    public static String produtoAlterado = "Produto alterado com sucesso";
    public static String produtoErroExcluir = "Erro ao excluir produto";
    public static String produtoErroSalvar = "Erro ao salvar produto";
    //Mensagens correspondente ao tratamento de Estoque
    public static String desejaExcluirEstoque = "Deseja mesmo excluir o estoque?";
    public static String estoqueExcluido = "Estoque excluído com sucesso";
    public static String estoqueSalvo = "Estoque salvo com sucesso";
    public static String estoqueAlterado = "Estoque alterado com sucesso";
    public static String estoqueErroExcluir = "Erro ao excluir estoque";
    public static String selecioneEstoque = "É necessário selecionar um estoque";
    public static String selecioneFormaDePagamento = "É necessário selecionar uma forma de pagamento";
    public static String vendaSalva = "Venda salva com sucesso!";
    public static String compraSalva = "Compra salva com sucesso!";
    
    
    
    
     public static String desejaEncerrar =  "Deseja encerrar o sistema?";
     public static String atencao =  "Atenção";
}
