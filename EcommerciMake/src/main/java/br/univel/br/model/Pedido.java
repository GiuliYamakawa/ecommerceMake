package br.univel.br.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import br.univel.br.model.ProdutoPedido;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements BaseEntity, Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
   private Set<ProdutoPedido> produtosPedido = new HashSet<ProdutoPedido>();

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
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Pedido))
      {
         return false;
      }
      Pedido other = (Pedido) obj;
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

   public Set<ProdutoPedido> getProdutosPedido()
   {
      return this.produtosPedido;
   }

   public void setProdutosPedido(final Set<ProdutoPedido> produtosPedido)
   {
      this.produtosPedido = produtosPedido;
   }

}