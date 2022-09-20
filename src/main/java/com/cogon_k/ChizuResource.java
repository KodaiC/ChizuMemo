package com.cogon_k;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.hibernate.Hibernate;
import org.hibernate.reactive.mutiny.Mutiny;
import org.hibernate.reactive.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Path("/chizus")
@ApplicationScoped
public class ChizuResource {
    @GET
    public Uni<List<Chizu>> get() {
        return Chizu.(Sort.by("createdTime"));
    }

    @GET
    @Path("/{id}")
    public Uni<Chizu> getOne(String id) {
        return Chizu.findById(id);
    }

    @POST
    @ReactiveTransactional
    public Uni<Response> create(Chizu chizu) {
        Chizu createChizu = new Chizu(chizu.imageURL, chizu.isShared);
        return Panache.<Chizu>withTransaction(createChizu::persist)
                .map(inserted -> Response.created(URI.create("/chizus/" + inserted.id)).build());
    }

    @PUT
    @Path("/{id}")
    @ReactiveTransactional
    public Uni<Response> update(String id, Chizu chizu) {
        return Panache.<Chizu>withTransaction(() -> {
                    return getOne(id).call(c -> {
                        c.imageURL = chizu.imageURL;
                        c.isShared = chizu.isShared;
                        for (Memo m : chizu.memos) {
                            m.chizu = c;
                        }
                        c.memos.clear();
                        c.memos.addAll(chizu.memos);
                        return c.persist();
                    });
                })
                .map(updated -> Response.ok().build());
    }

    @DELETE
    @Path("/{id}")
    @ReactiveTransactional
    public Uni<Response> delete(String id) {
        return Panache.<Chizu>withTransaction(() -> {
                    return getOne(id).call(Chizu::delete);
                })
                .map(inserted -> Response.ok().build());
    }
}
