package br.univel.br.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import br.univel.br.model.ProdutoPedido;

/**
 * 
 */
@Stateless
@Path("/produtopedidos")
public class ProdutoPedidoEndpoint
{
   @PersistenceContext(unitName = "EcommerciMake-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(ProdutoPedido entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(ProdutoPedidoEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      ProdutoPedido entity = em.find(ProdutoPedido.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<ProdutoPedido> findByIdQuery = em.createQuery("SELECT DISTINCT p FROM ProdutoPedido p LEFT JOIN FETCH p.pedido LEFT JOIN FETCH p.produto WHERE p.id = :entityId ORDER BY p.id", ProdutoPedido.class);
      findByIdQuery.setParameter("entityId", id);
      ProdutoPedido entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<ProdutoPedido> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<ProdutoPedido> findAllQuery = em.createQuery("SELECT DISTINCT p FROM ProdutoPedido p LEFT JOIN FETCH p.pedido LEFT JOIN FETCH p.produto ORDER BY p.id", ProdutoPedido.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<ProdutoPedido> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(@PathParam("id") Long id, ProdutoPedido entity)
   {
      if (entity == null)
      {
         return Response.status(Status.BAD_REQUEST).build();
      }
      if (!id.equals(entity.getId()))
      {
         return Response.status(Status.CONFLICT).entity(entity).build();
      }
      if (em.find(ProdutoPedido.class, id) == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      try
      {
         entity = em.merge(entity);
      }
      catch (OptimisticLockException e)
      {
         return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
      }

      return Response.noContent().build();
   }
}
