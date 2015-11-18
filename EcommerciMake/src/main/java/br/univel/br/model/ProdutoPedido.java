package br.univel.br.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProdutoPedido implements BaseEntity, Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String preco;

   @Column
   private Long quantidade;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Pedido pedido;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Produto produto;

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

   public Produto getProduto()
   {
      return produto;
   }

   public void setProduto(Produto produto)
   {
      this.produto = produto;
   }

   public Long getQuantidade()
   {
      return quantidade;
   }

   public void setQuantidade(Long quantidade)
   {
      this.quantidade = quantidade;
   }

}