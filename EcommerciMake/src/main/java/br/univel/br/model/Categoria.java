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
import br.univel.br.model.Produto;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Categoria implements BaseEntity, Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
   private Set<Produto> produtos = new HashSet<Produto>();

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
      if (!(obj instanceof Categoria))
      {
         return false;
      }
      Categoria other = (Categoria) obj;
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

   public Set<Produto> getProdutos()
   {
      return this.produtos;
   }

   public void setProdutos(final Set<Produto> produtos)
   {
      this.produtos = produtos;
   }
}