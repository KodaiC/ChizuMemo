package com.cogon_k;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

//@Path("/memos")
//@ApplicationScoped
public class MemoResource {
//    @GET
//    public Uni<List<Memo>> get() {
//        return Memo.listAll(Sort.by("id"));
//    }
//
//    @GET
//    @Path("/{id}")
//    public Uni<Memo> getOne(Long id) {
//        return Memo.findById(id);
//    }
//
//    @POST
//    public Uni<Response> create(Memo memo) {
//        return Panache.<Memo>withTransaction(memo::persist)
//                .map(inserted -> Response.created(URI.create("/memos/" + inserted.id)).build());
//    }


}
