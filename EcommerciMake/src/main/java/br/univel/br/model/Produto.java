package br.univel.br.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import br.univel.br.model.Categoria;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Produto implements BaseEntity, Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Categoria categoria;

   @Column
   private double peso;

   @Column
   private String descricao;

   @Column
   private double preco;

   @Column
   private String cor;

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
      if (!(obj instanceof Produto))
      {
         return false;
      }
      Produto other = (Produto) obj;
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

   public Categoria getCategoria()
   {
      return this.categoria;
   }

   public void setCategoria(final Categoria categoria)
   {
      this.categoria = categoria;
   }

   public double getPeso()
   {
      return peso;
   }

   public void setPeso(double peso)
   {
      this.peso = peso;
   }

   public double getPreco()
   {
      return preco;
   }

   public void setPreco(double preco)
   {
      this.preco = preco;
   }

   public String getCor()
   {
      return cor;
   }

   public void setCor(String cor)
   {
      this.cor = cor;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      result += "peso: " + peso;
      result += ", preco: " + preco;
      if (cor != null && !cor.trim().isEmpty())
         result += ", cor: " + cor;
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
}