package com.cogon_k;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.reactive.stage.Stage;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chizu extends PanacheEntityBase {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(insertable = false, nullable = false, updatable = false)
    public String id;
    public String imageURL;
    public boolean isShared;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chizu", orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Memo> memos = new ArrayList<>();
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public ZonedDateTime createdTime;

    public Chizu() {}

    public Chizu(String imageURL, boolean isShared) {
        this.imageURL = imageURL;
        this.isShared = isShared;
        this.createdTime = ZonedDateTime.now();
    }
}
