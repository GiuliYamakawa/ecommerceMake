package br.univel.br.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;

@Entity
public class Usuario implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(nullable = false)
   private String nome;

   @Column
   private int idade;

   @Column(nullable = false)
   private String cpf;

   @Column(nullable = false)
   private String endereco;

   @Column(nullable = false)
   private String cep;

   @Column(nullable = false)
   private String cidade;

   @Column(nullable = false)
   private String estado;

   @Column(nullable = false)
   private String email;

   @Column(nullable = false)
   private String numero;

   @Column
   private String telefone;

   @Column(nullable = false)
   private String login;

   @Column(nullable = false)
   private String senha;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Usuario))
      {
         return false;
      }
      Usuario other = (Usuario) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public int getIdade()
   {
      return idade;
   }

   public void setIdade(int idade)
   {
      this.idade = idade;
   }

   public String getCpf()
   {
      return cpf;
   }

   public void setCpf(String cpf)
   {
      this.cpf = cpf;
   }

   public String getEndereco()
   {
      return endereco;
   }

   public void setEndereco(String endereco)
   {
      this.endereco = endereco;
   }

   public String getCep()
   {
      return cep;
   }

   public void setCep(String cep)
   {
      this.cep = cep;
   }

   public String getCidade()
   {
      return cidade;
   }

   public void setCidade(String cidade)
   {
      this.cidade = cidade;
   }

   public String getEstado()
   {
      return estado;
   }

   public void setEstado(String estado)
   {
      this.estado = estado;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getNumero()
   {
      return numero;
   }

   public void setNumero(String numero)
   {
      this.numero = numero;
   }

   public String getTelefone()
   {
      return telefone;
   }

   public void setTelefone(String telefone)
   {
      this.telefone = telefone;
   }

   public String getLogin()
   {
      return login;
   }

   public void setLogin(String login)
   {
      this.login = login;
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (nome != null && !nome.trim().isEmpty())
         result += "nome: " + nome;
      result += ", idade: " + idade;
      if (cpf != null && !cpf.trim().isEmpty())
         result += ", cpf: " + cpf;
      if (endereco != null && !endereco.trim().isEmpty())
         result += ", endereco: " + endereco;
      if (cep != null && !cep.trim().isEmpty())
         result += ", cep: " + cep;
      if (cidade != null && !cidade.trim().isEmpty())
         result += ", cidade: " + cidade;
      if (estado != null && !estado.trim().isEmpty())
         result += ", estado: " + estado;
      if (email != null && !email.trim().isEmpty())
         result += ", email: " + email;
      if (numero != null && !numero.trim().isEmpty())
         result += ", numero: " + numero;
      if (telefone != null && !telefone.trim().isEmpty())
         result += ", telefone: " + telefone;
      if (login != null && !login.trim().isEmpty())
         result += ", login: " + login;
      if (senha != null && !senha.trim().isEmpty())
         result += ", senha: " + senha;
      return result;
   }
}