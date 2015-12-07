package br.univel.br.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.univel.br.model.ProdutoPedido;
import br.univel.br.model.Pedido;
import br.univel.br.model.Produto;

/**
 * Backing bean for ProdutoPedido entities.
 * <p>
 * This class provides CRUD functionality for all ProdutoPedido entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class ProdutoPedidoBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving ProdutoPedido entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private ProdutoPedido produtoPedido;

   public ProdutoPedido getProdutoPedido()
   {
      return this.produtoPedido;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
      }

      if (this.id == null)
      {
         this.produtoPedido = this.example;
      }
      else
      {
         this.produtoPedido = findById(getId());
      }
   }

   public ProdutoPedido findById(Long id)
   {

      return this.entityManager.find(ProdutoPedido.class, id);
   }

   /*
    * Support updating and deleting ProdutoPedido entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.produtoPedido);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.produtoPedido);
            return "view?faces-redirect=true&id=" + this.produtoPedido.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         ProdutoPedido deletableEntity = findById(getId());
         Pedido pedido = deletableEntity.getPedido();
         pedido.getProdutosPedido().remove(deletableEntity);
         deletableEntity.setPedido(null);
         this.entityManager.merge(pedido);
         this.entityManager.remove(deletableEntity);
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching ProdutoPedido entities with pagination
    */

   private int page;
   private long count;
   private List<ProdutoPedido> pageItems;

   private ProdutoPedido example = new ProdutoPedido();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public ProdutoPedido getExample()
   {
      return this.example;
   }

   public void setExample(ProdutoPedido example)
   {
      this.example = example;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<ProdutoPedido> root = countCriteria.from(ProdutoPedido.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<ProdutoPedido> criteria = builder.createQuery(ProdutoPedido.class);
      root = criteria.from(ProdutoPedido.class);
      TypedQuery<ProdutoPedido> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<ProdutoPedido> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      Pedido pedido = this.example.getPedido();
      if (pedido != null)
      {
         predicatesList.add(builder.equal(root.get("pedido"), pedido));
      }
      Produto produto = this.example.getProduto();
      if (produto != null)
      {
         predicatesList.add(builder.equal(root.get("produto"), produto));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<ProdutoPedido> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back ProdutoPedido entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<ProdutoPedido> getAll()
   {

      CriteriaQuery<ProdutoPedido> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(ProdutoPedido.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(ProdutoPedido.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final ProdutoPedidoBean ejbProxy = this.sessionContext.getBusinessObject(ProdutoPedidoBean.class);

      return new Converter()
      {

         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((ProdutoPedido) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private ProdutoPedido add = new ProdutoPedido();

   public ProdutoPedido getAdd()
   {
      return this.add;
   }

   public ProdutoPedido getAdded()
   {
      ProdutoPedido added = this.add;
      this.add = new ProdutoPedido();
      return added;
   }
}