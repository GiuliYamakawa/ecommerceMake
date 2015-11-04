package br.univel.br.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Set;
import java.util.HashSet;
import br.univel.br.model.Pedido;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

@Entity
public class ProdutoPedido implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String descricao;

   @Column
   private String nome;

   @Column
   private String preco;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Pedido pedido;

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
      if (!(obj instanceof ProdutoPedido))
      {
         return false;
      }
      ProdutoPedido other = (ProdutoPedido) obj;
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

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getPreco()
   {
      return preco;
   }

   public void setPreco(String preco)
   {
      this.preco = preco;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (descricao != null && !descricao.trim().isEmpty())
         result += "descricao: " + descricao;
      if (nome != null && !nome.trim().isEmpty())
         result += ", nome: " + nome;
      if (preco != null && !preco.trim().isEmpty())
         result += ", preco: " + preco;
      return result;
   }

   public Pedido getPedido()
   {
      return this.pedido;
   }

   public void setPedido(final Pedido pedido)
   {
      this.pedido = pedido;
   }

}